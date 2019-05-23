package pruebas;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import aplicacion.*;

public class ArkaPOOBTestBloques {
	
	//Bloque Rojo
	
	@Test
	public void deberiaDestruirBloqueRojo(){
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		juego.prepareJugadores(1, 0);
		juego.crearBola(1);
		int[] bloqueRojo = {1,0,0,0,0,0,0,0};
		try {
			juego.bloquesParaJugar(bloqueRojo);
		} catch (ArkaPOOBException e) {
			e.printStackTrace();
		}
		juego.crearNivel();
		juego.getJugador(0).soltarBola();
		Bloque bloqueATocar = juego.getBloque(6);
		while (bloqueATocar.enJuego()) {
			juego.mover();
		}
		int soloUnDestruido = 0;
		for (int i = 0; i<juego.numeroDeBloques();i++) {
			if(!juego.getBloque(i).enJuego()) {
				soloUnDestruido++;
			}
		}
		assertTrue(soloUnDestruido == 1);
		
	}
	
	@Test
	public void deberiaDar100PuntosAlDestruirBloqueRojo(){
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		juego.prepareJugadores(1, 0);
		juego.crearBola(1);
		int[] bloqueRojo = {1,0,0,0,0,0,0,0};
		try {
			juego.bloquesParaJugar(bloqueRojo);
		} catch (ArkaPOOBException e) {
			e.printStackTrace();
		}
		juego.crearNivel();
		juego.getJugador(0).soltarBola();
		Bloque bloqueATocar = juego.getBloque(6);
		while (bloqueATocar.enJuego()) {
			juego.mover();
		}
		assertTrue(juego.getJugador(0).getPuntaje()==100);
		
	}
	
	/*
	//Bloque Verde
	
	@Test
	public void deberiaDañarBloqueVerdeConUnGolpe(){
		Bloque bloqueVerde = new BloqueVerde(0,0);
		Bloque[] listaBloques = {};
		int[] lista = bloqueVerde.tocar(1, 18, 1, -1, listaBloques); //Algo lo va a tocar por debajo
		assertEquals(bloqueVerde.getImagen(), "recursos/BloqueVerdeRoto.png");
		
	}
	
	@Test
	public void deberiaDestruirBloqueVerdeConDosGolpes(){
		Bloque bloqueVerde = new BloqueVerde(0,0);
		Bloque[] listaBloques = {};
		bloqueVerde.tocar(1, 18, 1, -1, listaBloques); //Algo lo va a tocar por debajo
		int[] lista = bloqueVerde.tocar(1, 18, 1, -1, listaBloques); //Algo lo va a tocar por debajo
		assertFalse(bloqueVerde.enJuego());
		
	}
	
	
	@Test
	public void deberiaDar200PuntosAlDestruirBloqueVerde(){
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		juego.prepareJugadores(1, 0);
		juego.crearBola(1);
		int[] bloqueVerde = {0,0,0,1,0,0,0,0};
		try {
			juego.bloquesParaJugar(bloqueVerde);
		} catch (ArkaPOOBException e) {
			e.printStackTrace();
		}
		juego.crearNivel();
		juego.getJugador(0).soltarBola();
		Bloque bloqueATocar = juego.getBloque(6);
		while (bloqueATocar.enJuego()) {
			juego.mover();
		}
		assertTrue(juego.getJugador(0).getPuntaje()==200);
		
	}
	*/
	

	
	
	//Bloque rosado
	
	@Test
	public void deberiaDar500PuntosAlDestruirBloqueRosado(){
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		juego.prepareJugadores(1, 0);
		juego.crearBola(1);
		int[] bloqueRosa = {0,0,0,0,0,0,1,0};
		try {
			juego.bloquesParaJugar(bloqueRosa);
		} catch (ArkaPOOBException e) {
			e.printStackTrace();
		}
		juego.crearNivel();
		juego.getJugador(0).soltarBola();
		Bloque bloqueATocar = juego.getBloque(6);
		while (bloqueATocar.enJuego()) {
			juego.mover();
		}
		assertTrue(juego.getJugador(0).getPuntaje()==500);
		
	}
	
