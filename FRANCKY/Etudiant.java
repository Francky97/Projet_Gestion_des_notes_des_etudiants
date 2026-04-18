import java.util.ArrayList;
import java.util.List;

public class Etudiant {
    private String nom;
    private List<Double> notes;

    public Etudiant(String nom) {
        this.nom = nom;
        this.notes = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public List<Double> getNotes() {
        return notes;
    }

    public void ajouterNote(double note) {
        notes.add(note);
    }

    public double calculerMoyenne() {
        if (notes.isEmpty()) {
            return 0.0;
        }

        double somme = 0.0;
        for (double note : notes) {
            somme += note;
        }
        return somme / notes.size();
    }

    @Override
    public String toString() {
        return nom + " - Moyenne: " + String.format("%.2f", calculerMoyenne());
    }
}
