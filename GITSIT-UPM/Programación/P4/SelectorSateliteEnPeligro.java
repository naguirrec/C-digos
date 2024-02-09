package es.upm.dit.prog.practica4;

public class SelectorSateliteEnPeligro implements SelectorSatelite{
	
	private Satelite [] satelites;
	public SelectorSateliteEnPeligro (Satelite[] satelites) {
		this.satelites = satelites;
	}
	
	public boolean seleccionar(Satelite s) {
		if(s!=null && satelites!=null ) {
			for(Satelite i: satelites) {
				if(s.enPeligro(i))
					return true;
			}
		}
		return false;
	}
	
}
