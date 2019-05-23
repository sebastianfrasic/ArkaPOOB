package aplicacion;

import java.util.*;


/**
 * Representa un nivel del juego, conociendo los bloques, la probabilidad de que salga uno de cada tipo; y de organizar aleatoriamente los bloques según el nivel
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class Nivel {
	
	private Bloque[] configuracion;
	private Random ran;
	private static String[] seleccion;
	private static String[] nombreBloques = {"BloqueRojo","BloqueGris","BloqueAnaranjado","BloqueVerde","BloqueNegro","BloqueAzul","BloqueRosado","BloqueAmarillo"};

	/**
	 * Creador de un nivel
	 * @param n Numero del nivel que se va a crear
	 */
	public Nivel(int n) {
		ran = new Random();
		if (n == 5) {
			armarUltimo();
		}else {
			configuracion = new Bloque[n*13];
			int contador = 0;
			for(int i = 0; i < n;i++){
				for (int j = 0 ; j <13; j++) {
					configuracion[contador] = bloqueRandom(21+(j*66),100+(i*17));
					contador++;
				}
			}
		}
	}
	
	/**
	 * Crea un bloque random con los valores que esten el seleccion
	 * @param x x donde va a estar el bloque
	 * @param y y donde va a estar el bloque
	 * @return el bloque en la posicion (x,y)
	 */
	private Bloque bloqueRandom(int x,int y){
		Bloque bl = null;
		String n = seleccion[ran.nextInt(seleccion.length)];
		bl = Bloque.crearBloque(n);
		bl.setX(x);
		bl.setY(y);
		return bl;
	}
	
	/**
	 * Crear el último
	 */
	private void armarUltimo() {
		configuracion = new Bloque[53];
		int contador = 0;
		contador = armarP(contador);
		contador = armarO(contador);
		armarB(contador);
	}
	
	/**
	 * Crea la letra B del último nivel
	 * @param contador posicion donde debe poner el bloque dentro del arreglo de bloques
	 */
	private void armarB(int contador) {
		for (int i = 0;i < 5;i++) {
			configuracion[contador] = bloqueRandom(54+(66*9),117+(17*i));
			contador++;
			if(i != 2) {
				configuracion[contador] = bloqueRandom(54+(66*11),117+(17*i));
				contador++;
			}
		}
		for (int i = 0;i < 2;i++) {
			configuracion[contador] = bloqueRandom(681+(66*i),100);
			contador++;
		}
		for (int i = 0;i < 2;i++) {
			configuracion[contador] = bloqueRandom(681+(66*i),202);
			contador++;
		}
		configuracion[contador] = bloqueRandom(714,151);
	}

	/**
	 * Crea las letras O del último nivel
	 * @param contador posicion donde debe poner el bloque dentro del arreglo de bloques
	 */
	private int armarO(int contador) {
		for (int i = 0;i < 5;i++) {
			configuracion[contador] = bloqueRandom(54+(66*3),117+(17*i));
			contador++;
			configuracion[contador] = bloqueRandom(54+(66*5),117+(17*i));
			contador++;
		}
		for (int i = 0;i < 5;i++) {
			configuracion[contador] = bloqueRandom(54+(66*6),117+(17*i));
			contador++;
			configuracion[contador] = bloqueRandom(54+(66*8),117+(17*i));
			contador++;
		}
		for (int i = 0;i < 2;i++) {
			configuracion[contador] = bloqueRandom(285+(66*i),100);
			contador++;
		}
		for (int i = 0;i < 2;i++) {
			configuracion[contador] = bloqueRandom(483+(66*i),100);
			contador++;
		}
		for (int i = 0;i < 2;i++) {
			configuracion[contador] = bloqueRandom(285+(66*i),202);
			contador++;
		}
		for (int i = 0;i < 2;i++) {
			configuracion[contador] = bloqueRandom(483+(66*i),202);
			contador++;
		}
		return contador;
	}

	
	/**
	 * Crea la letra P del último nivel
	 * @param contador posicion donde debe poner el bloque dentro del arreglo de bloques
	 */
	private int armarP(int contador) {
		for (int i = 0;i < 6;i++) {
			configuracion[contador] = bloqueRandom(54,117+(17*i));
			contador++;
		}
		for (int i = 0;i < 2;i++) {
			configuracion[contador] = bloqueRandom(186,117+(17*i));
			contador++;
		}
		for (int i = 0;i < 2;i++) {
			configuracion[contador] = bloqueRandom(87+(66*i),100);
			contador++;
		}
		configuracion[contador] = bloqueRandom(121,151);
		contador++;
		return contador;
	}

	/**
	 * Da la configuración de bloques del nivel
	 * @return la configuración de bloques del nivel
	 */
	public Bloque[] getConfiguracion() {
		return configuracion;
	}
	
	/**
	 * Le da la seleccion de bloques que debe usar
	 * @param bloques bloques de la seleccion
	 */
	public static void setSeleccion(int[] bloques ) {
		int longitud = 0;
		for(int i = 0; i < bloques.length;i++) {
			longitud += probabilidad(bloques[i]);
		}
		String[] nuevaSeleccion = new String[longitud] ;
		int posicion = 0;
		for(int i = 0; i< bloques.length;i++) {
			for (int j = 0; j < probabilidad(bloques[i]);j++) {
				nuevaSeleccion[posicion] = nombreBloques[bloques[i]-1];
				posicion++;
			}
		}
		seleccion = nuevaSeleccion;
	}
	
	/**
	 * Da la probabilidad de aparicion dependiendo del tipo de bloque.
	 * @param i tipo de bloque
	 * @return la probabilidad dependiendo tipo de bloque
	 */
	private static int probabilidad(int i) {
		int cantidad = 0;
		if (i == 1) {
			cantidad = 13; 
		}else if (i == 2) {
			cantidad = 9;
		}else if (i == 3||i == 4) {
			cantidad = 7;
		}else if (i == 5||i == 6) {
			cantidad = 4;
		}else if (i == 7||i == 8) {
			cantidad = 2;
		}
		return cantidad;
	}
	
	/**
	 * Da la configuración de bloques del nivel
	 * @return la configuración de bloques del nivel
	 */
	public static String[] getSeleccion() {
		return seleccion;
	}
}