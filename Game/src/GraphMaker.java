/*JE
 * Class that will take the int[][] board (the resource tiles)and create a graph containing vertices and edges according to a fixed order.
 * 
 */
public class GraphMaker {
	static int[][] board;
	static Tile[] tilesInBoard;
	
	//each list in adjacentSpots contains the index within tilesInBoard that a given vertex lies between
	static int[][] adjacentSpots = {
			{ 0 },
			{ 0 },
			{ 0},
		  { 0, 1 },
		  { 1 },
		  { 1,2},
		  {2},
		  {2},
		  {2,6},
		  {2,6,5},
		  {2,5,1},
		  {5,4,1},
		  {1,4,0},
		  {4,3,0},
		  {3,0},
		  {0},
		};
	
	
	public GraphMaker(int[][] boardFromGamePlayer){
		board = boardFromGamePlayer;
		convertBoardToTiles();
		createGraph();
	}
	
	//method will decode the int[][]board into a single Tiles[]
	private static void convertBoardToTiles(){
		boolean debugCB = false;
		
		tilesInBoard = new Tile[board[0].length];
		//board[0][i]= the resource type of tile i
		//board[1][i] = the probability of tile i
		for (int i=0; i<board[0].length; i++){
			Tile newTile = new Tile(board[0][i], board[1][i]);
			if (debugCB){
				System.out.println("Created tile "+i);
				newTile.printTile();
			}
			tilesInBoard[i] = newTile;
		}
	}
	
	
	//Go through the board and create vertices 
	private static void createGraph(){
		boolean debugCG = true;		
		//adjacentTiles[0] = the resource type of tile x, adjacentTiles[1]= roll #
		
		//the first vertex will be on the upper left tile and we are assuming no port (only one resource)

		Vertex top = new Vertex(new Tile[1]);
		
	
		for (int i= 0; i < adjacentSpots.length; i++){
			//looking at first vertex
			int[] indices = adjacentSpots[i];
			if (false){
				System.out.println("Indices "+ indices[0]);
				System.out.println("Indices.length"+indices.length);
			}
			//indices of the tiles, now create tiles[]
			Tile[] adjTiles = new Tile[indices.length];
			for (int j=0; j<indices.length; j++){
				adjTiles[j] = tilesInBoard[j];
				tilesInBoard[j].printTile();
			}
			Vertex toAdd = new Vertex(adjTiles);
			if (debugCG){
				System.out.println("Created vertex "+i);
				System.out.println("Vertex lies between Tiles:");
				toAdd.printResources();
			}
			//add toAdd to graph
		}
		
	}
}