package pyredrid.mapgen;

import java.awt.Color;
import java.util.Random;

public class MapGenIsland extends MapGen {
	public static final int MAP_WIDTH = 1280;
	public static final int MAP_HEIGHT = 720;
	public Color[][] gen() {
		Color[][] terrain = new Color[MAP_WIDTH][MAP_HEIGHT];
		float[][] noise = PerlinNoiseGen.generatePerlinNoise(MAP_WIDTH, MAP_HEIGHT, 8, new Random().nextLong());
		float[][] greenNoise = PerlinNoiseGen.generatePerlinNoise(MAP_WIDTH, MAP_HEIGHT, 8, new Random().nextLong());
		for (int x = 0; x < MAP_WIDTH; x++) {
			for (int y = 0; y < MAP_HEIGHT; y++) {
				if ((noise[x][y] >= 0.0F) && (noise[x][y] < 0.5F)) {
					terrain[x][y] = new Color(0, 0, (int)(noise[x][y]*255*2));
				} else if ((noise[x][y] >= 0.5F) && (noise[x][y] < 0.65F)) {
					terrain[x][y] = new Color(0, (int)((greenNoise[x][y])*127)+127, 0);
				} else {
					terrain[x][y] = new Color((int)(noise[x][y]*255), (int)(noise[x][y]*255), (int)(noise[x][y]*255));
				}
			}
		}
		return terrain;
	}
}
