import java.util.ArrayList;

public class Room {

	protected String textDescription;
	
	private Room exitWest;
	private Room exitSouth;
	private Room exitNorth;
	private Room exitEast;
	
	
	private ArrayList<Enemy> enemies = new ArrayList<>(); //TODO Only add arrays if there's enemies in the room
	private Items[] items;
	private Player player;

	public Room() {
		

	}
	
	public void addEnemies(Enemy e){
		this.enemies.add(e);
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}

	public Room getExitWest() {
		return exitWest;
	}


	public void setExitWest(Room exitWest) {
		this.exitWest = exitWest;
	}


	public Room getExitSouth() {
		return exitSouth;
	}


	public void setExitSouth(Room exitSouth) {
		this.exitSouth = exitSouth;
	}


	public Room getExitNorth() {
		return exitNorth;
	}


	public void setExitNorth(Room exitNorth) {
		this.exitNorth = exitNorth;
	}


	public Room getExitEast() {
		return exitEast;
	}


	public void setExitEast(Room exitEast) {
		this.exitEast = exitEast;
	}

	public String getTextDescription() {
		return textDescription;
	}


	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}

	

	



}
