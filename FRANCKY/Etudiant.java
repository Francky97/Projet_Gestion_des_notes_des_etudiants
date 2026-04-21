/**
 * Classe représentant un étudiant avec ses informations personnelles
 * et ses notes dans trois matières : Math, Physique et Informatique.
 */
public class Etudiant {
    private int id;
    private String nom;
    private String prenom;
    private double math;
    private double physique;
    private double info;

    // Constructeur permettant d'initialiser un étudiant avec toutes ses informations.
    public Etudiant(int id, String nom, String prenom, double math, double physique, double info) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.math = math;
        this.physique = physique;
        this.info = info;
    }

    // Getters permettant d'accéder aux attributs privés
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public double getMath() {
        return math;
    }

    public double getPhysique() {
        return physique;
    }

    public double getInfo() {
        return info;
    }

    // Calcul de la moyenne generale
    public double calculerMoyenne() {
        return (math + physique + info) / 3.0;
    }

    @Override
    public String toString() {
        return id + "," + nom + "," + prenom + "," + math + "," + physique + "," + info + "," + "," + calculerMoyenne();
    }
}
