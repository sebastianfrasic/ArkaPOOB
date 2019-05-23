package aplicacion;

import java.util.ArrayList;


/**
 * Representa un modo de usuario CPU, su prioridad es recoger sorpresas
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class Curioso extends Maquina {

	public Curioso(int x, int y) {
		super(x, y);
	}

	@Override
	public int tipoMaquina() {
		return 0;
	}

	@Override
	public void mover(Bola bola, ArrayList sorpresas, Jugador otroJugador) {
		if(buscarDireccion(bola, sorpresas) == 1 && !esEspecial() || buscarDireccion(bola, sorpresas) == 0 && esEspecial()) {
			moveLeft(otroJugador);
		}
		else {
			moveRight(otroJugador);	
		}
	}
	
	/**
	 * Dice hacia donde moverse dependiendo de la bola o de las sorpresas 
	 * @param bola Bola actual del juego 
	 * @param sorpresas Lista de las sorpresas en el juego. 
	 * @return 1 si va a al izquierda, o 0 si va a la derecha
	 */
	private int buscarDireccion(Bola bola,ArrayList<Sorpresa> sorpresas) {
		int direccion = 0, x, y;
		boolean sorpresaActiva = false;
		int j = 0; 
		if (sorpresas.size() > 0) {
			while (!sorpresaActiva && j < sorpresas.size()) { 
				sorpresaActiva = sorpresas.get(j).isVisible();
				j++;
			}
		}
		if(sorpresaActiva) {
			j--;
			x = sorpresas.get(j).getX();
			y = sorpresas.get(j).getY();
			for(Sorpresa s: sorpresas) {
				if(s.isVisible() && s.getY() > y) {
					x = s.getX();
				}
			}
			if(x < getX() + (getAncho()/2)) {
				direccion = 1;
			}
		}else {
			if(bola.getX() < getX() + (getAncho()/2)) {
				direccion = 1;
			}
		}
		return direccion;
	}
}