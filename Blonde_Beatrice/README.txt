- Lire les étudiants depuis un fichier CSV.
- Afficher la liste des étudiants.
- Trier les étudiants par moyenne décroissante.
- Sauvegarder les résultats dans un fichier CSV.
- Gérer les erreurs de lecture, d’écriture et de format CSV.

 lireEtudiantsDepuisCsv("fichier.csv") 
- Ouvre le fichier   - Lit ligne par ligne   
- Transforme chaque ligne → 1 étudiant  
- Ignore la 1ère ligne (titres)

ecrireResultats("sortie.csv", listeEtudiants)   
- Crée un nouveau fichier CSV   
- Met tous les étudiants dedans
 
