package es.upm.dit.prog.practica2;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class MapaPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image im;
	private int x0;
	private int y0;
	private Satelite[] satelites;
    private JFrame f;
	//private MapaPanel rp;

	private static final int WIDTH = 1050;
	private static final int HEIGHT = 915;

    private static final double EARTH_RADIUS = 6371*1000;
    //private static final double MIN_HEIGHT = 150*1000 + EARTH_RADIUS;
    private static final double LEO_HEIGHT = 20000*1000 + EARTH_RADIUS;
    //private static final double MEO_HEIGHT = 35786*1000; //+ EARTH_RADIUS;
    
    private static final double SCALE = HEIGHT / (2 * LEO_HEIGHT);
    

    private static final double A = Math.PI / 4;
    
	private static final int SIMULATION_SPEED = 1000;    
	private Timer timer;
    private long t;
    
    private long tini;
    private long tfin;
    private long step;
    
	
	public MapaPanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		/*try {
            this.im = ImageIO.read(new File("madrid.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
		this.x0 = WIDTH / 2;
		this.y0 = HEIGHT / 2;
	}

	public double escala (double e) {
		return e * SCALE;
	}
	
	public double y (double x, double y, double z) {
		return y - x * Math.cos(A);
	}
	public double z (double x, double y, double z) {
		return z - x * Math.sin(A);
	}
	
	public int y (Posicion p) {
		return (int) y (p.getX(), p.getY(), p.getZ());
	}

	public int z (Posicion p) {
		return (int) z (p.getX(), p.getY(), p.getZ());
	}

	public void init(Satelite[] satelites, long tini, long tfin, long step) {
		this.init(satelites);
		this.tini = tini;
		this.tfin = tfin;
		this.step = step;
		this.t = tini;
	}
	
    public void init(Satelite[] satelites) {
    	this.satelites = satelites;
    }
	    
    public void createAndShowGUI() {
        System.out.println("Launching PruebaSatelite " + 
                SwingUtilities.isEventDispatchThread());
        System.out.println ("tini= " +this.tini + " tfin= "+ this.tfin + " step= " + this.step + " t= " + this.t);
        this.f = new JFrame("PruebaSatelite ");
        //this.f.setResizable(false);
        this.f.setLayout(new BorderLayout());
        this.f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);   
        
        if (this.tini < this.tfin) {
        	this.timer = new Timer(SIMULATION_SPEED, new ActionListener () {
        		public void actionPerformed(ActionEvent ae) {
        			step();
        		}
        	});
       		this.timer.start();
        }
        //this.rp = new MapaPanel();
        //this.f.add(this.rp, BorderLayout.CENTER);
        this.f.add(this, BorderLayout.CENTER);
        this.f.pack();
    	this.f.setVisible(true);
    }
    
    public void step() {
    	if (this.t >= this.tfin) {
    		this.timer.stop();
    		return;
    	}
    	this.t += this.step;
 		f.setTitle ("Sim, t = " + this.t);
 		for (Satelite s: this.satelites)
 			s.mueveHasta(this.t);
 		this.repaint();    	
    }	
    
	public void setSatelites (Satelite[] ttes) {
		this.satelites = (ttes != null) ? ttes : new Satelite[0];
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH,HEIGHT);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);    
		this.im = null;
		if (this.im == null) {
			g.setColor(Color.white);
			g.fillRect(0,0, this.getWidth(), this.getHeight());
		} else
			g.drawImage(this.im, 0, 0, null);
		g.setColor(Color.black);
		this.linea(g, -this.getWidth() / 2, 0, this.getWidth() / 2, 0); 
		this.linea(g, 0, -this.getHeight() / 2, 0, this.getHeight() / 2);
		this.linea(g, -this.getWidth() / 3, -this.getWidth() / 3, this.getWidth() / 3, this.getWidth() / 3); 
		
		this.circulo(g, 0, 0, escala(EARTH_RADIUS));
		
		for (Satelite t: this.satelites) {
			if (t != null) {
				this.paintSatelite (g, t, false);
			}
		}
	}

/*
	public void paintDeteccion(Graphics g, Deteccion d) {
    	g.setColor(new Color(10000 * (int) d.getPos().getAltura()));
    	this.circuloRelleno(g, escala(y(d.getPos())), escala(z(d.getPos())), 5);
     	this.texto(g, d.toString(), escala(y(d.getPos())) + 10, escala(z(d.getPos())));	
    	this.linea(g, 0, 0, escala(y(d.getPos())), escala(z(d.getPos())));
	}
*/	
	public void paintSatelite(Graphics g, Satelite tte) {
		this.paintSatelite(g, tte, false);
    }
	
    public void paintSatelite(Graphics g, Satelite a, boolean enPeligro) {
    	// x,y,z->y,z,x; x->y, y->-z, z->x
    	g.setColor(new Color(10000 * (int) a.getPos().getAltura()));
    	this.triangulo(g, escala(y(a.getPos())), escala(z(a.getPos())),
    			Math.atan2(escala(y(a.getPos())), escala(z(a.getPos()))), 10,
    			new BasicStroke(5), enPeligro);
    	this.triangulo(g, escala(y(a.getPos0())), escala(z(a.getPos0())),
    			Math.atan2(escala(y(a.getPos0())), escala(z(a.getPos0()))), 10,
    			((Graphics2D)g).getStroke(), false);
    	this.texto(g, a.getId() + " h="+(int)(a.getPos().getAltura()), escala(y(a.getPos())), escala(z(a.getPos())));
    	this.linea(g, 0, 0, escala(y(a.getPos())), escala(z(a.getPos())));
    }

    public void triangulo(Graphics g, double xc, double yc, double angle, double r, Stroke s, boolean fill) {
    	Stroke s0 = ((Graphics2D)g).getStroke();
    	((Graphics2D)g).setStroke(s);
    	int[] xs = new int [] {x0+ (int) (xc+(r*Math.cos(angle))),
    			x0+ (int) (xc+(r*Math.cos(angle + (2*Math.PI / 3)))),
    			x0+ (int) (xc+(r*Math.cos(angle + (2*2*Math.PI / 3))))
    	};
    	int[] ys = new int [] {y0 - (int) (yc+(r*Math.sin(angle))),
    			y0 - (int) (yc + (r*Math.sin(angle+(2*Math.PI/3)))),
    			y0 - (int) (yc + (r*Math.sin(angle+(2*2*Math.PI/3))))
    	};
    	if (! fill)
    		g.drawPolygon (xs, ys, 3);
    	else
    		g.fillPolygon (xs, ys, 3);		  
    	((Graphics2D)g).setStroke(s0);
    }
    
    // basic rendering methods
    public void limpia(Graphics g, double x1, double y1, double x2, double y2) {
        g.clearRect((int)Math.round(x1+x0),
    		(int)Math.round(y0-y1),
    		(int)Math.round(x2+x0),
    		(int)Math.round(y0-y2));
    }
    
    public void texto(Graphics g, String str, double x, double y) {
		g.setColor(Color.black);
    	g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString(str,
    		 (int)Math.round(x+this.x0),
    		 (int)Math.round(this.y0-y));
    }

    public void linea(Graphics g, double x1, double y1, double x2, double y2) {
        g.drawLine((int)Math.round(x1+this.x0),
    	       (int)Math.round(this.y0-y1),
    	       (int)Math.round(x2+this.x0),
    	       (int)Math.round(this.y0-y2));
    }
    
    public void circulo(Graphics g, double x, double y, double radio) {
	   	g.drawOval((int)Math.round(x-radio+this.x0),
	       (int)Math.round(this.y0-y-radio),
	       (int)Math.round(2*radio),
	       (int)Math.round(2*radio));
    }
       
    public void circuloRelleno(Graphics g, double x, double y, double radio) {
	   	g.fillOval((int)Math.round(x-radio+this.x0),
	 	       (int)Math.round(this.y0-y-radio),
	 	       (int)Math.round(2*radio),
	 	       (int)Math.round(2*radio));
	}
    
    public void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2) {
    	this.drawArrowLine(g, x1, y1, x2, y2, 20, 6);
    }
    
    /**
     * Draw an arrow line between two points.
     * @param g the graphics component.
     * @param x1 x-position of first point.
     * @param y1 y-position of first point.
     * @param x2 x-position of second point.
     * @param y2 y-position of second point.
     * @param d  the width of the arrow. Default=20
     * @param h  the height of the arrow. Default=6
     */
    public void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        x1 = this.x0+x1;
        x2 = this.x0+x2;
        y1 = this.y0-y1;
        y2 = this.y0-y2;
  	    int dx = x2 - x1;
        int dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d;
        double xn = xm;
        double ym = h;
        double yn = -h;
        double sin = dy / D;
        double cos = dx / D;
        double x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;
        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;
        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};
        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }
}
