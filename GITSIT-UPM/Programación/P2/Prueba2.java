package es.upm.dit.prog.practica2;

//import static org.junit.Assert.assertEquals;

public class Prueba2 {

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
			Posicion.class.getMethod("getLatitud");
			Posicion.class.getMethod("getLongitud");
			Posicion.class.getMethod("getAltura");
			
			Posicion.class.getMethod("equals", Object.class);
			Posicion.class.getMethod("toString");

			Posicion.class.getMethod("getX");
			Posicion.class.getMethod("getY");
			Posicion.class.getMethod("getZ");
			
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
			
			// practica2
			Satelite.class.getMethod("mueveHasta", Long.TYPE);
			Satelite.class.getMethod("detectadoEn", Posicion.class, Long.TYPE);
			
			Satelite.class.getMethod("isCompatible", Posicion.class);
			Satelite.class.getMethod("enPeligro", Satelite.class);

		} catch(Throwable t) {
			t.printStackTrace(System.out);
			assertTrue("Satelite: faltan métodos.", false); 
		}
		assertTrue("Satelite: cabeceras de métodos correctas.", true);
	}


	// detectadoEn t == s.t no cambia t
	// detectadoEn t > s.t se mueve directo
	//@Test (timeout=500)
	public void test1() {
		int i = 1;
		String t = "Test1-";
		Posicion p1 = new Posicion(0,Math.PI/2,10000);
		Posicion p0 = new Posicion(0,Math.PI/2,0);
		Satelite s = new Satelite("s1", p1, 10, p0, 0);
		
		// posicion null, no mueve
		s.detectadoEn(null, 20);
		System.out.println(t +i+" "+s);
		assertEquals(t+i+";Satelite.detectadoEn(null,0);getPos()", p1, s.getPos());
		assertEquals(t+i+";Satelite.detectadoEn(null,0);getT()", 10, s.getT());
		assertEquals(t+i+";Satelite.detectadoEn(null,0);getPos0()", p0, s.getPos0());
		assertEquals(t+i+";Satelite.detectadoEn(null,0);getT0()", 0, s.getT0());	
		
		// detectado en t = s.t mueve pero no cambia t
		i++;
		Posicion p2 = new Posicion(0,0,20000);
		s.detectadoEn(p2, 10);
		assertEquals(t+i+";Satelite.detectadoEn("+p2+",10);getPos()", p2, s.getPos());
		assertEquals(t+i+";Satelite.detectadoEn("+p2+",10);getT()", 10, s.getT());
		assertEquals(t+i+";Satelite.detectadoEn("+p2+",10);getPos0()", p0, s.getPos0());
		assertEquals(t+i+";Satelite.detectadoEn("+p2+",10);getT0()", 0, s.getT0());
	}

	// detectadoEn t == s.t no cambia t
	// detectadoEn t > s.t se mueve directo
	//@Test (timeout=500)
	public void test2() {
		int i = 1;
		String t = "Test1-";
		Posicion p1 = new Posicion(0,Math.PI/2,10000);
		Posicion p0 = new Posicion(0,Math.PI/2,0);
		Satelite s = new Satelite("s1", p1, 10, p0, 0);
		
		// detectado en t = s.t mueve pero no cambia t
		i++;
		Posicion p2 = new Posicion(0,0,20000);
		s.detectadoEn(p2, 10);
		assertEquals(t+i+";Satelite.detectadoEn("+p2+",10);getPos()", p2, s.getPos());
		assertEquals(t+i+";Satelite.detectadoEn("+p2+",10);getT()", 10, s.getT());
		assertEquals(t+i+";Satelite.detectadoEn("+p2+",10);getPos0()", p0, s.getPos0());
		assertEquals(t+i+";Satelite.detectadoEn("+p2+",10);getT0()", 0, s.getT0());
		
		// detectado en t > s.t mueve y cambia t
		i++;
		Posicion p3 = new Posicion(0,0,30000);
		s.detectadoEn(p3, 20);
		assertEquals(t+i+";Satelite.detectadoEn("+p3+",20);getPos()", p3, s.getPos());
		assertEquals(t+i+";Satelite.detectadoEn("+p3+",20);getT()", 20, s.getT());
		assertEquals(t+i+";Satelite.detectadoEn("+p3+",20);getPos0()", p2, s.getPos0());
		assertEquals(t+i+";Satelite.detectadoEn("+p3+",20);getT0()", 10, s.getT0());
	}

	
	// mueveHasta, detectadoEn
	// mueveHasta t == s.t no mueve
	// mueveHasta t > s.t, s.t == s.t0: se mueve de pos a pos
	// mueveHasta t > s.t, vel > 0: mueve
	//@Test (timeout=500)
	public void test3() {
		int i = 1;
		String t = "Test2-";
		
		Posicion p0 = new Posicion(0,0,    0);
		Posicion p1 = new Posicion(0,Math.PI/2,10000);
		Satelite s = new Satelite("s1", p1, 10, p0, 0);
		
		// mueveHasta t == s.t no mueve
		System.out.println(t+i+";"+s);
		s.mueveHasta(10); 
		assertEquals(t+i+";Satelite.mueveHasta(10);getPos()", p1, s.getPos());
		assertEquals(t+i+";Satelite.mueveHasta(10);getPos0()", p0, s.getPos0());
		assertEquals(t+i+";Satelite.mueveHasta(10);getT()", 10, s.getT());
		assertEquals(t+i+";Satelite.mueveHasta(10);getT0()", 0, s.getT0());
		
		i++;
		// mueveHasta t > s.t, s.t == s.t0 se mueve de pos a pos
		s = new Satelite("s1", p1, 0, p0, 0);
		System.out.println(t+i+";"+s);
		s.mueveHasta(10); 
		assertEquals(t+i+";Satelite.mueveHasta(10);getPos()", p1, s.getPos());
		assertEquals(t+i+";Satelite.mueveHasta(10);getPos0()", p1, s.getPos0());
		assertEquals(t+i+";Satelite.mueveHasta(10);getT()", 10, s.getT());
		assertEquals(t+i+";Satelite.mueveHasta(10);getT0()", 0, s.getT0());
	}	

	// mueveHasta, detectadoEn
	// mueveHasta t == s.t no mueve
	// mueveHasta t > s.t, s.t == s.t0: se mueve de pos a pos
	// mueveHasta t > s.t, vel > 0: mueve
	//@Test (timeout=500)
	public void test4() {
		int i = 1;
		String t = "Test2-";
		
		Posicion p0 = new Posicion(0,0,    0);
		Posicion p1 = new Posicion(0,Math.PI/2,10000);
		Satelite s = new Satelite("s1", p1, 10, p0, 0);
		
		// mueveHasta t > s.t, vel > 0: mueve		
		s = new Satelite("s1", p1, 10, p0, 0);
		System.out.println(t+i+";"+s);
		Posicion p2 = new Posicion(0,2*Math.PI/2,20000);
		s.mueveHasta(20);
		assertEquals(t+i+";Satelite.mueveHasta(20);getPos()", p2, s.getPos());
		assertEquals(t+i+";Satelite.mueveHasta(20);getPos0();", p1, s.getPos0());
		assertEquals(t+i+";Satelite.mueveHasta(20);getT();", 20, s.getT());		
		assertEquals(t+i+";Satelite.mueveHasta(20);getT0();", 10, s.getT0());		
	}	

	// isCompatible
	//@Test (timeout=500)
	public void test5() { 
		int i = 1;
		String t = "Test3-";
		
		Posicion p0 = new Posicion(0,0,    0);
		Posicion p1 = new Posicion(0,Math.PI/2,10000);
		Satelite s = new Satelite("s1", p1, 0, p0, 0);

		// isCompatible null
		System.out.println(t+i+";"+s);
		assertFalse(t+i+";Satelite.isCompatible(null)", s.isCompatible(null));
	
		i++;
		// isCompatible pos1 SI la misma posicion
		assertTrue(t+i+";Satelite.isCompatible("+ p1 +")", s.isCompatible(p1));
		
		i++;
		// isCompatible pos2 SI posicion cercana
		Posicion p2 = new Posicion(p1.getLatitud(), p1.getLongitud(), p1.getAltura()+ 2);
		assertTrue(t+i+";Satelite.isCompatible("+ p2 +")", s.isCompatible(p2));
		
		i++;
		// isCompatible pos2 NO posicion lejana
		Posicion p3 = new Posicion(p1.getLatitud(), p1.getLongitud(), p1.getAltura()+ 25);
		assertFalse(t+i+";Satelite.isCompatible("+ p3 +")", s.isCompatible(p3));
	}
	
	// enPeligro, isCompatible, mueveHasta
	//@Test (timeout=500)
	public void test6() {
		int i = 1;
		String t = "Test4-";
		
		Posicion p0 = new Posicion(0,0,    0);
		Posicion p1 = new Posicion(0,Math.PI/2,10000);
		Satelite s1 = new Satelite("s1", p1, 10, p0, 0);

		// enPeligro null
		System.out.println(t+i+";"+s1);
		assertFalse(t+i+";Satelite.enPeligro(null)", s1.enPeligro(null));
		
		i++;
		// enPeligro s1 ahora cercanos
		Posicion p2 = new Posicion(0,Math.PI/2, 10003);
		Satelite s2 = new Satelite("s2", p2, 10, p0, 0);
		System.out.println(t+i+";"+s2);
		assertTrue(t+i+";s1.enPeligro(s2)", s1.enPeligro(s2));

		i++;
		// enPeligro s1 ahora lejanos
		p2 = new Posicion(Math.PI/2, Math.PI/2, 10000);
		s2 = new Satelite("s2", p2, 10, p0, 0);
		System.out.println(t+i+";"+s2);
		assertFalse(t+i+";s1.enPeligro(s2)", s1.enPeligro(s2));
		
		i++;
		// enPeligro s1 luego alejándose
		p0 = new Posicion (0,0,100000);
		p1 = new Posicion (0,Math.PI/8,100000);
		p2 = new Posicion (0,-Math.PI/8,100000);
		s1 = new Satelite ("s1", p1, 100, p0, 0);
		s2 = new Satelite ("s2", p2, 100, p0, 0);
		System.out.println(t+i+";"+s1);
		System.out.println(t+i+";"+s2);
		assertFalse(t+i+";s1.enPeligro(s2)", s1.enPeligro(s2));
		
		i++;
		// enPeligro s1 luego acercándose
		//Posicion p10 = new Posicion (0,Math.PI/8,100000);
		//Posicion p20 = new Posicion (0,-Math.PI/8,100000);
		Posicion p10 = new Posicion (0,0,100000-200);
		Posicion p20 = new Posicion (0,0,100000+200);
		Posicion p11 = new Posicion (0,0,100000-100);
		Posicion p21 = new Posicion (0,0,100000+100);

		s1 = new Satelite ("s1", p11, 100, p10, 0);
		s2 = new Satelite ("s2", p21, 100, p20, 0);
		System.out.println(t+i+";"+s1);
		System.out.println(t+i+";"+s2);
		assertTrue(t+i+";s1.enPeligro(s2)", s1.enPeligro(s2));
	}

	


	public static void main(String[] args) {
		Prueba2 p = new Prueba2();
		p.checkDependenciesPosicion();
		p.checkDependenciesSatelite();

		p.test1();
		p.test2();
		p.test3();
		p.test4();
		p.test5();
		p.test6();
	}
}
