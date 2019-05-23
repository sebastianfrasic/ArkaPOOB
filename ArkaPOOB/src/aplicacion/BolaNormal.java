package aplicacion;


/**
 * Representa una bola que recorre el tablero destruyendo bloques y rebotando con paredes y plataformas.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class BolaNormal extends Bola{

	protected double velocidad = 2;
	
	public BolaNormal(int x, int y) {
		super(x, y);
	}

}
