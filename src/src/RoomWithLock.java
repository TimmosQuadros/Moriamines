package src;
public class RoomWithLock extends Room {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean lockNorth=false; 
	private Boolean lockSouth=false;
	private Boolean lockEast=false;
	private Boolean lockWest=false;

	public RoomWithLock(String aDescription,int lockDoorNumber){ 
		super(aDescription); 
		if(lockDoorNumber==1) { 
			lockNorth=true; 
		}else if(lockDoorNumber==2){
			lockEast=true;
		}else if(lockDoorNumber==3){
			lockSouth=true;
		}else if(lockDoorNumber==4){
			lockWest=true;
		}
	}

	@Override public Room getNorth() { 
		if( lockNorth== false ) { 
			return super.getNorth(); 
		} else { 
			return null; 
		} 
	}
	
	@Override public Room getSouth() { 
		if( lockSouth== false ) { 
			return super.getSouth(); 
		} else { 
			return null; 
		} 
	}
	
	@Override public Room getEast() { 
		if( lockEast == false ) { 
			return super.getEast(); 
		} else { 
			return null; 
		} 
	}
	
	@Override public Room getWest() { 
		if( lockWest == false ) { 
			return super.getWest(); 
		} else { 
			return null; 
		} 
	}

	public void unlockNorth(){
		lockNorth= false; 
	}
	
	public void unlockSouth(){
		lockSouth= false; 
	}
	
	public void unlockEast(){
		lockEast= false; 
	}
	
	public void unlockWest(){
		lockWest= false; 
	}

	String use(String item){
		
		if (item.equalsIgnoreCase("Compas")){ 
			return getCompasDirection(); 
		}
		if (!item.equalsIgnoreCase("Screwdriver")){ 
			return"I do not know how to apply the "+ item + " here"; 
		} 
		if (this.getNorth() != null) { 
			return"The door is already open"; 
		} 
		lockNorth= false; 
		return"You insert the screwdriver in the small hole in the floor... " + "\nThere is a large rumble and you see a door open which was not visible before"; 
	}
	
	public Boolean getLockNorth(){
		return this.lockNorth;
	}
	public Boolean getLockSouth(){
		return this.lockSouth;
	}
	public Boolean getLockEast(){
		return this.lockEast;
	}
	public Boolean getLockWest(){
		return this.lockWest;
	}

}