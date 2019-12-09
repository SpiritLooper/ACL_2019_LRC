# ACL_2019_LRC
Projet Analyse et Conception de Logiciel - Groupe : Julien Claisse / Pierre Gouth / Nicolas Kleinhentz / Emilien Lambert

## Présentation

Incarnez évoli dans une folle aventure ! Trouvez le trésor qdans la dernière salle du labyrithe en évitant les fantominus et les simularbre pour gagner !

### Commandes

#### Déplacement

- `z : déplacer le personnage en HAUT`
- `q : déplacer le personnage à GAUCHE`
- `s : déplacer le personnage en BAS`
- `d : déplacer le personnage à DROITE`

#### Attaques

Pour attaquer, déplacez vous vers la direction de l'ennemi

#### Menu
- `a : ouvrir le menu principal`
- `z : monter la flèche de sélection`
- `s : descendre la flèche de sélection`
- `ESPACE : confirmer la sélection`

### Outils

Dans les fichiers du jeu, vous avez à votre disposition un classeur libre Office, où il vous faudra accepter les macros.

- `/res/saves/generator_level.xmls`

Ce fichier permet de générer des fichiers de niveau .lyt plus facilement grâce à la grille qu'offre un classeur.
Pour ce faire il suffit de : 

1. Placer vos divers élement, à l'endroit desiré dans le niveau. (Légende dans le classeur)
2. Cliquer sur le bouton `Générer` 
3. Prener le code généré de la cellule M2
4. Enregistrer ce code dans un fichier *nom_fichier.lyt* dans le dossier `res/saves/`
5. Ajouter dans le fichier `res/saves/game.lyt`  le nom du fichier que vous venez de créer en pensant bien de mettre à jour le nombre de __LEVEL:__*X*
6. Mentionner le champ BIOM dans le fichier *nom_fichier.lyt* avec le biom que vous souhaitez avoir.
> Il existe 5 types de Biom :
> - *STATION_PASS*
> - *MURKY_FOREST*
> - *DARK_WASTLAND*
> - *TEMPORAL_TOWER*
> - *ABYSSE*

> *Attention /!\ : Il n'est possible que de créer des niveaux 10 x 10 cases*

### Capture d'écrans
![GitHub Logo](/img/murky.png)
![GitHub Logo](/img/temporal.png)

## Prérequis
#### Exécution du projet
- Java installé sur son ordinateur
#### Compilation du projet
- JDK installé sur son ordinateur 
- Maven installé sur son ordinateur

## Compiler le projet
1. Aller à la racine du projet
2. Lancer la commande `mvn package`
3. Puis executer le jar généré avec la commande `java -jar .\target\ACL_2019_LRC-*.0-SNAPSHOT.jar`

## Exécuter le projet 
1. Récupérer dans le dossier **/bin** le dossier du sprint voulu, le dernier Sprint est la version la plus récente du projet
2. Puis exécuter le jar du dossier récupéré avec la commande `java -jar ACL_2019_LRC-*.0-SNAPSHOT.jar`

> *A partir du Sprint2 inclus, vérifier que le jar executé est à côté du dossier *res* contenant toutes les ressources du projet*

## Correction erreur de compilation
Si le projet refuse de se compiler avec maven
Modifier les lignes 
```    
    <source>12</source>
    <target>12</target>
 ```
 Selon la version de votre JDK
 Exemple avec un jdk 8
 ```    
    <source>8</source>
    <target>8</target>
 ```
 
