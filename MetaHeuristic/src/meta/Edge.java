package meta;

import java.util.Arrays;

public class Edge 
{
	private Node node1 ;
	private Node node2 ;
	private double duedate ;
	private int length ;
	private int capacity ;
	
	private int[] flot ;
	
	// -------------- Constructeur
	public Edge (Node n1, Node n2, double d, int l, int c)
	{
		this.node1=n1 ;
		this.node2=n2 ;
		this.duedate=d;
		this.length=l;
		this.capacity=c;
		
		this.flot = new int[l] ;
	}
	
	// -------------- Getter
	public Node getNode1() {
		return node1;
	}

	public Node getNode2() {
		return node2;
	}

	public double getDuedate() {
		return duedate;
	}

	public int getLength() {
		return length;
	}

	public int getCapacity() {
		return capacity;
	}
	
	public int[] getFlot()
	{
		return this.flot ;
	}
	
	// ----------- Setter
	
	// ----------- Fonctions
	public String toString()
	{
		return this.node1 + " " + this.node2 + " flot : " + this.flot + " capacite " + this.capacity;
	}
	
	public void resetFlot()
	{
		for (int i=0; i<this.flot.length;i++)
		{
			this.flot[i]=0 ;
		}
	}
	

}
