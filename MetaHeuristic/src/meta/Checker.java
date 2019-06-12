package meta;

import java.util.ArrayList;
import java.util.Iterator;

public class Checker {

	public static boolean check(Solution sol, Graph graphe)
	{
		boolean fini=false ; // ce boolean passe à vrai dès que toute les populations sont arrivées au bout de leur route
		
		while(!fini)
		{
			fini = true;
			// avant de faire le reste il faut remettre a 0 les flots de tous les arcs du graphe
			graphe.resetFlot();
			
			
			// on recupere le temps courant
			int temps = graphe.getTime() ;
			
			// on parcourt les solutions (ie noeud et date départ)
			Iterator<Solution_Node> it = sol.getListe_nodes().iterator() ;
			while(it.hasNext())
			{
				Solution_Node node_sol_courant = it.next() ;
				int date_node = node_sol_courant.getDate() ;
				
				// on verifie que la date de depart n'est pas negative
				if(date_node<0)
				{
					return false ;
				}
				
				
				// si il doit partir et que la route n'est pas finie
				if(date_node<=temps && !node_sol_courant.getNode().isRouteFinie())
				{
					//faire avancer les gens : on met a jour le  flot de l'arc
					// population - max_rate
					
					Route_temps route_t_courante = node_sol_courant.getNode().getRoute() ;
					ArrayList<Edge> route_courante = route_t_courante.getRoute() ;
					int popu = node_sol_courant.getNode().getPopulation() ;
					int flot_max = node_sol_courant.getTauxEvac() ; //TODO
					
					int flot_prec ;
					
					// on regarde la population restante dans le node à evacuer
					if(popu>0)
					{
						if (popu>flot_max)
						{
							flot_prec = flot_max ;
							node_sol_courant.getNode().setPop(popu-flot_max);
						}
						else
						{
							flot_prec = popu ;
							node_sol_courant.getNode().setPop(0);
						}
					}
					else
					{
						flot_prec = 0 ;
					}

					// on parcourt la route
					for (int i=0; i<route_courante.size();i++) {
						
						// met à jour le flot de l'arc ET  de la route
						flot_prec = route_t_courante.avanceFlots(i, flot_prec, route_courante.get(i));

					}			
					
					//System.out.println(temps);
					//System.out.println(route_t_courante);
					
				}
				
				fini = fini && node_sol_courant.getNode().isRouteFinie() ;
			}
			//on verifie que pour tous les arcs, leur utilisation est inferieure a leur capacite
			// parcourt la liste des arcs
			Iterator<Edge> ite = graphe.getEdge().iterator() ;
			while(ite.hasNext())
			{
				Edge e = ite.next() ;
			//	System.out.println("edge :"+ e.getNode1().getId()+" "+ e.getNode2().getId() + " flot : "+ e.getFlot()[0] + " capa : "+ e.getCapacity());

				if(e.getFlot()[0]>e.getCapacity())
				{
			//		System.out.println("edge :"+ e.getNode1().getId()+" "+ e.getNode2().getId() + " flot : "+ e.getFlot()[0] + " capa : "+ e.getCapacity());
					System.out.println("false temps : "+ temps);
					return false ;					
				}
			}

			// incrémentation du temps
			graphe.incrementTime();
			
		}
		
		graphe.reset();
		
		return true ;
		

	}
}
