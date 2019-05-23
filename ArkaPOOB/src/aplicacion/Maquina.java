package aplicacion;

/**
 * Representa a un jugador maquina en el juego
 * @author Juan Sebastian Fr�sica y Juan Sebasti�n G�mez
 *
 */
public abstract class Maquina extends Jugador {

	public Maquina(int x, int y) {
		super(x, y, false);
	}
	
	@Override
	public void pegarBola() {
		if(plataforma.pegarBola()) {
			this.plataforma.soltarBola();
		}
	}
}
