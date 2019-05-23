package aplicacion;


/**
 * Corresponden a los bloques normales. Son destruidos con un solo golpe de la bola. Es el tipo de bloque m�s
com�n. Dan 100 puntos.
 * @author Juan Sebastian Fr�sica y Juan Sebasti�n G�mez
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
