package es.upm.dit.prog.practica5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.SwingUtilities;


import java.util.Arrays;

//import javax.swing.SwingUtilities;

public class PruebaInteractiva5 {
	
	private String[] cmds = new String[] {
			"hello \n saludo",
			"status \n valores de variables",
			"help \n lista de órdenes",
			"exit \n salir",
			"clear \n reset de variables",
			"pos lat long alt \n crea una posicion con: lat:double long:double alt:double",
			"sat id t t0 \n crea un satelite con: id:String t:long t0:long",
			"moverse t \n mueve el satelite con: t:long",
			"desplazarse t \n mueve el satelite a la posicion con: t:long",
			"add \n añade el ultimo satelite a la estacion",
			"estacion \n muestra la estacion",
			"actualizar t \n actualiza los satelites de la estacion con: t:long",			
			"det t \n crea una nueva detección con la última posicion con: t:long",
			"true \n crea un selector true y lo pone como último selector",
			"id id \n crea un selector de identidad y lo pone como último selector con: id:String",
			"and \n crea un selector and y lo pone como último selector con los dos últimos selectores",
			"compatible \n crea un selector compatible con la última posicion creada lo pone como último selector",
			"altura hmin hmax \n crea un selector de altura y lo pone como último selector con: hmin:double hmax:double",
			"peligro \n crea un selector peligro y lo pone como último selector",
			"filter \n aplica el último selector creado a los satelites de la estacion",
			"show \n muestra un diagrama con los satelites de la estacion filtrados con el último selector",
			"sim tini tfin step \n muestra una simulación del movimiento de los satelites de la estacion con: tini:long tfin:long step:long"
	};

	private Posicion p1;
	private Posicion p2;
	private Satelite sat;
	private Deteccion det;
	private List<Deteccion> detecciones;
	private EstacionSeguimiento estacion;
	private List<Satelite> satelites;
	private SelectorSatelite s1;
	private SelectorSatelite s2;
	
	
	public PruebaInteractiva5() {
		this.init();
	}

	public void init() {
		this.detecciones = new ArrayList<Deteccion>(); //new Deteccion[100]; 
		this.satelites = new ArrayList<Satelite>();
		this.p1 = new Posicion (0,0, 0);
		this.p2 = new Posicion (0,0, 0);
		this.sat = new Satelite("", new Posicion (0,0, 0), 0, new Posicion (0,0, 0), 0);
		this.det = new Deteccion(new Posicion (0,0,0), 0);
		this.estacion = new EstacionSeguimiento("ESTACION");
		this.s1 = new SelectorSateliteTrue();
		this.s2 = new SelectorSateliteTrue();
	}
	
