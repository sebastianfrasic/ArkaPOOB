package aplicacion;

/**
 * Cuando la bola la toca, queda pegada a ella por unos segundos y luego se desprende.
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class PlataformaPegajosa extends Plataforma{
	
	private Bola bolaPegada;
	private boolean pegada;
	private int dxBola;

	public PlataformaPegajosa(int x, int y,int color) {
		super(x, y,("PlataformaPegajosa"+Integer.toString(color)));
	}
	
	@Override
	public void moveRight(Jugador otroJugador){
		super.moveRight(otroJugador);	
		moverBolaPegada();	
	}
	
	@Override
	public void moveRight(){
		super.moveRight();
		moverBolaPegada();
	}
	
	@Override
	public void moveLeft(Jugador otroJugador){
		super.moveLeft(otroJugador);
		moverBolaPegada();
	}
	
	@Override
	public void moveLeft(){
		super.moveLeft();
		moverBolaPegada();  //Para que la bola se mueva junto con la plataforma		
	}
	
	@Override
	public void moverBolaPegada() {
		if(bolaPegada!=null)
			if (bolaPegada.pegada() && pegada) {
				bolaPegada.setPos(getX()-dxBola);
			}
	}
	
	@Override
	public boolean pegarBola() {
		bolaPegada.pegar();
		dxBola = (int)Math.round(getX()-bolaPegada.getX());
		pegada = true;
		return true;
	}
	
	@Override
	public void darBola(Bola bola) {
		bolaPegada = bola;
	}
	
	
	@Override
	/**
	 * Suelta la bola cuando tiene le poder pegajoso o cuando inicia en el modo de un jugador 
	 */
	public void soltarBola() {
		if (bolaPegada != null)
			if (bolaPegada.pegada()) {
				bolaPegada.despegar();
				pegada = false;
			}	
	}
	
	@Override
	public void setColor(int color) {
		super.setImagen("recursos/PlataformaPegajosa"+Integer.toString(color)+".png");
	}
}