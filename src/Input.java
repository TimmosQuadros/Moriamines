import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {
	Room currentRoom;
	Scanner input = new Scanner(System.in);;
	String in;
	Room[][]rooms;
	Player player;
	boolean enemyKilled=false;

	World w;

	public Input() {

		waitForInput();
	}

	public void waitForInput(){


		w = new World();
		rooms=w.getRoomsInWorld();
		currentRoom=rooms[0][0];
		player=rooms[0][0].getPlayer(); //Hard coded because player is in this room in the beginning
		while (true){

			in=input.nextLine();
			if(in.compareTo("east")==0){
				in="";
				if(currentRoom.getExitEast()!=null){
					System.out.println("Going east...");

					currentRoom=currentRoom.getExitEast();
					if(!currentRoom.getEnemies().isEmpty()){
						Fight(currentRoom.getEnemies());
					}
					player.movePlayer(currentRoom);

					System.out.println("Current room is now "+currentRoom.getTextDescription());
				}else{
					System.out.println("You hit a wall");
				}

			}else if(in.compareTo("west")==0){
				in="";
				if(currentRoom.getExitWest()!=null){
					System.out.println("Going west...");
					currentRoom=currentRoom.getExitWest();
					if(!currentRoom.getEnemies().isEmpty()){
						Fight(currentRoom.getEnemies());
					}
					player.movePlayer(currentRoom);
					System.out.println("Current room is now "+currentRoom.getTextDescription());
				}else{
					System.out.println("You hit a wall");
				}
			}else if(in.compareTo("north")==0) {
				in="";
				if(currentRoom.getExitNorth()!=null){
					System.out.println("Going north...");
					currentRoom=currentRoom.getExitNorth();
					if(!currentRoom.getEnemies().isEmpty()){
						Fight(currentRoom.getEnemies());
					}
					player.movePlayer(currentRoom);
					System.out.println("Current room is now "+currentRoom.getTextDescription());
				}else{
					System.out.println("You hit a wall");
				}
			}else if(in.compareTo("south")==0){
				in="";
				if(currentRoom.getExitSouth()!=null){
					System.out.println("Going south...");
					if(!currentRoom.getEnemies().isEmpty()){
						Fight(currentRoom.getEnemies());
					}
					currentRoom=currentRoom.getExitSouth();
					player.movePlayer(currentRoom);
					System.out.println("Current room is now "+currentRoom.getTextDescription());
				}else{
					System.out.println("You hit a wall");
				}
			}else if (in.compareTo("exit")==0) {
				in="";
				System.out.println("Exit game");
				break;
			}else{
				in="";
				System.out.println("Not a valid command!");
			}

		}


	}

	public void Fight(ArrayList<Enemy> eList){ 

		if(!eList.isEmpty()){
			String build = "";
			enemyKilled=false;

			Dice dice = new Dice();
			//String input="";

			for (int i = 0; i < eList.size(); i++) {
				build+=eList.get(i).getName();
				build+=" ";
			}
			System.out.println("You are beeing attacked by "+build);
			System.out.println("Do u wish to fight y/n:");
			Enemy currentenemy=eList.get(0);




			in=input.nextLine();
			
			while(in.compareTo("y")==0){
				in="";
				dice.roll();
				if(dice.getFaceValue()==1){
					System.out.println("Noooo! you damaged yourselfe and loose 1 in life...");
					player.decrementLive(1);
					if(player.getLive()<1){
						System.out.println("Ohh nooooo you dead, you suck big time! game over!");
					}
				}else if (dice.getFaceValue()==2) {
					System.out.println("Miss!...");
				}else if (dice.getFaceValue()==3) {
					if(currentenemy.getLive()>1){
						System.out.println("Hit! 1 x damage...");
						currentenemy.decrementLive(player.getDamage());
					}else{
						System.out.println("You killed one enemy, now i kill your mom!...");
						eList.remove(0);
						enemyKilled=true;
						if(!eList.isEmpty()) currentenemy=eList.get(0);
					}
				}else if(dice.getFaceValue()==4) {
					if(currentenemy.getLive()>1){
						System.out.println("Hit! 1 x damage...");
						currentenemy.decrementLive(player.getDamage());
					}else{
						System.out.println("You killed one enemy, now i kill your mom!...");
						eList.remove(0);
						enemyKilled=true;
						if(!eList.isEmpty()) currentenemy=eList.get(0);
					}
				}else if (dice.getFaceValue()==5) {
					if(currentenemy.getLive()>1){
						System.out.println("Hit! 2 x damage...");
						currentenemy.decrementLive(player.getDamage()*2);
					}else{
						System.out.println("You killed one enemy, now i kill your mom!...");
						eList.remove(0);
						enemyKilled=true;
						if(!eList.isEmpty()) currentenemy=eList.get(0);
					}
					//double damage
				}else if (dice.getFaceValue()==6) {
					System.out.println("Yeah you killed the monster with one punch!...");
					//instant kill
					eList.remove(0);
					enemyKilled=true;
					if(!eList.isEmpty()) currentenemy=eList.get(0);
				}
				if(!enemyKilled){
					System.out.println("You know have " + player.getLive() +" in life" + " the enmy has " + currentenemy.getLive() + " in life " + "do you still want to fight y/n:");
					in=input.nextLine();
					while(in.compareTo("n")!=0 && in.compareTo("y")!=0){
						in="";
						System.out.println("not a valid command");
						in=input.nextLine();

					}
				}else{
					in="n";
				}
			}
			//scanner.close();
		}
	}
}
