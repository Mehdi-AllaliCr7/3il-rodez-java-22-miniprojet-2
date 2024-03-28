package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.List;

public class Noeud <E> {
    private E valeur; // La valeur associée au nœud
    private List<Noeud<E>> voisins; // La liste des voisins du nœud

    /**
     * Construit un nouveau nœud avec la valeur spécifiée.
     *
     * @param valeur la valeur associée au nœud
     */
    public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new ArrayList<>();
    }

    /**
     * Récupère la valeur associée au nœud.
     *
     * @return la valeur associée au nœud
     */
    public E getValeur() {
        return valeur;
    }

    /**
     * Ajoute un nœud voisin à ce nœud, s'il n'existe pas déjà et n'est pas lui-même.
     *
     * @param voisin le nœud voisin à ajouter
     */
    public void ajoutNoeud(Noeud<E> voisin) {
        if (voisin != this && !voisins.contains(voisin)) {
            voisins.add(voisin);
            voisin.getVoisins().add(this);
        }
    }

    /**
     * Récupère la liste des voisins de ce nœud.
     *
     * @return la liste des voisins de ce nœud
     */
    public List<Noeud<E>> getVoisins() {
        return voisins;
    }
}
