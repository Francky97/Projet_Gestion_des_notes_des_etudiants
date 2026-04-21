# Projet_Gestion_des_notes_des_etudiants

## Description
Ce projet Java permet de gérer les notes des étudiants à partir d’un fichier CSV.
Il lit les données, calcule la moyenne de chaque étudiant, classe les étudiants par moyenne, puis sauvegarde les résultats dans un autre fichier CSV.

## Fonctionnalités
- Lecture d’un fichier CSV contenant les noms des étudiants et leurs notes.
- Regroupement des notes par étudiant.
- Calcul de la moyenne de chaque étudiant.
- Tri des étudiants par moyenne décroissante.
- Sauvegarde des résultats dans un fichier CSV.

## Organisation du projet
- `Main.java` : point d’entrée du programme.
- `Etudiant.java` : représentation d’un étudiant.
- `GestionCSV.java` : gestion de la lecture et de l’écriture des fichiers.
- `GestionNotes.java` : logique principale du traitement des notes.

## Format du fichier d’entrée
Le fichier `etudiants.csv` doit contenir 6 colonnes :
```csv
id,nom,prenom,math,physique,info
1,Dupont,Jean,8,8,11
2,Simon,Paul,2,1,16
3,Moreau,Thomas,19,6,13
4,Martin,Lucas,6,1,19
```

## Compilation
```bash
javac *.java
```

## Exécution
```bash
java Main
```

## Résultat
Le programme affiche les étudiants triés par moyenne dans la console et génère un fichier `resultats.csv`.

## Auteurs
- FRANCKY MANFRED
- BLONDE BEATRICE
