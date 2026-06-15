#!/bin/bash

# ============================================================
#  Script de compilation et lancement (Version Terminal via Hack)
#  Projet Briqu'IUT-O — SAÉ 2.01
# ============================================================

# Aller dans le projet Maven
cd sae || {
  echo "Dossier 'sae' introuvable."
  exit 1
}

echo "=== Nettoyage anti-bug VS Code ==="
rm -rf target/

echo "=== Compilation du projet ==="
mvn clean compile -q
if [ $? -ne 0 ]; then
  echo "ERREUR : la compilation a echoue."
  exit 1
fi
echo "Compilation OK"

echo ""
echo "=== Lancement de l'application (Terminal) ==="

# mvn javafx:run -Djavafx.mainClass="fr.univorleans.iut45.briquiuto.App" -q

mvn javafx:run -Djavafx.mainClass="fr.univorleans.iut45.briquiuto.AppJavaFx" -q