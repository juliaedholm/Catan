
public class Board {
//creates the random map and tile allocation
int[] probabilities;
int[] resources;
int[][] completeBoard;
Vertex head;

	public Board() {
		int n = 36; //number of tiles on board
		probabilities = new int[n];
		resources = new int[n];
		initialize();
		createBoard();
	}
	
	/* 
	 * fill the prob/resources arrays with random order
	 * of possible resources and probabilities
	*/
	private void initialize(){
	
	}
	
	/*method that will go through prob/resource arrays  
	 * and create vertices/edges as appropriate
	 */
	private void createBoard(){
		
	}
	
	
	//method that will give the GamePlayer access to the graph
	public int[][] getBoard(){
		return completeBoard;
	}
}