package fr.univorleans.iut45.briquiuto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fr.univorleans.iut45.briquiuto.JDBC.ConnexionBD;
import fr.univorleans.iut45.briquiuto.JDBC.RequetesLEGO;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConnexionBD connexion = null;

        try {
            connexion = new ConnexionBD();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MariaDB non trouve !!!");
            System.exit(1);
        }

        System.out.println("=== Connexion a la base de donnees ===");
        System.out.println("Serveur : localhost (fixe)");
        System.out.print("Base de donnees [LEGO] : ");
        String base = scanner.nextLine();
        if (base.isEmpty()) base = "lego"; 

        System.out.print("Login : ");
        String login = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String mdp = scanner.nextLine();

        try {
            connexion.connecter("localhost", base, login, mdp);
            System.out.println("Connexion reussie !");
        } catch (SQLException e) {
            System.out.println("Echec de connexion : " + e.getMessage());
            System.exit(1);
        }

        BriqueCollectionManager manager = new BriqueCollectionManager();
        RequetesLEGO requetes = new RequetesLEGO(connexion, manager);

        try {
            for (Theme t : requetes.getAllThemes()) {
                manager.getCatalogueThemes().add(t);
            }
            System.out.println(manager.getCatalogueThemes().size() + " themes charges.");
        } catch (SQLException e) {
            System.out.println("Erreur chargement themes : " + e.getMessage());
        }

        Administrateur admin = new Administrateur(manager);
        Collectionneur collectionneur = new Collectionneur(manager);

        int choix;
        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Administrateur");
            System.out.println("2. Collectionneur");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            
            choix = lireEntierSecurise(scanner);  

            if (choix == 1) {
                menuAdmin(scanner, admin, manager, requetes);
            } else if (choix == 2) {
                menuCollectionneur(scanner, collectionneur, manager, requetes);
            } else if (choix == 0) {
                System.out.println("Au revoir !");
            } else {
                System.out.println("Choix invalide.");
            }
        } while (choix != 0);

        try {
            if (connexion.isConnecte()) connexion.close();
        } catch (SQLException e) {
            System.out.println("Erreur fermeture connexion : " + e.getMessage());
        }
        scanner.close();
    }

    // ── Menu Administrateur ───────────────────────────────────────────────

    public static void menuAdmin(Scanner scanner, Administrateur admin,
            BriqueCollectionManager manager, RequetesLEGO requetes) {
        int choix;
        do {
            System.out.println("\n=== Menu Administrateur ===");
            System.out.println("1. Ajouter une boite");
            System.out.println("2. Ajouter une piece");
            System.out.println("3. Creer un theme");
            System.out.println("4. Lister les boites par theme");
            System.out.println("5. Lister les pieces d'une boite");
            System.out.println("6. Lister les figurines d'une boite");
            System.out.println("7. Lister les sous-boites d'une boite");
            System.out.println("8. Rechercher une boite par numero");
            System.out.println("9. Rechercher une boite par nom");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            
            choix = lireEntierSecurise(scanner);  

            if (choix == 1) {
                System.out.print("Numero : ");
                String numero = scanner.nextLine();
                System.out.print("Nom : ");
                String nom = scanner.nextLine();
                System.out.print("Annee : ");
                int annee = lireEntierSecurise(scanner);  
                System.out.print("Nb pieces : ");
                int nbPieces = lireEntierSecurise(scanner);  
                
                Boite b = new BoiteComposee(numero, nbPieces, nom, annee);
                admin.ajouterBoite(b);
                try {
                    requetes.ajouterBoite(b);
                    System.out.println("Boite ajoutee en base !");
                } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
                }

            } else if (choix == 2) {
                System.out.print("Numero piece : ");
                String num = scanner.nextLine();
                System.out.print("Nom piece : ");
                String nom = scanner.nextLine();
                admin.ajouterPiece(num, nom, null);
                try {
                    requetes.ajouterPiece(new Piece(num, nom));
                    System.out.println("Piece ajoutee en base !");
                } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
                }

            } else if (choix == 3) {
                System.out.print("ID theme : ");
                int id = lireEntierSecurise(scanner);  
                System.out.print("Nom theme : ");
                String nom = scanner.nextLine();
                Theme t = admin.creerTheme(id, nom);
                try {
                    requetes.ajouterTheme(t);
                    System.out.println("Theme cree en base !");
                } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
                }

            } else if (choix == 4) {
                System.out.print("ID du theme : ");
                int id = lireEntierSecurise(scanner);  
                try {
                    System.out.println(requetes.listerBoitesParTheme(id));
                } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
                }

            } else if (choix == 5) {
                System.out.print("Numero de la boite : ");
                String num = scanner.nextLine();
                try {
                    System.out.println(requetes.listerPiecesBoite(num));
                } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
                }

            } else if (choix == 6) {
                System.out.print("Numero de la boite : ");
                String num = scanner.nextLine();
                try {
                    System.out.println(requetes.listerFigurinesBoite(num));
                } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
                }

            } else if (choix == 7) {
                System.out.print("Numero de la boite : ");
                String num = scanner.nextLine();
                try {
                    System.out.println(requetes.listerSousBoite(num));
                } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
                }

            } else if (choix == 8) {
                System.out.print("Numero de la boite : ");
                String num = scanner.nextLine();
                try {
                    Boite b = requetes.rechercherBoiteParNumero(num);
                    if (b == null) {
                        System.out.println("Boite introuvable.");
                    } else {
                        System.out.println("[" + b.getNumero() + "] "
                                + b.getNom() + " (" + b.getAnnee() + ")");
                    }
                } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
                }

            } else if (choix == 9) {
                System.out.print("Nom (partiel) : ");
                String nom = scanner.nextLine();
                try {
                    Boite b = requetes.rechercherBoiteParNom(nom);
                    if (b == null) {
                        System.out.println("Boite introuvable.");
                    } else {
                        System.out.println("[" + b.getNumero() + "] "
                                + b.getNom() + " (" + b.getAnnee() + ")");
                    }
                } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
                }
            }

        } while (choix != 0);
    }

    // ── Menu Collectionneur ───────────────────────────────────────────────

    public static void menuCollectionneur(Scanner scanner, Collectionneur collectionneur,
            BriqueCollectionManager manager, RequetesLEGO requetes) {
        int choix;
        do {
            System.out.println("\n=== Menu Collectionneur ===");
            System.out.println("1. Composer une boite personnalisee");
            System.out.println("2. Ajouter une boite a ma collection");
            System.out.println("3. Afficher ma collection");
            System.out.println("4. Rechercher boites par theme");
            System.out.println("5. Voir le detail d'une boite");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");
            
            choix = lireEntierSecurise(scanner);  

            if (choix == 1) {
                System.out.print("Nom de la boite : ");
                String nom = scanner.nextLine();
                System.out.print("Annee : ");
                int annee = lireEntierSecurise(scanner);  
                
                BoitePersonnalisee boite = collectionneur.composerBoitePersonnalisee(
                        nom, annee, null, new ArrayList<>());
                collectionneur.ajouterCollection(boite);
                try {
                    requetes.ajouterBoite(boite);
                    System.out.println("Boite creee ! Numero : " + boite.getNumero());
                } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
                }

            } else if (choix == 2) {
                System.out.print("Numero de la boite : ");
                String num = scanner.nextLine();
                try {
                    Boite boite = requetes.rechercherBoiteParNumero(num);
                    if (boite == null) {
                        System.out.println("Boite introuvable.");
                    } else {
                        collectionneur.ajouterCollection(boite);
                        System.out.println("Boite ajoutee a ta collection !");
                    }
                } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
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

            } else if (choix == 4) {
                System.out.print("ID du theme : ");
                int id = lireEntierSecurise(scanner);  
                try {
                    System.out.println(requetes.listerBoitesParTheme(id));
                } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
                }

            } else if (choix == 5) {
                System.out.print("Numero de la boite : ");
                String num = scanner.nextLine();
                try {
                    System.out.println("--- Pieces ---");
                    System.out.println(requetes.listerPiecesBoite(num));
                    System.out.println("--- Figurines ---");
                    System.out.println(requetes.listerFigurinesBoite(num));
                    System.out.println("--- Sous-boites ---");
                    System.out.println(requetes.listerSousBoite(num));
                } catch (SQLException e) {
                    System.out.println("Erreur SQL : " + e.getMessage());
                }
            }

        } while (choix != 0);
    }

    /**
     * Lit une ligne complète et tente de la convertir en entier.
     * Boucle indéfiniment tant que la saisie n'est pas un entier valide.
     */
    private static int lireEntierSecurise(Scanner scanner) {
        while (true) {
            String saisie = scanner.nextLine();
            try {
                return Integer.parseInt(saisie);
            } catch (NumberFormatException e) {
                System.out.print("Saisie invalide. Veuillez entrer un nombre entier : ");
            }
        }
    }
}