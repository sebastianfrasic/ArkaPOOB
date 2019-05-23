package aplicacion;


/**
 * Se encarga de disminuir el tamaño de la plataforma
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class DisminuirPlataforma extends Sorpresa {

	
	public DisminuirPlataforma(int x, int y) {
		super(x, y);
		setImagen("recursos/SorpresaPlataforma-.png");
	}
	
	
	@Override
	public void aplicarPoder(Jugador jugador, Bola bola) {
		Plataforma pl = new PlataformaPequeña(0,0,0);
		jugador.cambiarPlataforma(pl, bola);		
	}
}
