package aplicacion;


/**
 * Corresponden a los bloques normales. Son destruidos con un solo golpe de la bola. Es el tipo de bloque más
común. Dan 100 puntos.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class BloqueRojo extends Bloque {

	public BloqueRojo(int x, int y) {
		super(x, y);
		setImagen("recursos/BloqueRojo.png");
	}

	@Override
	public int getPuntaje() {
		return 100;
	}
	

}
