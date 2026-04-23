import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Scanner pour lire les choix de l'utilisateur dans le menu
        Scanner scanner = new Scanner(System.in);

        // Services responsables de la lecture/écriture CSV et de la gestion des notes
        GestionCSV csvService = new GestionCSV();
        GestionNotes noteService = new GestionNotes();

        // Chemin du fichier CSV d'entrée contenant les étudiants
        String fichierEntree = "C:\\Users\\manfr\\IdeaProjects\\Projet_Gestion_des_notes_des_etudiants\\src\\etudiants.csv";

        // Nom du fichier CSV de sortie pour sauvegarder les résultats
        String fichierSortie = "resultats_etudiants.csv";

        int choix;

        // Boucle principale du menu
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Lire et afficher les étudiants");
            System.out.println("2. Trier les étudiants par moyenne décroissante");
            System.out.println("3. Sauvegarder les résultats dans un fichier CSV");
            System.out.println("4. Afficher les étudiants triés et sauvegarder");
            System.out.println("5. Quitter");
            System.out.print("Votre choix : ");

            // Vérifie que l'utilisateur entre bien un nombre
            while (!scanner.hasNextInt()) {
                System.out.print("Veuillez entrer un nombre entre 1 et 5 : ");
                scanner.next();
            }

            choix = scanner.nextInt();

            try {
                // Option 1 : Lire et afficher les étudiants
                if (choix == 1) {
                    List<Etudiant> etudiants = csvService.lireEtudiantsDepuisCsv(fichierEntree);
                    noteService.afficherEtudiants(etudiants);

                    // Option 2 : Trier par moyenne décroissante
                } else if (choix == 2) {
                    List<Etudiant> etudiants = csvService.lireEtudiantsDepuisCsv(fichierEntree);
                    noteService.trierParMoyenneDecroissante(etudiants);
                    System.out.println("Étudiants triés par moyenne décroissante :");
                    noteService.afficherEtudiants(etudiants);

                    // Option 3 : Sauvegarder les résultats dans un CSV
                } else if (choix == 3) {
                    List<Etudiant> etudiants = csvService.lireEtudiantsDepuisCsv(fichierEntree);
                    csvService.ecrireResultats(fichierSortie, etudiants);
                    System.out.println("Résultats sauvegardés dans : " + fichierSortie);

                    // Option 4 : Trier, afficher et sauvegarder
                } else if (choix == 4) {
                    List<Etudiant> etudiants = csvService.lireEtudiantsDepuisCsv(fichierEntree);
                    noteService.trierParMoyenneDecroissante(etudiants);
                    noteService.afficherEtudiants(etudiants);
                    csvService.ecrireResultats(fichierSortie, etudiants);
                    System.out.println("Résultats triés et sauvegardés dans : " + fichierSortie);

                    // Option 5 : Quitter
                } else if (choix == 5) {
                    System.out.println("Au revoir !");

                    // Mauvais choix
                } else {
                    System.out.println("Choix invalide. Veuillez réessayer.");
                }

                // Gestion des erreurs liées aux fichiers
            } catch (IOException e) {
                System.out.println("Erreur de lecture/écriture du fichier : " + e.getMessage());

                // Gestion des erreurs liées au format CSV
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur de format CSV : " + e.getMessage());

                // Gestion des erreurs imprévues
            } catch (Exception e) {
                System.out.println("Erreur inattendue : " + e.getMessage());
            }

        } while (choix != 5); // Continue tant que l'utilisateur ne choisit pas de quitter

        scanner.close();
    }
}
