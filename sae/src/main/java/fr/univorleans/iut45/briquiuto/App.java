package fr.univorleans.iut45.briquiuto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BriqueCollectionManager manager = new BriqueCollectionManager();
        Administrateur admin = new Administrateur(manager);
        Collectionneur collectionneur = new Collectionneur(manager);

        int choix;
        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Administrateur");
            System.out.println("2. Collectionneur");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            if (choix == 1) {
                menuAdmin(scanner, admin, manager);
            } else if (choix == 2) {
                menuCollectionneur(scanner, collectionneur, manager);
            } else if (choix == 0) {
                System.out.println("Au revoir !");
            } else {
                System.out.println("Choix invalide.");
            }

        } while (choix != 0);

        scanner.close();
    }

    // ── Menu Administrateur ───────────────────────────────────────────────

    public static void menuAdmin(Scanner scanner, Administrateur admin,
                                  BriqueCollectionManager manager) {
        int choix;
        do {
            System.out.println("\n=== Menu Administrateur ===");
            System.out.println("1. Ajouter une boite");
            System.out.println("2. Ajouter une piece");
            System.out.println("3. Creer un theme");
            System.out.println("4. Afficher le catalogue");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            if (choix == 1) {
                System.out.print("Numero : ");
                String numero = scanner.nextLine();
                System.out.print("Nom : ");
                String nom = scanner.nextLine();
                System.out.print("Annee : ");
                int annee = scanner.nextInt();
                System.out.print("Nb pieces : ");
                int nbPieces = scanner.nextInt();
                scanner.nextLine();
                admin.ajouterBoite(new BoiteComposee(numero, nbPieces, nom, annee));
                System.out.println("Boite ajoutee !");

            } else if (choix == 2) {
                System.out.print("Numero piece : ");
                String num = scanner.nextLine();
                System.out.print("Nom piece : ");
                String nom = scanner.nextLine();
                admin.ajouterPiece(num, nom, null);
                System.out.println("Piece ajoutee !");

            } else if (choix == 3) {
                System.out.print("ID theme : ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Nom theme : ");
                String nom = scanner.nextLine();
                admin.creerTheme(id, nom);
                System.out.println("Theme cree !");

            } else if (choix == 4) {
                List<Boite> catalogue = manager.getCatalogueBoites();
                if (catalogue.isEmpty()) {
                    System.out.println("Le catalogue est vide.");
                } else {
                    System.out.println("=== Catalogue ===");
                    for (Boite b : catalogue) {
                        System.out.println("- [" + b.getNumero() + "] "
                            + b.getNom() + " (" + b.getAnnee() + ")");
                    }
                }
            }

        } while (choix != 0);
    }

    // ── Menu Collectionneur ───────────────────────────────────────────────

    public static void menuCollectionneur(Scanner scanner, Collectionneur collectionneur,
                                           BriqueCollectionManager manager) {
        int choix;
        do {
            System.out.println("\n=== Menu Collectionneur ===");
            System.out.println("1. Composer une boite personnalisee");
            System.out.println("2. Ajouter une boite a ma collection");
            System.out.println("3. Afficher ma collection");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            if (choix == 1) {
                System.out.print("Nom de la boite : ");
                String nom = scanner.nextLine();
                System.out.print("Annee : ");
                int annee = scanner.nextInt();
                scanner.nextLine();
                BoitePersonnalisee boite = collectionneur.composerBoitePersonnalisee(
                    nom, annee, null, new ArrayList<>()
                );
                collectionneur.ajouterCollection(boite);
                System.out.println("Boite creee ! Numero : " + boite.getNumero());

            } else if (choix == 2) {
                System.out.print("Numero de la boite : ");
                String num = scanner.nextLine();
                Boite boite = manager.rechercherBoiteParNumero(num);
                if (boite == null) {
                    System.out.println("Boite introuvable.");
                } else {
                    collectionneur.ajouterCollection(boite);
                    System.out.println("Boite ajoutee a ta collection !");
                }

            } else if (choix == 3) {
                List<Boite> collection = collectionneur.getCollectionPersonnelle();
                if (collection.isEmpty()) {
                    System.out.println("Ta collection est vide.");
                } else {
                    System.out.println("=== Ma collection ===");
                    for (Boite b : collection) {
                        System.out.println("- [" + b.getNumero() + "] "
                            + b.getNom() + " (" + b.getAnnee() + ")");
                    }
                }
            }

        } while (choix != 0);
    }
}