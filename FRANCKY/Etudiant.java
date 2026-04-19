import java.util.ArrayList;
import java.util.List;

public class Etudiant {
    private String nom;
    private List<Double> notes;

    // Constructeur : initialise le nom et crée une liste vide pour les notes
    public Etudiant(String nom) {
        this.nom = nom;
        this.notes = new ArrayList<>();
    }

    // Retourne le nom de l'étudiant
    public String getNom() {
        return nom;
    }

    // Retourne une copie de la liste des notes (sécurité : on ne renvoie pas la liste originale)
    public List<Double> getNotes() {
        return new ArrayList<>(notes);
    }

    // Ajoute une note si elle est valide (entre 0 et 100)
    public void ajouterNote(double note) {
        if (note >= 0 && note <= 100) {
            notes.add(note);
        } else {
            System.out.println("Note invalide");
        }
    }

    // Calcule et retourne la moyenne des notes
    // Retourne 0.0 si aucune note n'a été ajoutée
    public double calculerMoyenne() {
        if (notes.isEmpty()) {
            return 0.0;
        }

        double somme = 0.0;

        // On additionne toutes les notes
        for (double note : notes) {
            somme += note;
        }

        // Moyenne = somme des notes / nombre de notes
        return somme / notes.size();
    }

    // Représentation textuelle de l'objet Etudiant
    // Exemple : "Alice - Moyenne: 85.50"
    @Override
    public String toString() {
        return nom + " - Moyenne: " + calculerMoyenne();
    }
}
