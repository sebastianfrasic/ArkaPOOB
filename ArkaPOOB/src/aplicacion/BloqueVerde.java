package aplicacion;


/**
 * Estos bloques tienen que ser golpeados 2 veces por la bola para ser destruidos. Dan 200 puntos.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class BloqueVerde extends Bloque {

	private boolean roto;
	
	public BloqueVerde(int x, int y) {
		super(x, y);
		setImagen("recursos/BloqueVerde.png");
	}
	
	@Override
	public void destruyase(ArkaPOOB juego, Bola bola) {
		if (roto) {
			setVisible(false);
		}else {
			setImagen("recursos/BloqueVerdeRoto.png");
			roto = true;
		}
	}
	
	@Override
	public int getPuntaje() {
		int puntaje = 0;
		if(!isVisible()) {
			puntaje = 200;
		}
		return puntaje;
	}
	
	@Override
	public void reiniciar() {
		setImagen("recursos/BloqueVerde.png");
		roto = false;
	}
	
}
