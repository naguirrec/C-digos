package es.upm.dit.prog.practica4;

public class SelectorSateliteY implements SelectorSatelite{
	private SelectorSatelite s1;
	private SelectorSatelite s2;
	public SelectorSateliteY(SelectorSatelite s1, SelectorSatelite s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	public boolean seleccionar(Satelite s) {
		
		return s!= null && s1!=null && s2!=null && s1.seleccionar(s) && s2.seleccionar(s);
	}
	
}
