package aplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Jugador implements Serializable{
	
	protected Plataforma plataforma;
	protected static final int VELOCIDAD = 30;
	public static final int limXFinal = 885;
	public static final int limXInicial = 15;
	private int vidas;
	private int puntaje;
	private int color = 0;
	private int poder;
	protected boolean esHumano, especial, pegajoso, cambiado;

	public Jugador(int x, int y, boolean esHumano) {
		vidas = 3;
		puntaje = 0;
		cambiado = false;
		this.esHumano = esHumano;	
		plataforma = new PlataformaNormal(x,y,color);
	}
	
	/**
	 * Da el tipo de maquina que es segun corresponda: 0 si es curioso, 1 si es destructor o 2 si es mimo. 
	 * @return
	 */
	public abstract int tipoMaquina();
	
	
	
	/**
	 * Determina el movimiento del jugador dependiendo de la bola, sorpresas y otro jugador segun corresponda
	 * @param bola bola actual del juego
	 * @param sorpresas sorpresas en juego 
	 * @param otroJugador en juego
	 */
	public abstract void mover(Bola bola, ArrayList sorpresas, Jugador otroJugador);
	
	
	
	/**
	 * Asigna una nueva posicion en x para el jugador
	 * @param xn nueva posicion en x
	 */
	public void setX(int xn) {
		plataforma.setX(xn);
	} 
	
	
	/**
	 * Realiza el movimiento a la derecha cuando hay dos jugadores
	 * @param otroJugador otro jugador en juego
	 */
	public void moveRight(Jugador otroJugador){
		plataforma.moveRight(otroJugador);
	}
	
	/**
	 * Realiza el movimiento a la derecha cuando hay un jugador
	 */
	public void moveRight(){
		plataforma.moveRight();
	}
	
	/**
	 * Realiza el movimiento a la izquierda cuando hay dos jugadores
	 * @param otroJugador otro jugador en juego
	 */
	public void moveLeft(Jugador otroJugador){
		plataforma.moveLeft(otroJugador);
	}	
	
	/**
	 * Realiza el movimiento a la izquierda cuando hay un jugador
	 */
	public void moveLeft(){
		plataforma.moveLeft();
	}
		
	/**
	 * Da la posicion en x del jugador
	 * @return la posicion en x del jugador 
	 */
	public int getX(){
		return plataforma.getX();
	}
	
	/**
	 * Da la posicion en y del jugador
	 * @return la posicion en y del jugador 
	 */
	public int getY(){
		return plataforma.getY();
	}
	
	/**
	 * Da el puntaje del juagdor
	 * @return el puntaje del jugador  
	 */
	public int getPuntaje() {
		return puntaje;
	}
	
	/**
	 * Da las  vidas del jugador
	 * @return las vidas del jugador
	 */
	public int getVidas() {
		return vidas;
	}
	
	/**
	 * Dice si el jugador es humano
	 * @return si el jugador es humano
	 */
	public boolean esHumano() {
		return esHumano;
	}
	
	/**
	 * Da la visibilidad del jugador
	 * @return la visibilidad del jugador
	 */
	public boolean isVisible(){
		return plataforma.isVisible();
	} 
	
	/**
	 * Añade puntaje al jugador
	 * @param puntajeN puntaje a agregar al jugador
	 */
	public void addPuntaje(int puntajeN) {
		puntaje += puntajeN;
	}
	
	/**
	 * Le da el indice para el color del jugador: 0 si es rojo, 1 narajana, 2 morado, 3 rosado, 4 verde
	 * @param i indice del color 
	 */
	public void setColor(int i){
		color = i;
		plataforma.setColor(color);
	}
	
	/**
	 * Dice si el jugador va ser tocado por la bola
	 * @param xBola posicion x de la bola 
	 * @param yBola posicion y de la bola 
	 * @param dx cambio en x de la bola 
	 * @param dy cambio en y de la bola 
	 * @return si el jugador va a ser tocado por la bola 
	 */
	public boolean tocado(double xBola, double yBola,double dx,double dy) {
		return((yBola < getY() - 20 && yBola + dy >= getY() - 20) && ((xBola + 20 >= getX()  && xBola + 20 <= getX() + plataforma.getAncho())||(xBola >= getX()  && xBola <= getX() + plataforma.getAncho())) && isVisible());
	}
	
	/**
	 * Indica el nuevo angulo que va a tener la bola cuando ésta toca la plataforma
	 * @return El nuevo angulo
	 */
	public int nuevoAngulo(double xBola, double yBola,double dx,double dy) {
		int respuesta = 0;
		if (tocado(xBola, yBola, dx, dy)) {
			disminuirPoder();
			pegarBola();
			respuesta = (((xBola+20)-getX())>0) ? (int)((xBola+20)-getX()): 1;
			respuesta = (respuesta * 179)/(19 + getAncho());
		}
		return respuesta;
	}
	
	/**
	 * Pega una bola a la plataforma 
	 */
	public void pegarBola() {
		plataforma.pegarBola();
	}
	
	/**
	 * Pega la bola al la plataforma cuando esta el el modo de un jugador
	 * @return La bola del inicio del juego
	 */
	public Bola asignarBola() {
		Bola nuevaBola = new BolaNormal(getX()+(33),getY()-20);
		poder = 1;
		plataforma = new PlataformaPegajosa(plataforma.getX(),plataforma.getY(),color);
		plataforma.darBola(nuevaBola);
		plataforma.pegarBola();
		nuevaBola.cambiarDireccion(89);
		return nuevaBola;
	}
	
	/**
	 * Cuando la bola rebota en el jugador se va quitando el poder. Un poder alcanza 3 rebotes. 
	 */
	private void disminuirPoder() {
		poder--;
		if (poder == 0) {
			quitarPoderes();
		}
	}
	
	/**
	 * Quita una vida la jugador
	 */
	public void quitarVida(){
		vidas--;
	}
	
	/**
	 * Añade una vida al juagdor 
	 */
	public void añadirVida(){
		if (vidas<3)
			vidas++;
	}
	
	/**
	 * Asigna la ruta de la imagen para la plataforma
	 * @param root ruta de la imagen
	 */
	public void setRoot(String root) {
		plataforma.setImagen(root);
	}
	
	/**
	 * Da la ruta de imagen que tiene la plataforma 
	 * @return la ruta de imagen de la plataforma 
	 */
	public String getRoot() {
		return plataforma.getImagen();
	}
	
	/**
	 * Da el ancho de la plataforma 
	 * @return el ancho de la plataforma
	 */
	public int getAncho() {
		return plataforma.getAncho();
	}
	
	/**
	 * Quita las vidas de un jugador .
	 */
	public void end() {
		vidas = 0;
	} 
	
	public void cambiarPlataforma(Plataforma plat,Bola bola){
		poder = 3;
		plat.setX(plataforma.getX());
		plat.setY(plataforma.getY());
		plat.setColor(color);
		plat.darBola(bola);
		plataforma.soltarBola();
		plataforma  = plat;
	}
	
	/**
	 * Dice si el jugador tiene la sorpresa de plataforma especial activa
	 * @return si el jugador tiene el poder especial activo
	 */
	public boolean esEspecial() {
		return especial;
	}
	
	/**
	 *Dice si el jugador tiene la sorpresa de plataforma pegajosa activa
	 * @return si el jugador tiene el poder pagajoso activo
	 */
	public boolean esPegajosa() {
		return pegajoso;
	}
	
	/**
	 * Suelta la bola cuando tiene le poder pegajoso o cuando inicia en el modo de un jugador 
	 */
	public void soltarBola() {
		plataforma.soltarBola();	
	}
	
	/**
	 * Quita todos los poderes que tenga el jugador
	 */
	public void quitarPoderes(){
		plataforma = new PlataformaNormal(plataforma.getX(),plataforma.getY(),color);
	}
	
	/**
	 *Le dice a la plataforma que mueva la bola que esta pegada 
	 */
	public void moverBolaPegada() {
		plataforma.moverBolaPegada();
	}	
	
	/**
	 * Da el color de la plataforma 
	 * @return el color de la plataforma
	 */
	public String getColor() {
		return Integer.toString(color);
	}
	
	public Plataforma getPlataforma() {
		return plataforma;
	}
}