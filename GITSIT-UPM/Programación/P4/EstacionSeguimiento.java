package es.upm.dit.prog.practica4;

import java.util.Arrays;

public class EstacionSeguimiento {
	
	private final int N = 10;
	private String id;
	private int nuevosSatelites;
	private Satelite[] satelites;
	public EstacionSeguimiento(String id) {
		this.id = id;
		this.nuevosSatelites = 0;
		this.satelites = new Satelite[N];
	}
	
	public String getId() {
		return id;
	}
	
	public Satelite[] getSatelites() {
		return satelites;
	}
	
	public String toString() {
		return "EstacionSeguimiento [N=" + N + ", id=" + id + ", nuevosSatelites=" + nuevosSatelites + ", satelites="
				+ Arrays.toString(satelites) + "]";
	}
	
	public void addSatelite(Satelite s) {
		if(s!= null) {
			int i1=0;
			do{ 
				if(satelites[i1] == null) {
					satelites[i1]=s;
					return;
				}
				i1++;
			}while(i1<satelites.length);
			
			for(int i = 0; i<satelites.length-1;i++)
				satelites[i] = satelites[i+1];
			
			satelites[satelites.length-1]=s;
		}
	}
	
	public Satelite[] getSatelitesValidos(SelectorSatelite s) {
		
		if(s==null) {
			s = new SelectorSateliteTrue();
		}
		int SatelitesTotal = 0;
		for(int i = 0; i<satelites.length;i++) {
			if(satelites[i]!=null && s.seleccionar(satelites[i]))
				SatelitesTotal++;
		}
		
		Satelite[] SatelitesValidos;
			SatelitesValidos = new Satelite[SatelitesTotal];
		
		int k1 = 0;
		for(int i = 0; i<satelites.length;i++) {
			if(satelites[i]!=null && s.seleccionar(satelites[i]))
				SatelitesValidos[k1++] = satelites[i];
		}
		return SatelitesValidos;
	}
	
	public void actualizar(long t) {
		int i2 = 0;
		do {
			int i3 = 0;
			if(satelites[i2]!=null)
				satelites[i2].mueveHasta(t);
			i2++;
		} while(i2<satelites.length);
	}
	
	public void addDeteccion(Posicion pos, long t) {
		this.actualizar(t);
		Satelite[] satelitesCompatibles = this.getSatelitesValidos(new SelectorSateliteCompatible(pos));
		
		if(satelitesCompatibles.length != 0)
			satelitesCompatibles[0].detectadoEn(pos, t);
		else {
			Satelite sateliteNuevo = new Satelite("AUTO" +this.nuevosSatelites++,pos,t,pos,t);
			this.addSatelite(sateliteNuevo);
		}
	}
	
}
