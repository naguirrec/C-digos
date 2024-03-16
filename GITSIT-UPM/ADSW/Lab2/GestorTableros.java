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
    private List<Partida> partidas;//lab2
    
    /**
    * Método getter del atributo partidas para hacer pruebas   
    * @return partidas
    */
    public List<Partida> getPartidas() {//lab2
    	return partidas;
    }
        
    /**
    * Método getter del atributo tableros para hacer pruebas   
    * @return tableros
    */
    public List<Tablero> getTableros() {
    	return tableros;
    }

    public static void main(String[] args) throws Exception {
        GestorTableros gestor = new GestorTableros(LectorPartidas.leerPartidasZip("data/partidas_extendido.zip"));
    }

    /**
     * Crea un objeto GestorTableros con una lista de partidas.
     * @param partidas una lista de objetos Partida
     */
    public GestorTableros( List<Partida> partidas) {
    	this.partidas = partidas;//lab2
        this.tableros = new ArrayList<>();
        for (Partida partida : partidas) {
            tableros.addAll(partida.turnos);
        }
        this.ordenarTableros();//ordena la lista de tableros que acabamos de crear
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
    
    /*
     * Devuelve el tablero de mayor puntuación de una colección de tableros ordenados
     * @return el tablero de mayor puntuación de una colección de tableros ordenados
     */
    //Tableros se ordena justo después de crearse, el mayor será el último
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
//Lab1  int posicion = this.buscaPrimerTableroMayorIgual(puntuacion);
//P1    int posicion = this.buscaPrimerTableroMayorIgual(puntuacion,0,tableros.size());
    	int posicion = GestorTableros.busquedaBinaria(this.tableros, puntuacion);//lab2
    	return tableros.subList(posicion, tableros.size());
    }
    //El método sublist devuelve una "sublista" de una lista, pasando como parámetro un origen(posición) y un final(tableros.size)
    

//    //Búsqueda lineal
//    private int buscaPrimerTableroMayorIgual (int puntuacion) {
//    	for(int i = tableros.size()-1;i>=0;i--)
//    		if(tableros.get(i).getPuntuacionGeneral()<puntuacion)
//    			return i+1;
//    	return 0;
//    }
//    //Búsqueda binaria P1
//    private int buscaPrimerTableroMayorIgual (int puntuacion, int a, int z) {
//    	int m = 0;
//    	while(a<z) {
//    		m = (a+z)/2;
//    			if(tableros.get(m).getPuntuacionGeneral() == puntuacion) {
//    				while(m>= 0 && tableros.get(m).getPuntuacionGeneral() == puntuacion)
//    					m--;
//    				return m+1;
//   				}
//   				else if(tableros.get(m).getPuntuacionGeneral() < puntuacion)
//   					a = m + 1;
//   				else
//    				z=m;		
//    	}
//    	if(tableros.get(m).getPuntuacionGeneral() < puntuacion)
//    		m++;
//    	return m;
//    }
    
    
//Lab2:    
    /**
     * Devuelve el primer tablero de una lista ordenada por puntuaciones, que tiene una puntuación mayor
     * o igual a la que se pasa como parámetro, incluyendo posibles puntuaciones repetidas
     * @paran tablerosOrdenados lista de tableros ordenada por puntuación
     * @param puntuación del tablero que buscamos
     * @return el inidice de la lista del tablero buscado. Si no hay ninguún tablero de puntuación mayor
     * o igual que la buscada, devuelve el tamaño de la lista 
     */
    public static int busquedaBinaria(List<Tablero> tablerosOrdenados, int puntuacion) {
    	// TODO: implementar en el laboratorio 2
    	int a = 0;
    	int z = tablerosOrdenados.size();
    	int m = 0;
    	while(a<z) {
    		m = (a+z)/2;
    		if(tablerosOrdenados.get(m).getPuntuacionGeneral() == puntuacion) {
    			while(m>= 0 && tablerosOrdenados.get(m).getPuntuacionGeneral() == puntuacion)//Nos aseguramos de devolver
    				//el primer elemento (desde la izq) de la colección con puntuación == a la pasada como parámetro
    				m--;//Desplazas m a la izq hasta que encuentre un elemento != de puntuacion (parámetro)
    			return m+1;//devuelve la posición siguiente = 1er elemento >= a puntuacion (parámetro)
   			}
   			else if(tablerosOrdenados.get(m).getPuntuacionGeneral() < puntuacion)//Si se cumple sabemos que el parámetro es  
   				//> que la punt de la pos[m], por lo que estará a la dcha de este
   				a = m + 1;//desplazas el lim izq (a) a el centro (m) para acotar la búsqueda
   			else//parámetro < punt de pos[m] --> está a la izq de m 
    			z=m;//desplazas lim dch (z) para acotar		
    	}
    	//Dependiendo de si la última pos. es par o impar, puede fallar para la siguiente iteración, por eso hacemnos esto:
    	if(tablerosOrdenados.get(m).getPuntuacionGeneral() < puntuacion)
    		m++;
    	return m;//Si no se encuentra, se devuelve el primero que se acerca
    }

