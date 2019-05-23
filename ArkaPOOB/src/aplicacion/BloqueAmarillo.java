package aplicacion;

/**
 * Al igual que los bloques rosados, son muy inusuales. Y otorgan una vida extra. Son destruidos con un solo
golpe de la bola. Dan 300 puntos.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class BloqueAmarillo extends Bloque {

	public BloqueAmarillo(int x, int y) {
		super(x, y);
		setImagen("recursos/BloqueAmarillo.png");
	}
	
	@Override
	public int getPuntaje() {
		int puntaje = 0;
		if(!isVisible())puntaje = 300;
		return puntaje;
	}
	
	@Override
	public void destruyase(ArkaPOOB juego,Bola bola) {
		setVisible(false);
		juego.añadirVida(bola.getJugador());
	}
}
