#!/bin/bash

# ============================================================
#  Script de compilation et lancement (Multi-versions)
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

# ---------------------------------------------------------
# MENU DE CHOIX
# ---------------------------------------------------------
echo "================================================="
echo "   Quelle version de l'application lancer ?"
echo "   1) Version Terminal (App.java)"
echo "   2) Version Graphique (ApplicationJavaFx.java)"
echo "================================================="
read -p "Entrez votre choix (1 ou 2) : " CHOIX
echo ""

# ---------------------------------------------------------
# EXECUTION SELON LE CHOIX
# ---------------------------------------------------------
if [ "$CHOIX" = "1" ]; then
    echo "=== Lancement de l'application (TERMINAL) ==="
    # On contourne Maven ! On utilise Java pur en lui donnant le chemin 
    # de ton code compilé et de la bibliothèque MariaDB.
    java -cp "target/classes:$HOME/.m2/repository/org/mariadb/jdbc/mariadb-java-client/3.3.2/mariadb-java-client-3.3.2.jar" fr.univorleans.iut45.briquiuto.App

elif [ "$CHOIX" = "2" ]; then
    echo "=== Lancement de l'application (GRAPHIQUE) ==="
    mvn javafx:run -q

else
    echo "Choix invalide. Le lancement a été annulé."
    exit 1
fi