package meta;

public class Solution_Node {

	private Node_evac node ;
	private int date ;
	private int taux_evac;
	
	public Solution_Node(Node_evac n, int d, int r)
	{
		this.node = n ;
		this.date = d ;
		this.taux_evac = r;
	}
	
	// getter
	public Node_evac getNode()
	{
		return this.node ;
	}
	
	public int getDate()
	{
		return this.date ;
	}
	
	// fonctions
	public String toString()
	{
		return " node: "+ this.node.getId() + " depart: " + this.date ;
	}
	
	public int getTauxEvac() {
		return this.taux_evac;
	}
}
