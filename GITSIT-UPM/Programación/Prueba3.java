package es.upm.dit.prog.practica3;

import java.util.Arrays;

public class Prueba3 {

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
			// EstacionSeguimiento.class.getMethod("buscaSatelite", String.class);
			EstacionSeguimiento.class.getMethod("addSatelite", Satelite.class);
			EstacionSeguimiento.class.getMethod("getSatelitesValidos");
			EstacionSeguimiento.class.getMethod("getSatelitesCompatibles", Posicion.class);
			EstacionSeguimiento.class.getMethod("addDeteccion", Posicion.class, Long.TYPE);
			EstacionSeguimiento.class.getMethod("actualizar", Long.TYPE);
		} catch(Throwable t) {
			t.printStackTrace(System.out);
			assertTrue("EstacionSeguimiento: faltan métodos.", false); 
		}
		assertTrue("EstacionSeguimiento: cabeceras de métodos correctas.", true);
	}

	// ctor
	//@Test (timeout=500)
	public void test1() {
		int i = 1;
		String t = "Test1-";	
		// ctor: vacio, accesor
		EstacionSeguimiento e = new EstacionSeguimiento("e1");
		assertEquals(t+i+";new EstacionSeguimiento(e1);getId()", "e1", e.getId());
		assertEquals(t+i+";new EstacionSeguimiento(e1);getSatelites().length", 10, e.getSatelites().length);
		i++;
	}

	// buscaSatelite: vacío, está o no
	
	// addSatelite: vacío, hay sitio, no hay sitio
	//@Test (timeout=500)
	public void test2() {
		int i = 1;
		String t = "Test2-";	
		// ctor: vacio, accesor
		EstacionSeguimiento e = new EstacionSeguimiento("e1");
		
		// addSatelite vacío
		Satelite s1 = new Satelite ("000000000", new Posicion(0, 0, 0), 0, new Posicion(0, 0, 0), 0);
		Satelite[] ss = new Satelite[] {s1, null, null, null, null, null, null, null, null, null};
		e.addSatelite(s1);
		System.out.println(t+i+e);
		assertArrayEquals(t+i+";new EstacionSeguimiento(e1);addSatelite(s1);", ss, e.getSatelites());

		i++;
		// addSatelite hasta llenarlo
		e = new EstacionSeguimiento("e1");
		ss = new Satelite[10];
		for (int j = 0; j < ss.length; j++) {
			ss[j] = new Satelite ("s" + i, new Posicion(i, i, i), 0, new Posicion(0, 0, 0), 0);
			e.addSatelite(ss[j]);
		}
		assertArrayEquals(t+i+";new EstacionSeguimiento(e1);addSatelite(array completo)", ss, e.getSatelites());

		i++;
		// addSatelite lleno
		e = new EstacionSeguimiento("e1");
		ss = new Satelite[15];
		for (int j = 0; j < ss.length; j++) {
			ss[j] = new Satelite ("s" + i, new Posicion(i, i, i), 0, new Posicion(0, 0, 0), 0);
			e.addSatelite(ss[j]);
		}
		Satelite[] r = new Satelite[10];
		for (int j = 0; j < r.length; j++)
			r[j]=ss[ss.length - r.length + j];
		assertArrayEquals(t+i+";new EstacionSeguimiento(e1);addSatelite(más de los que caben)", r, e.getSatelites());
		
	}

	// getSatelitesValidos: vacío, ninguno, algunos, con huecos
	//@Test (timeout=500)
	public void test3() {
		int i = 1;
		String t = "Test3-";	

		// getSatelitesValidos vacío
		EstacionSeguimiento e = new EstacionSeguimiento("e1");
		Satelite[] ss = e.getSatelitesValidos();
		assertArrayEquals(t+i+";new EstacionSeguimiento(e1);getSatelitesValidos()", new Satelite[0], e.getSatelitesValidos());
		
		i++;
		// getSatelitesValidos algunos
		ss = new Satelite[6];
		for (int j = 0; j < ss.length; j++) {
			ss[j] = new Satelite ("s" + i, new Posicion(i, i, i), 0, new Posicion(0, 0, 0), 0);
			e.addSatelite(ss[j]);
		}
		assertArrayEquals(t+i+";new EstacionSeguimiento(e1);addSatelite(s1);array a medias;getSatelitesValidos()", ss, e.getSatelitesValidos());
		
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
		assertArrayEquals(t+i+";new EstacionSeguimiento(e1);addSatelite(más de los que caben);getSatelitesValidos()", r, e.getSatelitesValidos());
	}	
	
	// getSatelitesCompatibles: vacío, ninguno, algunos, con huecos
	//@Test (timeout=500)
	public void test4() {
		int i = 1;
		String t = "Test4-";	

		// getSatelitesCompatibles vacío
		EstacionSeguimiento e = new EstacionSeguimiento("e1");
		assertArrayEquals(t+i+";new EstacionSeguimiento(e1);getSatelitesValidos()", new Satelite[0],
				e.getSatelitesCompatibles(new Posicion(0,0,0)));
		
		i++;
		// getSatelitesCompatibles ninguno
		Satelite[] ss = new Satelite[6];
		for (int j = 0; j < ss.length; j++) {
			ss[j] = new Satelite ("s" + i, new Posicion(i, i, i), 0, new Posicion(0, 0, 0), 0);
			e.addSatelite(ss[j]);
		}
		assertArrayEquals(t+i+";new EstacionSeguimiento(e1);addSatelite(s1);array a medias;getSatelitesCompatibles(ninguno)", new Satelite [0],
				e.getSatelitesCompatibles(new Posicion(-100,-100,-100)));

		i++;
		// getSatelitesCompatibles algunos-huecos
		ss = new Satelite[6];
		for (int j = 0; j < ss.length; j++) {
			ss[j] = new Satelite ("s" + i, new Posicion(j, j, j), 0, new Posicion(0, 0, 0), 0);
			e.addSatelite(ss[j]);
		}
		System.out.println("res" + Arrays.toString(e.getSatelitesCompatibles(new Posicion(5,5,5))));
		assertArrayEquals(t+i+";new EstacionSeguimiento(e1);addSatelite(s1);array a medias;getSatelitesCompatibles(algunos)", new Satelite []{ss[4], ss[5]},
				e.getSatelitesCompatibles(new Posicion(5,5,5)));

		i++;
		// getSatelitesValidos con huecos-todos
		e = new EstacionSeguimiento("e1");
		ss = new Satelite[15];
		for (int j = 0; j < ss.length; j++) {
			ss[j] = new Satelite ("s" + i, new Posicion(5, 5, 5), 0, new Posicion(0, 0, 0), 0);
			e.addSatelite(ss[j]);
		}
		Satelite[] r = new Satelite[10];
		for (int j = 0; j < r.length; j++)
			r[j]=ss[ss.length - r.length + j];
		assertArrayEquals(t+i+";new EstacionSeguimiento(e1);addSatelite(más de los que caben);getSatelitesCompatibles(todos)", r,
				e.getSatelitesCompatibles(new Posicion(5,5,5)));
	}	
	
	// addDetección: detectado, nuevo con sitio, nuevo sin sitio
	//@Test (timeout=500)
	public void test5() {
		int i = 1;
		String t = "Test5-";	

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
		ss = new Satelite[6];
		for (int j = 0; j < ss.length; j++) {
			ss[j] = new Satelite ("s" + j, new Posicion(j*10, j*10, j*10), 0, new Posicion(0, 0, 0), 0);
			e.addSatelite(ss[j]);
		}
		p = new Posicion(3000, 3000, 3000);
		e.addDeteccion(p, 0); 
		assertEquals(t+i+";new EstacionSeguimiento(e1);lleno;addDeteccion(3000,3000,3000)", p, e.getSatelitesValidos()[6].getPos());
		
		i++;
		// addDeteccion nuevo sin sitio
		ss = new Satelite[10];
		for (int j = 0; j < ss.length; j++) {
			ss[j] = new Satelite ("s" + j, new Posicion(j*10, j*10, j*10), 0, new Posicion(0, 0, 0), 0);
			e.addSatelite(ss[j]);
		}
		p = new Posicion(3000, 3000, 3000);
		e.addDeteccion(p, 0); 
		assertEquals(t+i+";new EstacionSeguimiento(e1);lleno;addDeteccion(3000,3000,3000)", p, e.getSatelitesValidos()[9].getPos());
	}
	
	// actualizar: vacío, movimientos
	//@Test (timeout=500)
	public void test6() {
		int i = 1;
		String t = "Test6-";
		
		EstacionSeguimiento e = new EstacionSeguimiento("e1");
		e.actualizar(10); 
		assertEquals(t+i+";new EstacionSeguimieno(e1);vacía;actualizar(10);", 0, e.getSatelitesValidos().length);
		
		i++;
		Satelite[] ss = new Satelite[10];
		for (int j = 0; j < ss.length; j++) {
			ss[j] = new Satelite ("s" + i, new Posicion(0, Math.PI*(j+1)/10, 10000), 10, new Posicion(0, Math.PI*(j)/100, 10000), 0);
			e.addSatelite(ss[j]);
		}
		e.actualizar(20); 
		for (int j = 0; j < e.getSatelitesValidos().length; j++) {
			assertEquals(t+i+";new EstacionSeguimiento(e1);llena;actualizar(20);", 20, e.getSatelitesValidos()[j].getT());
		}
	}


	
	// practica4: getSatelitesValidos: selector null, selectorTrue
	
	public static void main(String[] args) {
		Prueba3 p = new Prueba3();
		p.checkDependenciesPosicion();
		p.checkDependenciesSatelite();
		p.checkDependenciesEstacionSeguimiento();

		p.test1();
		p.test2();
		p.test3();
		p.test4();
		p.test5();
		p.test6();
	}
}