	/*
	@Test
	public void deberiaAvanzarNivelAlDestruirBloqueRosado(){
		Bloque bloqueRosado = new BloqueRosado(20,70);
		Bola bola = new BolaNormal(25,88);
		bola.cambiarDireccion(90);
		Bloque[] listaBloquesRosados = {bloqueRosado};
		Jugador[] listaJugadores = {};
		int[] lista = bola.mover(listaBloquesRosados, listaJugadores);
		assertEquals(lista[2], -2);
		
		
		
	}*/
	
	//Bloque amarillo
	
	@Test
	public void deberiaDar300PuntosAlDestruirBloqueAmarillo(){
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		juego.prepareJugadores(1, 0);
		juego.crearBola(1);
		int[] bloqueAmarillo = {0,0,0,0,0,0,0,1};
		try {
			juego.bloquesParaJugar(bloqueAmarillo);
		} catch (ArkaPOOBException e) {
			e.printStackTrace();
		}
		juego.crearNivel();
		juego.getJugador(0).soltarBola();
		Bloque bloqueATocar = juego.getBloque(6);
		while (bloqueATocar.enJuego()) {
			juego.mover();
		}
		assertTrue(juego.getJugador(0).getPuntaje()==300);
		
	}
	
	
	@Test
	public void deberiaDarVidaExtraAlDestruirBloqueAmarillo(){
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		juego.prepareJugadores(1, 0);
		juego.crearBola(1);
		int[] bloqueAmarillo = {0,0,0,0,0,0,0,1};
		try {
			juego.bloquesParaJugar(bloqueAmarillo);
		} catch (ArkaPOOBException e) {
			e.printStackTrace();
		}
		juego.crearNivel();
		juego.getJugador(0).quitarVida();
		juego.getJugador(0).soltarBola();
		Bloque bloqueATocar = juego.getBloque(6);
		while (bloqueATocar.enJuego()) {
			juego.mover();
		}
		assertTrue(juego.getJugador(0).getVidas() == 3);
	}
	
	//Bloque naranja
	
	@Test
	public void deberiaDar500PuntosAlDestruirBloqueNaranja(){
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		juego.prepareJugadores(1, 0);
		juego.crearBola(1);
		int[] bloqueNaranja = {0,0,1,0,0,0,0,0};
		try {
			juego.bloquesParaJugar(bloqueNaranja);
		} catch (ArkaPOOBException e) {
			e.printStackTrace();
		}
		juego.crearNivel();
		juego.getJugador(0).soltarBola();
		Bloque bloqueATocar = juego.getBloque(6);
		while (bloqueATocar.enJuego()) {
			juego.mover();
		}
		assertTrue(juego.getJugador(0).getPuntaje()==500);
		
	}
	
	/*
	//Bloque negro
	
	@Test
	public void deberiaDar600PuntosAlGolpearBloqueNegro(){
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		juego.prepareJugadores(1, 0);
		juego.crearBola(1);
		int[] bloqueNegro = {0,,0,0,1,0,0,0};
		try {
			juego.bloquesParaJugar(bloqueNegro);
		} catch (ArkaPOOBException e) {
			e.printStackTrace();
		}
		juego.crearNivel();
		juego.getJugador(0).soltarBola();
		Bloque bloqueATocar = juego.getBloque(6);
		while (bloqueATocar.enJuego()) {
			juego.mover();
		}
		assertTrue(juego.getJugador(0).getPuntaje()==600);
		
	}*/
	
	
	
	//Bloque azul
	
	@Test
	public void deberiaDar300PuntosAlDestruirBloqueAzul(){
		ArkaPOOB.nuevoJuego();
		ArkaPOOB juego = ArkaPOOB.getJuego();
		juego.prepareJugadores(1, 0);
		juego.crearBola(1);
		int[] bloqueAzul = {0,0,0,0,0,1,0,0};
		try {
			juego.bloquesParaJugar(bloqueAzul);
		} catch (ArkaPOOBException e) {
			e.printStackTrace();
		}
		juego.crearNivel();
		juego.getJugador(0).soltarBola();
		Bloque bloqueATocar = juego.getBloque(6);
		while (bloqueATocar.enJuego()) {
			juego.mover();
		}
		assertTrue(juego.getJugador(0).getPuntaje()==300);
	}
	

	

	
}
