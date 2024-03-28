package fr.ecole3il.rodez2023.carte.chemin.elements;

import fr.ecole3il.rodez2023.carte.elements.Case;

import java.util.*;
/**
 * La classe Graphe représente un graphe pondéré utilisé pour la recherche de chemin.
 *
 * @param <E> le type générique pour les éléments du graphe
 */
public class Graphe<E> {

    private List<Noeud<E>> adjacence; // Liste des nœuds du graphe
    private Map<AbstractMap.SimpleEntry<Noeud<E>, Noeud<E>>, Double> lesAretes = new HashMap<>(); // Map des arêtes avec leur coût associé

    /**
     * Construit un nouveau graphe vide.
     */
    public Graphe() {
        this.adjacence = new ArrayList<>();
    }

    /**
     * Ajoute un nœud au graphe s'il n'existe pas déjà.
     *
     * @param noeud le nœud à ajouter
     */
    public void ajouterNoeud(Noeud<E> noeud) {
        if (!adjacence.contains(noeud)) {
            adjacence.add(noeud);
        }
    }

    /**
     * Ajoute une arête pondérée entre deux nœuds du graphe.
     *
     * @param depart  le nœud de départ de l'arête
     * @param arrivee le nœud d'arrivée de l'arête
     * @param cout    le coût de l'arête
     */
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        // Vérifier si l'arête n'existe pas déjà entre les deux nœuds
        if (getCoutArete(depart, arrivee) == 0) {
            lesAretes.put(new AbstractMap.SimpleEntry<>(depart, arrivee), cout);
        }
    }

    /**
     * Récupère la liste des nœuds du graphe.
     *
     * @return la liste des nœuds du graphe
     */
    public List<Noeud<E>> getNoeuds() {
        return adjacence;
    }

    /**
     * Récupère la liste des nœuds voisins d'un nœud donné.
     *
     * @param noeud le nœud dont on veut les voisins
     * @return la liste des nœuds voisins du nœud donné
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        return noeud.getVoisins();
    }

    /**
     * Récupère le coût de l'arête entre deux nœuds donnés.
     *
     * @param depart   le nœud de départ de l'arête
     * @param arrivee  le nœud d'arrivée de l'arête
     * @return le coût de l'arête entre les deux nœuds, ou 0 si l'arête n'existe pas
     */
    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        AbstractMap.SimpleEntry<Noeud<E>, Noeud<E>> key = new AbstractMap.SimpleEntry<>(depart, arrivee);
        return (lesAretes.containsKey(key)) ? lesAretes.get(key) : 0;
    }

    /**
     * Récupère le nœud du graphe aux coordonnées spécifiées.
     *
     * @param x la coordonnée x du nœud
     * @param y la coordonnée y du nœud
     * @return le nœud du graphe aux coordonnées spécifiées, ou null s'il n'existe pas
     */
    public Noeud<E> getNoeud(int x, int y) {
        for (Noeud<E> noeud : this.getNoeuds()) {
            Case caseActuelle = (Case) noeud.getValeur();
            if (caseActuelle.getX() == x && caseActuelle.getY() == y) {
                return noeud;
            }
        }
        return null;
    }
}
