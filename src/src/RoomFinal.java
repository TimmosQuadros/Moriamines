package src;
import java.util.ArrayList;

public class RoomFinal extends Room {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomFinal(String aDescription) { 
		super(aDescription); 
	} 

	@Override public String enterRoom() { 
		String Message; 
		int Score= 0; 
		ArrayList<ComplexItem> PlayerComplexInventory= MoriaMines.player.getComplexInventory(); 

		for (int i=0; i < PlayerComplexInventory.size(); i++) { 
			if(PlayerComplexInventory.get(i).getName()=="Treasurechest") { 
				ComplexItemTreasure aTreasureChest= (ComplexItemTreasure) PlayerComplexInventory.get(i); 
				Score += aTreasureChest.getTreasureValue(); 
			} 
		} 

		MoriaMines.GameOver=true; 
		Message = "You have reached the final room and your adventure is over!"; 
		Message += "\nYou collected" + Score + " gold!"; 
		return Message; 
	} 

}