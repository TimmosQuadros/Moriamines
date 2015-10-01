import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	private String name;
	private int hitpoints;
	private int damage;
	private int level;
	private int live;
	private Room currentRoom;


	public Player(int startLevel, int hitpoints, int damage,  int live) { //start level = 1 hitpoints = 30
		this.setLevel(startLevel);
		this.hitpoints=hitpoints;
		this.setDamage(damage);
		this.setLive(live);

	}

	public void movePlayer(Room tooRoom){
		if(!(tooRoom.getEnemies().isEmpty())){
			try {
				Fight(tooRoom.getEnemies());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 *
	 * 
	 * 
	 */
	public void Fight(ArrayList<Enemy> eList) throws IOException{ 

		if(!eList.isEmpty()){
			String build = "";
			//Scanner scanner = new Scanner(System.in);
			BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
			Dice dice = new Dice();
			String input="";
			Enemy currentenemy=eList.get(0);
			eList.remove(0);
			for (int i = 0; i < eList.size(); i++) {
				build+=eList.get(i).getName();
				build+=" ";
			}
			System.out.println("You are beeing attacked by "+build);
			System.out.println("Do u wish to fight y/n:");
			
			
			input=scanner.readLine();
			//input=scanner.next();
			while(input.compareTo("y")==0 && !eList.isEmpty()){
				input="";
				dice.roll();
				if(dice.getFaceValue()==1){
					System.out.println("Noooo! you damaged yourselfe and loose 1 in life...");
					this.decrementLive(1);
					if(this.live<1){
						System.out.println("Ohh nooooo you dead, you suck big time! game over!");
					}
				}else if (dice.getFaceValue()==2) {
					System.out.println("Miss!...");
				}else if (dice.getFaceValue()==3) {
					if(currentenemy.getLive()>1){
						System.out.println("Hit! 1 x damage...");
						currentenemy.decrementLive(this.damage);
					}else{
						System.out.println("You killed one enemy, now i kill your mom!...");
						currentenemy=eList.get(0);
					}
				}else if(dice.getFaceValue()==4) {
					if(currentenemy.getLive()>1){
						System.out.println("Hit! 1 x damage...");
						currentenemy.decrementLive(this.damage);
					}else{
						System.out.println("You killed one enemy, now i kill your mom!...");
						currentenemy=eList.get(0);
					}
				}else if (dice.getFaceValue()==5) {
					if(currentenemy.getLive()>1){
						System.out.println("Hit! 2 x damage...");
						currentenemy.decrementLive(this.damage*2);
					}else{
						System.out.println("You killed one enemy, now i kill your mom!...");
						currentenemy=eList.get(0);
					}
					//double damage
				}else if (dice.getFaceValue()==6) {
					System.out.println("Yeah you killed the monster with one punch!...");
					//instant kill
					currentenemy=eList.get(0);
				}
				System.out.println("You know have " + this.live +" live" + "do u still want to fight y/n:");
				input=scanner.readLine();
			}
			scanner.close();
		}else{
			System.out.println("You got lucky there no enemies, but you still suck...");
		}
	}


	public int getDamage() {
		return damage;
	}


	public void setDamage(int damage) {
		this.damage = damage;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getLive() {
		return live;
	}


	public void setLive(int live) {
		this.live = live;
	}

	public void decrementLive(int amount){
		live=live-amount;
	}
	public void incrementLive(int amount){
		live=live+amount;
	}






}
