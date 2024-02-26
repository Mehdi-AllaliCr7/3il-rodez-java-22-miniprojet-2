package fr.ecole3il.rodez2023.carte.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graphe<E> {

	private Map<Noeud<E>, Map<Noeud<E>, Double>> adjacence;
	

    public Graphe() {
        this.adjacence = new HashMap<>();
    }
    
    public void ajouterNoeud(Noeud<E> noeud) {
        if (!adjacence.containsKey(noeud)) {
            adjacence.put(noeud, new HashMap<>());
        }
    }
    
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);

        if (adjacence.get(depart) == null) {
            adjacence.put(depart, new HashMap<>());
        }

        adjacence.get(depart).put(arrivee, cout);
        
    }
    
    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<>(adjacence.keySet());
    }
    
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        if (adjacence.containsKey(noeud)) {
            Map<Noeud<E>, Double> voisinsMap = adjacence.get(noeud);
            return new ArrayList<>(voisinsMap.keySet());
        } else {
            return new ArrayList<>();
        }
    }
}
