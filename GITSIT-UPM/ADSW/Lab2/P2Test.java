package es.upm.dit.adsw;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class P2Test {

	@Test
	void testBusquedaBinaria()throws Exception {
		GestorTableros gestor = new GestorTableros(LectorPartidas.leerPartidas("data/partidas.txt"));
		List<Tablero> tableros = gestor.getTableros();
		Tablero mayor = gestor.mayorTablero();
		int puntuacion = mayor.getPuntuacionGeneral();
		assertEquals(tableros.size()-3, GestorTableros.busquedaBinaria(tableros, puntuacion));
	}
	
	@Test 
	void testPartidas() throws Exception {
		GestorTableros gestor = new GestorTableros(LectorPartidas.leerPartidas("data/partidas.txt"));
		List<Partida> partidas = gestor.getPartidas();
		Partida primeraPartida = partidas.get(0);
		Tablero primerTurno = primeraPartida.turnos.get(0);
		assertEquals(primeraPartida,gestor.PartidaDeTablero(primerTurno));
	}
	
	@Test
	void testTableroNoExiste() throws Exception {
		GestorTableros gestor = new GestorTableros(LectorPartidas.leerPartidas("data/partidas.txt"));
		Tablero t = new Tablero("................................................................");
		assertNull(gestor.PartidaDeTablero(t));
	}

}
