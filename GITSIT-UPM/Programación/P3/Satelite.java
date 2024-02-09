package es.upm.dit.prog.practica3;

import java.util.Objects;

public class Satelite {

	
	private String id;
	private Posicion pos;
	private long t;
	private Posicion pos0;
	private long t0;
	private final static double ERROR_POS = 5;
	private final static long SAFETY_TIME = 100;
	
	
	public Satelite(String id, Posicion pos, long t, Posicion pos0, long t0) {
		this.id = id;
		if (pos == null) {
			this.pos0 = new Posicion (0.0,0.0,0.0);
		}
		else {
			this.pos = pos;
		}
		this.t = t;
		if (pos0 == null) {
			this.pos0 = new Posicion (0.0,0.0,0.0);
		}
		else {
			this.pos0 = pos0;
		}
		this.t0 = t0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Posicion getPos() {
		return pos;
	}

	public void setPos(Posicion pos) {
		if(pos == null) {
			this.pos = new Posicion (0.0,0.0,0.0);
		}
		else {
			this.pos = pos;
		}
	}

	public long getT() {
		return t;
	}

	public void setT(long t) {
		this.t = t;
	}

	public Posicion getPos0() {
		return pos0;
	}

	public void setPos0(Posicion pos0) {
		if (pos0 == null) {
			this.pos0 = new Posicion (0.0,0.0,0.0);
		}
		else {
			this.pos0 = pos0;
		}
	}

	public long getT0() {
		return t0;
	}

	public void setT0(long t0) {
		this.t0 = t0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Satelite other = (Satelite) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Satelite [id=" + id + ", pos=" + pos + ", t=" + t + ", pos0=" + pos0 + ", t0=" + t0 + "]";
	}

	
	public void detectadoEn(Posicion pos, long t) {
		if(pos != null) {
			if(t==this.t) {
				this.pos=pos;
			}
			else {
				this.pos0 = this.pos;
				this.t0 = this.t;
				this.pos = pos;
				this.t = t;
			}
		}
		
	}
	
	public void mueveHasta (long t) {
		if(t!=this.t) {
			Posicion nuevaPos = null;
			if(this.t==this.t0) {
				nuevaPos = new Posicion(this.pos.getLatitud(),this.pos.getLongitud(),this.pos.getAltura());
			}
			else {
				long dT = this.t-this.t0;
				
				double dLat = this.pos.getLatitud() - this.pos0.getLatitud();
				double dLong = this.pos.getLongitud()-this.pos0.getLongitud();
				double dAlt = this.pos.getAltura() - this.pos0.getAltura();
				
				double vLat = dLat/dT;
				double vLong = dLong/dT;
				double vAlt = dAlt/dT;
				
				long dTMovimiento = t - this.t;
				double nLat = this.pos.getLatitud() + vLat* dTMovimiento;
				double nLong = this.pos.getLongitud() + vLong* dTMovimiento;
				double nAlt = this.pos.getAltura() + vAlt* dTMovimiento;
				
				nuevaPos = new Posicion (nLat,nLong, nAlt);
			}
			this.detectadoEn(nuevaPos, t);
		}
	}
	
	
	public boolean isCompatible (Posicion p) {
		if(p==null) 
			return false;
		else 
			return this.pos.distancia(p) < ERROR_POS; 
	}
	
	public boolean enPeligro(Satelite otro) {
		if(otro == null)
			return false;
		else if(this.isCompatible(otro.getPos()))
			return true;
		else {
			Satelite otroCopia = new Satelite (otro.getId(),otro.getPos(),otro.getT(),otro.getPos0(), otro.getT0());
			otroCopia.mueveHasta(otro.getT() + SAFETY_TIME);
			
			Satelite thisCopia = new Satelite(this.id,this.pos,this.t,this.pos0,this.t0);
			thisCopia.mueveHasta(this.getT() + SAFETY_TIME);
			
			return otroCopia.isCompatible(thisCopia.getPos());
		}
	}

}


