package es.upm.dit.prog.practica2;

import java.util.Scanner;
import java.util.Arrays;

//import javax.swing.SwingUtilities;

public class PruebaInteractiva2 {
	
	private String[] cmds = new String[] {
			"hello \n saludo",
			"status \n valores de variables",
			"help \n lista de órdenes",
			"exit \n salir",
			"clear \n reset de variables",
			"pos lat long alt \n crea una posicion con: lat:double long:double alt:double",
			"sat id t t0 \n crea un satelite con: id:String t:long t0:long",
			"moverse t \n mueve el satelite con: t:long",
			"desplazarse t \n mueve el satelite a la posicion con: t:long"
//			,
//			"add \n añade el ultimo satelite a la estacion",
//			"estacion \n muestra la estacion",
//			"actualizar t \n actualiza los satelites de la estacion con: t:long",			
//			"det t \n crea una nueva detección con la última posicion con: t:long",
//			"todos \n obtiene todos los satelites de la estacion",

//			"true \n obtiene los satelites de la estacion con el selector true",
//			"id id \n obtiene los satelites de la estacion con el selector de identidad con: id:String",
//			"and \n obtiene los satelites de la estacion con el selector and y los dos últimos selectores",
//			"compatible \n obtiene los satelites de la estacion con el selector compatible con la última posicion",
//			"altura hmin hmax \n obtiene los satelites de la estacion con el selector altura con: hmin:double hmax:double",
//			"filter \n aplica el selector a los satelites de la estacion",
//			"show \n muestra un diagrama con los satelites de la estacion filtrados con el último selector",
//			"sim tini tfin step \n muestra una simulación del movimiento de los satelites de la estacion con: tini:long tfin:long step:long"
	};
	
	private Posicion p1;
	private Posicion p2;
	private Satelite sat;
	private Satelite[] satelites;
	
	
	public PruebaInteractiva2() {
		this.init();
	}

	public void init() {
		this.satelites = new Satelite[0]; //new ArrayList<Satelite>();
		this.p1 = new Posicion (0,0, 0);
		this.p2 = new Posicion (0,0, 0);
		this.sat = new Satelite("", new Posicion (0,0, 0), 0, new Posicion (0,0, 0), 0);
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
					;
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
			return  this.sat.toString(); //.estacion.toString();
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


		return "";
	}
	
	public static void main(String[] args) throws Exception{
		PruebaInteractiva2 m = new PruebaInteractiva2();
		@SuppressWarnings("resource")
		Scanner sc= new Scanner(System.in);
		String cmd = "hello";
		do {
			System.out.print ("" + m.run(cmd) + "\n>" );
			cmd = sc.nextLine();  				
		} while (true);
	}

	
}

