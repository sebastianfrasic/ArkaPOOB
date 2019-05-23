package aplicacion;


/**
 * Aparecen raramente. Si alguno es golpeado, hace pasar directamente al siguiente nivel. Son destruidos con
un solo golpe de la bola. Dan 500 puntos.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class BloqueRosado extends Bloque {

	public BloqueRosado(int x, int y) {
		super(x, y);
		setImagen("recursos/BloqueRosado.png");
	}

	@Override
	public int getPuntaje() {
		return 500;
	}
	
	@Override
	public void destruyase(ArkaPOOB juego,Bola bola) {
		setVisible(false);
		juego.acabarNivel();
		Bloque bloque  = Bloque.crearBloque("BloqueNegro");
		juego.setUltimoDestruido(bloque);
	}
}
