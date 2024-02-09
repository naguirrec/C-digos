package es.upm.dit.prog.practica4;

public class Prueba4 {

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

	//@BeforeClass
	public void checkDependenciesEstacionSeguimiento() {
		try {
			EstacionSeguimiento.class.getClassLoader().loadClass(Satelite.class.getCanonicalName());
			EstacionSeguimiento.class.getConstructor(String.class);
			
			EstacionSeguimiento.class.getMethod("toString");
			EstacionSeguimiento.class.getMethod("getId");
			EstacionSeguimiento.class.getMethod("getSatelites");
			EstacionSeguimiento.class.getMethod("addSatelite", Satelite.class);
			EstacionSeguimiento.class.getMethod("addDeteccion", Posicion.class, Long.TYPE);
			EstacionSeguimiento.class.getMethod("actualizar", Long.TYPE);
			EstacionSeguimiento.class.getMethod("getSatelitesValidos", SelectorSatelite.class);

		} catch(Throwable t) {
			t.printStackTrace(System.out);
			assertTrue("EstacionSeguimiento: faltan métodos.", false); 
		}
		assertTrue("EstacionSeguimiento: cabeceras de métodos correctas.", true);
	}

	//@BeforeClass
	public void checkDependenciesSelectorSatelite() {
		try {
			SelectorSateliteTrue.class.getClassLoader().loadClass(SelectorSateliteTrue.class.getCanonicalName());
			SelectorSateliteTrue.class.getMethod("seleccionar", Satelite.class);

			SelectorSateliteId.class.getClassLoader().loadClass(SelectorSateliteId.class.getCanonicalName());
			SelectorSateliteId.class.getConstructor(String.class);
			SelectorSateliteId.class.getMethod("seleccionar", Satelite.class);

			SelectorSateliteY.class.getClassLoader().loadClass(SelectorSateliteY.class.getCanonicalName());
			SelectorSateliteY.class.getConstructor(SelectorSatelite.class, SelectorSatelite.class);
			SelectorSateliteY.class.getMethod("seleccionar", Satelite.class);

			SelectorSateliteAltura.class.getClassLoader().loadClass(SelectorSateliteAltura.class.getCanonicalName());
			SelectorSateliteAltura.class.getConstructor(Double.TYPE, Double.TYPE);
			SelectorSateliteAltura.class.getMethod("seleccionar", Satelite.class);

			SelectorSateliteCompatible.class.getClassLoader().loadClass(SelectorSateliteCompatible.class.getCanonicalName());
			SelectorSateliteCompatible.class.getConstructor(Posicion.class);
			SelectorSateliteCompatible.class.getMethod("seleccionar", Satelite.class);


			SelectorSateliteEnPeligro.class.getClassLoader().loadClass(SelectorSateliteEnPeligro.class.getCanonicalName());
			SelectorSateliteEnPeligro.class.getConstructor(Satelite[].class);
			SelectorSateliteEnPeligro.class.getMethod("seleccionar",Satelite.class);
		} catch(Throwable t) {
			t.printStackTrace(System.out);
			assertTrue("Errores en alguno de los SelectorTransportes.", false); 
		}
		assertTrue("SelectorTransportes, métodos correctos.", true);
	}
	
	// SelectorSateliteTrue
	//@Test (timeout=500)
	public void test1() {
		int i = 1;
		String t = "Test1-";	
		SelectorSatelite s = new SelectorSateliteTrue();
		// not null
		assertTrue(t+i+";selectorTrue;seleccionar(s)", s.seleccionar(new Satelite ("s1", null, 0, null, 0)));
		i++;
		// null
		assertFalse(t+i+";selectorTrue;seleccionar(null)", s.seleccionar(null));
		i++;
	}
	
	// SelectorSateliteId
	//@Test (timeout=500)
	public void test2() {
		int i = 1;
		String t = "Test2-";	
		SelectorSatelite s = new SelectorSateliteId("s1");
		// not null true
		assertTrue(t+i+";selectorId(s1);seleccionar(s1)", s.seleccionar(new Satelite ("s1", null, 0, null, 0)));
		i++;
		// not null false
		assertFalse(t+i+";selectorTrue;seleccionar(s2)", s.seleccionar(new Satelite ("s2", null, 0, null, 0)));
		i++;
		// null
		assertFalse(t+i+";selectorTrue;seleccionar(null)", s.seleccionar(null));
		i++;

	}

