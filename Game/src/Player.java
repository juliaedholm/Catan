//CJ

public class Player {

	private int playerID;
	private int victoryPoints;
	private boolean largestArmy;
	private boolean longestRoad;

	private int numberOfSettlements; //max 5
	private int numberOfCities; //max 4
	private int numberOfRoads; //max 15
	
	private Ports portTracker;

	private ResourceCards resourceTracker;
	private DevCards dCardTracker;
	//settlements and cities and roads are pointed to from the graph


	public Player(int id){
		playerID = id;
		victoryPoints = 0;

		numberOfSettlements=0;
		numberOfRoads=0;
		numberOfCities=0;

		resourceTracker = new ResourceCards();

		dCardTracker = new DevCards();

		portTracker = new Ports();
	}

	public int[] getPlayerStats(){
		//this method returns an array with the following information: 
		//index 0 number of sheep
		// 1 numer of rock
		// 2 number of wheat
		// 3 number of brick
		// 4 number of wood
		// 5 number of victory points
		// 6 number of played knights
		// 7 number of unplayed knights
		// 8 number of road builders
		// 9 number of monopoly cards
		// 10 number of year of plenty cards

		int[] stats = new int[6];
		stats[0] = resourceTracker.getSheep();
		stats[1] = resourceTracker.getRock();
		stats[2] = resourceTracker.getWheat();
		stats[3] = resourceTracker.getBrick();
		stats[4] = resourceTracker.getWood();
		stats[5] = victoryPoints;
		stats[6] = dCardTracker.armySize();
		stats[7] = dCardTracker.getK();
		stats[8] = dCardTracker.getRB();
		stats[9] = dCardTracker.getM();
		stats[10] = dCardTracker.getYoP();

		return stats;

	}

	public void addResource(int i, int q){
		if(i==1)
			resourceTracker.addRock(q);
		if(i==2)
			resourceTracker.addWheat(q);
		if(i==3)
			resourceTracker.addBrick(q);
		if(i==4)
			resourceTracker.addWood(q);
		if(i==5)
			resourceTracker.addSheep(q);
	}

	public boolean buildSetCheck(){
		if(resourceTracker.getSheep()<1 || resourceTracker.getWheat()<1 || resourceTracker.getWood()<1 || resourceTracker.getBrick()<1){
			System.out.println("You do not have enough resources to build a settlement.");
			return false;
		}
		if(numberOfSettlements==5){
			System.out.println("You have already built the maximum number of settlements.");
			return false;
		}
		return true;
	}

	public void placeSettlement(){
		numberOfSettlements ++;
		victoryPoints++;
	}
	
	public void buildSettlement(){
		resourceTracker.useSheep(1);
		resourceTracker.useWheat(1);
		resourceTracker.useWood(1);
		resourceTracker.useBrick(1);
		placeSettlement();
	}

	public void addPort(int i){
		if(i==0)
			portTracker.addThreePort();
		if(i==1)
			portTracker.addRockPort();
		if(i==2)
			portTracker.addWheatPort();
		if(i==3)
			portTracker.addBrickPort();
		if(i==4)
			portTracker.addWoodPort();
		if(i==5)
			portTracker.addSheepPort();
	}

	public boolean buildDevCheck(){
		if(resourceTracker.getSheep()<1 || resourceTracker.getWheat()<1 || resourceTracker.getRock()<1){
			System.out.println("You do not have enough resources to build a development card.");
			return false;
		}
		return true;
	}

	public boolean buildDev(int i){
		resourceTracker.useSheep(1);
		resourceTracker.useRock(1);
		resourceTracker.useWheat(1);

		if(i == 0)
			dCardTracker.addKnight();

		if(i == 1){
			victoryPoints++;
			dCardTracker.addVictory();
		}

		if(i == 2)
			dCardTracker.addRoadBuilder();

		if(i == 3)
			dCardTracker.addMonopoly();

		if(i == 4)
			dCardTracker.addYearOfPlenty();
	}

	/*public boolean useDevCard(int i){
		boolean build;
		if(i==0){
			build = dCardTracker.useKnight();
			if(build){
				//initiate knight sequence
				return true;
			}
			System.out.println("You do not have a knight to use.")
			return false;
		}

		if(i==2){
			build=dCardTracker.useRoadBuilder();
			if(build){
				//build two roads

			}	
		}
	}*/

	public void sevenRoll(){
		int total = resourceTracker.getNumber();
		if (total>7){
			for (int i=0; i<(total/2); i++)
				resourceTracker.randomDelete();
		}
	}

	public boolean buildCityCheck(){
		if(resourceTracker.getWheat()<2 || resourceTracker.getRock()<3){
			System.out.println("You do not have enough resources to build a city.");
			return false;
		}
		if(numberOfSettlements==4){
			System.out.println("You have already built the maximum number of cities.");
			return false;
		}
		return true;
	}

	public void buildCity(){
		resourceTracker.useRock(3);
		resourceTracker.useWheat(2);
		numberOfCities++;
		victoryPoints++;
	}

	public boolean buildRoadCheck(){
		if(resourceTracker.getBrick()<1 || resourceTracker.getWood()<1){
			System.out.println("You do not have enough resources to build a road.");
			return false;
		}
		if(numberOfRoads==15){
			System.out.println("You have already built the maximum number of roads.");
			return false;
		}
		return true;
	}
	
	public void placeRoad(){
		numberOfRoads++;
	}
	
	public void buildRoad(){
		resourceTracker.useBrick(1);
		resourceTracker.useWood(1);
		placeRoad();
	}
	
	public int getID(){
		return playerID;
	}
	
	public void printStats(){
		System.out.println("Stats for player: "+playerID);
		System.out.println("Number of Settlements: " +numberOfSettlements);
		System.out.println("Number of Cities: " +numberOfCities);
		System.out.println("Number of Roads: " +numberOfRoads);
		System.out.println("Victory Points: " +victoryPoints);
	}


}
