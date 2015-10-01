
public class Enemy {

	private String name;

	private int hitPoints; //Points that it gives to kill this enemy 1..10
	private int live; //How much live the enemy has 1.100
	private int damage; //How much damage this enemy can give to other players 1.100

	public Enemy getSkeleton(){
		this.setName("Skeleton warier");
		this.hitPoints=1;
		this.setLive(10);
		this.setDamage(1);
		return this;
	}
	
	public Enemy getZombie(){
		this.setName("Zombie");
		this.hitPoints=3;
		this.setLive(15);
		this.setDamage(3);
		return this;
	}
	
	public Enemy getWarden(){
		this.setName("Warden");
		this.hitPoints=3;
		this.setLive(1);
		this.setDamage(3);
		return this;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
