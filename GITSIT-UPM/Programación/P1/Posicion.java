package es.upm.dit.prog.practica1;

import java.util.Objects;

public class Posicion {
	private double latitud;
	private double longitud;
	private double altura;
	
	public Posicion(double latitud, double longitud, double altura) {
		this.latitud = latitud;
		this.longitud = longitud;
		this.altura = altura;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	@Override
	public String toString() {
		return "Posición [latitud=" + latitud + ", longitud=" + longitud + ", altura=" + altura + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(altura, latitud, longitud);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		return Double.doubleToLongBits(altura) == Double.doubleToLongBits(other.altura)
				&& Double.doubleToLongBits(latitud) == Double.doubleToLongBits(other.latitud)
				&& Double.doubleToLongBits(longitud) == Double.doubleToLongBits(other.longitud);
	}
	
	public double getX() {
		return altura * Math.cos(latitud) * Math.cos(longitud);
	}
	public double getY() {
		return altura * Math.cos(latitud) * Math.sin(longitud);
	}
	public double getZ() {
		return altura * Math.sin(latitud);	
	}
	
	
	public double distancia(Posicion otra) {
		if (otra == null) {
			return 0.0;
		}
		else {
			double dx = this.getX() - otra.getX();
			double dy = this.getY()- otra.getY();
			double dz = this.getZ() -otra.getZ();
			return Math.sqrt(dx*dx + dy*dy + dz*dz);
		}
	}
}
