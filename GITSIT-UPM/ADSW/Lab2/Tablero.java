package es.upm.dit.adsw;

/**
 * Esta clase representa un tablero de ajedrez. Contiene información sobre las
 * piezas y el estado del tablero.
 */
public class Tablero {

	private String representacion;
	private boolean mate;

	// TODO: en el laboratorio 1 se implementará la representación como array
	// bidimensional
	private Pieza[][] tablero;

	/**
	 * Crea un tablero de ajedrez con las piezas en su posición inicial.
	 * 
	 * @return un objeto Tablero con las piezas en su posición inicial
	 */
	public static Tablero tableroBasico() {
		return new Tablero("rnbqkbnrpppppppp................................PPPPPPPPRNBQKBNR");
	}

	/**
	 * Crea un tablero de ajedrez sin piezas.
	 */
	public Tablero() {
		tablero = new Pieza[8][8];
		representacion = "................................................................";
	}

	/**
	 * Crea un tablero de ajedrez con las piezas en la posición especificada.
	 * 
	 * @param tableroSerializado una cadena de 64 caracteres que representa la
	 *                           posición de las piezas en el tablero
	 */
	public Tablero(String tableroSerializado) {
		this.representacion = tableroSerializado;
		// TODO: se implementará en el laboratorio 1
		tablero = new Pieza[8][8];
		int k = 0;
		for(int i = 0; i<tablero.length; i++) {
			for(int j = 0; j<tablero[i].length; j++) {
				char charPieza = tableroSerializado.charAt(k++);
				if(charPieza != '.')
					tablero[i][j]= new Pieza(charPieza);
			}
		}
	}

	/**
	 * Devuelve true si el tablero está en mate.
	 * 
	 * @return true si el tablero está en mate
	 */
	public boolean getMate() {
		return mate;
	}

	/**
	 * Marca el tablero como mate o no mate.
	 * 
	 * @param mate true si el tablero está en mate
	 */
	public void setMate(boolean mate) {
		this.mate = mate;
	}

	/**
	 * Devuelve el array bidimensional que representa el tablero.
	 * @return el array bidimensional que representa el tablero
	 */
	public Pieza[][] getTablero() {
		return this.tablero;
	}

	/**
	 * Devuelve una cadena de texto que representa el tablero.
	 * 
	 * @return una cadena que representa el tablero
	 */
	public String toString() {
		return this.representacion;
	}

	/**
     * Calcula la puntuación del tableo para el bando especificado.
     * @param bando el bando para el que se calcula la puntuación
     * @return la puntuación del tablero para el bando especificado
     */
    public int getPuntuacionBando(Bando bando) {
		// TODO: se implementará en el laboratorio 1
		int puntuacion = 0;
		for(int i = 0; i<tablero.length; i++) {
			for(int j = 0; j<tablero[i].length;j++) {
				if(tablero[i][j] != null && tablero[i][j].getBando()==bando) {
					switch(tablero[i][j].getTipo() ) {
					case REY: puntuacion+=100; break;
					case REINA: puntuacion+=9; break;
					case TORRE: puntuacion+=5; break;
					case ALFIL: puntuacion+=3; break;
					case CABALLO: puntuacion+=3; break;
					case PEON: puntuacion+=1; break;
					}
				}
			}
		}
		return puntuacion;
	}

	/**
	 * Indica si el tablero es igual a otro objeto.
	 * 
	 * @param obj el objeto con el que se compara
	 * @return true si el tablero es igual al objeto especificado; false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Tablero) {
			Tablero other = (Tablero) obj;
			return this.representacion.equals(other.representacion);
		}
		return false;
	}
	
	public int compareTo(Tablero otro){
//		int puntuacionGeneralThis = this.getPuntuacionBando(Bando.BLANCAS)-otro.getPuntuacionBando(Bando.NEGRAS);
//		int puntuacionGeneralOtro = otro.getPuntuacionBando(Bando.NEGRAS) - this.getPuntuacionBando(Bando.BLANCAS);
//		return puntuacionGeneralThis-puntuacionGeneralOtro;
		return this.getPuntuacionGeneral()-otro.getPuntuacionGeneral();
	}
	
	//He decidido programar por comodidad de uso en métodos posteriores un método que calcule la puntuación general de un tablero
	/**
	 * Calcule la puntuación general de un tablero
	 * @return Puntuación General de un tablero
	 */
	public int getPuntuacionGeneral() {
		return this.getPuntuacionBando(Bando.BLANCAS)-this.getPuntuacionBando(Bando.NEGRAS);
	}
	
	
	
//	private int filaPrimerPeonBlanco() {
//		for(int i = 0; i < tablero.length;i++) {
//			for(int j = 0; j < tablero[i].length;j++)
//				if(tablero[i][j] != null && tablero[i][j].toChar()== 'p')
//					return i;
//			return tablero.length;
//		}
//	}
//	
//	public int compareTo(Tablero otro) {
//		if(this.getPuntuacionGeneral() - otro.getPuntuacionGeneral() != 0)
//			return this.getPuntuacionGeneral()-otro.getPuntuacionGeneral();
//		else
//			return otro.filaPrimerPeonBlanco() - this.filaPrimerPeonBlanco();
//	}
	
//	public boolean equals2(Object obj) {
//		if (obj == null) {
//			return false;
//		}
//		if (obj instanceof Tablero) {
//			Tablero other = (Tablero) obj;
//			for(int i =0; i< tablero.length;i++)
//				for(int j = 0; j < tablero[i].length;j++)
//					if(!tablero[i][j].equals(other.getTablero()[i][j]))
//						return false;
//			return true;
//		}
//		return false;
//	}
	
	
}
