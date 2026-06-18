### 3. Rapport de tests

Avant de livrer l’application, on a fait plusieurs tests pour vérifier que tout fonctionnait correctement. L’objectif était surtout de contrôler la connexion à la base de données, la navigation dans l’application et le comportement de l’interface graphique.

#### Environnement de test
Les tests ont été réalisés avec la base de données MariaDB de l’IUT, en utilisant les comptes admin pour l’administrateur et collec pour le collectionneur.

#### Tests réalisés
On a d’abord testé le lancement de l’application et la connexion à la base de données. Au démarrage, les données comme les thèmes et les pièces se chargent bien, donc la connexion JDBC fonctionne correctement.

On a aussi testé la navigation entre les différents écrans avec les boutons comme Retour et Accueil, en se connectant avec les deux profils. Les redirections se font bien et les scènes s’affichent sans problème.

Ensuite, on a vérifié le comportement des images. Pour ça, on a forcé une URL d’image invalide dans la base. L’application n’a pas planté : l’erreur est bien gérée et l’interface reste utilisable, ce qui évite un crash du tableau JavaFX.

On a également testé la recherche dynamique dans la page Mes MOCs. Quand on tape du texte dans la barre de recherche, le tableau se met à jour directement, sans avoir besoin de relancer une requête SQL à chaque fois. C’est fluide et ça marche bien.

Après ça, on a testé la création d’un MOC. Quand on ajoute une pièce ou une figurine au panier, les éléments s’affichent bien dans la liste temporaire et le total se met à jour correctement. Puis, quand on clique sur Créer la boîte personnalisée, l’insertion en base se fait correctement et le MOC apparaît bien dans Mes MOCs.

Enfin, on a testé un cas d’erreur côté administrateur : si on essaye d’ajouter une pièce avec un identifiant déjà utilisé, l’application bloque l’insertion. L’erreur SQL est bien interceptée et un message s’affiche à l’écran au lieu de faire planter l’application.

#### Bilan des tests
Au final, les tests montrent que l’application est assez stable. Les erreurs liées à la base de données sont bien gérées, l’interface reste réactive et les fonctionnalités principales marchent comme prévu. On a donc pu valider les parties les plus importantes du projet avant la soutenance.