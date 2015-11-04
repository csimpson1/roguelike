package roguelike;

import java.awt.Color;
import java.util.List;

public class World {
	private Tile[][] tiles;
	
	private List<Creature> creatures;
	public List<Creature> creatures(){
		return this.creatures;
	}
	
	//Creature functions
	
	//Return the entity (creature or null) at a location
	public Creature creature(int x, int y){
		for(Creature c : creatures){
			if(c.x == x && c.y == y)
				return c;
		}
		return null;
	}
	
	//Places creature at an empty location on the map.
	public void addAtEmptyLocation(Creature c){
		int x,y;
		
		//Try random map locations until we find a ground tile with nothing on it
		do{
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		}
		while (!tile(x,y).isGround() || creature(x,y) != null);
		
		//Update coordinates, and put the creature in the list
		c.x = x;
		c.y = y;
		creatures.add(c);
	}
	

	private int width;
	public int width(){ return width; }

	private int height;
	public int height(){ return height; } 

	public World(Tile[][] tiles, List<Creature> creatures){
		this.tiles = tiles;
		this.creatures = creatures;
		this.width = tiles.length;
		this.height = tiles[0].length;
	}
	
	//Grab a specific tile on the map. Check for map bounds
	public Tile tile(int x, int y){
		if(x < 0 || x >= width || y < 0 || y >= height)
			return Tile.BOUNDS;
		else
			return tiles[x][y];
	}
	
	// Get the specific character representing a tile
	public char glyph(int x, int y){
		return tile(x,y).glyph();
	}
	
	// Get the color of a specific tile
	public Color color(int x, int y){
		return tile(x,y).color();
	}
	
	//Digging events
	public void dig(int x, int y){
		if (tile(x, y).isDiggable())
			tiles[x][y] = Tile.FLOOR;
	}
	

}
