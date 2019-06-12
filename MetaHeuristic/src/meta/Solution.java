package meta;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class Solution {

	private String nom ;
	private int nbSommet ;
	private ArrayList<Solution_Node> liste_sol_node ;
	private boolean valide ;
	private int objectif ;
	private long temps_calcul ;
	private String methode ;
	
	public Solution (String nom, int nbSommet, ArrayList<Solution_Node> liste_sol_node, boolean valide, int objectif, long temps_calcul, String methode)
	{
		this.nom = nom ;
		this.nbSommet = nbSommet ;
		this.liste_sol_node = liste_sol_node ;
		this.valide = valide ;
		this.objectif = objectif ;
		this.temps_calcul = temps_calcul ;
		this.methode = methode ;
	}
	public Solution (String nom, int nbSommet, ArrayList<Solution_Node> liste_sol_node, int objectif, long temps_calcul, String methode,Graph graphe)
	{
		this.nom = nom ;
		this.nbSommet = nbSommet ;
		this.liste_sol_node = liste_sol_node ;
		this.objectif = objectif ;
		this.temps_calcul = temps_calcul ;
		this.methode = methode ;
		this.valide=Checker.check(this, graphe);
	}
	
	
	public void setNom (String n) {
		this.nom = n;
	}
	
	public void setTempsCalcul (long l) {
		this.temps_calcul = l;
	}
	
	public void setMethode (String s) {
		this.methode=s;
	}
	// ------------------ décalage
	public Solution decale(int d, Solution_Node sn, Graph graphe)
	{
		ArrayList<Solution_Node> listeNode = new ArrayList<Solution_Node>() ;
		Iterator<Solution_Node> it = this.liste_sol_node.iterator() ;
		boolean decalage = false;
		while(it.hasNext())
		{
			Solution_Node n = it.next() ;
			if(n.getNode().getId()==sn.getNode().getId()) {
				decalage = true;
			}
			if(decalage == true) {
				listeNode.add(new Solution_Node(n.getNode(),n.getDate()-d,n.getTauxEvac())) ;
			}
			else {
				listeNode.add(new Solution_Node(n.getNode(),n.getDate(),n.getTauxEvac())) ;
			}
		}
		return new Solution(this.nom,this.nbSommet,listeNode,this.objectif-d,0,this.methode,graphe) ;
	}

	
	// ---------------- Getter
	public ArrayList<Solution_Node> getListe_nodes()
	{
		return this.liste_sol_node ;
	}
	public int getDateFromNode(Node n)
	{
		// parcourt des nodes de la liste solution
		Iterator<Solution_Node> it = liste_sol_node.iterator();
		while(it.hasNext())
		{
			Solution_Node node_sol = it.next() ;
			// si c'est celui qu'on cherche
			if (node_sol.getNode().equals(n))
			{
				// on retourne la date
				return node_sol.getDate();
			}
		}
		return -1 ;
	}
	
	public int getObjectif() {
		return this.objectif;
	}
	
	public boolean getValide()
	{
		return this.valide ;
	}
	
	// ---------------- Fonctions
	public String toString()
	{
		String nodes ="";
		Iterator<Solution_Node> it = this.liste_sol_node.iterator() ;
		while(it.hasNext())
		{
			nodes = nodes + it.next().toString() ;
		}
		return(this.nom + " objectif: "+ this.objectif + " valide: "+ this.valide + " nodes: "+nodes) ;
	}
	
	public void writeToFile() {
		String path = "Solutions\\" + nom;
		String data = this.nom + "\n" + this.nbSommet + "\n";
		
		for (Solution_Node sn : this.liste_sol_node) {
			data = data + sn.getNode().getId() + " " + sn.getTauxEvac() + " " + sn.getDate() + "\n";
		}
		if (this.valide) {
			data = data + "valid \n";
		}
		else {
			data = data + "invalid \n";
		}
		data = data + this.objectif + "\n" + this.temps_calcul + "\n" + this.methode;
		
		try {
			Files.createFile(Paths.get(path));
			Files.write(Paths.get(path), data.getBytes());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
