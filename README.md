# ACL_2019_LRC
Projet Analyse et Conception de Logiciel - Groupe : Julien Claisse / Pierre Gouth / Nicolas Kleinhentz / Emilien Lambert

## Présentation

Incarner évoli dans une folle aventure ! Trouver le trésor qui se trouve dans la dernière salle du labyrithe en évitant les fantominus et les simularbre pour gagner !

### Commandes

#### Déplacement

`z : déplacer le personnage en HAUT`
`q : déplacer le personnage à GAUCHE`
`s : déplacer le personnage en BAS`
`d : déplacer le personnage à DROITE`

#### Attaques

Pour attaquer, déplacez vous vers la direction de l'ennemi

#### Menu
`a : ouvrir le menu principal`
`z : monter la flèche de sélection`
`d : descendre la flèche de sélection`
`ESPACE : confiremr la sélection`

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
 
