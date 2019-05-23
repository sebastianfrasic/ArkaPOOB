package aplicacion;

import java.util.ArrayList;


/**
 * Representa un modo de usuario CPU, su prioridad es destruir bloques
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class Destructor extends Maquina {

	public Destructor(int x, int y) {
		super(x, y);
	}

	
	@Override
	public int tipoMaquina() {
		return 1;
	}

	@Override
	public void mover(Bola bola, ArrayList sorpresas, Jugador otroJugador) {
		if(buscarDireccion(bola) == 1) {
			moveLeft(otroJugador);
		}else {
			moveRight(otroJugador);	
		}
	}
	
	/**
	 * Dice hacia donde moverse dependiendo de la bola  
	 * @param bola bola actual del juego 
	 * @param sorpresas lista de las sorpresas en el juego. 
	 * @return 1 si va a al izquierda o 0 si va a la derecha
	 */
	private int buscarDireccion(Bola bola) {
		int direccion = 0;
		if(bola.getX() < getX() + (getAncho()/2)) {
			direccion = 1;
		}
		return direccion;
	}
	
}
