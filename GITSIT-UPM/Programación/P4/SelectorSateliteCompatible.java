package es.upm.dit.prog.practica4;

public class SelectorSateliteCompatible implements SelectorSatelite{
	
	private Posicion p;
	public SelectorSateliteCompatible(Posicion p) {
		this.p = p;
	}
	
	public boolean seleccionar(Satelite s) {
		return s!=null && s.isCompatible(p);
	}
	
}
