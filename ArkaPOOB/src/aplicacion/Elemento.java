 package aplicacion;

import java.io.Serializable;


/**
 * Representa un elemento del juego, sabe su imagen y sus posiciones en X y en Y, y si está visible o invisible
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public abstract class Elemento implements Serializable{
	
	protected int x;
	protected int y;
	protected int dx;
	protected int dy;
	protected String imagen;
	protected boolean visible;
	
	public Elemento(int x, int y) {
		this.x = x;
		this.y = y;
		dx = 1;
		dy = 1;
		visible = true;
	}
	
	/**
	 * Devuelve la ruta de la imagen
	 * @return
	 */
	public String getImagen() {
		return imagen;
	}
	
	/**
	 * Da la posicion en x del elemento
	 * @return la posicon en x del elemento
	 */
	public int getX() {
		return x;
	}

	/**
	 * Da la posición en y del elemnto
	 * @return la posición en y del elemento
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Asigna una nueva posicion en x para el elemento
	 * @param xn la nueva posicion en x del elemento
	 */
	public void setX(int xn) {
		x = xn;
	}
	
	/**
	 * Asigna una nueva posicion en y para el elemento
	 * @param yn la nueva posicion en y del elemento
	 */
	public void setY(int yn) {
		y = yn;
	}

	/**
	 * Si el elemento es visible o no
	 * @return la visibilidad del elemteno
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Asigna una nueva ruta para la imegen del elemento
	 * @param r nueva ruta de la imagen
	 */
	public void setImagen(String r) {
		imagen = r;
	}
	
	/**
	 * Asigna una nueva visibilidad para le elemento
	 * @param v si es visible
	 */
	public void setVisible(boolean v){
		visible = v;
	}
	
	/**
	 * Asigna un nuevo cambio en x
	 * @param dxn nuevo cambio en x
	 */
	public void setDX(int dxn) {
		dx = dxn;
	}
	
	/**
	 * Asigna un nuevo cambio en y
	 * @param dyn nuevo cambio en y
	 */
	public void setDY(int dyn) {
		dy = dyn;
	}
}