package aplicacion;

/**
 * Representa una plataforma grande apodada Nave Espacial Vaus, que impide
que una bola salga de la zona de juego, haci�ndola rebotar.
 * @author Juan Sebastian Fr�sica y Juan Sebasti�n G�mez
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
