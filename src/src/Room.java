package src;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Room implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Room exitWest;
	private Room exitNorth;
	private Room exitSouth;
	private Room exitEast;
	private String description;

	public Room(String description)
	{
		this.description = description;
	}   

	public String enterRoom()
	{
		return getDescription();
	}

	public void setNorth(Room exitNorth)
	{
		this.exitNorth = exitNorth;
	}

	public void setSouth(Room exitSouth)
	{
		this.exitSouth = exitSouth;
	}

	public void setEast(Room exitEast)
	{
		this.exitEast = exitEast;
	}

	public void setWest(Room newExitWest)
	{
		exitWest = newExitWest;
	}

	public Room getWest()
	{
		return exitWest;
	}

	public Room getNorth()
	{
		if( exitNorth== null ) { 
			//System.out.print("There is no path leading north"); 
			return null; 
		} else { 
			return exitNorth; 
		} 
	}

	public Room getSouth()
	{
		return exitSouth;
	}

	public Room getEast()
	{
		return exitEast;
	}

	private ArrayList<String> items;

	public Room(String aDescription, String[] someitems){ 
		description = aDescription; 
		items = new ArrayList<>(Arrays.asList(someitems)); 
	} 



	String use(String part){ 
		if (part.equalsIgnoreCase("Compas")){ 
			return getCompasDirection(); 
		}
		
		return "There is nothing here to use the " + part + " for"; 
	}

	public String getCompasDirection(){
		System.out.println("You can move in the following directions: ");
		String directions="";
		if(exitNorth!=null){
			directions+="      [North]\n";
		}
		if(exitWest!=null){
			directions+="[West]";
		}
		if(exitEast!=null){
			directions+="             [East]";
		}
		if(exitSouth!=null){
			directions+="\n      [South]";
		}
		return directions;
	}

	protected ArrayList<ComplexItem> complexItems;

	public Room(String aDescription, ComplexItem[] someItems) { 
		description = aDescription; 
		complexItems= new ArrayList<>( Arrays.asList( someItems) ); 
	} 


	public ArrayList<String> getItems() {
		ArrayList<String> theItems= items; 
		items = null; 
		return theItems; 
	}


	public ArrayList<ComplexItem> getComplexItems() { 
		ArrayList<ComplexItem> theComplexItems= complexItems; 
		complexItems= null; 
		return theComplexItems; 
	} 

	public String getDescription(){ 
		String ItemMessage= description; 
		if (items != null) { 
			ItemMessage+= "\nThere are some items here: " + items.toString(); 
		} 
		if (complexItems!= null) { 
			ItemMessage+= "\nThere are some items here: " + complexItems.toString(); 
		} 
		return ItemMessage;
	}

	public String attack() { 
		return "There is nothing to attack in the room!"; 
	}

	public String attackWith(String weapon) {
		
		return "There is noothin to attack in the room!";
	}






}
