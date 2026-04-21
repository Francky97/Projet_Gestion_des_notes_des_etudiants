import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Classe responsable des opérations liées aux notes des étudiants :
 * - tri par moyenne
 * - affichage formaté
 */
public class GestionNotes {

    // Trie la liste des étudiants par moyenne générale décroissante.
    public void trierParMoyenneDecroissante(List<Etudiant> etudiants) {

        // Utilisation d'un Comparator pour comparer les moyennes
        Collections.sort(etudiants, new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant e1, Etudiant e2) {
                // Tri décroissant : on compare e2 avant e1
                return Double.compare(e2.calculerMoyenne(), e1.calculerMoyenne());
            }
        });
    }

    // Affiche la liste des étudiants avec leurs notes et leur moyenne.
    public void afficherEtudiants(List<Etudiant> etudiants) {

        // Vérifie si la liste est vide
        if (etudiants.isEmpty()) {
            System.out.println("Aucun étudiant à afficher.");
            return;
        }

        // Affichage détaillé de chaque étudiant
        for (Etudiant e : etudiants) {
            System.out.println(
                    "ID: " + e.getId() +
                            " | Nom: " + e.getNom() +
                            " | Prénom: " + e.getPrenom() +
                            " | Math: " + e.getMath() +
                            " | Physique: " + e.getPhysique() +
                            " | Info: " + e.getInfo() +
                            " | Moyenne: " + String.format("%.2f", e.calculerMoyenne())
            );
        }
    }
}
