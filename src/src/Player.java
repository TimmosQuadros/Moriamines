package src;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> inventory;
	private ArrayList<ComplexItem> complexInventory; 
	private Room currentRoom; 
	private Room lastRoom;
	private int hitPoints=30;
	private int damage=0;
	private int defaultDamage=0;
	private int maxHitpoints=30;
	private int level=1;

	public Player() { 
		currentRoom=MoriaMines.getEntryRoom(); 
		inventory= new ArrayList<>(); 
		inventory.add("Screwdriver");
		inventory.add("Potion");
		complexInventory= new ArrayList<>();
	}

	public String getInventory() {
		if(inventory==null){
			return"You have the following in your inventory: " + complexInventory.toString();
		}
		return"You have the following in your inventory: " + inventory.toString() + complexInventory.toString(); 
	} 


	public String enterRoom(Room newRoom) { 
		lastRoom=currentRoom; currentRoom= newRoom; 
		return currentRoom.enterRoom(); 
	} 
	public String goNorth() { 
		Room newRoom= currentRoom.getNorth(); 
		if (newRoom!= null) { 
			return enterRoom( newRoom); 
		}else if(currentRoom instanceof RoomWithLock && ((RoomWithLock)currentRoom).getLockNorth()) { 
			return "The door is locked";
		}else{
			return "There is no door here! try \"use compas\"";
		}
	}

	public String goBack(){
		currentRoom=lastRoom;
		return lastRoom.enterRoom();
	}

	public String goSouth() { 
		Room newRoom= currentRoom.getSouth(); 
		if (newRoom!= null) { 
			return enterRoom( newRoom); 
		}else if((currentRoom instanceof RoomWithLock) && ((RoomWithLock)currentRoom).getLockSouth()) { 
			return "The door is locked";
		}else { 
			return "There is no door here! try \"use compas\""; 
		}  
	}

	public String goWest() { 
		Room newRoom= currentRoom.getWest(); 
		if (newRoom!= null) { 
			return enterRoom( newRoom); 
		}else if((currentRoom instanceof RoomWithLock) && ((RoomWithLock)currentRoom).getLockWest()) { 
			return "The door is locked";
		}else { 
			return "There is no door here! try \"use compas\""; 
		}  
	}

	public String goEast() { 
		Room newRoom= currentRoom.getEast(); 
		if (newRoom!= null) { 
			return enterRoom( newRoom); 
		}else if((currentRoom instanceof RoomWithLock) && ((RoomWithLock)currentRoom).getLockEast()) { 
			return "The door is locked";
		}else { 
			return "There is no door here! try \"use compas\""; 
		}  
	}

	public String help() 
	{ 
		return "help ‐prints this message\n" + "[east, west, north, south] ‐attempt to move player to new room\n"
				+ "[use x] where x is one of the following items: [Poison,Potion,Shortsword,Bastardsword,Compas,Screwdriver,Hammer] -attempts to use an item\n"
				+ ""; 
	} 

	public String look() 
	{ 
		return currentRoom.getDescription(); 
	}

	public String printStats(){
		return "you are level: "+level+" hp="+hitPoints+" maxhp="+maxHitpoints+" damage="+damage;
	}

	public String pickup(){ 
		ArrayList<String> newItems= currentRoom.getItems(); 
		ArrayList<ComplexItem> newComplexItems= currentRoom.getComplexItems(); 
		String PickUps="";
		if(newItems!= null || newComplexItems!= null) { 
			PickUps= "You picked up the following items: "; 
		} 


		if(newItems!= null && !newItems.isEmpty()) { 
			inventory.addAll(newItems); 
			PickUps+=newItems.toString();
		} 

		if(newComplexItems!= null && !newComplexItems.isEmpty()) { 
			complexInventory.addAll(newComplexItems); 
			PickUps+=newComplexItems.toString(); 
		} 

		if(newItems!= null || newComplexItems!= null) { 
			return PickUps; 
		} 

		return "Nothing to pickup";  
	}

	String use(String item){
		if (inventory.contains(item)){
			if(item.equalsIgnoreCase("potion")){
				hitPoints=maxHitpoints;
				return "You have used a healing potion, your hitpoints is now: "+hitPoints;
			}else{
				return currentRoom.use(item);	
			}
		} 
		return "You do not have a "+ item; 
	}




	public ArrayList<ComplexItem> getComplexInventory() { 
		return complexInventory; 

	}

	public ArrayList<String> getInventoryObject(){
		return this.inventory;
	}

	public void setInventoryObject(ArrayList<String> l){
		this.inventory=l;
	}






	public int getDamage() { 
		return damage; 
	} 

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String receiveDamage(int amount) { 
		hitPoints-= amount; 

		if(hitPoints<= 0) { 
			MoriaMines.GameOver=true; 
			return "Player died!"; 
		} else { 
			return "The player has " + hitPoints+ " hitpointsleft!"; 
		} 
	} 

	public String attackWith(String weapon) {
		if(inventory.contains(weapon) && currentRoom instanceof RoomWithMonster){
			return currentRoom.attackWith(weapon);
		}
		return "You don't have a "+weapon; 
	}

	public void levelUp() {
		this.level+=1;
		this.defaultDamage=this.level;
		if(hitPoints==maxHitpoints){
			this.hitPoints+=10;
		}
		this.maxHitpoints+=10;
	}

	public int getDefaultDamage() {
		return defaultDamage;
	}

	public void setDefaultDamage(int defaultDamage) {
		this.defaultDamage = defaultDamage;
	}

	public String gameOver(){
		MoriaMines.setGameOver(true);
		return "Quiting game......";
	}

	public String saveGame(){
		try
		{
			FileOutputStream fileOut =
					new FileOutputStream("C:/Users/TimmosQuadros/Desktop/MoriaMines.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(MoriaMines.player);
			out.writeObject(MoriaMines.getAllRooms());
			out.close();
			fileOut.close();
		}catch(IOException i)
		{
			i.printStackTrace();
		}
		return "Serialized data is saved in C:/Users/TimmosQuadros/Desktop/MoriaMines.ser";
	}

	@SuppressWarnings("unchecked")
	public String loadGame() throws ClassNotFoundException{
		try
		{
			FileInputStream fileIn = new FileInputStream("C:/Users/TimmosQuadros/Desktop/MoriaMines.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			MoriaMines.player = (Player) in.readObject();
			MoriaMines.setAllRooms((ArrayList<Room>)in.readObject());
			//MoriaMines.class.getClassLoader().loadClass(m.getClass().getName());
			in.close();
			fileIn.close();
		}catch(IOException i)
		{
			i.printStackTrace();
			return "";
		}
		return "Loaded your game";
	}







}



