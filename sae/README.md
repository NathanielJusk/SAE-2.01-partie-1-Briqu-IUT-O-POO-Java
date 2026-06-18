# SAE - Application LEGO (SAE)

Ce dossier contient le code source de la SAE (Java + JavaFX) utilisé pour le projet.

## Pré-requis
- Java 17+ installé
- Maven
- JavaFX SDK (si vous ne lancez pas via un module system intégré)
- Une base de données MariaDB/MySQL (ou adapter la configuration)

## Build
Depuis le dossier `sae/` :

```bash
mvn clean package
```

## Tests
Lancer les tests unitaires avec :

```bash
mvn test
```

## Exécution
Après `mvn package`, lancez l'application en fournissant le chemin vers le SDK JavaFX si nécessaire :

```bash
# exemple (adapter /path/to/javafx/lib et le nom du jar généré)
java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -jar target/your-app.jar
```

Vous pouvez aussi exécuter depuis votre IDE (IntelliJ/VSCode) en ajoutant les modules JavaFX dans la configuration d'exécution.

## Configuration de la base de données
Copiez `config.sample.properties` en `config.properties` et renseignez vos identifiants/URL.

Fichier d'exemple : `config.sample.properties` (dans ce dossier).

## Initialiser une base de test
Un script SQL d'initialisation est fourni dans `db/init.sql`. Il crée des tables minimales et insère quelques données de démonstration.

## Notes pour le rendu première année
- Structure MV(C) claire : vues / contrôleurs / modèle.
- Documenter localement les identifiants de BD **ne pas** commit.
- Ajouter dans le README local les commandes exactes et où placer JavaFX si nécessaire.
- Si le correcteur ne veut pas de base distante, proposer un dump SQL (fourni) ou une option H2 embarquée.

---
Commentaires ajoutés et reformulés dans les vues/contrôleurs pour un style "étudiant BUT1".