package es.upm.dit.adsw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Esta clase representa un buscador de partidas de ajedrez.
 * Contiene métodos para buscar partidas en una lista de objetos Partida según
 * distintos criterios.
 */
public class GestorTableros {

    private List<Tablero> tableros;

    public static void main(String[] args) throws Exception {
        GestorTableros gestor = new GestorTableros(LectorPartidas.leerPartidasZip("data/partidas_extendido.zip"));
    }

    /**
     * Crea un objeto GestorTableros con una lista de partidas.
     * @param partidas una lista de objetos Partida
     */
    public GestorTableros( List<Partida> partidas) {
        this.tableros = new ArrayList<>();
        for (Partida partida : partidas) {
            tableros.addAll(partida.turnos);
        }
        this.ordenarTableros();
    }

    /**
     * Devuelve el tablero con mayor puntuación general.
     * @return el tablero con mayor puntuación general
     */
//    public Tablero mayorTablero() {
//        Tablero mayor = tableros.get(0);
//        // TODO: implementar en el laboratorio 1
//        int puntuacionMax = mayor.getPuntuacionGeneral();
//        for(Tablero t: tableros) {
//        	if(t !=null && t.getPuntuacionGeneral() > puntuacionMax) {
//        		puntuacionMax = t.getPuntuacionGeneral();
//        		mayor = t;
//        	}
//        }
//        return mayor;
//    }
    public Tablero mayorTablero() {
    	return tableros.get(tableros.size()-1);
    }

    /**
     * Devuelve una lista con los tableros que tienen una puntuación igual o
     * superior a la puntuación mínima especificada.
     * @param puntuacion la puntuación mínima
     * @return una lista con los tableros que tienen una puntuación igual o
     * superior a la puntuación mínima especificada
     */
    
//    public List<Tablero> getTablerosConPuntuacionMinima(int puntuacion) {
//        List<Tablero> tablerosConPuntuacionMinima = new ArrayList<>();
//        // TODO: implementar en el laboratorio 1
//        for(Tablero t: tableros) {
//        	if(t != null && t.getPuntuacionGeneral() >= puntuacion)
//        		tablerosConPuntuacionMinima.add(t);
//        }
//        return tablerosConPuntuacionMinima;
//    }
   //Extra lab1 
//    public List<Tablero> getTablerosConPuntuacionMinima2(int puntuacionMin, int puntuacionMax) { 
//        List<Tablero> tablerosConPuntuacionMinima = new ArrayList<>();
//        
//        for(Tablero t: tableros) {
//        	if(t != null && t.getPuntuacionGeneral() >= puntuacionMin && t.getPuntuacionGeneral() <= puntuacionMax)
//        		tablerosConPuntuacionMinima.add(t);
//        }
//        return tablerosConPuntuacionMinima;
//    }
    //P1:
    public List<Tablero> getTablerosConPuntuacionMinima(int puntuacion){
    	int posicion = this.buscaPrimerTableroMayorIgual(puntuacion);
    	return tableros.subList(posicion, tableros.size());
    }
    //Búsqueda lineal
    private int buscaPrimerTableroMayorIgual (int puntuacion) {
    	for(int i = tableros.size()-1;i>=0;i--)
    		if(tableros.get(i).getPuntuacionGeneral()<puntuacion)
    			return i+1;
    	return 0;
    }
    //Búsqueda binaria
    private int buscaPrimerTableroMayorIgual (int puntuacion, int a, int z) {
    	int m = 0;
    	while(a<z) {
    		int m = (a+z)/2;
    				if(tableros.get(m).getPuntuacionGeneral() == puntuacion) {
    					while(m>= 0 && tableros.get(m).getPuntuacionGeneral() == puntuacion)
    						m--;
    					return m+1;
    				}
    				else if(tableros.get(m).getPuntuacionGeneral() < puntuacion)
    					a = m + 1;
    				else
    					z=m;		
    	}
    	return m;
    }
    
    public void ordenarTableros() {
    	ordenarTableros(this.tableros);
    }

	private void ordenarTableros(List<Tablero> list) {
		if(list.size()<2)
			return;
		int m = list.size()/2;
		List<Tablero> izq = new ArrayList<>(list.subList(0, m));
		List<Tablero> der = new ArrayList<>(list.subList(m, list.size()));
		ordenarTableros(izq);
		ordenarTableros(der);
		list.clear();
		while(izq.size() > 0 && der.size()>0) {
			Tablero ti = izq.get(0);
			Tablero td = der.get(0);
			if(ti.compareTo(td)<0)
				list.add(izq.remove(0));
			else
				list.add(der.remove(0));
		}
		list.addAll(izq);
		list.addAll(der);
	}
	public List<Tablero> getTableros(){
		return tableros;
	}
	
	public int getPuntuacionMediana() {
		System.out.println("Tableros: ");
		for(Tablero t: tableros)
			System.out.println( t.getPuntuacionGeneral());
		if(tableros.size() == 0)
			return 0;
		else if(tableros.size()%2==0) { //par
			return (tableros.get(tableros.size()/2-1).getPuntuacionGeneral() + tableros.get(tableros.size()/2).getPuntuacionGeneral())/2;		
		}
		else
			return tableros.get(tableros.size()/2).getPuntuacionGeneral();
	}
	
	
	
	
}
