import java.util.*;
/*
 * The class that will keep track of what's going on in the game
 * 1. Ask for number of players
 * 2. Create GP and have it initialize board
 * 3. Create GUI/build board
 * 4. Create front end interface
 * 
 */
public class RunGame {
	private static Scanner sc = new Scanner(System.in);
	private static GameLogic gl;
	private static Player[] players;
	private int playerCount;
	
	//RUN WITH NUMBER OF PLAYERS AS COMMAND LINE ARGUMENT
	public static void main (String[] args){
		//creates Player classes for each player and stores in players[]
		System.out.println("Enter number of players: ");
		int numPlayers = sc.nextInt();
		playerCount = numPlayers;
		players = new Player[numPlayers+1];
		for(int i=1; i<(numPlayers+1); i++)
			players[i] = new Player(i);
		
		int[][] testBoard = new Board().getTestBoard();
		//pass this to gl 
		gl = new GameLogic(numPlayers);

		//testboard gives a predetermined board
	
		//int[][] board= new Board().getBoard();
		
	//	gl.testCombinedFeatures();
		startGame();

		//Visualizer GUI = new Visualizer(board);
	}
	
	private static void startGame(){
		System.out.println("Let's start playing!");
		System.out.println("Roll");
		roll();
	
	
	}
	private static int roll(){
		//pick a random int between 1 and 6
		Random generator =  new Random();
		int roll = generator.nextInt(6);
		return roll+1;
	}
	
	private int rollForFirst(){
		Random generator =  new Random();
		int first = generator.nextInt(playerCount);
		return first+1;
	}
	
	private void firstRound(int p){
		System.out.println("Player "+ p + "'s turn");
		System.out.println("Where woudl you like to place your first settlement?");
		int vertexNum = sc.nextInt();
	}
}
