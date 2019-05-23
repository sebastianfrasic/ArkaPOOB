package aplicacion;

/**
 * Representa una plataforma grande apodada Nave Espacial Vaus, que impide
que una bola salga de la zona de juego, haciéndola rebotar.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class PlataformaGrande extends Plataforma{

	public PlataformaGrande(int x, int y,int color) {
		super(x, y,("PlataformaNormalGrande"+Integer.toString(color)));
	}
	
	@Override
	public void setColor(int color) {
		super.setImagen("recursos/PlataformaNormalGrande"+Integer.toString(color)+".png");
	}
}
