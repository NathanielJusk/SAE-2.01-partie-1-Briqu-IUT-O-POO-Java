#!/bin/bash

# ============================================================
#  Script de compilation, génération de doc et lancement
#  Projet Briqu'IUT-O — SAÉ 2.01
# ============================================================

# Aller dans le projet Maven
cd sae || {
  echo "Dossier 'sae' introuvable."
  exit 1
}

echo "=== Compilation du projet ==="
mvn clean compile -q
if [ $? -ne 0 ]; then
  echo "ERREUR : la compilation a echoue."
  exit 1
fi
echo "Compilation OK"

echo ""
echo "=== Generation de la JavaDoc ==="
mvn javadoc:javadoc -q
if [ $? -ne 0 ]; then
  echo "ERREUR : la generation de la JavaDoc a echoue."
  exit 1
fi
echo "JavaDoc generee dans : sae/target/site/apidocs/"

echo ""
echo "=== Lancement de l'application ==="
mvn exec:java -Dexec.mainClass="fr.univorleans.iut45.briquiuto.App"