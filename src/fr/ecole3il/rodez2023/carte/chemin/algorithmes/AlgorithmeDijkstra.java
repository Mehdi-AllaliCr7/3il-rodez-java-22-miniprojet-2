package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme;
import fr.ecole3il.rodez2023.carte.chemin.elements.*;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E>{
    /**
     * Recherche le chemin le plus court entre deux nœuds dans un graphe en utilisant l'algorithme de Dijkstra.
     *
     * @param graphe   le graphe dans lequel rechercher le chemin
     * @param depart   le nœud de départ
     * @param arrivee  le nœud d'arrivée
     * @return une liste de nœuds représentant le chemin le plus court entre le nœud de départ et le nœud d'arrivée
     */

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {



        Map<Noeud<E>, Double> couts = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>(Comparator.comparingDouble(couts::get));

        // Initialisation des structures de données
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }

        couts.put(depart, 0.0);
        filePriorite.add(depart);

        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudActuel = filePriorite.poll();

            if (noeudActuel.equals(arrivee)) {
                break;
            }

            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                double nouveauCout = couts.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                if (nouveauCout < couts.get(voisin)) {
                    couts.put(voisin, nouveauCout);
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

    /**
     * Recherche le chemin le plus court entre deux cases sur une carte en utilisant l'algorithme de Dijkstra.
     *
     * @param carte     la carte sur laquelle trouver le chemin
     * @param xDepart   la coordonnée x de la case de départ
     * @param yDepart   la coordonnée y de la case de départ
     * @param xArrivee  la coordonnée x de la case d'arrivée
     * @param yArrivee  la coordonnée y de la case d'arrivée
     * @return un objet {@code Chemin} représentant le chemin le plus court entre les deux cases spécifiées sur la carte
     */

    @Override
    public Chemin trouverChemin(Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        return AdaptateurAlgorithme.trouverChemin((AlgorithmeChemin<Case>) this, carte, xDepart, yDepart, xArrivee, yArrivee);
    }

}
