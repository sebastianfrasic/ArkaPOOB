package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import aplicacion.Bola;


/**
 * Representa la pantalla del juego cuando este empieza, es donde ocurre todo el juego desde que inicia, hasta su fin.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class Tablero extends JPanel{

	private BufferedImage fondo, fondoFin;
	private Sprite bola;
	private ArrayList<Sprite> bloques;
	private ArrayList<Sprite> jugadores;
	private ArrayList<Sprite> vidas;
	private ArrayList<Sprite> sorpresas;
	private String[] puntajes;
	private String nivel;
	private boolean terminar, nNivel, gameOver, enPausa;
	protected JButton guardar,abrir,reiniciar;
	
	public Tablero(int nJugadores){
		super(null);
		puntajes = new String[nJugadores];
		bloques = new ArrayList<Sprite>();
		jugadores = new ArrayList<Sprite>();
		vidas = new ArrayList<Sprite>();
		sorpresas = new ArrayList<Sprite>();
		terminar = false;
		try {
			fondo = ImageIO.read(new File("recursos/Fondo "+(2+nJugadores)+".png"));
			fondoFin = ImageIO.read(new File("recursos/Fondo Fin.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	public void addBola() {
		bola = new Sprite(0,0,false,20,20);
	}
	
	public void addBloque() {
		bloques.add(new Sprite(0,0,false));
	}
	
	public void addJugador(){
		jugadores.add(new Sprite(0, 550,true));
		addVidas();
	}
	
	public void addSorpresa(){
		sorpresas.add(new Sprite(0, 550,true,20,20));
	}
	
	private void addVidas(){
		for (int i = 0; i < 3 ; i++) {
			if (jugadores.size() == 1){
				vidas.add(new Sprite(20 + (i*42), 25,true,40,10));
			}else{
				vidas.add(new Sprite(750 + (i*42), 25,true,40,10));
			}
		}
	}
	
	public void setPuntaje(int jugador,int puntaje){
		puntajes[jugador] = Integer.toString(puntaje);
	}
	
	public Sprite getJugador(int i){
		return jugadores.get(i);			
	}
	
	public Sprite getSorpresa(int i){
		return sorpresas.get(i);			
	}
	
	public Sprite getBloque(int i){
		return bloques.get(i);			
	}
	
	public Sprite getBola(){
		return bola;			
	}
	
	public ArrayList<Sprite> getVidas(){
		return vidas;			
	}
	
	public void End(boolean causa){
		terminar = true;
		gameOver = causa;
		reiniciar = new  Boton("reiniciar", 300, 415); add(reiniciar);
		repaint();
	}
	
	public void inicioNivel(String niv){
		nNivel = true;
		nivel = niv;
	}
	
	public void quitarNivel(){
		nNivel = false;
	}
	
	public void pausa(){
		enPausa = !enPausa;
	}
	
	public void setPausa(boolean pausa) {
		enPausa = pausa;
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		if(terminar){
			g.drawImage(fondoFin, 0, 0, this);
			g.setColor(Color.white);
			g.setFont(new Font("Century Gothic", Font.BOLD, 60));
			if(gameOver)
				g.drawString("¡Game Over!", 260, 120);
			else
				g.drawString("¡Acabaste el nivel final!", 130, 120);
			g.setFont(new Font("Century Gothic", Font.BOLD, 100));
			if (puntajes.length == 1) {
				g.drawString(puntajes[0], 312, 300);
			}else {
				g.setFont(new Font("Century Gothic", Font.BOLD, 70));
				g.drawString("Jugador 1", 90, 250);
				g.drawString("Jugador 2", 510, 250);
				g.setFont(new Font("Century Gothic", Font.BOLD, 80));
				g.drawString(puntajes[0], 150, 350);
				g.drawString(puntajes[1], 580, 350);
				
			}
		}else if(enPausa) {
			g.drawImage(fondo, 0, 0, this);
			g.setColor(Color.white);
			g.setFont(new Font("Century Gothic", Font.BOLD, 70));
			g.drawString("Pausa", 350, 320);
		}else{
			g.drawImage(fondo, 0, 0, this);
			bola.paint((Graphics2D) g);
			for (Sprite j: jugadores) j.paint((Graphics2D) g);
			for (Sprite s : bloques) s.paint((Graphics2D) g);
			for (Sprite v : vidas) v.paint((Graphics2D) g);
			for (Sprite so : sorpresas) so.paint((Graphics2D) g);
			g.setColor(Color.white);
			g.setFont(new Font("Century Gothic", Font.BOLD, 30));
			if(nNivel) {
				g.drawString("Nivel "+ nivel, 425, 400);
				g.drawString("¡Preparate!", 390, 430);
			}
			if (puntajes.length == 1) {
				g.drawString("Nivel "+ nivel, 644, 35);
				g.drawString(puntajes[0], 425, 49);
			}else {
				g.drawString("Nivel "+ nivel, 425, 35);
				g.drawString(puntajes[0], 198, 49);
				g.drawString(puntajes[1], 644, 49);
			}
			paintComponents(g);
		}
	}	
}
