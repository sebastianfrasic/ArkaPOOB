package pruebas;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import aplicacion.*;

public class ArkaPOOBTestPlataformas {
	
	
	@Test
	public void deberiaMoverPlataformaALaDerecha(){
		Humano jugador = new Humano(188,550);
		jugador.moveRight();
		assertTrue(jugador.getX() == 188+30);
		
	}
	
	@Test
	public void deberiaMoverPlataformaALaIzquierda(){
		Humano jugador = new Humano(188,550);
		jugador.moveLeft();
		assertTrue(jugador.getX() == 188-30);
	}
	
	@Test
	public void deberianHacerSwapAlMoverALaDerecha(){
		Humano jugador1 = new Humano(188,550);
		Humano jugador2 = new Humano(287,550);
		jugador1.moveRight(jugador2);
		assertTrue(jugador1.getX() == 287  && jugador2.getX() == 189);
	}
	
	@Test
	public void deberianHacerSwapAlMoverALaIzquierda(){
		Humano jugador1 = new Humano(188,550);
		Humano jugador2 = new Humano(287,550);
		jugador2.moveLeft(jugador1);
		assertTrue(jugador1.getX() == 287  && jugador2.getX() == 189);
	}
	
	
	@Test
	public void noDeberianMoverseEnUnBorde(){
		Humano jugador1 = new Humano(15,550);
		jugador1.moveLeft();
		assertTrue(jugador1.getX() == Plataforma.limXInicial);
	}
	
	
	@Test
	public void deberiaCambiarAPlataformaEspecial(){
		SorpresaPlataformaEspecial sorpresa = new SorpresaPlataformaEspecial(188,500);
		Humano jugador1 = new Humano(188,550);
		Jugador[] lista = {jugador1};
		Bola bola = new BolaNormal(0,0);
		while(sorpresa.isVisible()) {
			sorpresa.mover(lista, bola);
		}
		assertTrue(jugador1.getPlataforma().getClass().getSimpleName().equals("PlataformaEspecial"));
	}
	
	@Test
	public void deberiaCambiarAPlataformaPegajosa(){
		SorpresaPlataformaPegajosa sorpresa = new SorpresaPlataformaPegajosa(188,500);
		Humano jugador1 = new Humano(188,550);
		Jugador[] lista = {jugador1};
		Bola bola = new BolaNormal(0,0);
		while(sorpresa.isVisible()) {
			sorpresa.mover(lista, bola);
		}
		assertTrue(jugador1.getPlataforma().getClass().getSimpleName().equals("PlataformaPegajosa"));	
	}
}