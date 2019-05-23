package aplicacion;

import java.util.ArrayList;


/**
 * Representa un modo de usuario CPU, imita el movimiento de su pareja
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class Mimo extends Maquina {

	public Mimo(int x, int y) {
		super(x, y);
	}

	@Override
	public int tipoMaquina() {
		return 2;
	}

	@Override
	public void mover(Bola bola, ArrayList sorpresas, Jugador otroJugador) {
		
	}
}
