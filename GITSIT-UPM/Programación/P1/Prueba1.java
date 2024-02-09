package es.upm.dit.prog.practica1;

//import static org.junit.Assert.assertEquals;


public class Prueba1 {

	private double delta = 0.1;
	
	public boolean assertEquals (String msg, Object expected, Object obtained) {
		System.out.println (msg + " expected: " + expected.toString() + " obtained: " + obtained.toString());
		return expected.equals(obtained);
	}
	
	public boolean assertEquals (String msg, double expected, double obtained, double delta) {
		System.out.println (msg + " expected: " + expected + " obtained: " + obtained);
		return (Math.abs (expected - obtained) <= delta);
	}
	
	public boolean assertEquals (String msg, double expected, double obtained) {
		return assertEquals(msg, expected, obtained, delta);
	}
	
	public boolean assertTrue(String msg, boolean r) {
		System.out.println (msg + " expected true; obtained: " + r);
		return r;
	}
	
	public boolean assertFalse(String msg, boolean r) {
		System.out.println (msg + " expected false; obtained: " + r);
		return ! r;
	}
	
	public boolean assertArrayEquals(String msg, Satelite[] expected, Satelite[] obtained) {
		if (expected.length != obtained.length) {
			System.out.println(msg + " expected: " + expected.length + " obtained: " + obtained.length);
			return false;
		}
		//Arrays.sort(expected, (c1, c2) -> (int) (c1.getFini() - c2.getFini()));	
		//Arrays.sort(obtained, (c1, c2) -> (int) (c1.getFini() - c2.getFini()));
		for (int i = 0; i < expected.length; i++)
			if ((expected[i] != null) && (obtained[i] != null) && (! expected[i].equals(obtained[i]))) {
				System.out.println(msg + "[ " + i + "] expected: " + expected[i] + " obtained: " + obtained[i]);
				return false;
			}
		System.out.println(msg + " ok");
		return true;
	}
	
	
	//@BeforeClass
	public void checkDependenciesPosicion() {
		try {
			Posicion.class.getClassLoader().loadClass(Posicion.class.getCanonicalName());
			Posicion.class.getConstructor(Double.TYPE,Double.TYPE,Double.TYPE);
			// Method 
			Posicion.class.getMethod("getLatitud");
			Posicion.class.getMethod("getLongitud");
			Posicion.class.getMethod("getAltura");
			
			Posicion.class.getMethod("equals", Object.class);
			Posicion.class.getMethod("toString");

			Posicion.class.getMethod("getX");
			Posicion.class.getMethod("getY");
			Posicion.class.getMethod("getZ");
			
			// CUIDADO!!!
			Posicion.class.getMethod("distancia", Posicion.class);
		} catch(Throwable t) {
			t.printStackTrace(System.out);
			assertTrue("Posicion: faltan métodos.", false); 
		}
		assertTrue("Posicion: cabeceras de métodos correctas.", true);
	}
	
	//@BeforeClass
	public void checkDependenciesSatelite() {
		try {
			Satelite.class.getClassLoader().loadClass(Satelite.class.getCanonicalName());
			Satelite.class.getConstructor(String.class,
					Posicion.class,Long.TYPE,Posicion.class,
					Long.TYPE);
			Satelite.class.getMethod("equals", Object.class);
			Satelite.class.getMethod("toString");
			Satelite.class.getMethod("getId");
			Satelite.class.getMethod("getPos");
			Satelite.class.getMethod("getPos0");
			Satelite.class.getMethod("getT");
			Satelite.class.getMethod("getT0");
			
			Satelite.class.getMethod("setId", String.class);
			Satelite.class.getMethod("setPos", Posicion.class);
			Satelite.class.getMethod("setPos0", Posicion.class);
			Satelite.class.getMethod("setT", Long.TYPE);
			Satelite.class.getMethod("setT0", Long.TYPE);
		} catch(Throwable t) {
			t.printStackTrace(System.out);
			assertTrue("Satelite: faltan métodos.", false); 
		}
		assertTrue("Satelite: cabeceras de métodos correctas.", true);
	}

	
	//@Test (timeout=500)
	public void test1() {
		int i = 1;
		String t = "Test1-";
		assertEquals(t + i++ + ";new Posicion(0,0,0); getLatitud(); ", new Posicion(0,0,0).getLatitud(), 0, delta);
		assertEquals(t + i++ + ";new Posicion(2*Math.PI,0,0); getLatitud(); ", new Posicion(2*Math.PI,0,0).getLatitud(), 0, delta);
		assertEquals(t + i++ + ";new Posicion(-2*Math.PI,0,0); getLatitud(); ", new Posicion(-2*Math.PI,0,0).getLatitud(), 0, delta);
		assertEquals(t + i++ + ";new Posicion(0,0,0); getLongitud(); ", new Posicion(0,0,0).getLongitud(), 0, delta);
		assertEquals(t + i++ + ";new Posicion(0,2*Math.PI,0); getLongitud(); ", new Posicion(0,2*Math.PI,0).getLongitud(), 0, delta);
		assertEquals(t + i++ + ";new Posicion(0,-2*Math.PI,0); getLongitud(); ", new Posicion(0,-2*Math.PI,0).getLongitud(), 0, delta);
		assertEquals(t + i++ + ";new Posicion(0,0,0); getAltura(); ", new Posicion(0,0,0).getAltura(), 0, delta);
		assertEquals(t + i++ + ";new Posicion(0,2*Math.PI,100000); getAltura(); ", new Posicion(0,2*Math.PI,100000).getAltura(), 100000, delta);
		assertEquals(t + i++ + ";new Posicion(0,-2*Math.PI,1000000000); getAltura(); ", new Posicion(0,-2*Math.PI,1000000000).getAltura(), 1000000000, delta);
	}
	
