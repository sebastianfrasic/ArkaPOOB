package aplicacion;

/**
 * Sigue las instrucciones del jugador y rebota siempre la bola.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class PlataformaNormal extends Plataforma {
	
	public PlataformaNormal(int x, int y,int color){
		super(x, y,("PlataformaNormalBasica"+Integer.toString(color)));
	}
	
	@Override
	public void setColor(int color) {
		super.setImagen("recursos/PlataformaNormalBasica"+Integer.toString(color)+".png");
	}
}
