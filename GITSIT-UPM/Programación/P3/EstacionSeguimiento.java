package es.upm.dit.prog.practica3;

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
			for(int i = 0; i<satelites.length; i++) {
				if(satelites[i] == null) {
					satelites[i]=s;
					return;
				}
			}
			
			for(int i = 0; i<satelites.length-1;i++)
				satelites[i] = satelites[i+1];
			
			satelites[satelites.length-1]=s;
		}
	}
	
	public Satelite[] getSatelitesValidos() {
		int SatelitesTotal = 0;
		for(Satelite S: satelites) {
			if(S!=null)
				SatelitesTotal++;
		}
		
		Satelite[] SatelitesValidos;
			SatelitesValidos = new Satelite[SatelitesTotal];
		
		int k1 = 0;
		for(Satelite S1: satelites) {
			if(S1 != null)
				SatelitesValidos[k1++] = S1;
		}
		
		return SatelitesValidos;
	}
	
	public Satelite[] getSatelitesCompatibles(Posicion pos) {
		int SatelitesTotal_2 = 0;
		for(int i = 0; i<satelites.length; i++) {
			if(satelites[i]!=null && satelites[i].isCompatible(pos))
				SatelitesTotal_2++;
		}
		
		Satelite[] satelitesCompatibles; 
			satelitesCompatibles= new Satelite[SatelitesTotal_2];
		
		int k2 = 0;
		for(int i = 0; i<satelites.length;i++) {
			if(satelites[i]!=null && satelites[i].isCompatible(pos))
				satelitesCompatibles[k2++]=satelites[i];
		}	
		
		return satelitesCompatibles;
			
	}
	
	public void actualizar(long t) {
		int i = 0;
		do {
			int i1 = 0;
			if(satelites[i]!=null)
				satelites[i].mueveHasta(t);
			i++;
		} while(i<satelites.length);
	}
	
	public void addDeteccion(Posicion pos, long t) {
		this.actualizar(t);
		Satelite[] satelitesCompatibles = this.getSatelitesCompatibles(pos);
		
		if(satelitesCompatibles.length != 0)
			satelitesCompatibles[0].detectadoEn(pos, t);
		else {
			Satelite sateliteNuevo = new Satelite("AUTO" +this.nuevosSatelites++,pos,t,pos,t);
			this.addSatelite(sateliteNuevo);
		}
		
	}
}
