package aplicacion;

/**
 * Representa una pequeña plataforma apodada Nave Espacial Vaus, que impide
que una bola salga de la zona de juego, haciéndola rebotar.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class PlataformaPequeña extends Plataforma{

	public PlataformaPequeña(int x, int y, int color) {
		super(x, y,("PlataformaNormalPequeña"+Integer.toString(color)));
	}
	
	@Override
	public void setColor(int color) {
		super.setImagen("recursos/PlataformaNormalPequeña"+Integer.toString(color)+".png");
	}
}