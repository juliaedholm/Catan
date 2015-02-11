/*JE */

public class GamePlayer {
	/*	-Tasks:
	1.initialize the board
	2. create GUI
	3. control user input
	4. update tables/board when user gives input 
	*/
	
	Player[] players;
	
	public GamePlayer(int numPlayers){
		//create stats table for players
		//create Player classes for each player and store in players[]
		int[][] board= new Board().getBoard();
		createGraph(board);
		Visualizer GUI = new Visualizer(board);
		//add event listeners to gUI and figure out how GamePlayer will capture the click events 
	}
	
	
	//Go through the board and create verticies where appropriate
	private static void createGraph(int[][] board){
		//board[0][i]= the resource type of tile i
		//board[1][i] = the probability of tile i
		
		//adjacentTiles[0] = the resource type of tile x, adjacentTiles[1]= roll #
		
		Vertex top = new Vertex({board[0][0],board[1][0]});
		for (int i= 0; i < board.length; i++){
			//looking at first tile
			
		}
	}

	
	private static void playGame(){
		//method that will enable users to roll dice and interact
	}
}