	// SelectorSateliteAltura
	//@Test (timeout=500)
	public void test3() {
		int i = 1;
		String t = "Test3-";	
		SelectorSatelite s = new SelectorSateliteAltura(10000, 20000);
		// not null true
		assertTrue(t+i+";selectorAltura(10000,20000);seleccionar(s1(15000))", s.seleccionar(new Satelite ("s1", new Posicion (0,0,15000), 0, null, 0)));
		i++;
		// not null false
		assertFalse(t+i+";selectorAltura(10000,20000);seleccionar(s1(150000))", s.seleccionar(new Satelite ("s1", new Posicion (0,0,150000), 0, null, 0)));
		i++;
		// null
		assertFalse(t+i+";selectorAltura(10000,20000);seleccionar(null)", s.seleccionar(null));
		i++;
	}

	// SelectorSateliteCompatible
	//@Test (timeout=500)
	public void test4() {
		int i = 1;
		String t = "Test4-";	
		SelectorSatelite s = new SelectorSateliteCompatible(new Posicion (0, Math.PI/2, 10000));
		// not null true
		assertTrue(t+i+";selectorCompatible(Pos(0,Math.PI/2, 10000));seleccionar(s1(Pos(0,Math.PI/2, 10002)))", s.seleccionar(new Satelite ("s1", new Posicion (0,Math.PI/2, 10002), 0, null, 0)));
		i++;
		// not null false
		assertFalse(t+i+";selectorCompatible(Pos(0,Math.PI/2, 10000));seleccionar(s1(Pos(0,2*Math.PI/2, 10002)))", s.seleccionar(new Satelite ("s1", new Posicion (0,2*Math.PI/2, 10002), 0, null, 0)));
		i++;
		// null
		assertFalse(t+i+";selectorCompatible(Pos(0,Math.PI/2, 10000));seleccionar(null)", s.seleccionar(null));
		i++;
	}

	// SelectorSateliteY null, no null; id y true; true y id; 
	public void test5() {
		int i = 1;
		String t = "Test5-";	
		SelectorSatelite s1 = new SelectorSateliteTrue();
		SelectorSatelite s2 = new SelectorSateliteId("s1");
		SelectorSatelite s = new SelectorSateliteY(s1, s2);
		// not null true and true
		assertTrue(t+i+";selectorY(selectorId(s1),selectorTrue);seleccionar(s1)", s.seleccionar(new Satelite ("s1", new Posicion (0,Math.PI/2, 10002), 0, null, 0)));
		i++;
		// not null false and true
		assertFalse(t+i+";selectorY(selectorId(s1),selectorTrue);seleccionar(s1)", s.seleccionar(new Satelite ("s2", new Posicion (0,Math.PI/2, 10002), 0, null, 0)));
		i++;
		// null and true
		s = new SelectorSateliteY (s2, null);
		assertFalse(t+i+";selectorY(selectorId(s1),null);seleccionar(s1)", s.seleccionar(new Satelite ("s1", new Posicion (0,Math.PI/2, 10002), 0, null, 0)));
		i++;
		// null and true
		s = new SelectorSateliteY (null, s1);
		assertFalse(t+i+";selectorY(null,true);seleccionar(s1)", s.seleccionar(new Satelite ("s1", new Posicion (0,Math.PI/2, 10002), 0, null, 0)));
		i++;
	}
	
	// SelectorSateliteEnPeligro not null true, not null false, null
	public void test6() {
		int i = 1;
		String t = "Test6-";

		Satelite[] ss = new Satelite[] { new Satelite("ss1", new Posicion (0,Math.PI/2, 10002), 100, new Posicion (0,Math.PI/2, 10002), 0), 
										 new Satelite("ss2", new Posicion (0,0, 10002), 100, new Posicion (0,0, 10002), 0)};
		SelectorSatelite s = new SelectorSateliteEnPeligro(ss);
		// not null true
		assertTrue(t+i+";selectorEnPeligro();seleccionar(enpeligro)", s.seleccionar(new Satelite ("s1", new Posicion (0,Math.PI/2, 10002), 0, null, 0)));
		i++;
		// not null false
		assertFalse(t+i+";selectorEnPeligro();seleccionar(no enpeligro)", s.seleccionar(new Satelite ("s1", new Posicion (0,2*Math.PI/2, 10000), 0, null, 0)));
		i++;
		// null
		assertFalse(t+i+";selectorEnPeligro();seleccionar(null)", s.seleccionar(null));
		i++;
		// null
		s = new SelectorSateliteEnPeligro(null);
		assertFalse(t+i+";selectorEnPeligro(null);seleccionar(s1)", s.seleccionar(new Satelite("s1", new Posicion(10,10,10), 10, new Posicion(0,0,0), 0)));
		i++;

	}

