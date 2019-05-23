package aplicacion;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * Clase fachada del juego. Representa el juego ArkaPOOB
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class ArkaPOOB implements Serializable{
	
	private static ArkaPOOB juego = null;
	private Bola bola;
	private Jugador[] jugadores;
	private Bloque[] bloques;
	private Bloque ultimoDestruido;
	private ArrayList<Sorpresa> sorpresas;
	private int nivelActual;
	private boolean enPausa, creoBola, actualizarBloques;
	

	public ArkaPOOB(){
		nivelActual = 1;
		sorpresas = new ArrayList<Sorpresa>();
	}
	
	/**
	 * 
	 * @return Nueva instancia solitaria de la clase ArkaPOOB
	 */
	public static ArkaPOOB getJuego(){
		if(juego == null) {
			juego = new ArkaPOOB();
		}
		return juego;
	}
	
	public static void nuevoJuego(){
		juego = new ArkaPOOB();
	}
	
	/**
	 * Selecciona el nivel actual, crea la nueva bola y crea la configuracion de bloques
	 * @param nivel actual 
	 */
	public void setNivel(int nivel){
		nivelActual = nivel;
		int posBola = (nivelActual == 5)?120+(7*17):120+(nivelActual*17);
		crearBola(jugadores.length);
		crearNivel();
	}
	
	/**
	 * Crea los jugadores dependiendo de la cantidad y el tipo de jugador (humano o maquina) 
	 * @param NJugadores número de jugadores
	 * @param maquina número de maquinas 0 si no hay maquina, 1 si es destructor, 2 si es curioso y 3 si es mimo
	 */
	public void prepareJugadores(int NJugadores,int maquina){
		jugadores = new Jugador[NJugadores];
		if(NJugadores == 2){
			jugadores[0] = new Humano(188,550); //Posicion izquierda
			if(maquina > 0) {
				switch (maquina){
				case 1:
					jugadores[1] = new Destructor(556,550); //Posicion derecha
					break;
				case 2:
					jugadores[1] = new Curioso(556,550); //Posicion derecha
					break;
				default:
					jugadores[1] = new Mimo(556,550); //Posicion derecha
					break;
					}
			}else {
				jugadores[1] = new Humano(556,550); //Posicion derecha
			}
		}else{
			jugadores[0] = new Humano(400,550); //Posicion centro
		}
	}
	
	/**
	 * Da la bola actual con la que se esta jugando
	 * @return bola actual en juego  
	 */
	public Bola getBola(){
		return bola;
	}
	
	/**
	 * Da la cantidad de bloques en el tablero
	 * @return cantidad de bloques del nivel
	 */
	public int numeroDeBloques(){	
		return bloques.length;
	}
	
	/**
	 * Da un bloque en una posicion i especifica
	 * @param i posicion del bloque
	 * @return el bloque en la posicion i, si no hay retorna null
	 */
	public Bloque getBloque(int i){
		Bloque bloque = null;
		if (i >- 1 && i< bloques.length) {
			bloque = bloques[i];
		}
		return bloque;
	}
        /**
	 * Da la lista de bloque
	 * @return lista de bloques
	 */
	public Bloque[] getBloques(){
		return bloques;
	}
	
	/**
	 * Da la cantidad de jugadores en juego
	 * @return la cantidad de jugadores en juego
	 */
	public int numeroDeJugadores(){
		return jugadores.length;
	}
	
	/**
	 * Da un jugador en una posicion i especifica
	 * @param i posicion del jugador
	 * @return el jugador en la posicion i, si no hay retorna null
	 */
	public Jugador getJugador(int i){
		Jugador jugador = null;
		if (i >- 1 && i < jugadores.length) {
			jugador = jugadores[i];
		}
		return jugador;
	}
	
	/**
	 * Da la cantidad de sorpresas en el juego
	 * @return la cantidad de sorpresas en el juego 
	 */
	public int numeroDeSorpresas(){
		return sorpresas.size();
	}
	
	
	/**
	 * Da una sorpresa en una posicion i especifica
	 * @param i posicion de la sopresa
	 * @return la sorpresa en la posicion i, si no hay retorna null
	 */
	public Sorpresa getSorpresa(int i){
		return sorpresas.get(i);
	}

	
	/**
	 * Crea la configuración del nivel actual
	 */
	public void crearNivel(){
		Nivel nuevo = new Nivel(nivelActual);
		bloques = nuevo.getConfiguracion();
	}
	
	/**
	 * Mueve la bola y las sorpresas
	 */
	public void mover() {
		actualizarBloques = false;
		bola.mover(this);
		for(Sorpresa s:sorpresas){
			if (s.isVisible()) {
				s.mover(jugadores,bola);
			}
		}
		if(jugadores.length>1) {
			if(!jugadores[1].esHumano() && jugadores[1].tipoMaquina()!=2){
				jugadores[1].mover(bola,sorpresas,jugadores[0]);
			}
		}
	}
	
	public void atenderBolaCaida(int jugador) {
		quitarVidas(jugador);
		crearBola(jugadores.length);
		bola.setJugador(jugador);
		creoBola = true;
	}
	
	public void noCreoBola() {
		creoBola = false;		
	}
	
	public void setUltimoDestruido(Bloque bloque) {
		ultimoDestruido = bloque;
	}
	
	public Bloque getUltimoDestruido() {
		return ultimoDestruido;
	}
	
	/**
	 * 
	 */
	public void actualizaBloques() {
		actualizarBloques = true;
	}
	
	public boolean actualizarBloques() {
		return actualizarBloques;
	}
	
	public void agregarSorpresa(Sorpresa sorpresa) {
		sorpresas.add(sorpresa);
	}
	
	public void acabarNivel() {
		for(Bloque bl:bloques) {
			bl.fueraDeJuego();
		}
	}
	
	/**
	 * Atiende las opciones que dan los bloques
	 * @param opciones lista de opciones con datos necesarios para atenderlas
	 */
	public void atenderBloques(int[] opciones) {
		if (opciones[2] == -4) {
			a�adirVida(opciones[1]);//bloque amarillo
		}
	}
	
	/**
	 * Quita una vida a un jugador especifico
	 * @param jugador a quitar vida
	 */
	public void quitarVidas(int jugador) {
		sonidoDead();
		jugadores[jugador].quitarVida();
		for (Jugador j: jugadores) {
			j.quitarPoderes();
		}
	}
	
	/**
	 * Añade una vida a un jugador especifico
	 * @param jugador a añadir vida
	 */
	public void a�adirVida(int jugador) {
		sonidoVida();
		jugadores[jugador].a�adirVida();
	}
	
	/**
	 * Coloca la bola en el tablero dependiendo del numero de jugadores: En el centro de la plataforma o arriba
	 * @param jugador numero de jugadores 
	 */
	public void crearBola(int nJugadores) {
		if (nJugadores == 1) {
			bola = jugadores[0].asignarBola();  //En el centro de la plataforma
		}
		else {
			int posBola = (nivelActual == 5)?120+(7*17):120+(nivelActual*17); //Arriba
			bola = new BolaNormal(430, posBola);
		}
	}
	
	/**
	 * Dice si  el nivel actual se acabo, un nivel se acaba cuando todos sus bloques estan fuera de juego. Ademas si acaba un nivel
	 * reinicia al estado actual del siguiente nivel.
	 * @return si el nivel actual termino.
	 */
	public boolean nivelAcabado() {
		boolean terminado = true;
		for(Bloque b: bloques) {
			terminado = (b.enJuego())?false:terminado;
		}
		if(terminado) {
			for (Jugador j: jugadores) {
				j.quitarPoderes();
			}
			avanzaNivel();
			crearBola(jugadores.length);
			for(Sorpresa s:sorpresas){
				s.setVisible(false);
			}
		}
		return terminado;
	}
	
	/**
	 * Avanza un nivel y manda crear la configuración del siguiente nivel 
	 */
	public void avanzaNivel(){
		nivelActual ++;
		crearNivel();
	}
	
	
	/**
	 * Dice si el juego acabo. Un juego acaba cuando uno de sus jugadores se queda sin vidas.
	 * @return Si alguno de los jugadores se queda sin vidas
	 */
	public boolean gameOver() {
		boolean aunActivo = false;
		for(Jugador j: jugadores) {
			aunActivo = (j.getVidas()==0)?true:aunActivo;
		}
		return aunActivo;
	}
	
	/**
	 * Dice si quedo en el ultimo nivel
	 * @return Si ya se acabaron los niveles
	 */
	public boolean finished() {
		return nivelActual > 5;
	}
	
	/**
	 * Mueve el jugador n a la izquierda
	 * @param n posicion del jugador 
	 */
	public void JugadorNLeft(int n){
		if(jugadores.length>1) {
			if (n == 0) {
				jugadores[n].moveLeft(jugadores[1]);
				if(!jugadores[1].esHumano() && jugadores[1].tipoMaquina() == 2) {
					jugadores[1].moveLeft(jugadores[0]);
				}
			}else {
				jugadores[n].moveLeft(jugadores[0]);
			}
		}else {
			jugadores[n].moveLeft();
		}
	}
	
	/**
	 * Mueve el jugador n a la derecha
	 * @param n posicion del jugador 
	 */
	public void JugadorNRight(int n){
		if(jugadores.length > 1) {
			if (n == 0) {
				jugadores[n].moveRight(jugadores[1]);
				if(!jugadores[1].esHumano() && jugadores[1].tipoMaquina() == 2) {
					jugadores[1].moveRight(jugadores[0]);
				}
			}
			else 
				jugadores[n].moveRight(jugadores[0]);
		}else {
			jugadores[n].moveRight();
		}
	}
	
	/**
	 * Le dice al jugador n que suelte la bola.
	 * @param n posicion del jugador
	 */
	public void soltarBola(int n) {
		jugadores[n].soltarBola();
	}
	
	/**
	 * Pausa o despausa el juego
	 */
	public void pausa(){
		enPausa = !enPausa;
	}
	
	/**
	 * Dice si el juego actual esta en pausa 
	 * @return el juego actual en pausa
	 */
	public boolean enPausa(){
		return enPausa;
	}
	
	/**
	 * Dice si en la ultima accion creo una bola.
	 * @return si en el ultimo turno creo una bola 
	 */
	public boolean creoBola(){
		return creoBola;
	}
	
	/**
	 * Acaba el juego
	 */
	public void endGame() {
		nivelActual = 6;
		for(Jugador j: jugadores) {
			j.end();
		}
	}
	
	/**
	 * Asigna un nuevo juego
	 * @param aP nuevo juego 
	 */
	public static void setJuego(ArkaPOOB aP) {
		juego = aP;
	}
	
	
	/**
	 * Da el número (en String) del nivel actual
	 * @return nivel actual
	 */
	public String getNivel(){
		return Integer.toString(nivelActual);		
	}
	
	/**
	 * Da el número (en entero) del nivel actual
	 * @return nivel actual
	 */
	public int getNivelActual(){
		return nivelActual;		
	}
	
	/**
	 * Salva el estado actual del juego.
	 * @param archivo donde se ava a guardar
	 */
	public void salvar (File archivo) throws ArkaPOOBException {
		if (juego == null) {
			throw new ArkaPOOBException(ArkaPOOBException.SIN_JUEGO);
		}
		try{
			ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(archivo));
			out.writeObject(juego);
			out.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Abre un juego a apartir de un archivo.
	 * @param archivo de donde se va a abrir.
	 */
	public static void abra(File archivo) throws ArkaPOOBException {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
			setJuego((ArkaPOOB)ois.readObject() );
		} catch (Exception e) {
			throw new ArkaPOOBException(ArkaPOOBException.NO_ABRIR);
		}
	}
	
	/**
	 * Reproduce un sonido cuando la bola cae por debajo de la plataforma
	 */
	private void sonidoDead() {
		try {
			FileInputStream is = new FileInputStream("recursos/sonidos/dead.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			Clip dead = (Clip) AudioSystem.getLine(info);
			dead.open(ais);
			dead.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	
	/**
	 * Reproduce un sonido cuando se gana una vida
	 */
	private void sonidoVida() {
		try {
			FileInputStream is = new FileInputStream("recursos/sonidos/life.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			Clip vida = (Clip) AudioSystem.getLine(info);
			vida.open(ais);
			vida.start();
		}catch (Exception e) {
			e.printStackTrace();
		}
	};
	
	/**
	 * Asigna los bloques con los que se va a jugar.
	 * @param bloques bloques con los que se va a jugar
	 * @throws ArkaPOOBException cuando no hay bloques o solo se juega con bloques grises/negros
	 */
	public void bloquesParaJugar(int[] bloques)throws ArkaPOOBException {
		int[] bloquesNivel = convertir(bloques);
		if (bloquesNivel.length == 0) {
			throw new ArkaPOOBException(ArkaPOOBException.SIN_BLOQUES);
		}
		if (bloquesNivel.length == 1 && bloquesNivel[0] == 2) {
			throw new ArkaPOOBException(ArkaPOOBException.SOLO_GRISES);
		}
		if (bloquesNivel.length == 1 && bloquesNivel[0] == 5) {
			throw new ArkaPOOBException(ArkaPOOBException.SOLO_NEGROS);
		}
		Nivel.setSeleccion(bloquesNivel);
	}
	
	/**
	 * Asigna el tipo de sorpresas con los que se va a jugar
	 * @param sorpresas con las que se va a jugar
	 */
	public void sorpresasParaJugar(int[] sorpresas)throws ArkaPOOBException {
		int[] sorpresasNivel = convertir(sorpresas);
		boolean hayAzul = false;
		for(String bloque : Nivel.getSeleccion()) {
			hayAzul = (bloque.equals("BloqueAzul"))?true:hayAzul;
		}
		if(hayAzul && sorpresasNivel.length ==0) {
			throw new ArkaPOOBException(ArkaPOOBException.SIN_SORPRESAS);
		}
		BloqueAzul.setSeleccion(sorpresasNivel);
	}
	
	/**
	 * Convierte el formato en el que vienen los elementos seleccionados (mappeo segun posicion: 1 si esta, o 0 si no esta el elemento) a una lista de 
	 * los números de los elementos con los que se va a jugar 
	 * @param bloques elementos seleccionados 
	 * @return elementos seleccionados con el nuevo formato
	 */
	public int[] convertir(int[] bloques) {
		int cantidad = 0;
		for(int i  = 0; i < bloques.length; i++) {
			if (bloques[i]==1) {
				cantidad++;
			}
		}
		int[] bloquesNivel = new int[cantidad];
		int posicion = 0;
		for(int i  = 0; i < bloques.length; i++) {
			if (bloques[i]==1) {
				bloquesNivel[posicion]= i+1;
				posicion++;
			}
		}
		return bloquesNivel;
	}

	/**
	 * Asigna los colores a los jugadores (plataformas).
	 * @param colores de la(s) platafoma(s)
	 * @throws ArkaPOOBException cuando los colores de los los jugadores son iguales 
	 */
	public void colores(int[] colores) throws ArkaPOOBException{
		if(colores.length == 2 && colores[0] == colores[1]) {
			throw new ArkaPOOBException(ArkaPOOBException.MISMO_COLOR);
		}
		for (int i = 0 ;i < colores.length;i++) {
			jugadores[i].setColor(colores[i]);
		}
		crearBola(jugadores.length);
	} 
}
