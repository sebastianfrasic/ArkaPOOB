package aplicacion;

/**
 * Estos bloques si puden esquivar la bola moviendose hacia arriba, se mueven. Son comunes. Otorgan
500 puntos.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class BloqueAnaranjado extends Bloque {

	public BloqueAnaranjado(int x, int y) {
		super(x, y);
		setImagen("recursos/BloqueNaranja.png");
	}

	public int getPuntaje() {
		return 500;
	}
	

	public void esquivar(double xBola, double yBola,double dx,double dy,ArkaPOOB juego) {
		Bloque[] bloques = juego.getBloques();
		if (((xBola < getX() - 20 && xBola + dx >= getX() - 20) && ((yBola + 20 >= getY()  && yBola + 20 <= getY() + 17)||(yBola >= getY()  && yBola <= getY() + 17)||(yBola <= getY() && yBola + 20 >= getY() + 17)) && isVisible())||
				((xBola > getX() + 66 && xBola + dx <= getX() + 66) && ((yBola + 20 >= getY()  && yBola + 20 <= getY() + 17)||(yBola >= getY()  && yBola <= getY() + 17)||(yBola <= getY() && yBola + 20 >= getY() + 17)) && isVisible())||
				((yBola < getY() - 20 && yBola + dy >= getY() - 20) && ((xBola + 20 >= getX()  && xBola + 20 <= getX() + 66)||(xBola >= getX()  && xBola <= getX() + 66)) && isVisible())||
				((yBola > getY() + 17 && yBola + dy <= getY() + 17) && ((xBola + 20 >= getX()  && xBola + 20 <= getX() + 66)||(xBola >= getX()  && xBola <= getX() + 66)) && isVisible())){
			boolean puestoOcupado = false;
			int i = 0;
			while (i < bloques.length && !puestoOcupado) {
				puestoOcupado = ((bloques[i].getY() == (getY() - 17)) && 
						((getX() >= bloques[i].getX() && getX() <= bloques[i].getX() + 66)||
								(getX()+66 >= bloques[i].getX() && getX()+66 <= bloques[i].getX() + 66)) && bloques[i].isVisible());
				i++;
			}
			if(!puestoOcupado && (getY()-17) >= 63){
				setY(getY() - 17);
			}
		
		}
		juego.actualizaBloques();
	}
}
