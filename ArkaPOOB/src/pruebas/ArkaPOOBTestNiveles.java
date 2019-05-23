package pruebas;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import aplicacion.*;


public class ArkaPOOBTestNiveles {
	
	//Niveles
	
	@Test
	public void deberiaCrearNivel1() throws ArkaPOOBException {
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		int[] configuracion = {1,1,1,1,1,1,1,1};
		int[] bloques = juego.convertir(configuracion);
		juego.bloquesParaJugar(bloques);
		juego.crearNivel();
		int cantidadBloques = juego.numeroDeBloques();
		assertTrue(cantidadBloques == 13);
		
	}
	
	@Test
	public void deberiaCrearNivel2() {
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		juego.prepareJugadores(1, 0);
		juego.setNivel(2);
		int cantidadBloques = juego.numeroDeBloques();
		assertTrue(cantidadBloques == 26);
	}
	
	@Test
	public void deberiaCrearNivel3() {
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		juego.prepareJugadores(1, 0);
		juego.setNivel(3);
		int cantidadBloques = juego.numeroDeBloques();
		assertTrue(cantidadBloques == 39);
		
	}
	
	@Test
	public void deberiaCrearNivel4() {
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		juego.prepareJugadores(1, 0);
		juego.setNivel(4);
		int cantidadBloques = juego.numeroDeBloques();
		assertTrue(cantidadBloques == 52);
		
	}
	
	@Test
	public void deberiaCrearNivel5() {
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		juego.prepareJugadores(1, 0);
		juego.setNivel(5);
		int cantidadBloques = juego.numeroDeBloques();
		assertTrue(cantidadBloques == 53);
	}
	
	
	
	@Test
	public void deberiaAvanzarNivel() throws ArkaPOOBException {
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		int[] configuracion = {1,1,1,1,1,1,1,1};
		int[] bloques = juego.convertir(configuracion);
		juego.bloquesParaJugar(bloques);
		juego.crearNivel();
		juego.prepareJugadores(1, 0);
		juego.setNivel(4);
		juego.avanzaNivel();
		assertTrue(juego.getNivelActual() == 5);
	}
	
	
}
