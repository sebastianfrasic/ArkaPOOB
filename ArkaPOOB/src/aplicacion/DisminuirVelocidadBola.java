package aplicacion;

public class DisminuirVelocidadBola extends Sorpresa {

	
	public DisminuirVelocidadBola(int x, int y) {
		super(x, y);
		setImagen("recursos/SorpresaBola-.png");
	}
	
	@Override
	public void aplicarPoder(Jugador jugador, Bola bola) {
		bola.disminuirVelocidad();
	}
}
