package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.*;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.elements.Tuile;

import java.util.ArrayList;
import java.util.List;

public class AdaptateurAlgorithme {


    /**
     * Trouve le chemin le plus court entre deux cases sur la carte en utilisant l'algorithme spécifié.
     *
     * @param algorithme l'algorithme de recherche de chemin à utiliser
     * @param carte      la carte sur laquelle trouver le chemin
     * @param xDepart    la coordonnée x de la case de départ
     * @param yDepart    la coordonnée y de la case de départ
     * @param xArrivee   la coordonnée x de la case d'arrivée
     * @param yArrivee   la coordonnée y de la case d'arrivée
     * @return un objet {@code Chemin} représentant le chemin le plus court entre les deux cases spécifiées sur la carte
     */

    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);
        Noeud<Case> noeudDepart = graphe.getNoeud(xDepart, yDepart);
        Noeud<Case> noeudArrivee = graphe.getNoeud(xArrivee, yArrivee);
        List<Noeud<Case>> cheminNoeuds = algorithme.trouverChemin(graphe, noeudDepart, noeudArrivee);
        List<Case> cheminCases = new ArrayList<>();
        for (Noeud<Case> noeud : cheminNoeuds) {
            cheminCases.add(noeud.getValeur());
        }

        return new Chemin(cheminCases);
    }

    // KISS
    /**
     * Crée un graphe représentant la carte en utilisant les cases comme nœuds et ajoute des arêtes entre les cases
     * voisines dans le graphe.
     *
     * @param carte la carte à partir de laquelle créer le graphe
     * @return un objet {@code Graphe<Case>} représentant le graphe de la carte
     */
    static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();

        // Création des nœuds
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Case caseActuelle = new Case(carte.getTuile(x, y), x, y);
                graphe.ajouterNoeud(new Noeud<>(caseActuelle));
            }
        }

        // Ajout des arêtes
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Case caseActuelle = new Case(carte.getTuile(x, y), x, y);
                ajouterAretesVoisines(graphe, caseActuelle, x, y, largeur, hauteur);
            }
        }

        return graphe;
    }
    /**
     * Ajoute des arêtes entre une case donnée et ses cases voisines dans le graphe.
     *
     * @param graphe     le graphe auquel ajouter les arêtes
     * @param currentCase la case à laquelle ajouter les arêtes
     * @param x          la coordonnée x de la case
     * @param y          la coordonnée y de la case
     * @param largeur    la largeur de la carte
     * @param hauteur    la hauteur de la carte
     */
    public static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // Parcourir les voisins de la case actuelle
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            // Vérifier si les coordonnées sont valides
            if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {
                Case neighborCase = new Case(currentCase.getTuile(), newX, newY);
                graphe.ajouterArete(new Noeud<>(currentCase), new Noeud<>(neighborCase), calculerCout(currentCase, neighborCase));
            }
        }
    }
    /**
     * Calcule le coût pour se déplacer d'une case à une autre.
     *
     * @param from la case de départ
     * @param to   la case d'arrivée
     * @return le coût pour se déplacer de la case de départ à la case d'arrivée
     */
    static double calculerCout(Case from, Case to) {
        Tuile tuileFrom = from.getTuile();
        Tuile tuileTo = to.getTuile();
        // Vous devrez adapter cette partie selon la logique spécifique de calcul de coût entre les tuiles
        return Math.abs(tuileFrom.getPenalite() - tuileTo.getPenalite());
    }

    /**
     * Affiche le chemin trouvé dans la console.
     *
     * @param chemin le chemin trouvé
     * @return un objet {@code Chemin} représentant le chemin trouvé
     */
    static Chemin afficherChemin(List<Noeud<Case>> chemin) {
        if (chemin.isEmpty()) {
            System.out.println("No path found!");
            return new Chemin(new ArrayList<>());
        }
        System.out.print("Path: ");
        List<Case> cheminCases = new ArrayList<>();
        for (Noeud<Case> noeud : chemin) {
            Case caseNode = noeud.getValeur();
            cheminCases.add(caseNode);
            System.out.print(" -> " + caseNode.toString());
        }
        System.out.println();

        return new Chemin(cheminCases);
    }
}
