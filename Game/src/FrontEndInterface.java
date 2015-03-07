/*
 * This is the class that we will use to communicate with the GUI and interpret requests from players
 * 
 * Game flow will be
 * 1. User makes an action
 * 2. FrontEndInterface gets request
 * 3. FEI sends request to GamePlayer
 * 4. GamePlayer gives result to FEI, which will call redraw on GUI
 */
public class FrontEndInterface {

	static RunGame rg;
	static Hexanew h;
	public static int currentPlayerID;
	
	public FrontEndInterface (RunGame r){
		rg = r;
		h = new Hexanew( this );
	}
	
	public FrontEndInterface (){
		h = new Hexanew( this );
		currentPlayerID = 1;
	}

	public static void settyClicked (){
		rg.setActionType(1);
		System.out.println("Setty Clicked in FRONT END INTERFACE");
	}
	
	public static void cityClicked (){
		rg.setActionType(2);
	}
	
	public static void vertexClicked (int v){
		rg.setVertex(v);
		System.out.println("Vertex "+v+" Clicked in FRONT END INTERFACE");
		drawSettlement(v);
	}

	public static int[] diceClicked (){
		System.out.println("Dice Clicked in FRONT END INTERFACE");
	//	int r1 = rg.roll();
	//	int r2 = rg.roll();
		int[] toPass = new int[]{3,3};
		currentPlayerID = 3;
		return toPass;
	}

	public static void drawSettlement(int v){
		h.buildSettlement(v);
	}

	public static void nullClick(){
	//reset all action values and vertex etc
	}

	public static void main(String [] args){
		new FrontEndInterface();
	}
	
}
