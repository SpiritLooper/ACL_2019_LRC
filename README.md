# ACL_2019_LRC
Projet Analyse et Conception de Logiciel - Groupe : Julien Claisse / Pierre Gouth / Nicolas Kleinhentz / Emilien Lambert

## Prérequis
JDK installé sur son ordinateur
Maven installé sur son ordinateur

## Executer le projet
1. Aller à la racine du projet
2. Lancer la commande `mvn package`
3. Puis executer le jar généré avec la commande `java -jar .\target\ACL_2019_LRC-2.0-SNAPSHOT.jar`

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
 
