package aplicacion;

/**
 * Representa una plataforma apodada Nave Espacial Vaus, que impide
que una bola salga de la zona de juego, haciéndola rebotar.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public abstract class Plataforma extends Elemento{
	
	public static final int limXFinal = 885;
	public static final int limXInicial = 15;
	protected static final int VELOCIDAD = 30;

	public Plataforma(int x, int y,String imagen) {
		super(x, y);
		super.setImagen("recursos/"+imagen+".png");
	}
	
	/**
	 * Da el ancho de la plataforma segun su imagen
	 * @return El ancho de la plataforma
	 */
	public int getAncho(){
		return 98;
	}
	
	/**
	 * Realiza el movimiento a la derecha cuando hay dos jugadores
	 * @param otroJugador otro jugador en juego
	 */
	public void moveRight(Jugador otroJugador){
		if(x + getAncho() + VELOCIDAD < limXFinal) { //No se salga por la derecha
			if (x + getAncho() + VELOCIDAD > otroJugador.getX() && x + getAncho() + VELOCIDAD < otroJugador.getX() + otroJugador.getAncho()) { //Si me estrello con la otra plataforma cuando me muevo hacia la izquierda
				if (((x - otroJugador.getAncho()) >= limXInicial) && ((otroJugador.getX() + getAncho()) <= limXFinal)){ //Al hacer swap no se salga ninguna plataforma por los límites (Swap seguro)
					x = otroJugador.getX();
					otroJugador.setX(x - otroJugador.getAncho());  //El swap
				}
			}else { //Si no me voy a chocar con ninguna otra plataforma
				x+=VELOCIDAD; //Solo me muevo
			}			
		}else { //Si se sale por la derecha
			x = limXFinal - getAncho(); //Para que la plataforma no se salga
		}
		otroJugador.moverBolaPegada();
	}
	
	/**
	 * Realiza el movimiento a la derecha cuando hay un jugador
	 */
	public void moveRight(){
		if(x + getAncho() + VELOCIDAD < limXFinal) { //Verfico que no se salga por la derecha
			x+=VELOCIDAD; //Me muevo
		}else {
			x = limXFinal - getAncho(); //Para que la plataforma no se salga
		}
	}
	
	
	/**
	 * Realiza el movimiento a la izquierda cuando hay dos jugadores
	 * @param otroJugador otro jugador en juego
	 */
	public void moveLeft(Jugador otroJugador){
		if(x - VELOCIDAD > limXInicial) { //No se salga por la izquierda
			if (x - VELOCIDAD < otroJugador.getX() + otroJugador.getAncho() && x - VELOCIDAD > otroJugador.getX()) {  //Si me estrello con la otra plataforma cuando me muevo hacia la izquierda
				if (((x - getAncho()) >= limXInicial) && ((x + otroJugador.getAncho()) <= limXFinal)){ //Al hacer swap no se salga ninguna plataforma por los límites (Swap seguro)
					otroJugador.setX(x);
					x = otroJugador.getX() - getAncho(); //El swap :D
				}
			}else { //Solo me muevo
				x-=VELOCIDAD; //Solo me muevo
			}
		}else {
			x = limXInicial; //Para que la plataforma no se salga
		}
		otroJugador.moverBolaPegada();
	}
	
	/**
	 * Realiza el movimiento a la izquierda cuando hay un jugador
	 */
	public void moveLeft(){
		if(x-VELOCIDAD > limXInicial) { //Verfico que no se salga por la izquierda
			x-=VELOCIDAD; //Me muevo
		}else {
			x = limXInicial; //Para que la plataforma no se salga
		}
	}
	
	/**
	 * Pega la bola a la plataforma cuando el jugador tiene el poder "pegajoso"
	 * @param dx diferencia entre el x de la bola y el x del jugador
	 */
	public boolean pegarBola() {
		return false;
	}
	
	/**
	 * Mueve la bola cuando esta pegada al juagdor
	 */
	public void moverBolaPegada() {
	}
	
	/**
	 * Asigna la bola para la plataforma 
	 * @param bola bola que usa la palatforma
	 */
	public void darBola(Bola bola) {
	}
	
	/**
	 * Suelta la bola cuando tiene le poder pegajoso o cuando inicia en el modo de un jugador 
	 */
	public void soltarBola() {
	}
	
	/**
	 * Asigna el color de la plataforma
	 * @param color color de la plataforma
	 */
	public void setColor(int color) {
	}
}