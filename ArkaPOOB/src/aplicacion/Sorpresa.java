package aplicacion;

import java.io.Serializable;
import java.util.Random;

/**
 * Representa una sorpresa que da poderes o desventajas al jugador.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public abstract class Sorpresa extends Elemento implements Serializable {
	
	public static final int limYFinal = 533;
	private final int avanza = 2;
	private int avanzaActual;
	
	
	public Sorpresa(int x, int y) {
		super(x,y);
		setDX(0);
		avanzaActual = 0;
	}
	
	/**
	 * Aplica el poder de la sorpresa que cae en la plataforma
	 * @param jugador Jugador que tendrá la sorpresa
	 * @param bola Bola actual del juego
	 */
	public abstract void aplicarPoder(Jugador jugador, Bola bola); 
	

	
	/**
	 * Mueve la sorpresa mirando los jugadores que hay en juego 
	 * @param jugadores jugadores que hay en juego 
	 * @param bola bola que esta en juego
	 */
	public void mover(Jugador[] jugadores,Bola bola) {
		if(y + dx> 554) setVisible(false); //Si se salió
		if (isVisible()) {
			Jugador jug = null;
			for(Jugador j:jugadores){
				if(j.tocado(x, y, dx, dy)) { 
					this.aplicarPoder(j, bola);
					setVisible(false);
				}
			}
			if(avanzaActual == avanza) {
				y += dy;
				avanzaActual = 0;
			}
			else
				avanzaActual ++;
		}
	}
	
	/**
	 * Crea una sorpresa segun su tipo
	 * @param sorpresa
	 * @return
	 */
	public static Sorpresa crearSorpresa(String sorpresa) {
		Sorpresa s = null;
		if(sorpresa.equals("AumentarPlataforma")) {
			s = new AumentarPlataforma(0, 0);
		}else if(sorpresa.equals("DisminuirPlataforma")) {
			s = new DisminuirPlataforma(0, 0);
		}else if(sorpresa.equals("SorpresaPlataformaEspecial")) {
			s = new SorpresaPlataformaEspecial(0, 0);
		}else if(sorpresa.equals("SorpresaPlataformaPegajosa")) {
			s = new SorpresaPlataformaPegajosa(0, 0);
		}else if(sorpresa.equals("AumentarVelocidadBola")) {
			s = new AumentarVelocidadBola(0, 0);
		}else if(sorpresa.equals("DisminuirVelocidadBola")) {
			s = new DisminuirVelocidadBola(0, 0);
		}
		return s;
	}
}