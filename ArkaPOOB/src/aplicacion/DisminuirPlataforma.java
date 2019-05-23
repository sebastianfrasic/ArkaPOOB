package aplicacion;


/**
 * Se encarga de disminuir el tama�o de la plataforma
 * @author Juan Sebastian Fr�sica y Juan Sebasti�n G�mez
 *
 */
public class DisminuirPlataforma extends Sorpresa {

	
	public DisminuirPlataforma(int x, int y) {
		super(x, y);
		setImagen("recursos/SorpresaPlataforma-.png");
	}
	
	
	@Override
	public void aplicarPoder(Jugador jugador, Bola bola) {
		Plataforma pl = new PlataformaPeque�a(0,0,0);
		jugador.cambiarPlataforma(pl, bola);		
	}
}
