package meta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lecteur 
{
	// TODO: readSolution(String fichier)
	
	public static Graph readData(String fichier)
	{
		Graph graphe = new Graph() ;
		BufferedReader reader ;
		try
		{	
			File f = new File(fichier);
			//récupération de la ligne
			reader = new BufferedReader(new FileReader(fichier)) ;
			String ligne = reader.readLine() ;
			// on passe les lignes d'infos
			ligne = reader.readLine() ;
			ligne = reader.readLine() ;

			
			boolean bool_node = true;

			ArrayList<ArrayList<Integer>> tab_route = new ArrayList<ArrayList<Integer>>();
			
			// stock les nodes utiles
			ArrayList<Integer> nodes_utiles = new ArrayList<Integer>() ;
			
			while (ligne != null)
			{
				// on coupe les mots
				String[] mots = ligne.split(" ",0) ;
				// traitement de la ligne
				// partie nodes
				if (bool_node)
				{
					// on passe les lignes d'infos
					if(mots[0].equals("c"))
					{
						bool_node=false;	
						ligne = reader.readLine() ;
					}
					else
					{
						// on ajoute le node
						Node_evac node = new Node_evac(Integer.parseInt(mots[0]),Integer.parseInt(mots[1]),Integer.parseInt(mots[2])) ;
						graphe.addNode(node);
						graphe.addNode_Evac(node);
						// on stock sa route
						ArrayList<Integer> route = new ArrayList<Integer>() ;
						///// ON n'oublie pas d'ajoute le premier arc à la route
						route.add(node.getId()) ;
						for(int i=4;i< mots.length ; i++)
						{
							int inode = Integer.parseInt(mots[i]) ;
							// on ajoute à la route
							route.add(inode) ;
							// on ajoute au graphe si ils ne sont pas déjà dedans
							if (graphe.findNodeID(inode)==null)
							{
								graphe.addNode(new Node(inode));
							}
							
						}
						tab_route.add(route) ;
					}
				}
				// partie arcs
				else
				{
					if ((mots.length > 2))
					{
						// on trouve les nodes qui sont dans le graphe ==  les nodes utiles
						Node node1 = graphe.findNodeID(Integer.parseInt(mots[0]));
						Node node2 = graphe.findNodeID(Integer.parseInt(mots[1]));
						
						// si ils y sont, on ajoute l'arc utile
						if(node1!=null && node2!=null)
						{
							double duedate = Double.parseDouble(mots[2]) ;
							int length = Math.round(Float.parseFloat(mots[3])) ;		
							int capacity = Math.round(Float.parseFloat(mots[4])) ;
							graphe.addArc(new Edge(node1,node2,duedate,length,capacity));
						}
					}
				}			

				// fin ligne
				ligne = reader.readLine() ;
			}
			
			// ajouter la route aux nodes
			for(int i = 0 ; i< tab_route.size() ; i++)
			{
				ArrayList<Integer> route_courante = tab_route.get(i) ;
				
				// parcours la route
				for(int j = 0 ; j<(route_courante.size()-1) ; j++)
				{
					Edge arc = graphe.findEdgeFromNodesIndex(route_courante.get(j), route_courante.get(j+1)) ;
					((Node_evac)(graphe.getNodeEvacIndex(i))).addArc(arc);
				}
			}
			
			
			
			reader.close();
			graphe.setFilename(f.getName());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		graphe.setAverages();
		
		return graphe ;
	}
}
