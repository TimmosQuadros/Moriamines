package src;
import java.io.Serializable;

public class Monster implements Serializable{ 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Boolean dead; 
	public String name; 
	public String description; 
	public int hitPoints; 
	public int damage; 

	public Monster( String aName, String aDescription, int hitPointsValue, int damageValue) { 
		dead = false; 
		name = aName; 
		description = aDescription; 
		hitPoints= hitPointsValue; 
		damage = damageValue; 
	} 

	public Boolean isDead() { 
		return dead; 
	} 

	public String getName() { 
		return name; 
	} 

	public String getDescription() { 
		return description; 
	} 

	public int getDamage() { 
		return damage; 
	} 

	public String receiveDamage(int amount) { 
		hitPoints-= amount; 
		if(hitPoints<= 0) { 
			dead = true; 
			return "Monster died!"; 
		} else { 
			return "The monster has " + hitPoints+ " hitpointsleft!"; 
		} 
	} 
}