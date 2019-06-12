package meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Route_temps {

	private ArrayList<Edge> route ;
	private ArrayList<Integer[]> flots ;
	
	
	public Route_temps()
	{
		this.route = new ArrayList<Edge>() ;
		this.flots = new ArrayList<Integer[]>() ;
	}
	
	// reset
	public void reset()
	{
		Iterator<Integer[]> it = this.flots.iterator() ;
		while(it.hasNext())
		{
			Integer[] f = it.next() ;
			for(int i=0; i<f.length;i++)
			{
				f[i]=0 ;
			}
		}
	}
	
	
	
	// ------- Getter
	public ArrayList<Edge> getRoute()
	{
		return this.route ;
	}
	public ArrayList<Integer[]> getFlots()
	{
		return this.flots ;
	}
	public int getSize()
	{
		return this.route.size() ;
	}
	// -------- Add
	public void addToRoute(Edge arc)
	{
		this.route.add(arc) ;
		Integer[] tab = new Integer[arc.getLength()] ;
		Arrays.fill(tab, 0);
		this.flots.add(tab) ;
		
	}
	
	// -------- Setter

	public void setRoute(ArrayList<Edge> route)
	{
		this.route=route;
		this.flots=new ArrayList<Integer[]>()	;
		for (int i=0;i<this.route.size();i++)
		{
			this.flots.add(new Integer[route.get(i).getLength()]) ;
		}
	}
	// --------- Fonctions
	// dit si une route est vide
	public boolean isRouteVide()
	{
		Iterator<Integer[]> it = this.flots.iterator() ;
		while(it.hasNext())
		{
			Integer[] tab = it.next();
			for(int i =0;i<tab.length;i++)
			{
				if (tab[i]!=0)
				{
					return false ;
				}
			}
		}
		return true ;
	}
	
	// retourne le flot de sortie du tableau et fait avancer les flots
	// met aussi à jour les edges
	public int avanceFlots(int index, int flot_prec, Edge e)
	{
		int flot_courant = flot_prec ;
		int aux ;
		// recup le flot de l'edge
		int[] flot_e = e.getFlot() ;
		// on trouve le tableau flots
		Integer[] flot = this.flots.get(index) ;

		// on le parcourt pour avancer
		for(int i=0;i<flot.length;i++)
		{
			aux = flot[i] ;
			flot[i] = flot_courant ;
			flot_e[i] = flot_e[i]+ flot_courant ;
			flot_courant = aux ;
		}
		//for (int i=0;i<flot_e.length;i++) {
		//	flot_e[i]=flot_e[i]+flot[i];
	//	}
		
		
		
		return flot_courant ;
	}
	
	public String toString() {
		String tab ="";
		int index =0;
		
		Iterator<Integer[]> it= this.flots.iterator();
		while (it.hasNext())
		{
			tab = tab+ this.route.get(index).getNode1() + " "+this.route.get(index).getNode2()+ " ";
			Integer[] flot = it.next() ;
			for(int i=0;i<flot.length;i++)
			{
				tab = tab+flot[i] ;
			}
			index++;
			tab = tab + "\n" ;
		}
		return tab ;
	}
}