	public String run (String c) {
		c = c.toLowerCase();
		String[] fs = c.trim().split(" +");
		
		// practica1
		if (c.indexOf(cmds[0].split(" ")[0]) == 0) //"hello"
			return c;
		if (c.indexOf(cmds[1].split(" ")[0]) == 0) { //"status"
			return "this.p1 " + this.p1.toString()
			+ "\n" + "this.p2 " + this.p2.toString()
			+ "\n" + "this.sat " + this.sat.toString()
					+ Arrays.asList(this.satelites).toString() + "\n" 
					+ this.detecciones.toString() + "\n" 
					+ this.estacion.toString() + "\n"
					+ this.s1 + "\n" + this.s2;
		}
		if (c.indexOf(cmds[2].split(" ")[0]) == 0) { //"help"
			String r = "";
			for (String ci: cmds)
				r += ci + "\n";
			return r;
		}
		if (c.indexOf(cmds[3].split(" ")[0]) == 0) { //"exit"
			System.out.println("bye");
			System.exit(0);
		}

		if (c.indexOf(cmds[4].split(" ")[0]) == 0) { // "clear"
			this.init();
			return this.estacion.toString();
		}
		if (c.indexOf(cmds[5].split(" ")[0]) == 0) { // "pos"
			try {
				Posicion p = new Posicion(Double.parseDouble(fs[1]), Double.parseDouble(fs[2]), Double.parseDouble(fs[3]));
			  	this.p2 = this.p1;
			  	this.p1 = p;
			  	return "this.p1 " + this.p1.toString()
		  		+ " [x=" + this.p1.getX() + ", y="+ this.p1.getY() + ", z=" + this.p1.getZ() + "]"
		  		+ "\nthis.p2 " + this.p2.toString()
		  		+ " [x=" + this.p2.getX() + ", y="+ this.p2.getY() + ", z=" + this.p2.getZ() + "]";
			} catch (Exception e) {
				return cmds[5];
			}
		}
		if (c.indexOf(cmds[6].split(" ")[0]) == 0) { // "sat"
			try {
				this.sat = new Satelite (fs[1], this.p1, Long.parseLong (fs[2]), this.p2, Long.parseLong(fs[3]));
				return this.sat.toString(); 
			} catch (Exception e) {
				return cmds[6];
			}
		}
		
		// practica2
		if (c.indexOf(cmds[7].split(" ")[0]) == 0) { // "moverse"
			try {
				this.sat.mueveHasta(Long.parseLong (fs[1]));
				return this.sat.toString();
			} catch (Exception e) {
				return cmds[7];			
			}
		}
		if (c.indexOf(cmds[8].split(" ")[0]) == 0) { // "desplazarse"
			try {
				this.sat.detectadoEn(this.p1, Long.parseLong (fs[1]));
				return this.sat.toString();
			} catch (Exception e) {
				return cmds[8];			
			}
		}

		// practica3
		if (c.indexOf(cmds[9].split(" ")[0]) == 0) { // "add"
			if (!this.sat.getId().equals(""))
			  this.estacion.addSatelite(this.sat);
			return this.estacion.toString();
		}
		if (c.indexOf(cmds[10].split(" ")[0]) == 0) // "estacion"
			return this.estacion.toString();

		if (c.indexOf(cmds[11].split(" ")[0]) == 0) { // "actualizar"
			try {
				this.estacion.actualizar(Long.parseLong (fs[1]));
				return this.estacion.toString();
			} catch (Exception e) {
				return cmds[11];
			}
		}
		// practica3 o 4 ?
		if (c.indexOf(cmds[12].split(" ")[0]) == 0) { // "det"
			try {
				this.det = new Deteccion (this.p1, Long.parseLong (fs[1]));
				this.detecciones.add(this.det);
				return this.det.toString(); 
			} catch (Exception e) {
				return cmds[12];			
			}
		}

		// practica4
		if (c.indexOf(cmds[13].split(" ")[0]) == 0) { // "true"
			this.s2 = this.s1;
			this.s1 = new SelectorSateliteTrue();
			return this.s1.toString();
		}
		if (c.indexOf(cmds[14].split(" ")[0]) == 0) { // "id"
			try {
			  String id = fs[1];
			  this.s2 = this.s1;
			  this.s1 = new SelectorSateliteId(id);
			  return this.s1.toString();
			} catch (Exception e) {
			  this.s1 = new SelectorSateliteTrue();
			  return cmds[14];
			}
		}
		if (c.indexOf(cmds[15].split(" ")[0]) == 0) { // "and"
			SelectorSatelite ss = new SelectorSateliteY(this.s1, this.s2);
			this.s2 = this.s1;
			this.s1 = ss;
			return this.s1.toString();
		}
		if (c.indexOf(cmds[16].split(" ")[0]) == 0) { // "compatible"
			SelectorSatelite ss = new SelectorSateliteCompatible(this.p1);
			this.s2 = this.s1;
			this.s1 = ss;
			return this.s1.toString();
		}
		if (c.indexOf(cmds[17].split(" ")[0]) == 0) { // "altura"
			try {
			  this.s2 = this.s1;
			  this.s1 = new SelectorSateliteAltura(Double.parseDouble(fs[1]), Double.parseDouble(fs[2]));
			  return this.s1.toString();
			} catch (Exception e) {
			  this.s1 = new SelectorSateliteTrue();
			  return cmds[17];
			}
		}
		if (c.indexOf(cmds[18].split(" ")[0]) == 0) { // "peligro"
			SelectorSatelite ss = new SelectorSateliteEnPeligro((Satelite[]) this.estacion.getSatelitesValidos(null).toArray());
			this.s2 = this.s1;
			this.s1 = ss;
			return this.s1.toString();
		}

		// PELIGROSO ????
		if (c.indexOf(cmds[19].split(" ")[0]) == 0) {  // "filter"
			this.satelites = this.estacion.getSatelitesValidos(this.s1);
			return Arrays.asList(this.satelites).toString();
		}

		if (c.indexOf(cmds[20].split(" ")[0]) == 0) { // "show"
			System.out.println("Launching...");
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	MapaPanel pva = new MapaPanel();
	            	//if (satelites.length == 0)
	            	satelites = estacion.getSatelitesValidos(null);
	                pva.init(estacion, detecciones, new SelectorSateliteTrue(), 0, 0, 0); 
	                pva.createAndShowGUI();
	            }
	        });
			return "viewer launched";
		}
		if (c.indexOf(cmds[21].split(" ")[0]) == 0) { // "sim"
			try {
				long tini = Long.parseLong (fs[1]);
				long tfin = Long.parseLong (fs[2]);
				long step = Long.parseLong (fs[3]);
			System.out.println("Launching sim from " + tini + " to " + tfin + " stepping at " + step);
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	MapaPanel pva = new MapaPanel();
	            	//if (satelites.length == 0)
	            	satelites = estacion.getSatelitesValidos(null);
	                pva.init(estacion, detecciones, s1, tini, tfin, step); 
	                pva.createAndShowGUI();
	            }
	        });
			return "viewer launched";
			} catch(Exception e) {
				return cmds[21];
			}
		}

		return "";
	}
	
	public static void main(String[] args) throws Exception{
		PruebaInteractiva5 m = new PruebaInteractiva5();
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		String cmd = "hello";
		do {
			System.out.print ("" + m.run(cmd) + "\n>" );
			cmd = sc.nextLine();  				
		} while (true);
	}

	
}

