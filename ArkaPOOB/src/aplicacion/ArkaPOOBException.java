package aplicacion;

/**
 * Clase propia de excepciones
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class ArkaPOOBException extends Exception {
	
	public static final String MISMO_COLOR = "Los jugadores no pueden tener el mismo color.";
	public static final String SOLO_GRISES = "No puede jugar solo con bloques grises.";
	public static final String SOLO_NEGROS = "No puede jugar solo con bloques negros.";
	public static final String SIN_BLOQUES = "No puede jugar sin bloques seleccionados.";
	public static final String SIN_SORPRESAS = "Si escogio el bloque azul, no puede jugar sin sorpresas.";
	public static final String SIN_JUEGO = "Cree un juego antes de guardar.";
	public static final String NO_ABRIR = "Imposible abrir el juego. Asegúrse que es un archivo válido.";
	
	public ArkaPOOBException(String message) {
		super(message);
	}

}
