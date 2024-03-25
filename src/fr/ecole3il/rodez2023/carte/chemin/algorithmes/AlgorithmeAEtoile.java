package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

public class AlgorithmeAEtoile <E> implements AlgorithmeChemin<E> {
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {

        Map<Noeud<E>, Double> coutEstime = new HashMap<>();
        Map<Noeud<E>, Double> coutActuel = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>(Comparator.comparingDouble(coutEstime::get));

        for (Noeud<E> noeud : graphe.getNoeuds()) {
            coutEstime.put(noeud, Double.POSITIVE_INFINITY);
            coutActuel.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }
        coutEstime.put(depart, 0.0);
        coutActuel.put(depart, 0.0);
        filePriorite.add(depart);

        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudActuel = filePriorite.poll();
            if (noeudActuel.equals(arrivee)) {
                break;
            }
            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                double nouveauCout = coutActuel.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                if (nouveauCout < coutActuel.get(voisin)) {
                    coutActuel.put(voisin, nouveauCout);
                    double coutTotalEstime = nouveauCout + estimationHeuristique(voisin, arrivee);
                    coutEstime.put(voisin, coutTotalEstime);
                    predecesseurs.put(voisin, noeudActuel);
                    filePriorite.add(voisin);
                }
            }
        }

        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeud = arrivee;
        while (noeud != null) {
            chemin.add(noeud);
            noeud = predecesseurs.get(noeud);
        }
        Collections.reverse(chemin);
        return chemin;
    }

    private double estimationHeuristique(Noeud<E> noeud, Noeud<E> arrivee) {
        // Exemple d'estimation heuristique : la distance à vol d'oiseau
        // Cette méthode doit être adaptée à votre problème spécifique
        // Ici, nous supposons que les nœuds ont des coordonnées (x, y)
        double distanceX = Math.abs(noeud.getX() - arrivee.getX());
        double distanceY = Math.abs(noeud.getY() - arrivee.getY());
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY);
    }
}
