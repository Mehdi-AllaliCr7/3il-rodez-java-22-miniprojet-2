package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme;
import fr.ecole3il.rodez2023.carte.chemin.elements.*;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.*;

public class AlgorithmeAEtoile <E> implements AlgorithmeChemin<E> {
    /**
     * Trouve le chemin le plus court entre deux nœuds dans un graphe en utilisant l'algorithme A*.
     *
     * @param graphe   le graphe dans lequel rechercher le chemin
     * @param depart   le nœud de départ
     * @param arrivee  le nœud d'arrivée
     * @return une liste de nœuds représentant le chemin trouvé, ou une liste vide si aucun chemin n'est trouvé
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> coutTotalEstime = new HashMap<>();
        Map<Noeud<E>, Double> coutReel = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>(Comparator.comparingDouble(coutTotalEstime::get));

        // Initialisation des structures de données
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            coutTotalEstime.put(noeud, Double.POSITIVE_INFINITY);
            coutReel.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }
        coutReel.put(depart, 0.0);
        coutTotalEstime.put(depart, 0.0); // Coût initial estimé du départ est 0

        filePriorite.add(depart);

        // Boucle principale
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudActuel = filePriorite.poll();
            if (noeudActuel.equals(arrivee)) {
                break;
            }
            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                double nouveauCoutReel = coutReel.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                if (nouveauCoutReel < coutReel.get(voisin)) {
                    coutReel.put(voisin, nouveauCoutReel);
                    coutTotalEstime.put(voisin, nouveauCoutReel + estimerCout(voisin, arrivee)); // Mise à jour de l'estimation heuristique
                    predecesseurs.put(voisin, noeudActuel);
                    filePriorite.add(voisin);
                }
            }
        }

        // Reconstruction du chemin
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
     * Estime le coût restant pour atteindre la destination depuis un nœud donné.
     * Cette méthode fournit une heuristique pour guider l'algorithme A*.
     *
     * @param noeud    le nœud actuel
     * @param arrivee  le nœud d'arrivée
     * @return une estimation du coût restant pour atteindre la destination depuis le nœud actuel
     */
    private double estimerCout(Noeud<E> noeud, Noeud<E> arrivee) {
        // Exemple d'estimation heuristique : distance entre les nœuds en utilisant le nombre de pas nécessaires
        return 1.0; // Coût constant pour chaque nœud
    }
    /**
     * Trouve le chemin le plus court entre deux points sur une carte en utilisant l'algorithme A*.
     *
     * @param carte        la carte sur laquelle rechercher le chemin
     * @param xDepart     la coordonnée x du point de départ
     * @param yDepart     la coordonnée y du point de départ
     * @param xArrivee    la coordonnée x du point d'arrivée
     * @param yArrivee    la coordonnée y du point d'arrivée
     * @return un objet Chemin représentant le chemin trouvé
     */
    @Override
    public Chemin trouverChemin(Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        return AdaptateurAlgorithme.trouverChemin((AlgorithmeChemin<Case>) this, carte, xDepart, yDepart, xArrivee, yArrivee);
    }

}
