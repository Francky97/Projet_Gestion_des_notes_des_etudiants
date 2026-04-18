import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GestionNotes {

    public static List<Etudiant> lireEtudiantsDepuisCSV(String cheminFichier) {
        Map<String, Etudiant> etudiants = new LinkedHashMap<>();

        try (BufferedReader br = GestionCSV.ouvrirLecture(cheminFichier)) {
            String ligne;
            boolean premiereLigne = true;

            while ((ligne = br.readLine()) != null) {
                if (premiereLigne) {
                    premiereLigne = false;
                    continue;
                }

                if (ligne.trim().isEmpty()) {
                    continue;
                }

                String[] parties = ligne.split(",");
                if (parties.length < 2) {
                    continue;
                }

                String nom = parties[0].trim();
                String noteTexte = parties[1].trim();

                try {
                    double note = Double.parseDouble(noteTexte);

                    if (!etudiants.containsKey(nom)) {
                        etudiants.put(nom, new Etudiant(nom));
                    }
                    etudiants.get(nom).ajouterNote(note);

                } catch (NumberFormatException e) {
                    System.out.println("Note invalide ignorée pour " + nom + " : " + noteTexte);
                }
            }

        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier CSV : " + e.getMessage());
        }

        return new ArrayList<>(etudiants.values());
    }

    public static void trierParMoyenneDesc(List<Etudiant> etudiants) {
        etudiants.sort((e1, e2) -> Double.compare(e2.calculerMoyenne(), e1.calculerMoyenne()));
    }

    public static void afficherEtudiants(List<Etudiant> etudiants) {
        System.out.println("=== Résultats des étudiants ===");
        for (Etudiant e : etudiants) {
            System.out.println(e);
        }
    }

    public static void sauvegarderResultats(List<Etudiant> etudiants, String cheminFichier) {
        try (PrintWriter pw = GestionCSV.ouvrirEcriture(cheminFichier)) {
            pw.println("Nom,Moyenne");

            for (Etudiant e : etudiants) {
                pw.println(e.getNom() + "," + String.format("%.2f", e.calculerMoyenne()));
            }

        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture du fichier CSV : " + e.getMessage());
        }
    }
}
