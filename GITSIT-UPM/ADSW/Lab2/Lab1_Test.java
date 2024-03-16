package es.upm.dit.adsw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class Lab1_Test {

	@Test
	void testTableroVacio() {
		Tablero t = new Tablero();
		Pieza[][] piezasTablero = t.getTablero();
		for(int i = 0; i< piezasTablero.length; i++)
			for(int j = 0; j<piezasTablero[i].length; j++)
				assertNull(piezasTablero[i][j]);		
	}
	@Test
	void testBuscarTableros() throws Exception{
		GestorTableros gestor = new GestorTableros(LectorPartidas.leerPartidas("data/partidas.txt"));
		Tablero mayor = gestor.mayorTablero();
		assertEquals(42,mayor.getPuntuacionGeneral());
		
		List<Tablero> mayores = gestor.getTablerosConPuntuacionMinima(25);
		assertEquals(501,mayores.size());
	}
}