	// getSatelitesValidos(SelectorSatelite s): null-vacio-lleno, True-vacío-lleno, Id-vacío-lleno
	//@Test (timeout=500)
	public void test7() {
		int i = 1;
		String t = "Test7-";	

		// getSatelitesValidos vacío
		EstacionSeguimiento e = new EstacionSeguimiento("e1");
		Satelite[] ss = e.getSatelitesValidos(null);
		assertArrayEquals(t+i+";new EstacionSeguimiento(e1);getSatelitesValidos(SelectorTrue)", new Satelite[0], e.getSatelitesValidos(new SelectorSateliteTrue()));
		
		i++;
		// getSatelitesValidos algunos
		ss = new Satelite[6];
		for (int j = 0; j < ss.length; j++) {
			ss[j] = new Satelite ("s" + i, new Posicion(i, i, i), 0, new Posicion(0, 0, 0), 0);
			e.addSatelite(ss[j]);
		}
		assertArrayEquals(t+i+";new EstacionSeguimiento(e1);addSatelite(s1);array a medias;getSatelitesValidos(SelectorTrue)", ss, e.getSatelitesValidos(new SelectorSateliteTrue()));
		
		i++;
		// getSatelitesValidos lleno
		e = new EstacionSeguimiento("e1");
		ss = new Satelite[15];
		for (int j = 0; j < ss.length; j++) {
			ss[j] = new Satelite ("s" + i, new Posicion(i, i, i), 0, new Posicion(0, 0, 0), 0);
			e.addSatelite(ss[j]);
		}
		Satelite[] r = new Satelite[10];
		for (int j = 0; j < r.length; j++)
			r[j]=ss[ss.length - r.length + j];
		assertArrayEquals(t+i+";new EstacionSeguimiento(e1);addSatelite(más de los que caben);getSatelitesValidos(SelectorTrue)", r, e.getSatelitesValidos(new SelectorSateliteTrue()));
	}	
	
	
	// addDetección: detectado, nuevo con sitio, nuevo sin sitio
	//@Test (timeout=500)
	public void test8() {
		int i = 1;
		String t = "Test8-";	

		// addDetección: detectado
		EstacionSeguimiento e = new EstacionSeguimiento("e1");
		
		Satelite[] ss = new Satelite[6];
		for (int j = 0; j < ss.length; j++) {
			ss[j] = new Satelite ("s" + j, new Posicion(j*10, j*10, j*10), 0, new Posicion(0, 0, 0), 0);
			e.addSatelite(ss[j]);
		}
		Posicion p = new Posicion(30, 30, 33);
		e.addDeteccion(p, 0); 
		//System.out.println(t+i+ e);
		assertEquals(t+i+";new EstacionSeguimiento(e1);addDeteccion(30,30,33)", p, ss[3].getPos());
		
		i++;
		// addDeteccion nuevo con sitio
		e = new EstacionSeguimiento("e1");
		ss = new Satelite[6];
		for (int j = 0; j < ss.length; j++) {
			ss[j] = new Satelite ("s" + j, new Posicion(j*10, j*10, j*10), 0, new Posicion(0, 0, 0), 0);
			e.addSatelite(ss[j]);
		}
		p = new Posicion(3000, 3000, 3000);
		e.addDeteccion(p, 0); 
		assertEquals(t+i+";new EstacionSeguimiento(e1);lleno;addDeteccion(3000,3000,3000)", p, e.getSatelitesValidos(new SelectorSateliteTrue())[6].getPos());
		assertEquals(t+i+";new EstacionSeguimiento(e1);lleno;addDeteccion(3000,3000,3000)", "AUTO0", e.getSatelitesValidos(new SelectorSateliteTrue())[6].getId());
		
		i++;
		// addDeteccion nuevo sin sitio
		e = new EstacionSeguimiento("e1");
		ss = new Satelite[10];
		for (int j = 0; j < ss.length; j++) {
			ss[j] = new Satelite ("s" + j, new Posicion(j*10, j*10, j*10), 0, new Posicion(0, 0, 0), 0);
			e.addSatelite(ss[j]);
		}
		p = new Posicion(3000, 3000, 3000);
		e.addDeteccion(p, 0); 
		assertEquals(t+i+";new EstacionSeguimiento(e1);lleno;addDeteccion(3000,3000,3000)", p, e.getSatelitesValidos(new SelectorSateliteTrue())[9].getPos());
	}
		
	public static void main(String[] args) {
		Prueba4 p = new Prueba4();
		p.checkDependenciesPosicion();
		p.checkDependenciesSatelite();
		p.checkDependenciesEstacionSeguimiento();
		p.checkDependenciesSelectorSatelite();

		
		p.test1();
		p.test2();
		p.test3();
		p.test4();
		p.test5();
		p.test6();
		p.test7();
		p.test8();
	}
}