	//@Test (timeout=500)
	public void test2() {
		int i = 1;
		String t = "Test2-";
		for (double lat = -Math.PI; lat <= Math.PI; lat += Math.PI) {
			for (double lng = -Math.PI; lng <= Math.PI; lng += Math.PI) {
				for (double h = 150000; h < 2000000; h +=1000000) {
					Posicion p = new Posicion (lat, lng, 1000*h);
					double x = 1000*h * Math.cos(lat)* Math.cos(lng);
					assertEquals(t + i++ + ";"+ p +".getX(); ", p.getX(), x);
				}
			}
		}
	}

	//@Test (timeout=500)
	public void test3() {
		int i = 1;
		String t = "Test3-";
		for (double lat = -Math.PI; lat <= Math.PI; lat += Math.PI) {
			for (double lng = -Math.PI; lng <= Math.PI; lng += Math.PI) {
				for (double h = 150000; h < 2000000; h +=1000000) {
					Posicion p = new Posicion (lat, lng, 1000*h);
					double y = 1000*h * Math.cos(lat) * Math.sin(lng);
					assertEquals(t + i++ + ";" +p +".getY(); ", p.getY(), y);
				}
			}
		}
	}

	//@Test (timeout=500)
	public void test4() {
		int i = 1;
		String t = "Test4-";
		for (double lat = -Math.PI; lat <= Math.PI; lat += Math.PI) {
			for (double lng = -Math.PI; lng <= Math.PI; lng += Math.PI) {
				for (double h = 150000; h < 2000000; h +=1000000) {
					Posicion p = new Posicion (lat, lng, 1000*h);
					double z = 1000*h * Math.sin(lat);
					assertEquals(t + i++ +";"+ p +".getZ(); ", p.getZ(), z);
				}
			}
		}
	}

	//@Test (timeout=500)
	public void test5() {
		Posicion [] p = new Posicion[100];
		int k = 0;
		String t = "Test5;";
		for (double lat = -Math.PI; lat <= Math.PI; lat += Math.PI) {
			for (double lng = -Math.PI; lng <= Math.PI; lng += Math.PI) {
				for (double h = 150; h < 2000; h +=1000) {
					if (k < p.length)
						p[k++] = new Posicion (lat, lng, 1000*h);
				}
			}
		}
		k = 0;
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p.length; j++) {
				if (p[i] != null && p[j] != null) {
					double dx = p[i].getX() - p[j].getX();
					double dy = p[i].getY() - p[j].getY();
					double dz = p[i].getZ() - p[j].getZ();
					double d = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + (Math.pow(dz, 2)));
					assertEquals(t + k++ + " "+  p[i] + " distancia " + p[j], p[i].distancia(p[j]), d);
				}
			}
		}


	}
	

	public static void main(String[] args) {
		Prueba1 p = new Prueba1();
		p.checkDependenciesPosicion();
		p.checkDependenciesSatelite();
		p.test1();
		p.test2();
		p.test3();
		p.test4();
		p.test5();
	}
}
