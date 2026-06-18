# 4. Manuel utilisateur et configuration de test

## A. Configuration technique
Pour permettre le lancement de l’application et sa connexion à la base de données de l’IUT, les paramètres JDBC sont déjà configurés sur la session étudiante prévue pour les tests.

* **Identifiant base de données :** `o22403771`
* **Mot de passe base de données :** `o22403771`

---

## B. Page de connexion et profils
Au démarrage, l’application affiche la page de connexion de Briqu’IUT-O. Pour accéder aux fonctionnalités, il faut utiliser l’un des deux comptes de test préconfigurés.

### Accès Administrateur
Le profil Administrateur permet de gérer le catalogue mondial.
* **Identifiant :** `admin`
* **Mot de passe :** `admin`

### Accès Collectionneur
Le profil Collectionneur permet de consulter les données et de créer des boîtes personnalisées.
* **Identifiant :** `collec`
* **Mot de passe :** `collec`

---

## C. Guide de l’administrateur
Le mode Administrateur permet d’enrichir et de consulter le catalogue.

### Ajouter des éléments
Depuis le menu principal, utilisez les boutons **Ajouter** pour créer de nouvelles pièces, figurines ou thèmes. Il suffit ensuite de compléter le formulaire correspondant. 
> **Attention :** L’identifiant doit être unique pour éviter toute erreur lors de l’enregistrement.

### Consulter le catalogue
Cliquez sur **Catalogue Global** pour afficher les données disponibles. Vous pouvez ensuite naviguer entre les onglets **Boîtes**, **Pièces** et **Figurines**.
*Note : L’application affiche un aperçu des 500 premiers éléments afin de garantir une navigation fluide.*

---

## D. Guide du collectionneur
Le mode Collectionneur permet de rechercher des boîtes et de créer ses propres MOCs.

### Explorer les boîtes
Utilisez les boutons de recherche pour trouver une boîte précise, soit par son numéro, soit à partir d’une pièce qu’elle contient.
Les tableaux affichent les informations principales ainsi qu’un aperçu visuel de la boîte lorsque l’image est disponible.

### Composer un MOC
Pour créer une boîte personnalisée :

1. Cliquez sur **Composer une boîte**.
2. Renseignez les informations générales. *(Utilisez de préférence un identifiant commençant par `PERSO-`)*.
3. Sélectionnez une pièce, sa couleur et sa quantité, puis cliquez sur **Ajouter au panier**.
4. Répétez l’opération pour les figurines si nécessaire.
5. Vérifiez le contenu du panier situé à droite.
6. Cliquez sur le bouton vert **Créer la boîte personnalisée**.

### Consulter vos créations
Pour retrouver vos boîtes personnalisées, cliquez sur **Consulter mes MOCs**.
Vous pouvez ensuite utiliser la barre de recherche située en haut du tableau pour filtrer instantanément vos créations par nom ou par numéro.