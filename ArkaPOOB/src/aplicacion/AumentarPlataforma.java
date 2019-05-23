package aplicacion;

/**
 * Se encarga de aumentar el tamaño de la plataforma
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class AumentarPlataforma extends Sorpresa {
	
	public AumentarPlataforma(int x, int y) {
		super(x,y);
		setImagen("recursos/SorpresaPlataforma+.png");
	}
	
	@Override
	public void aplicarPoder(Jugador jugador, Bola bola) {
		Plataforma pl = new PlataformaGrande(0,0,0);
		jugador.cambiarPlataforma(pl, bola);		
	}
}