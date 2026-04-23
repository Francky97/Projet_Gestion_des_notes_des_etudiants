import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsable de la lecture et de l'écriture des fichiers CSV
 * contenant les informations des étudiants.
 */
public class GestionCSV {

    // Lit un fichier CSV et convertit chaque ligne en objet Etudiant.
    public List<Etudiant> lireEtudiantsDepuisCsv(String cheminFichier) throws IOException {

        // Liste qui contiendra tous les étudiants lus
        List<Etudiant> etudiants = new ArrayList<>();

        // Lecture du fichier avec BufferedReader (try-with-resources ferme automatiquement le fichier)
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            boolean premiereLigne = true; // Permet d'ignorer l'en-tête du CSV


            while ((ligne = br.readLine()) != null) {

                // Ignore les lignes vides
                if (ligne.trim().isEmpty()) {
                    continue;
                }

                // Ignore la première ligne si c'est un en-tête
                if (premiereLigne) {
                    premiereLigne = false;

                    // Vérifie si la ligne contient des mots-clés typiques d'un header
                    if (ligne.toLowerCase().contains("id") && ligne.toLowerCase().contains("nom")) {
                        continue; // On saute l'en-tête
                    }
                }

                // Découpe la ligne CSV en colonnes
                String[] donnees = ligne.split(",");

                // Vérifie que la ligne contient bien toutes les colonnes nécessaires
                if (donnees.length < 6) {
                    throw new IllegalArgumentException("Ligne CSV invalide : " + ligne);
                }

                try {

                    // Conversion des données en types appropriés
                    int id = Integer.parseInt(donnees[0].trim());
                    String nom = donnees[1].trim();
                    String prenom = donnees[2].trim();
                    double math = Double.parseDouble(donnees[3].trim());
                    double physique = Double.parseDouble(donnees[4].trim());
                    double info = Double.parseDouble(donnees[5].trim());

                    // Ajout de l'étudiant à la liste
                    etudiants.add(new Etudiant(id, nom, prenom, math, physique, info));
                } catch (NumberFormatException e) {

                    // Erreur si une valeur numérique est mal formatée
                    throw new NumberFormatException("Erreur de conversion numérique dans la ligne : " + ligne);
                }
            }
        }

        return etudiants;
    }

    // Écrit la liste des étudiants dans un fichier CSV
    public void ecrireResultats(String cheminFichier, List<Etudiant> etudiants) throws IOException {

        // Ouverture du fichier en écriture
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cheminFichier))) {

            // Écriture de l'en-tête CSV
            bw.write("id,nom,prenom,math,physique,info,moyenne");
            bw.newLine();

            // Écriture de chaque étudiant sous forme CSV
            for (Etudiant e : etudiants) {
                bw.write(e.toString());
                bw.newLine();
            }
        }
    }
}
