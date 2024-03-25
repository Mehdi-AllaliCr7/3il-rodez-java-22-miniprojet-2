package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.List;

public class Noeud <E> {
	private E valeur;
	private List<Noeud<E>> voisins;
	
	public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new ArrayList<>();
    }
	
	public E getValeur() {
        return valeur;
    }
	
	public void ajoutNoeud(Noeud<E> voisin) {
        voisins.add(voisin);
    }
	
	public List<Noeud<E>> getVoisins() {
        return voisins;
    }
}
