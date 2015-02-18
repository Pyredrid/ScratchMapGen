package pyredrid.mapgen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawMap extends JPanel {
	private static final long serialVersionUID = 6539967163563343222L;
	private Graphics2D g2;
	private MapGen generator;
  
	public DrawMap(MapGen generator) {
		this.generator = generator;
	}
  
	public void paint(Graphics g) {
		super.paint(g);
		this.g2 = ((Graphics2D)g);

		Color[][] terrain;
	    terrain = generator.gen();
	    for (int x = 0; x < terrain.length; x ++) {
	      for (int y = 0; y < terrain[0].length; y ++) {
		        this.g2.setColor(terrain[x][y]);
		        this.g2.drawRect(x, y, 1, 1);
	      	}
	    }
	}
}
