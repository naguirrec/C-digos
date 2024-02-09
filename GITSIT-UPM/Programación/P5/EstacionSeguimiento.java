package es.upm.dit.prog.practica5;

import java.util.ArrayList;
import java.util.List;

public class EstacionSeguimiento {
	private String id;
	private int nuevosSatelites;
	private List<Satelite> satelites;
	public EstacionSeguimiento(String id) {
		this.id = id;
		this.satelites = new ArrayList<Satelite>();
		this.nuevosSatelites = 0;
	}
	@Override
	public String toString() {
		return "EstacionSeguimiento [id=" + id + ", nuevosSatelites=" + nuevosSatelites + ", satelites=" + satelites
				+ "]";
	}
	
	public List<Satelite> getSatelites() {
		return satelites;
	}
	
	public String getId() {
		return id;
	}
	
	public void addSatelite(Satelite s) {
		if(s!=null)
			this.satelites.add(s);
	}
	
	public void actualizar(long t) {	//recorre toda la lista invocando el método mueve hasta
//		int i = 0;
//		do {
//		
//			if(satelites.get(i) != null)
//				satelites.get(i).mueveHasta(t);
//			i++;
//		} while(i<satelites.size());
		
		for(Satelite i: satelites) {
			if(i!=null)
				i.mueveHasta(t);
		}
	}
	
	public List<Satelite> getSatelitesValidos(SelectorSatelite s){
		if(s==null)
			s = new SelectorSateliteTrue();
		
		List<Satelite> satelitesValidos = new ArrayList<Satelite>();
		
		for(Satelite i: satelites)
			if(s.seleccionar(i))
				satelitesValidos.add(i);
		
		return satelitesValidos;
	}
	
	public void addDeteccion(Posicion p, long t) {
		this.actualizar(t);
		List<Satelite> satelitesCompatibles = this.getSatelitesValidos(new SelectorSateliteCompatible(p));
		
		if(satelitesCompatibles.size() != 0)
			satelitesCompatibles.get(0).detectadoEn(p, t);
		else {
			Satelite sateliteNuevo = new Satelite("AUTO" +this.nuevosSatelites++,p,t,p,t);
			this.addSatelite(sateliteNuevo);
		}
	}
	
	
}
