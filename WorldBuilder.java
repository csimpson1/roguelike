package roguelike;

import java.util.ArrayList;
import java.util.List;

public class WorldBuilder {
	private int width;
	private int height;
	private Tile[][] tiles;

	
	public WorldBuilder(int w, int h){
		this.width = w;
		this.height = h;
		this.tiles=new Tile[width][height];
	}
	
	//export a world for playing. Pass an empty list of creatures
	public World build() {
		List<Creature> creatures = new ArrayList<Creature>();
		return new World(tiles, creatures);
	}
	
	//Create a world with randomly distributed walls and floors. 
	
	private WorldBuilder randomizeTiles(){
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				//change this bound to change the probability a tile is a floor
				tiles[x][y] = Math.random() < 0.45 ? Tile.WALL : Tile.FLOOR;
			}
		}
		return this;
	}
	//Defines the cellular automaton we use to smooth the random world 
	//into a cavernous one.
	private WorldBuilder smooth(int n){
		Tile[][]tiles2 = new Tile[width][height];
		for (int t = 0; t < n; t++){
			
			for(int x = 0; x < width; x++){
				for(int y = 0; y < height; y++ ){
					int floors = 0;
					int walls = 0;
					//Set up offset loop: we need to check all tiles within a 
					//1 step radius and count the number of floors and walls
					for (int dx = -1; dx < 2; dx ++){
						for(int dy = -1; dy < 2; dy++){
							//Skip over all out of bounds indices
							if( x + dx < 0 || x + dx >= width || y + dy < 0 || y + dy >= height)
								continue;
							if(tiles[x + dx][y + dy] == Tile.FLOOR)
								floors++;
							else 
								walls++;
						}
					}
					
					tiles2[x][y] = floors >= walls ? Tile.FLOOR : Tile.WALL;	
				}
			}
			//We've updated the world, now erase the old one.
			tiles = tiles2;
		}
		return this;
	
	}
	
	//Make some damn caves
	public WorldBuilder makeCaves(){
	return randomizeTiles().smooth(8);
	}
//DEBUGGING
	//public static void main(String[] args){
		// World world = new WorldBuilder(90,31)
	//	.makeCaves()
		//.build();
		 
	//}
	
}
