package es.upm.dit.prog.practica5;

public class SelectorSateliteId implements SelectorSatelite{
	private String id;
	public SelectorSateliteId(String id) {
		this.id = id;
	}
	
	public boolean seleccionar(Satelite s) {
		return s!=null && s.getId().equals(this.id);
	}
	
}
