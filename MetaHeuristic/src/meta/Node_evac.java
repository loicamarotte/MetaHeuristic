package meta;

import java.util.ArrayList;
import java.util.Iterator;

public class Node_evac extends Node
{
	private int pop_depart;
	private int population;
	private int max_rate;
	private Route_temps route ;
	private int capa_route ;
	private int temps_min;
	
	// ---------- constructeur
	public Node_evac (int id, int pop,int max_r)
	{
		super(id);
		this.pop_depart=pop;
		this.population=pop;
		this.max_rate=max_r;
		this.route = new Route_temps() ;
		this.temps_min = 0;
	}
	
	// reset
	public void reset()
	{
		this.population = this.pop_depart ;
		this.route.reset() ;
	}
	
	private int find_temps()
	{
		Node_evac ne = this;
		int temps = 0 ;
		
		Iterator<Edge> it = ne.getRoute().getRoute().iterator() ;
		while(it.hasNext())
		{
			Edge e = it.next() ;
			temps = temps + e.getLength() ;
		}
		
		temps = temps + (ne.getPopDepart()/ne.getCapaRoute()) + (ne.getPopDepart()%ne.getCapaRoute()) ;
		return temps ;
	}
	// ----------- Getter
	
	public int getPopulation() {
		return population;
	}
	
	public int getPopDepart()
	{
		return this.pop_depart ;
	}


	public Route_temps getRoute() {
		return route;
	}
	public int getRouteLength()
	{
		return this.route.getRoute().size();
	}
	
	public int getCapaRoute()
	{
		return this.capa_route ;
	}
	
	// ---------------- Setter
	public void setRoute(ArrayList<Edge> route) {
		this.route.setRoute(route);
		this.setCapaRoute();
		this.temps_min=find_temps();
	}
	
	public void setPop(int p)
	{
		this.population=p;
	}
	
	// ----------------- Fonctions
	// ajoute un arc àla route
	public void addArc( Edge arc)
	{
		this.route.addToRoute(arc);
		this.setCapaRoute();
		this.temps_min=find_temps();
	}
	
	// dit si la route est finie
	public boolean isRouteFinie()
	{
		if(this.population==0 && this.route.isRouteVide())
		{
			return true ;
		}
		else
		{
			return false ;
		}
	}
	
	// calcul la capa de la route
	public void setCapaRoute()
	{
		int capa = this.max_rate ;
		Iterator<Edge> it = this.route.getRoute().iterator() ;
		while(it.hasNext())
		{
			Edge e = it.next() ;
			if(e.getCapacity()<capa)
			{
				capa=e.getCapacity();
			}
		}
		this.capa_route=capa;
	}
	
	public int getTempsMin() {
		return this.temps_min;
	}

}
