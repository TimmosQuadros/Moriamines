

public class World {


	private Room[][] roomsInWorld;

	public World() {
		Generator g = new Generator();
		setRoomsInWorld(g.generateRooms());
	}

	public Room[][] getRoomsInWorld() {
		return roomsInWorld;
	}

	public void setRoomsInWorld(Room[][] roomsInWorld) {
		this.roomsInWorld = roomsInWorld;
	}

}





