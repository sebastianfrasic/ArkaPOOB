package aplicacion;

/**
 * Su movimiento es inverso al dado por el usuario. Es decir, si el usuario indica un movimiento a la derecha, la
plataforma se moverá hacia la izquierda; y viceversa.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class PlataformaEspecial extends Plataforma{

	private boolean cambiado;
	
	public PlataformaEspecial(int x, int y,int color) {
		super(x, y,("PlataformaEspecial"+Integer.toString(color)));
	}
	
	@Override
	public void moveRight(Jugador otroJugador){
		if (!cambiado) { //En caso de que sea plataforma especial, para que no se quede siempre en movimiento a la izquierda
			cambiado = true;
			moveLeft(otroJugador);
		}else {
			cambiado = false;
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
	}
	
	@Override
	public void moveRight(){
		if (!cambiado) { //En caso de que sea plataforma especial, para que no se quede siempre en movimiento a la derecha
			cambiado = true;
			moveLeft();
		}else {
			cambiado = false;
			if(x + getAncho() + VELOCIDAD < limXFinal) { //Verfico que no se salga por la derecha
				x+=VELOCIDAD; //Me muevo
			}else {
				x = limXFinal - getAncho(); //Para que la plataforma no se salga
			}
		}
	}
	
	@Override
	public void moveLeft(Jugador otroJugador){
		if (!cambiado) { //En caso de que sea plataforma especial, para que no se quede siempre en movimiento a la derecha
			cambiado = true;
			moveRight(otroJugador);
		}else {
			cambiado = false;
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
	}
	
	
	@Override
	public void moveLeft(){
		if (!cambiado) { //En caso de que sea plataforma especial, para que no se quede siempre en movimiento a la izquierda
			cambiado = true;
			moveRight();
		}else {
			cambiado = false;
			if(x-VELOCIDAD > limXInicial) { //Verfico que no se salga por la izquierda
				x-=VELOCIDAD; //Me muevo
			}
			else {
				x = limXInicial; //Para que la plataforma no se salga
			}
		}
	}
	
	@Override
	public void setColor(int color) {
		super.setImagen("recursos/PlataformaEspecial"+Integer.toString(color)+".png");
	}
}