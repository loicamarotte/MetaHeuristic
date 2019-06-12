package meta;

import java.util.ArrayList;

public class Node 
{
	private int id ;
	
	// ---------- constructeur
	public Node (int id)
	{
		this.id=id;
	}

	// ----------- Getter
	public int getId() {
		return id;
	}

	
	public String toString()
	{
		return "" + this.id ;
	}


}
