package pyredrid.mapgen;

import javax.swing.JFrame;

public class Main extends JFrame{
	private static final long serialVersionUID = 5955519306303907290L;
	private DrawMap drawMapPanel;

	public static void main(String[] args) {
		new Main();
		while (true) {
			//Don't quit immediately
		}
	}
	
	public Main(){
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(MapGenIsland.MAP_WIDTH, MapGenIsland.MAP_HEIGHT);
	    setLocationRelativeTo(null);
	    setTitle("Map Generator From Scratch");
	    setVisible(true);
	    drawMapPanel = new DrawMap(new MapGenIsland());
	    this.add(drawMapPanel);
	}
}
