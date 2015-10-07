package src;
import java.util.ArrayList;

public class RoomWithMonster extends Room{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Monster MonsterInRoom; 


	//	RoomWithMonster() { 
	//
	//	}

	RoomWithMonster( String aDescription, Monster aMonster) { 
		super(aDescription); 
		MonsterInRoom= aMonster; 
	} 

	RoomWithMonster( String aDescription, String[] someItems, Monster aMonster) { 
		super(aDescription, someItems); 
		MonsterInRoom= aMonster; 
	} 

	@Override public Room getNorth() { 
		if( MonsterInRoom.dead== true ) { 
			return super.getNorth(); 
		} else { 
			System.out.print("You can not go north before the monster is defeated!"); 
			return null; 
		} 
	} 

	@Override public ArrayList<ComplexItem> getComplexItems() { 
		ArrayList<ComplexItem> roomComplexItems= new ArrayList<>(); 
		if(MonsterInRoom.isDead()) { 
			roomComplexItems= super.complexItems; 
			super.complexItems= null; 
			return roomComplexItems; 
		} else { 
			return roomComplexItems; 
		} 
	}

	String use(String item){
		if (item.equalsIgnoreCase("Compas")){ 
			return getCompasDirection(); 
		}

		return item;
	}


	@Override public String enterRoom() { 
		if ( MonsterInRoom.isDead() ) { 
			return this.getDescription() + "\nThere is a dead monster called "+ MonsterInRoom.getName() + " in this room"; 
		} 
		System.out.println(this.getDescription() + "\nThere is the following monster in this room: " + MonsterInRoom.getDescription() + ".\nThemonster is called " + MonsterInRoom.getName()); 
		System.out.println("You are being attacked by the monster in the room..."); 
		System.out.println("The monster has " + MonsterInRoom.hitPoints+ " hitpointsleft!"); 
		return MoriaMines.player.receiveDamage( MonsterInRoom.getDamage()); 
	} 

	private String retaliate() { 
		System.out.println("The monster attacks the player again..."); 
		return MoriaMines.player.receiveDamage( MonsterInRoom.getDamage()); 
	} 

	public String attackWith(String weapon) {

		if (weapon.equalsIgnoreCase("Shortsword")){
			MoriaMines.player.setDamage(6);
			return attack();
		}
		if (weapon.equalsIgnoreCase("Bastardsword")){ 
			MoriaMines.player.setDamage(14);
			return attack();
		}
		if (weapon.equalsIgnoreCase("Poison")){
			int dmg = MoriaMines.player.getDamage();
			String s="";
			if(dmg == 6){
				s+="Your last weapon was short sword";
			}else if(dmg == 14){
				s+="Your last weapon was bastard sword";
			}
			s+=",\nin this round you are using the same weapon but gives 10 more in damage";
			MoriaMines.player.setDamage(10+MoriaMines.player.getDamage());
			attack();
			MoriaMines.player.setDamage(0);
			return s;
		}
		if (weapon.equalsIgnoreCase("hammer")){
			MoriaMines.player.setDamage(1);
			return attack();
		}

		return "You can't attack with that weapon "+weapon;
	}

	@Override public String attack() { 
		if(!MonsterInRoom.isDead()) { 
			MonsterInRoom.receiveDamage(MoriaMines.player.getDamage()); 
			if(MonsterInRoom.hitPoints> 0) { 
				System.out.println("You attacked the monster...\nThe monster has " + MonsterInRoom.hitPoints+ " hitpoints left!"); 
				return retaliate(); 
			}else{ 
				MoriaMines.player.levelUp(); 
				return "You attacked the monster...\nThe monster was killed!"; 
			} 
		}else{ 
			return "The monster is dead and can not be attacked anymore!"; 
		} 
	}





}