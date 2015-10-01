
public class RoomWithLock extends Room {
	boolean isLocked=false;

	public RoomWithLock(boolean isLocked) {
		super();
		this.isLocked=isLocked;
		System.out.println(this.textDescription);
	}

	@Override
	public Room getExitNorth() {

		if(!isLocked){
			return super.getExitNorth();	
		}

		return null;

	}
}
