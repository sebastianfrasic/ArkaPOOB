package aplicacion;

import java.awt.Graphics2D;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * Representa una bola que recorre el tablero destruyendo bloques y rebotando con paredes y plataformas.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public abstract class Bola extends Elemento{

	public static final int limXFinal = 865;
	public static final int limXInicial = 14;
	public static final int limYFinal = 554;
	public static final int limYInicial = 64;
	private int jugador;
	private int poder;
	private int angulo;
	protected double velocidad;
	private double vx;
	private double vy;
	private double xReal;
	private double yReal;
	private boolean pegada;

	
	public Bola(int x,int y){
		super(x,y);
		velocidad = 1.5;
		Random ran = new Random();
		angulo = ran.nextInt(90)+45;
		setDeltas();
		xReal = x;
		yReal = y;
		setImagen("recursos/Bola.png");
		pegada = false;
	}
	
	/**
	 * Define el cambio en x y y partiendo del angulo y la velocidad de rebote
	 */
	private void setDeltas() {
		vy = velocidad*Math.sin(Math.toRadians(angulo));
		vx = velocidad*Math.cos(Math.toRadians(angulo));
	}

	/**
	 * Define el cambio en x y y partiendo del angulo y la velocidad de rebote despues de aplicar  un poder 
	 */
	private	void setDeltasPoder() {
		double vxn = velocidad*Math.cos(Math.toRadians(angulo));
		double vyn = velocidad*Math.sin(Math.toRadians(angulo));
		if ((vx < 0 && vxn > 0)||(vx > 0 && vxn < 0)) {
			vxn *= -1;
		}
		if ((vx < 0 && vyn > 0)||(vy > 0 && vyn < 0)) {
			vyn *= -1;
		}
		vx = vxn;
		vy = vyn;
	}

	
	
	/**
	 * Mueve la bola en el tablero
	 * @param juego Juego actual
	 */
	public void mover(ArkaPOOB juego) {
		boolean seCayo = false;
		if(!pegada) {
            dx = (int)Math.round(vx);
            dy = (int)Math.round(vy);
            y = (int)Math.round(yReal);
            x = (int)Math.round(xReal);
			if (xReal + vx >= limXFinal || xReal + vx <= limXInicial) {
				vx *= -1;
				dx *= -1;
			}
			if (yReal + vy <= limYInicial){
				vy *= -1;
				dy *= -1;
			}
			if (yReal > limYFinal) {
				seCayo = true;
				juego.atenderBolaCaida(jugador);
			}
			if(!seCayo) {
				juego.noCreoBola();
				revisarBloques(juego);
				revisarJugadores(juego);
				xReal += vx;
	            yReal += vy;
			}
		}
	}
	
	/**
	 * Revisa si rebota con alguno de los bloques en tablero
	 * @param bloques que estan en juego
	 */
	public void revisarBloques(ArkaPOOB juego) {
		for(int i  = 0; i<juego.numeroDeBloques();i++) {
			Bloque bl = juego.getBloque(i);
			char cambio = (bl.isVisible())? bl.tocar(this, juego):'n';
			if (cambio == 'x') {
				vx *= -1;
				dx *= -1;
			}
			if (cambio == 'y') {
				vy *= -1;
				dy *= -1;
			}
			if (cambio != 'n') {
				sonidoBloque();
			}
		}
	}
	
	/**
	 * Revisa si rebota con alguno de los jugadores en juego
	 * @param jugadores que estan en juego
	 */
	private void revisarJugadores(ArkaPOOB juego) {
		for(int i = 0;i<juego.numeroDeJugadores();i++){
			int cambio = juego.getJugador(i).nuevoAngulo(xReal,yReal,vx,vy);
			if (cambio > 0){
				disminuirPoder();
				cambiarDireccion(cambio);
				sonidoPlataforma();
				jugador = i;
			}
		}
	}

	/**
	 * Cambia el angulo de rebote de la bola
	 * @param cambio nuevo angulo
	 */
	public void cambiarDireccion(int cambio) {
		angulo = 180 + cambio;
		setDeltas();
	}
	
	/**
	 * Cuando rebota en una plataforma se va quitando el poder. Un poder alcanza 3 rebotes en plataformas 
	 */
	private void disminuirPoder() {
		poder--;
		if (poder == 0) {
			this.velocidadNormal();
		}
	}
	
	/**
	 * Cambia el estado de la pelota a "pegada"
	 */
	public void pegar() {
		pegada = true;
	}
	
	/**
	 * Quita el estado de "pegada" a la pelota
	 */
	public void despegar() {
		pegada = false;
	}
	
	/**
	 * Dice si la pelota esta pegada a alguna plataforma
	 * @return si la pelota esta pegada
	 */
	public boolean pegada() {
		return pegada;
	}
	
	/**
	 * Cambia la posicion en x actual a una nueva xn
	 * @param xn nueva posicion en x
	 */
	public void setPos(int xn) {
		xReal = xn;
		x = xn;
	}
	
	/**
	 * Asigna el último jugador que toco la bola
	 * @param player ultimo jugador que toco la bola
	 */
	public void setJugador(int player) {
		jugador = player;
	}
	
	/**
	 * Aumenta la velocidad de la bola
	 */
	public void aumentarVelocidad() {
		poder = 3;
		velocidad = 2;
		setDeltasPoder();		
	}
	
	/**
	 * Disminuye la velocidad de la bola
	 */
	public void disminuirVelocidad() {
		poder = 3;
		velocidad = 1;
		setDeltasPoder();
	}
	
	/**
	 * Pone la velocidad de la bola normal que es 0.8 as default 
	 */
	public void velocidadNormal() {
		velocidad = 1.5;
		setDeltasPoder();
	}
	
	/**
	 * Da la varacion en x que tiene la bola 
	 * @return la variacion en x de la bola  
	 */
	public double getVX() {
		return vx;
	}

	/**
	 * Da la varacion en y que tiene la bola 
	 * @return la variacion en y de la bola  
	 */
	public double getVY() {
		return vy;
	}
	
	/**
	 * Da la posicion x (en double) de la bola
	 * @return la posicion en x de la bola 
	 */
	public double getXReal() {
		return xReal;
	}
	
	/**
	 * Da la posicion y (en double) de la bola
	 * @return la posicion en y de la bola 
	 */
	public double getYReal() {
		return yReal;
	}
	
	/**
	 * Da el ultimo jugador que toco la bola
	 * @return el indice del ultimo jugador que toco la bola 
	 */
	public int getJugador() {
		return jugador;
	}
	
	/**
	 * Reproduce un sonido cuando rebota en un bloque.
	 */
	private void sonidoBloque() {
		try {
			FileInputStream is = new FileInputStream("recursos/sonidos/bloque.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			Clip bloque = (Clip) AudioSystem.getLine(info);
			bloque.open(ais);
			bloque.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Repoduce un sonido cuando rebota en una pltaforma
	 */
	private void sonidoPlataforma() {
		try {
			FileInputStream is = new FileInputStream("recursos/sonidos/plataforma.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			Clip plataforma = (Clip) AudioSystem.getLine(info);
			plataforma.open(ais);
			plataforma.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}