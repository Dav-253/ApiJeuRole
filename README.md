# ApiJeuRole

API REST d'un jeu de rôle (joueurs, héros, lobbys, parties, combats), en Java avec Jersey et Grizzly.

> Le code est dans le dossier **`untitled/`**. C'est là que se trouve le `pom.xml`.

## Prérequis

- **Java 21** ou plus récent
- **Maven** : celui d'IntelliJ suffit, pas besoin de l'installer séparément

## Lancer le serveur

### Avec IntelliJ (recommandé)

1. **File → Open**, puis choisir le dossier **`untitled`**. Ne pas ouvrir le dossier `ApiJeuRole` au complet, sinon IntelliJ ne trouve pas les dépendances.
2. Attendre qu'IntelliJ finisse de charger le projet Maven (barre de progression en bas).
3. **Une seule fois** : `Ctrl + Alt + S`, puis **Build, Execution, Deployment → Build Tools → Maven → Runner**, cocher **Delegate IDE build/run actions to Maven**, puis **OK**.
4. Ouvrir `src/main/java/apijeurole/Main.java` et cliquer sur le ▶ vert.

Le serveur démarre sur **http://localhost:8080**.

### En ligne de commande

```bash
cd untitled
mvn compile exec:java
```

Arrêt : `Ctrl + C`.

Pour utiliser un autre port, définir la variable d'environnement `PORT` (par exemple `PORT=9090`).

## Lancer les tests

- **IntelliJ** : dans le panneau **Maven** (icône **m** à droite), ouvrir **api-jeu-role → Lifecycle** et double-cliquer sur **test**. On peut aussi cliquer sur le ▶ vert à côté d'une classe de test.
- **Ligne de commande** :

```bash
cd untitled
mvn test
```

Le résultat attendu à la fin : `Tests run: …, Failures: 0, Errors: 0` et `BUILD SUCCESS`.

## Tester l'API à la main

Le fichier **`untitled/requests.http`** contient des exemples de requêtes pour chaque story. Dans IntelliJ, on clique sur le ▶ à côté d'une requête pour l'envoyer.

1. Créer un compte (`POST /players`), puis se connecter (`POST /sessions`).
2. Copier le `token` et le `playerId` reçus dans les variables `@token` et `@playerId` du fichier.
3. Envoyer les autres requêtes (profil, lobbys, héros…).

⚠️ Les données sont gardées **en mémoire** : tout disparaît quand on redémarre le serveur. Il faut donc recréer son compte après chaque redémarrage.

## Problèmes fréquents

| Erreur | Cause | Solution |
|---|---|---|
| `package org.junit.jupiter.api does not exist` | IntelliJ compile sans passer par Maven | Ouvrir le dossier `untitled` et cocher **Delegate IDE build/run actions to Maven** (voir plus haut) |
| `Address already in use: bind` | Un serveur roule déjà sur le port 8080 | Arrêter l'ancien avec le carré rouge ⏹ dans l'onglet **Run**, puis relancer |
| Des `WARNING` au démarrage (`Unsafe`, `JNA`, `WADL`…) | Avertissements des librairies | Rien à faire, le serveur fonctionne |

## Contribuer

1. Partir de `develop` à jour :
   ```bash
   git switch develop
   git pull
   git switch -c feat/<code-story>-<description>   # ex. feat/l3-rejoindre-lobby
   ```
2. Coder, puis vérifier que `mvn test` passe.
3. Committer avec un message du genre `feat(lobby): rejoindre un lobby (L3)`, puis pousser.
4. Ouvrir une pull request **vers `develop`** (pas `main`), avec `Closes #<numéro de l'issue>` dans la description.
