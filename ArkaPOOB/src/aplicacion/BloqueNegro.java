package aplicacion;


/**
 * Estos bloques al ser golpeados se transforman en el último bloque eliminado manteniendo su posición. Son
poco comunes. Otorgan 600 puntos.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class BloqueNegro extends Bloque {

	public BloqueNegro(int x, int y) {
		super(x, y);
		setImagen("recursos/BloqueNegro.png");
	}

	@Override
	public int getPuntaje() {
		int puntaje = 0;
		if(!isVisible()) {
			puntaje = 600;
		}
		return puntaje;
	}
	
	@Override
	public void destruyase(ArkaPOOB juego, Bola bola) {
		setVisible(false);
		cambiar(juego.getUltimoDestruido());
		juego.setUltimoDestruido(this);
	}
	
	private Bloque cambiar(Bloque nuevoBloque) {
		if(nuevoBloque == null) {
			nuevoBloque = new BloqueNegro(0,0);
		}
		nuevoBloque.reiniciar();
		nuevoBloque.setX(getX());
		nuevoBloque.setY(getY());
		nuevoBloque.setVisible(true);
		return nuevoBloque;
	}
}
