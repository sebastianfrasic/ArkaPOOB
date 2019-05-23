package aplicacion;


/**
 * Convierte la plataforma en una plataforma especial
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class SorpresaPlataformaEspecial extends Sorpresa {


	public SorpresaPlataformaEspecial(int x, int y) {
		super(x, y);
		setImagen("recursos/SorpresaPlataformaEspecial.png");
	}
	
	@Override
	public void aplicarPoder(Jugador jugador, Bola bola) {
		Plataforma pl = new PlataformaEspecial(0,0,0);
		jugador.cambiarPlataforma(pl, bola);		
	}
}
