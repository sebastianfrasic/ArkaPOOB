package aplicacion;

/**
 * Representa una peque�a plataforma apodada Nave Espacial Vaus, que impide
que una bola salga de la zona de juego, haci�ndola rebotar.
 * @author Juan Sebastian Fr�sica y Juan Sebasti�n G�mez
 *
 */
public class PlataformaPeque�a extends Plataforma{

	public PlataformaPeque�a(int x, int y, int color) {
		super(x, y,("PlataformaNormalPeque�a"+Integer.toString(color)));
	}
	
	@Override
	public void setColor(int color) {
		super.setImagen("recursos/PlataformaNormalPeque�a"+Integer.toString(color)+".png");
	}
}