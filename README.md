# ACL_2019_LRC
Projet Analyse et Conception de Logiciel - Groupe : Julien Claisse / Pierre Gouth / Nicolas Kleinhentz / Emilien Lambert

## Prérequis
JDK installé sur son ordinateur

## Executer le projet
- **Windows**
1. Aller dans le répertoire `/src`
2. Compiler le projet en utilisant les commandes :
    `dir /s /B *.java > sources.txt` puis
    `javac @sources.txt`
3. Executer avec la commande :
    `java main.java.start.Main`
4. Vous pouvez clean les fichiers .class avec la commande :
    `del /S *.class`

 - **Linux**
1. Aller dans le dossier `/src`
2. Compiler le projet en utilisant les commandes :
    `find -name "*.java" > sources.txt` puis
    `javac @sources.txt`
3. Executer avec la commande :
    `java main.java.start.Main`
4. Vous pouvez clean les fichiers .class avec la commande :
     `rm -r *.class`
