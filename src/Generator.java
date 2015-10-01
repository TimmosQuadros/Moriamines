import java.util.Random;

public class Generator{

	private int Low=10;
	private int High=15;

	private int rows = new Random().nextInt(High-Low)+Low; //10 to 14 inclusive
	private int colums = new Random().nextInt(High-Low)+Low; //10 to 14 inclusive

	private Position pos;

	int randomNumber;
	Random rnd = new Random();
	//Generates a random number from Low (inclusive) to High (exclusive)
	
	private Room[][] roomsInWorld = new Room[rows][colums];
	

	public Room[][] generateRooms(){
		
		//System.out.println("Rows: "+ rows +"colums: " + colums);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < colums; j++) {
				roomsInWorld[i][j]=new Room();
				roomsInWorld[i][j].setTextDescription(Integer.toString((i*(rows))+j));
				
//				System.out.println(roomsInWorld[i][j].getTextDescription());
				randomNumber=rnd.nextInt(6)+0;
				if(randomNumber==0){
					roomsInWorld[i][j].addEnemies(new Enemy().getSkeleton());
					System.out.println("Added 1 skeleton");
				}else if(randomNumber==5){
					roomsInWorld[i][j].addEnemies(new Enemy().getZombie());
					System.out.println("Added 1 zombie");
				}
				//System.out.println(i+" "+j);
			}
		}
		roomsInWorld[0][0].setPlayer(new Player(0, 10, 1, 30));

		for (int k = 0; k < rows; k++) {
			for (int l = 0; l < colums; l++) {
				//System.out.println(k+" "+l);
				if(goSouth(k, l)!=null){

					pos=goSouth(k, l);
					
					roomsInWorld[pos.getI()][pos.getJ()].setExitNorth(roomsInWorld[k][l]);
					roomsInWorld[k][l].setExitSouth(roomsInWorld[pos.getI()][pos.getJ()]);

				}
				if(goNorth(k, l)!=null){
					pos=goNorth(k, l);
					//System.out.println("s "+pos.getI()+" "+pos.getJ());
					roomsInWorld[pos.getI()][pos.getJ()].setExitSouth(roomsInWorld[k][l]);
					roomsInWorld[k][l].setExitNorth(roomsInWorld[pos.getI()][pos.getJ()]);
				}
				if(goEast(k, l)!=null){
					pos=goEast(k, l);
					roomsInWorld[pos.getI()][pos.getJ()].setExitWest(roomsInWorld[k][l]);
					roomsInWorld[k][l].setExitEast(roomsInWorld[pos.getI()][pos.getJ()]);
				}
				if(goWest(k, l)!=null){
					pos=goWest(k, l);
					roomsInWorld[pos.getI()][pos.getJ()].setExitEast(roomsInWorld[k][l]);
					roomsInWorld[k][l].setExitWest(roomsInWorld[pos.getI()][pos.getJ()]);
				}
			}
		}



		return roomsInWorld;
	}

	public Position goNorth(int i, int j){

		if((i-1)>=0) {
			return new Position(i-1, j);
		}else{
			return null;
		}
	}
	public Position goSouth(int i, int j){

		if(i+1<rows) {
			return new Position(i+1, j);
		}else{
			return null;
		}
	}
	public Position goEast(int i, int j){

		if(j+1<colums) {
			return new Position(i, j+1);
		}else{
			return null;
		}
	}
	public Position goWest(int i, int j){

		if((j-1)>=0) {
			return new Position(i, j-1);
		}else{
			return null;
		}
	}
}
