package aplicacion;

import java.util.ArrayList;

/**
 * Representa a un jugador humano en el juego
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class Humano extends Jugador {

	public Humano(int x, int y) {
		super(x, y, true);
	}

	@Override
	public int tipoMaquina() {
		return -1;
	}

	@Override
	public void mover(Bola bola, ArrayList sorpresas, Jugador otroJugador) {
		
	}
}