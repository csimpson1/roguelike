package roguelike.screens;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import roguelike.Creature;
import roguelike.CreatureFactory;
import roguelike.World;
import roguelike.WorldBuilder;


public class PlayScreen implements Screen {
	private World world;
	private Creature player;
	private int screenWidth;
	private int screenHeight;
	
	public PlayScreen(){
		screenWidth = 80;
		screenHeight = 21;
		createWorld();
		CreatureFactory creatureFactory = new CreatureFactory(world);
		player = creatureFactory.newPlayer();
		//Put 8 fungi somewhere on the map
		for(int i = 1; i < 8; i++){
			creatureFactory.newFungus();
		}
	}
	
	private void createWorld(){
		world = new WorldBuilder(90,31)
		.makeCaves()
		.build();
	}
	//Methods to deal with scrolling bounds
	public int getScrollX(){
		return Math.max(0, Math.min(player.x - screenWidth/2, world.width() - screenWidth));
	}
	
	public int getScrollY(){
		return Math.max(0, Math.min(player.y - screenHeight / 2, world.height() - screenHeight));
	}
	
	// We want to display the contents of the world on the terminal
	private void displayTiles(AsciiPanel terminal, int left, int top){
		for(int x = 0; x < screenWidth; x++){
			for(int y = 0; y < screenHeight; y++){
				int wx = x+left;
				int wy = y+top;
				terminal.write(world.glyph(wx,wy), x, y, world.color(wx,wy));
			} 
		}
		
		//For every creature on the map, if its in the viewable region, draw it to the screen
		for (Creature c : world.creatures()){
			if( left <= c.x && c.x < left + screenWidth && top <= c.y & c.y <= top + screenHeight){
				terminal.write(c.glyph(), c.x - left, c.y - top, c.color());
			}
		}
	}
	//Prints our location to the screen
	public void displayOutput(AsciiPanel terminal){
		int left = getScrollX();
		int top = getScrollY();
		displayTiles(terminal, left, top);
		terminal.write(player.glyph(), player.x - left, player.y - top, player.color());
	}
	
	
	public Screen respondToUserInput(KeyEvent key){
		switch (key.getKeyCode()){
		case KeyEvent.VK_ESCAPE: return new LoseScreen();
		case KeyEvent.VK_ENTER: return new WinScreen();
		//Scrolling events
		
		//cardinals 
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_H:player.moveBy(-1,0); break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_L:player.moveBy(1,0);  break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_K:player.moveBy(0,-1); break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_J:player.moveBy(0,1); break;
		
		//diagonals
		case KeyEvent.VK_Y:player.moveBy(-1,-1); break;
		case KeyEvent.VK_U:player.moveBy(1,-1); break;
		case KeyEvent.VK_B:player.moveBy(-1,1); break;
		case KeyEvent.VK_N:player.moveBy(1,1); break;
		}
		
		return this;
	}
}
