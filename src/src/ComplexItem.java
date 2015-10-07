package src;
import java.io.Serializable;

public class ComplexItem implements Serializable{ 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String description; 

	public ComplexItem(String aName, String aDescription){ 
		name= aName; 
		description= aDescription; 
	} 
	public String getName(){ 
		return name; 
	} 

	public String toString() { 
		return description; 
	} 

}