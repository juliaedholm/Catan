
public class Vertex {
	private int[] adjacentTiles; //adjacentTiles[0] = the resource type of tile x, adjacentTiles[1]= roll #
	private Player owner;
	private boolean	isOccupied; 
	private int 	settlementType; //1= settlement, 2= city
	
	public Vertex(int[] resources){
		adjacentTiles = resources;
		isOccupied = false;
	}
	
	public void setOwner(Player p){
		owner = p;
		isOccupied = true;
	}
}