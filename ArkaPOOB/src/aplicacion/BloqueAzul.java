package aplicacion;
import java.util.ArrayList;
import java.util.Random;

/**
 * Estos bloques lanzan de manera aleatoria los “sorpresas”. También son poco comunes. Dan 300 puntos.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class BloqueAzul extends Bloque {
	
	private Sorpresa sorpresa;
	private static String[] seleccion = {"DisminuirPlataforma","AumentarPlataforma","SorpresaPlataformaEspecial","SorpresaPlataformaPegajosa","AumentarVelocidadBola","DisminuirVelocidadBola"};
	
	public BloqueAzul(int x, int y) {
		super(x, y);
		sorpresaRandom();
		setImagen("recursos/BloqueAzul.png");
	}
	/**
	 * Escoge una sorpresa sandom basada en la seleccion
	 * @return una sorpresa random
	 */
	private void sorpresaRandom() {
		Sorpresa s = null;
		Random ran = new Random();
		String n = seleccion[ran.nextInt(seleccion.length)];
		sorpresa = Sorpresa.crearSorpresa(n);
		sorpresa.setX(getX() + 23);
		sorpresa.setY(getY() + 17);
	}

	@Override
	public int getPuntaje() {
		int puntaje = 0;
		if(!isVisible()) {
			puntaje = 300;
		}
		return puntaje;
	}
	
	@Override
	public void setX(int xn) {
		super.setX(xn);
		sorpresa.setX(getX() + 23);
	}
	
	@Override
	public void setY(int yn) {
		super.setY(yn);
		sorpresa.setY(getY() + 17);
	}
	
	@Override
	public void destruyase(ArkaPOOB juego,Bola bola) {
		setVisible(false);
		juego.agregarSorpresa(sorpresa);
		juego.setUltimoDestruido(this);
	}
	
	@Override
	public void reiniciar() {
		sorpresaRandom();
	}
	
	public static void setSeleccion(int[] nuevaSeleccion) {
		seleccion = crearSeleccion(nuevaSeleccion);
	}
	
	/**
	 * Crea la seleccion de Sorpresas disponibles para lanzar
	 * @param seleccion
	 * @return
	 */
	private static String[] crearSeleccion(int[] seleccion) {
		String[] nuevaSeleccion = new String[seleccion.length];
		for(int i = 0; i < seleccion.length; i++) {
			if (seleccion[i] == 1) {
				nuevaSeleccion[i] = "DisminuirPlataforma";
			}else if (seleccion[i] == 2) {
				nuevaSeleccion[i] = "AumentarPlataforma";
			}else if (seleccion[i] == 3) {
				nuevaSeleccion[i] = "SorpresaPlataformaEspecial";
			}else if (seleccion[i] == 4) {
				nuevaSeleccion[i] = "SorpresaPlataformaPegajosa";
			}else if (seleccion[i] == 5) {
				nuevaSeleccion[i] = "AumentarVelocidadBola";
			}else if (seleccion[i] == 6) {
				nuevaSeleccion[i] = "DisminuirVelocidadBola";
			}
		}
		return nuevaSeleccion;
	} 
}