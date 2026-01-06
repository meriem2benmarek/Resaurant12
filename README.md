# Restaurant App

## Description
Cette application console Java simule un système de gestion pour un restaurant marocain. Elle permet aux utilisateurs de se connecter en mode admin ou client, de naviguer dans les catégories de plats, de passer des commandes et de générer des factures.

## Fonctionnalités
- **Authentification** : Connexion en tant qu'admin (username: admin, password: 1234) ou client.
- **Catégories de plats** : Affichage des plats marocains avec prix.
- **Gestion des commandes** : Ajout d'articles à la commande, calcul du total.
- **Mode admin** : Accès à des fonctionnalités supplémentaires (non implémentées dans cette version).

## Structure du projet
- `AppConsole.java` : Classe principale gérant l'interface console, la connexion et l'initialisation.
- `Category.java` : Représente une catégorie de plats avec une liste d'articles.
- `MenuItem.java` : Représente un article du menu avec nom et prix.
- `Order.java` : Gère la commande actuelle avec une liste d'articles commandés.
- `OrderItem.java` : Représente un article dans la commande avec quantité et total.

## Comment exécuter
1. Assurez-vous d'avoir Java installé sur votre système.
2. Compilez les fichiers Java :
   ```
   javac *.java
   ```
3. Exécutez l'application :
   ```
   java AppConsole
   ```
4. Suivez les instructions à l'écran pour vous connecter et utiliser l'application.

## Exemple d'utilisation
- Connectez-vous avec "admin" / "1234" pour le mode admin.
- Les plats marocains sont préchargés (Couscous Royal, Tajine, etc.).
- Ajoutez des articles à votre commande et consultez le total.

## Auteurs
- ElHallaoui Nouhayla
- Drhourhi Omaima
- BenMbarek  Meriem

## Licence
Ce projet est sous licence MIT.
