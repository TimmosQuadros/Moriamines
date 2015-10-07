package src;
public class ComplexItemTreasure extends ComplexItem { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int TreasureValue; 

	public ComplexItemTreasure(String aName, String aDescription, int Value){ 
		super(aName,aDescription); 
		TreasureValue= Value; 
	} 
	public int getTreasureValue(){ 
		return TreasureValue; 
	} 

}