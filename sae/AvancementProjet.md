## État d’avancement du projet

Par rapport à la commande de départ, notre application Briqu’IUT-O a bien avancé. On a réussi à développer la majorité des fonctionnalités demandées et à les relier à la base de données.

### A. Ce qui a été fait
On a mis en place une architecture MVC et développé les deux profils utilisateurs demandés.

* **Connexion et base de données :** l’application est connectée à MariaDB avec JDBC. On a importé les données et on les a adaptées pour ajouter les liens des images.
* **Espace administrateur :** on a créé les formulaires pour ajouter des pièces, des figurines et des thèmes, avec une insertion sécurisée dans la base.
* **Catalogue global :** on a mis en place un catalogue consultable avec plusieurs onglets.
* **Espace collectionneur :** les recherches avancées fonctionnent, par exemple la recherche d’une boîte par son numéro ou par une pièce qu’elle contient.
* **Création de MOC :** on a développé la création de boîtes personnalisées avec un panier temporaire pour ajouter les pièces et les figurines avant l’enregistrement.
* **Mes MOCs :** une page permet de retrouver ses propres créations.
* **Interface utilisateur :** on a ajouté une barre de recherche dynamique avec `FilteredList`, ce qui permet de filtrer les résultats en temps réel. Les images sont aussi chargées de manière asynchrone pour garder une application fluide.

### B. Ce qu’on n’a pas pu faire
Malgré notre avancement, certaines choses n’ont pas été terminées, soit par manque de temps, soit parce que c’était plus compliqué que prévu.

* **Pagination complète :** dans le catalogue administrateur, on a limité l’affichage à 500 éléments avec `LIMIT 500`. Une vraie pagination aurait demandé plus de travail.
* **Factorisation du code graphique :** les en-têtes et les pieds de page sont encore répétés dans plusieurs vues. On aurait pu créer une classe commune, mais cela aurait demandé une refonte trop importante en fin de projet.
* **Statistiques plus avancées :** on voulait faire plus de graphiques, mais la gestion des images et les changements dans la base ont pris plus de temps que prévu.
* **Page d’inscription utilisateur :** on aurait aussi dû faire une vraie page d’inscription pour les utilisateurs, ce qui aurait rendu l’application plus complète et plus logique pour la gestion des comptes.

Au final, on a surtout préféré sécuriser les fonctionnalités principales pour avoir une application stable et fonctionnelle.