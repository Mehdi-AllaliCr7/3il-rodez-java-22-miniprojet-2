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

    /*public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);
        Noeud<Case> depart = graphe.getNoeuds().get(xDepart * carte.getHauteur() + yDepart);
        Noeud<Case> arrivee = graphe.getNoeuds().get(xArrivee * carte.getHauteur() + yArrivee);
        List<Noeud<Case>> cheminNoeuds = algorithme.trouverChemin(graphe, depart, arrivee);
        afficherChemin(cheminNoeuds);
        return new Chemin(convertirNoeudsEnCases(cheminNoeuds));
    }

    static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();

        // Parcours de chaque case de la carte pour créer les noeuds du graphe
        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                Tuile tuileCourante = carte.getTuile(i, j);
                Case caseCourante = new Case(tuileCourante, i, j);
                graphe.ajouterNoeud(new Noeud<>(caseCourante));
                ajouterAretesVoisines(graphe, caseCourante, i, j, largeur, hauteur);
            }
        }
        return graphe;
    }

    public static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur) {
        /*List<Noeud<Case>> voisins = new ArrayList<>();
        if (x > 0) {
            Case leftCase = graphe.getNoeuds(x - 1, y).getValeur();
            voisins.add(graphe.getNoeuds(x - 1, y));
        }
        if (x < largeur - 1) {
            Case rightCase = graphe.getNoeuds(x + 1, y).getValeur();
            voisins.add(graphe.getNoeuds(x + 1, y));
        }
        if (y > 0) {
            Case topCase = graphe.getNoeuds(x, y - 1).getValeur();
            voisins.add(graphe.getNoeuds(x, y - 1));
        }
        if (y < hauteur - 1) {
            Case bottomCase = graphe.getNoeuds(x, y + 1).getValeur();
            voisins.add(graphe.getNoeuds(x, y + 1));
        }
        for (Noeud<Case> voisin : voisins) {
            graphe.ajouterArete(new Noeud<>(currentCase), voisin, calculerCout(currentCase, voisin.getValeur()));
        }
    }*/

    /*static List<Case> convertirNoeudsEnCases(List<Noeud<Case>> noeuds) {
        List<Case> cases = new ArrayList<>();
        for (Noeud<Case> noeud : noeuds) {
            cases.add(noeud.getValeur());
        }
        return cases;
    }

    static double calculerCout(Case from, Case to) {
        // Calcul du coût entre deux cases
        Tuile tuileFrom = from.getTuile();
        Tuile tuileTo = to.getTuile();
        // Vous devrez adapter cette partie selon la logique spécifique de calcul de coût entre les tuiles
        return Math.abs(tuileFrom.getPenalite() - tuileTo.getPenalite());
    }

    public static void afficherChemin(List<Noeud<Case>> chemin) {
        System.out.println("Le chemin est :");
        for (Noeud<Case> noeud : chemin) {
            Case caseCourante = noeud.getValeur();
            System.out.println("[" + caseCourante.getX() + ", " + caseCourante.getY() + "] : " + caseCourante.getTuile());
        }
    }*/

    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);

        Tuile tuileDepart = carte.getTuile(xDepart, yDepart);
        Case depart = new Case(tuileDepart, xDepart, yDepart);

        Tuile tuileArrivee = carte.getTuile(xArrivee, yArrivee);
        Case arrivee = new Case(tuileArrivee, xArrivee, yArrivee);

        List<Noeud<Case>> chemin = algorithme.trouverChemin(graphe, new Noeud<>(depart), new Noeud<>(arrivee));

        return afficherChemin(chemin);
    }

    // KISS

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

    public static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur) {

    }

    static double calculerCout(Case from, Case to) {
        Tuile tuileFrom = from.getTuile();
        Tuile tuileTo = to.getTuile();
        // Vous devrez adapter cette partie selon la logique spécifique de calcul de coût entre les tuiles
        return Math.abs(tuileFrom.getPenalite() - tuileTo.getPenalite());
    }

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
