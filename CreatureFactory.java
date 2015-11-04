package roguelike;

import asciiPanel.AsciiPanel;
//The class CreatureFactory is used to populate the world with creatures in random locations
public class CreatureFactory {

	private World world;
	
	public CreatureFactory(World world){
		this.world = world;
	}
	//Creates world actor controlled by player. Only one at a time
	public Creature newPlayer(){
		Creature player = new Creature(world, '@',AsciiPanel.brightWhite);
		world.addAtEmptyLocation(player);
		new PlayerAi(player);
		return player;
	}
	//Creates a fungus
	public Creature newFungus(){
		Creature fungus = new Creature (world, 'f', AsciiPanel.green);
		world.addAtEmptyLocation(fungus);
		new FungusAi(fungus);
		return fungus;
	}
}
