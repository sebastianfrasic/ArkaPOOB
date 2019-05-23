package aplicacion;

import java.io.Serializable;

/**
 * Representa un bloque en el juego ArkaPOOB. Existen diversos tipos de bloques
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public abstract class Bloque extends Elemento implements Serializable{
	
	protected int puntaje;

	public Bloque(int x, int y) {
		super(x, y);
	}
	
	
	/**
	 * Da el puntaje del bloque
	 * @return puntaje del bloque
	 */
	public abstract int getPuntaje();
	

	/**
	 * Da el estado de visibilidad del bloque
	 * @return la visibilidad del bloque
	 */
	public boolean isVisible(){
		return super.isVisible();
	}
	
	/**
	 * Destruye al bloque respectivo
	 */
	public void destruyase(ArkaPOOB juego,Bola bola) {
		setVisible(false);
		juego.setUltimoDestruido(this);
	}
	
	/**
	 * Dice si un bloque es tocado 
	 * @param bola Bola en el juego
	 * @param juego Juego actual
	 * @return 'y' si sí, 'n' de lo contrario
	 */
	public char tocar(Bola bola, ArkaPOOB juego) {
		char respuesta = 'n';
		double xBola = bola.getXReal(), yBola = bola.getYReal(), dx = bola.getVX(), dy = bola.getVY();
		esquivar(xBola, yBola, dx, dy, juego); 
		if ((xBola < getX() - 20 && xBola + dx >= getX() - 20) && ((yBola + 20 >= getY()  && yBola + 20 <= getY() + 17)||(yBola >= getY()  && yBola <= getY() + 17)||(yBola <= getY() && yBola + 20 >= getY() + 17)) && isVisible()) {
			respuesta = 'x';
		}else if ((xBola > getX() + 66 && xBola + dx <= getX() + 66) && ((yBola + 20 >= getY()  && yBola + 20 <= getY() + 17)||(yBola >= getY()  && yBola <= getY() + 17)||(yBola <= getY() && yBola + 20 >= getY() + 17)) && isVisible()) {
			respuesta = 'x';
		}else if ((yBola < getY() - 20 && yBola + dy >= getY() - 20) && ((xBola + 20 >= getX()  && xBola + 20 <= getX() + 66)||(xBola >= getX()  && xBola <= getX() + 66)) && isVisible()) {
			respuesta = 'y';
		}else if ((yBola > getY() + 17 && yBola + dy <= getY() + 17) && ((xBola + 20 >= getX()  && xBola + 20 <= getX() + 66)||(xBola >= getX()  && xBola <= getX() + 66)) && isVisible()) {
			respuesta = 'y';
		}
		if(respuesta != 'n'){ //Si es tocado
			destruyase(juego,bola);
			juego.getJugador(bola.getJugador()).addPuntaje(getPuntaje());
			juego.actualizaBloques();
		}
		return respuesta;
	}
	
	/**
	 * Dice si el bloque esta en juego
	 * @return si el bloque esta en juego
	 */
	public boolean enJuego(){
		return isVisible();
	}
	
	/**
	 * Saca de juego al bloque
	 */
	public void fueraDeJuego() {
		this.setVisible(false);
	}
	
	/**
	 * Vuelve al estado inicial el bloque
	 */
	public void reiniciar() {
		
	}
	
	/**
	 * Se encarga de esquivar hacia arriba la bola 
	 * @param xBola
	 * @param yBola
	 * @param dx
	 * @param dy
	 * @param juego
	 */
	public void esquivar(double xBola, double yBola,double dx,double dy,ArkaPOOB juego) {
	}
	
	
	/**
	 * Crear un bloque segun su tipo
	 * @param bloque Tipo de bloque
	 * @return
	 */
	public static Bloque crearBloque(String bloque) {
		Bloque s = null;
		if(bloque.equals("BloqueRojo")) {
			s = new BloqueRojo(0, 0);
		}else if(bloque.equals("BloqueGris")) {
			s = new BloqueGris(0, 0);
		}else if(bloque.equals("BloqueAnaranjado")) {
			s = new BloqueAnaranjado(0, 0);
		}else if(bloque.equals("BloqueVerde")) {
			s = new BloqueVerde(0, 0);
		}else if(bloque.equals("BloqueNegro")) {
			s = new BloqueNegro(0, 0);
		}else if(bloque.equals("BloqueAzul")) {
			s = new BloqueAzul(0, 0);
		}else if(bloque.equals("BloqueRosado")) {
			s = new BloqueRosado(0, 0);
		}else if(bloque.equals("BloqueAmarillo")) {
			s = new BloqueAmarillo(0, 0);
		}
		return s;
	}
}
