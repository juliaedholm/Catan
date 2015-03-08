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
	private  boolean usingGraphics;
	TurnOrderManager order;
	private  GameLogic gl;
	private  FrontEndInterface fei;
	
	private  Player[] players;
	private  int playerCount;
	private  int currentPlayerID;
	
	private  int actionType; //0= nothing, 1 = settlement, 2 = road, 3 = city
	private  int[] verticesToAct; //at most 2 vertices
	private int vertexCounter;
	public boolean inFirstRound;
	private boolean firstRoundSET;
	private int firstRoundRoadCounter;
	
	public RunGame(int numPlayers, boolean useGraphics){
		players = new Player[numPlayers+1];
		
		for(int i=1; i<(numPlayers+1); i++)
			players[i] = new Player(i);
		this.playerCount = numPlayers;
		order = new TurnOrderManager (numPlayers);
		currentPlayerID = order.getCurrentPlayer();
		verticesToAct = new int[2];
		vertexCounter = 0;
		
		usingGraphics = useGraphics;
		//testboard gives a predetermined board
		//int[][] board= new Board().getBoard();
		int[][] testBoard = new Board().getTestBoard();
		//pass this to gl 
		gl = new GameLogic(testBoard, players);
;
	
		if (usingGraphics){
			//FEI will draw the graph
			fei = new FrontEndInterface (this, testBoard);
			fei.currentPlayerID = currentPlayerID;
			inFirstRound = true;
			firstRoundSET = true;
		} else{
			for (int i=0; i<4; i++){
				currentPlayerID = order.getNextPlayer();
				//turn();
			}
		//	startGame();
		}
	}
	/* void startGame(){
		System.out.println("Let's start playing!");
		for (int i=0; i<turnOrder.length; i++){
			currentPlayerID = turnOrder[i];
			Player currentPlayer = players[currentPlayerID];
			System.out.println("Player who will go is: " +currentPlayerID);
			firstRound(currentPlayer);
		} 
		for (int i=turnOrder.length-1; i>=0; i--){
			currentPlayerID = turnOrder[i];
			Player currentPlayer = players[currentPlayerID];
			System.out.println("Player who will go is: " +currentPlayerID);
			firstRound(currentPlayer);
		}
	}

	private void turn(){
		int r = roll();
		System.out.println("roll was "+r);
		if(r == 7){
			sevenRolled();
		} else {
			gl.diceRoll(r);
		}
		System.out.println("New turn for");
		players[currentPlayerID].printStats();
		actionType = 0;
		System.out.println("Enter your action Type");
		int actionType = sc.nextInt();
		if (actionType == 1){
			System.out.println("Where would you like to place your settlement?");
			vertexToAct = sc.nextInt();
			gl.placeSettlement(currentPlayerID, vertexToAct);
		} else if(actionType == 2){
			System.out.println("What are vertexes you would ilke to place your road between?");
			int v1= sc.nextInt();
			int v2 = sc.nextInt();
			//gl.buildRoad();
		}
	}
	*/
	
	private int roll(){
		//pick a random int between 1 and 6
		Random generator =  new Random();
		int roll = generator.nextInt(6);
		return roll+1;
	}
	
	public int[] rollDice(){
		currentPlayerID = order.getNextPlayer();
		fei.updateCurrentPlayer(currentPlayerID);
		int r1 = roll();
		int r2 = roll();
		gl.diceRoll(r1+r2);
		return new int[] {r1,r2};
	}
	
	private void firstRound(Player p){
		System.out.println("Player "+ currentPlayerID + "'s turn");
		if (!usingGraphics){
			System.out.println("Where would you like to place your settlement?");
			int vertexNum = sc.nextInt();
			gl.placeSettlement(p.getID(),vertexNum);
			System.out.println("What are vertexes you would ilke to place your road between?");
			int v1= sc.nextInt();
			int v2 = sc.nextInt();
		} 
		//gl.buildRoad();
	}
	
	public void vertexClickedFirstRound( int vertex){
		if (firstRoundSET){
			//settlement building part of first round
			System.out.println("vertex: "+vertex+" clicked in first round. Trying to place settlement for player: "+currentPlayerID);
			gl.placeSettlement(currentPlayerID, vertex);
			if(players[currentPlayerID].numberOfSettlements ==2){
				gl.giveResourcesStartGame(vertex);
			}
			fei.drawSettlement(vertex);
			if (!order.firstRoundSettlementDone()){
				currentPlayerID = order.getNextPlayerGameStart();// switch players
				fei.updateCurrentPlayer(currentPlayerID);
			} else {
				firstRoundSET = false;
				firstRoundRoadCounter = 0;
				System.out.println("Click vertexes for Roads");
			}
		} else {
			//road placement part of Round 1
			System.out.println("vertex: "+vertex+" clicked in first round. Trying to place road for player: "+currentPlayerID);
			verticesToAct[vertexCounter] = vertex;
			vertexCounter ++;
			if (vertexCounter == 2){
				if (gl.placeRoad(currentPlayerID, verticesToAct[0], verticesToAct[1])){
					fei.drawRoad(verticesToAct[0], verticesToAct[1]);
					currentPlayerID = order.getNextPlayer();
					firstRoundRoadCounter ++;
					fei.updateCurrentPlayer(currentPlayerID); //switch players
				}
				System.out.println("Road placement didn't work, try again");
				vertexCounter = 0;
				if (firstRoundRoadCounter == 2*playerCount){
					inFirstRound = false;
					System.out.println("Initial Settlement and Road Placement is done");
				}
			}
		}
	}
	
	private void sevenRolled(){
		for(int i=1; i<=players.length; i++)
			players[i].sevenRoll();
			//initiate robber movement stealing sequence (same as for knight)
	}
	
	public void setActionType (int t){
		actionType = t;
	}
	
	public void setVertex (int v){
		verticesToAct[vertexCounter] = v;
	}
}
