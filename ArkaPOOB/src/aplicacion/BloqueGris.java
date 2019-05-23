package aplicacion;

/**
 * Estos bloques son indestructibles, la bola nunca los destruye. Cuando una bola se estrella con ellos, rebota
como lo haría con una plataforma. Al igual que los bloques rojos, aparecen comúnmente. No dan puntos.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class BloqueGris extends Bloque {

	public BloqueGris(int x, int y) {
		super(x, y);
		setImagen("recursos/BloqueGris.png");
	}

	@Override
	public void destruyase(ArkaPOOB juego,Bola bola) {
	}
	
	@Override
	public boolean enJuego(){
		return false;
	}

	@Override
	public int getPuntaje() {
		return 0;
	}
}
