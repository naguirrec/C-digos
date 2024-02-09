package es.upm.dit.prog.practica5;

import java.util.Objects;

class Deteccion {
	private Posicion pos;
	private long t;
	public Deteccion (Posicion pos, long t) {
		this.pos = pos;
		this.t = t;
	}
	public Posicion getPos() {
		return pos;
	}
	public void setP(Posicion pos) {
		this.pos = pos;
	}
	public long getT() {
		return t;
	}
	public void setT(long t) {
		this.t = t;
	}
	@Override
	public int hashCode() {
		return Objects.hash(pos, t);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deteccion other = (Deteccion) obj;
		return Objects.equals(pos, other.pos) && t == other.t;
	}
	@Override
	public String toString() {
		return "Deteccion [t=" + t + ", pos=" + pos + "]";
	}
	
}
