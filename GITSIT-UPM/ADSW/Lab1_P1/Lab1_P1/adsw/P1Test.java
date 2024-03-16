package es.upm.dit.adsw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class P1Test {

	@Test
	void testOrdenarTableros() throws Exception {
		GestorTableros gestor = new GestorTableros(LectorPartidas.leerPartidas("data/partidas.txt"));
		
		System.out.println("Ya he cargado el archivo y se ha ordenado");
		List<Tablero> tableros = gestor.getTableros();
		System.out.println("Hay: " + tableros.size() + " tableros");
		
		for(int i = 0; i < tableros.size()-1 ;i++)
			assertTrue(tableros.get(i).compareTo(tableros.get(i+1))<=0);
	}
	
	@Test
	void testMedianaCero() throws Exception{
		GestorTableros gestor = new GestorTableros(LectorPartidas.leerPartidas("data/partidas.txt"));
		assertEquals(0,gestor.getPuntuacionMediana());
	}

}
