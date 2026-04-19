import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GestionNotes {

    // Lit un fichier CSV contenant des noms d'étudiants et leurs notes
    // Regroupe automatiquement toutes les notes d'un même étudiant
    // Retourne une liste d'objets Etudiant
    public static List<Etudiant> lireEtudiantsDepuisCSV(String cheminFichier) {

        // Map pour stocker les étudiants sans doublons
        // LinkedHashMap conserve l'ordre d'apparition dans le fichier
        Map<String, Etudiant> etudiants = new LinkedHashMap<>();

        // Lecture du fichier CSV
        try (BufferedReader br = GestionCSV.ouvrirLecture(cheminFichier)) {
            String ligne;
            boolean premiereLigne = true;

            while ((ligne = br.readLine()) != null) {

                // Ignore la première ligne (ex: "Nom,Note")
                if (premiereLigne) {
                    premiereLigne = false;
                    continue;
                }

                // Ignore les lignes vides
                if (ligne.trim().isEmpty()) {
                    continue;
                }

                // Découpe la ligne CSV en colonnes
                String[] parties = ligne.split(",");

                // Vérifie qu'il y a au moins 2 colonnes (nom + note)
                if (parties.length < 2) {
                    continue;
                }

                // Récupère le nom et la note sous forme de texte
                String nom = parties[0].trim();
                String noteTexte = parties[1].trim();

                try {
                    // Convertit la note en double
                    double note = Double.parseDouble(noteTexte);

                    // Si l'étudiant n'existe pas encore, on le crée
                    if (!etudiants.containsKey(nom)) {
                        etudiants.put(nom, new Etudiant(nom));
                    }

                    // Ajoute la note à l'étudiant
                    etudiants.get(nom).ajouterNote(note);

                } catch (NumberFormatException e) {
                    // Si la note n'est pas un nombre valide
                    System.out.println("Note invalide ignorée pour " + nom + " : " + noteTexte);
                }
            }

        } catch (IOException e) {
            // Si le fichier ne peut pas être lu
            System.out.println("Erreur lors de la lecture du fichier CSV : " + e.getMessage());
        }

        // Retourne la liste des étudiants (valeurs de la map)
        return new ArrayList<>(etudiants.values());
    }

    // Trie la liste d'étudiants par moyenne décroissante
    // Le meilleur étudiant apparaît en premier
    public static void trierParMoyenneDesc(List<Etudiant> etudiants) {
        etudiants.sort((e1, e2) -> Double.compare(e2.calculerMoyenne(), e1.calculerMoyenne()));
    }

    // Affiche tous les étudiants avec leur moyenne
    public static void afficherEtudiants(List<Etudiant> etudiants) {
        System.out.println("=== Résultats des étudiants ===");
        for (Etudiant e : etudiants) {
            System.out.println(e); // utilise toString() de Etudiant
        }
    }

    // Sauvegarde les résultats (nom + moyenne) dans un fichier CSV
    public static void sauvegarderResultats(List<Etudiant> etudiants, String cheminFichier) {

        // Ouverture du fichier en écriture
        try (PrintWriter pw = GestionCSV.ouvrirEcriture(cheminFichier)) {

            // En-tête du fichier CSV
            pw.println("Nom,Moyenne");

            // Écriture de chaque étudiant
            for (Etudiant e : etudiants) {
                pw.println(e.getNom() + "," + e.calculerMoyenne());
            }

        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture du fichier CSV : " + e.getMessage());
        }
    }
}
