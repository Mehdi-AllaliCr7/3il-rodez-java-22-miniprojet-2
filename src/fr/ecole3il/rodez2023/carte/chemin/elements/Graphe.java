package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.*;

public class Graphe<E> {

	private List<Noeud<E>> adjacence;
    private Map<AbstractMap.SimpleEntry<Noeud<E>, Noeud<E>>, Double> lesAretes = new HashMap<>();



    public Graphe() {
        this.adjacence = new ArrayList<>();
    }
    
    public void ajouterNoeud(Noeud<E> noeud) {
        if (!adjacence.contains(noeud)) {
            adjacence.add(noeud);
        }
    }
    
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {


        if (getCoutArete(depart, arrivee) == 0) {
            lesAretes.put(new AbstractMap.SimpleEntry<>(depart, arrivee), cout);
        }
    }
    
    public List<Noeud<E>> getNoeuds() {
        return adjacence;
    }
    
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        return noeud.getVoisins();
    }
    

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        AbstractMap.SimpleEntry<Noeud<E>, Noeud<E>> key = new AbstractMap.SimpleEntry<>(depart, arrivee);
        return (lesAretes.containsKey(key)) ? lesAretes.get(key) : 0;
    }


}
