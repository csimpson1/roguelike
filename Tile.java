package roguelike;

import java.awt.Color;
import asciiPanel.AsciiPanel;
//Enum for tile types
public enum Tile {
	//Add your tile type here
	FLOOR((char)250,AsciiPanel.yellow),
	WALL((char)177, AsciiPanel.yellow),
	BOUNDS('x', AsciiPanel.brightBlack);

	//Get the symbol for this tile
	private char glyph;
	public char glyph(){ return glyph; }
	//Get tile color
	private Color color;
	public Color color(){ return color; }
	
	//Can we dig through this tile?
	public boolean isDiggable(){
		//We can currently only dig walls
		return this == Tile.WALL;
	}
	//Can we walk on this tile?
	//Currently only GROUND tiles can be walked on
	public boolean isGround(){
		return this != WALL && this != BOUNDS;
	}

	Tile(char glyph, Color c){
		this.glyph=glyph;
		this.color=c;
	}
}
