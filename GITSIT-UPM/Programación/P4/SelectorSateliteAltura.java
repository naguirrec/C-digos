package es.upm.dit.prog.practica4;

public class SelectorSateliteAltura implements SelectorSatelite{
	
	private double amin;
	private double amax;
	
	public SelectorSateliteAltura(double amin, double amax) {
		this.amin = amin;
		this.amax = amax;
	}
	
	public boolean seleccionar(Satelite s) {
		if(s==null) {
			return false;
		}
		double posicion = s.getPos().getAltura();
		return s!=null && posicion >= this.amin && posicion < this.amax;	
	}
	
}
