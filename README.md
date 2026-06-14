# NathanielJusk-SAE-2.01-partie-1---Briqu-IUT-O---POO---Java
# Briqu'IUT-O — SAÉ 2.01

Application Java en mode texte pour gérer une collection de boîtes LEGO à partir d'une base MariaDB, dans le cadre de la SAÉ 2.01 du BUT Informatique (IUT d'Orléans).

## Groupe

- JUSKO Nathaniel — Chef de projet
- DESSENEUX-AURIOL Florient
- MAKHLOUF Zakaria
- BELHAMIDA Adil
- BOUSLIM Mourad

## Dépôt Git

https://github.com/NathanielJusk/SAE-2.01-partie-1-Briqu-IUT-O-POO-Java

## Prérequis

- Java 17+
- Maven 3.8+
- MariaDB/MySQL avec base `lego` créée à partir des scripts SQL fournis

1. Depuis le dossier `sae/` :
   ```bash
   mvn clean compile
   mvn exec:java -Dexec.mainClass="fr.univorleans.iut45.briquiuto.App"
   ```