//Extra lab1
   /**
    * Recibe una PuntuacionMin y PuntuacionMax y devuelve una lista de tableros contenidos en ese rango
    * de puntuaciones
    * @param PuntuacionMin
    * @param PuntuacionMax
    * @return lista de tableros contenidos entre PuntuacionMin y PuntuacionMax
    */
    public List<Tablero> getTablerosConPuntuacionEntre(int PuntuacionMin, int PuntuacionMax) {
    	List<Tablero> tablerosConPuntuacion = new ArrayList<>();
    	for(Tablero t: tableros)
    		if(t != null && t.getPuntuacionGeneral() >= PuntuacionMin && t.getPuntuacionGeneral() <= PuntuacionMax )
    			tablerosConPuntuacion.add(t);
    	return tablerosConPuntuacion;
    }
 
    public void ordenarTableros() {//Método fachada para ordenar los tableros
    	ordenarTableros(this.tableros);//atributo de la clase
    }

	private void ordenarTableros(List<Tablero> list) {//MergeSort que recibe un list de tableros
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
	
	//Como tableros está ordenado, la mediana, estará en la mitad de la colección
	public int getPuntuacionMediana() {
//		System.out.println("Tableros: ");
//		for(Tablero t: tableros)
//			System.out.println( t.getPuntuacionGeneral());
		if(tableros.size() == 0)
			return 0;
		else if(tableros.size()%2==0) { //size par --> mediana = media de los 2 elementos centrales
			return (tableros.get(tableros.size()/2-1).getPuntuacionGeneral() + tableros.get(tableros.size()/2).getPuntuacionGeneral())/2;		
		}
		else//size impar --> mediana = elemento central
			return tableros.get(tableros.size()/2).getPuntuacionGeneral();
	}
	
//	Lab2 Partidas:
	/**
	 * Devuelve la partida que contiene cierte tablero
	 * @param t es el tablero del que queremos conocer la partida
	 * @return la partida en la que está incluida el tablero t, si no está incluido, devuelve null
	 */
	public Partida PartidaDeTablero(Tablero t) {
		for(Partida p: partidas)
			if(p.turnos.contains(t))//Accedemos a los turnos de p y si esos turnos de p contienen al tablero 
				return p;			//del turno t pasado como parámtro, devolvemos la partida p
		return null;
	}
	
	/**
	 * Devuelve la partidas del tablero con mayor puntuación general
	 * @return la partidas del tablero con mayor puntuación general
	 */	
	public Partida partidaMayorTablero() {
		Tablero mayor = this.mayorTablero();//invocamos a mayorTablero programado línea 72
		return this.PartidaDeTablero(mayor);
	}
	
	/**
	 * Devuelve una lista con las partidas que tienen tableros con puntuación igual o 
	 * superior a la especificada
	 * @param puntuacion la puntuación mínima
	 * @return partidasTablerosConPuntuacionMinima una lista con as partidas que tienen un tablero con  
	 * una putuación igual o superior a la puntuación mínima especificada
	 */
	public List<Partida> getPartidaTablerosConPuntuacionMinima(int puntuacion) {
		List<Partida> partidasTablerosConPuntuacionMinima = new ArrayList<Partida>();//lista que vamos a devolver
		List<Tablero> tablerosConPuntuacionMinima = this.getTablerosConPuntuacionMinima(puntuacion);//método programado línea 104
		for(Tablero t: tablerosConPuntuacionMinima)//recorremos la lista tCPMin creada en la línea anterior y vamos añadiendo los
			partidasTablerosConPuntuacionMinima.add(this.PartidaDeTablero(t));//elementos a la list pTCPMin que devolveremos
		return partidasTablerosConPuntuacionMinima;
	}
	
}
