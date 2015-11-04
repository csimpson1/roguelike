package roguelike;

public class PlayerAi extends CreatureAi {
	public PlayerAi(Creature creature){
		super(creature);
	}
	//Deals with character movement
	//If a tile is ground, we can walk on it. Otherwise, we dig it out
	public void onEnter(int x, int y, Tile tile){
		if(tile.isGround()){
			creature.x = x;
			creature.y = y;
		}
		else if (tile.isDiggable()){
			creature.dig(x, y);
		}
	}
}
