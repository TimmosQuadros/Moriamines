package src;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

public class MoriaMines implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean GameOver = false;
	private static Room entryRoom;
	private static Room caveRoom;
	private static Room finalRoom;
	private static Room hiddenRoom;
	private static Room orcRoom;
	private static Room itemRoom;
	public static Player player;
	private static Room anotherHiddenRoom;
	private static Room compasRoom;
	private static Room roomInBetween;

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String command = "", res = "";
		buildDungeon();

		System.out.println("Moria_Minesstarted!"); 

		while(GameOver==false) { 
			System.out.print("Command: "); 
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
			command = in.readLine(); 
			res = doCommand(command); 
			System.out.println(res); 
		} 

		System.out.println("Moria_Mines ended!"); 
	}

	private static void buildDungeon(){



		entryRoom = new Room("You are in the front of a cave. The entrance smells of wet soil");

		//caveRoom = new Room("You are inside the cave, there is an old firepiton the floor");
		//finalRoom = new Room("You are in a room with very little light. The is an old box on the floor");
		//hiddenRoom = new Room("You are in a hidden room");
		orcRoom = new RoomWithMonster("You are in a foul smelling place, with some human bones on the floor",new Monster("Orc", "Fuilthy animal", 20, 1));



		//caveRoom=new Room("You are inside the cave, there is an old firepiton the floor");
		hiddenRoom=new Room("You are in a hidden room", new String[]{"Hammer"});
		anotherHiddenRoom=new Room("You are here! There's a toolbox on the floor", new String[]{"Screwdriver"});

		caveRoom= new RoomWithLock("You are inside the cave, there is an old firepiton the floor",1);

		finalRoom= new RoomFinal("You are in a room with very little light. There is an old box on the floor");
		itemRoom= new Room("You are in a item room", new ComplexItem[]{new ComplexItemTreasure("Treasurechest", "A beautiful woddenchest", 350)});
		compasRoom = new Room("You walked into a room with a table, some maps are lying on the table!",new String[]{"Compas"});
		roomInBetween= new RoomWithLock("There is something special about this room, it's completely empty but there is a golden door", 4);

		entryRoom.setNorth(caveRoom);
		entryRoom.setEast(compasRoom);
		compasRoom.setWest(entryRoom);
		hiddenRoom.setWest(caveRoom);
		hiddenRoom.setEast(anotherHiddenRoom);
		anotherHiddenRoom.setWest(hiddenRoom);
		caveRoom.setSouth(entryRoom);
		caveRoom.setNorth(orcRoom);
		caveRoom.setEast(hiddenRoom);
		orcRoom.setSouth(caveRoom);
		orcRoom.setWest(roomInBetween);
		roomInBetween.setWest(finalRoom);
		roomInBetween.setEast(orcRoom);
		finalRoom.setEast(roomInBetween);
		orcRoom.setEast(itemRoom);
		itemRoom.setWest(orcRoom);

		player = new Player();
	}

	private static String doCommand(String command) throws IOException, ClassNotFoundException{
		String[] parts = command.split(" ");
		if(parts.length==0 || parts[0].length()==0){ 
			return "nothing typed";
		}else{
			if(parts[0].equalsIgnoreCase("back")){
				return player.goBack();
			}else if(parts[0].equalsIgnoreCase("use")) { 
				if(parts.length>1)return player.use(parts[1]); 
			}else if(parts[0].equalsIgnoreCase("north")){
				return player.goNorth();
			}else if(parts[0].equalsIgnoreCase("south")){
				return player.goSouth();
			}else if(parts[0].equalsIgnoreCase("east")){
				return player.goEast();
			}else if(parts[0].equalsIgnoreCase("west")){
				return player.goWest();
			}else if(parts[0].equalsIgnoreCase("pickup")){
				return player.pickup();
			}else if(parts[0].equalsIgnoreCase("inventory")){
				return player.getInventory();
			}else if(parts.length>2 && parts[0].equalsIgnoreCase("attack") && parts[1].equalsIgnoreCase("with")){
				return player.attackWith(parts[2]);
			}else if(parts[0].equalsIgnoreCase("help")){
				return player.help();
			}else if(parts[0].equalsIgnoreCase("restart")){
				main(new String[]{""});
			}else if(parts[0].equalsIgnoreCase("quit")){
				return player.gameOver();
			}else if(parts[0].equalsIgnoreCase("stats")){
				return player.printStats();
			}else if(parts[0].equalsIgnoreCase("save")){
				return player.saveGame();
			}else if(parts[0].equalsIgnoreCase("load")){
				return player.loadGame();
			}
		}
		return "not a valid command";
	}

	public static Room getEntryRoom() {
		return entryRoom;
	}
	
	public static void setGameOver(boolean flag){
		GameOver=flag;
	}

	//Unused so far
	public static ArrayList<Room> getAllRooms(){
		ArrayList<Room> r = new ArrayList<>();
		r.add(entryRoom);
		r.add(caveRoom);
		r.add(finalRoom);
		r.add(hiddenRoom);
		r.add(orcRoom);
		r.add(itemRoom);
		r.add(anotherHiddenRoom);
		r.add(compasRoom);
		r.add(roomInBetween);
		return r;
	}
	
	public static void setAllRooms(ArrayList<Room> r){
		entryRoom=r.get(0);
		caveRoom=r.get(1);
		finalRoom=r.get(2);
		hiddenRoom=r.get(3);
		orcRoom=r.get(4);
		itemRoom=r.get(5);
		anotherHiddenRoom=r.get(6);
		compasRoom=r.get(7);
		roomInBetween=r.get(8);
	}


}
