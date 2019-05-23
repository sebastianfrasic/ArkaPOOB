package aplicacion;

/**
 * Se encarga de aumentar la velocidad de la bola
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class AumentarVelocidadBola extends Sorpresa {
	
	public AumentarVelocidadBola(int x, int y) {
		super(x, y);
		setImagen("recursos/SorpresaBola+.png");
	}

	@Override
	public void aplicarPoder(Jugador jugador, Bola bola) {
		bola.aumentarVelocidad();
	}


}
