[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)
# FoundationModEditor
A été conçu pour le jeu de simulation Foundation de l'éditeur Polymorph Games.

A partir un fichier Snippet pour vsc donné pour l'autocomplétion du script lua necessaire à l'import d'assets
dans le moteur de "jeu Hurricane" cette application genere automatiquement les formulaires et les éditeurs pour générer
le code lua dans un bloc de texte.
Certains paramètres exigent l'ouverture d'un nouveau formulaire.
|     SOURCE     |           IHM                 |      SERVICE                |
|----------------|-------------------------------|-----------------------------|
|SNIPPETS        |   FORMULAIRES ET EDITEURS     | CODE LUA                    |


```mermaid
graph LR
A[snippet code]--- B[param = paramName : paramType]-->C[Asset = List<param>params]
D[Map d'Assets]
C-->D
E{choix parmis Assets} --->F[formulaire autoadaptatif quelque soit l'asset]
D-->E
D-->F
G[instanciation d'un éditeur spécifique par type]-->H[generateur de code Lua]
```


![image](https://user-images.githubusercontent.com/47913777/178121168-bdcaf358-56ed-4a5c-8891-08d34c1be565.png)
