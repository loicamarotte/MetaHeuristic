package meta;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

public class Intensification 
{
	public static Solution recherche(Graph graphe, Solution solution)
	{
		
		Instant debut = Instant.now();
		
		int decal = (graphe.getAverageEdgeTime()*graphe.getAverageRoadSize())/10;
		System.out.println("decal : "+decal);
		// on parcourt tous les solution nodes
		ArrayList<Solution_Node> listeNode = solution.getListe_nodes() ;
		Iterator<Solution_Node> it = listeNode.iterator() ;
		// attention le premier ne peut etre décalé
		it.next() ;
		while(it.hasNext())
		{
			Solution_Node sn = it.next();
			boolean cond = true ;
			Solution sol_prec = solution ;
			while(cond)
			{
				//System.out.println("while");
				// on bouge les departs de la solution
				Solution sol_test = sol_prec.decale(decal,sn, graphe) ;
				
				//System.out.println("sol_test: "+sol_test);
				
				// si elle n'est pas valide, on s'arrete et on renvoie la solution precedente
				if(!sol_test.getValide())
				{
					cond=false;
				}
				// si elle est valide, on la garde et on continue
				else
				{
					sol_prec = sol_test;
				}
			}
			
			// on recupere la meilleure pour cette route
			solution = sol_prec ;
			
			// on passe à la suivante
			}
		
		Instant fin = Instant.now();
		solution.setNom("intens_"+ graphe.getFilename() );
		solution.setMethode("Intensification");
		solution.setTempsCalcul(Duration.between(debut, fin).toMillis());
		return solution ;	
	
	}
	
	
	
	
}
