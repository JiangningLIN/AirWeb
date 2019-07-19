# Test Android Airweb
L'objet de test de réaliser une petite application: « fr.airweb.news ». L'application devra être supportée par les terminaux à partir de l'API level 23.

## Réalisation
L'application met en place deux écrans permettant de lister des news et afficher le détail d'une news.

### Le premier écran permet:
* De charger la liste des news depuis le webservice en Json. Afficher la liste des news uniquement de type news. Chaque élément 
de la liste est présenté avec image, une texte descriptif et une texte title. Il contient une barre de recherche et une barre de menu 
pour filtrer et trier. <br/>
![first_screen](https://user-images.githubusercontent.com/33124632/61568240-c3dcab00-aa82-11e9-8813-979d3342ef01.png) ![filter_all](https://user-images.githubusercontent.com/33124632/61568565-fe931300-aa83-11e9-98b5-9136916bfa3c.png)

* De filtrer en fonction de type (hot, news, actualité). <br/>
![filter](https://user-images.githubusercontent.com/33124632/61568928-67c75600-aa85-11e9-8888-a16801a0cd8c.png)

* De trier en fonction de date et de titre. <br/>
![order](https://user-images.githubusercontent.com/33124632/61569107-1ec3d180-aa86-11e9-82c5-9683352c5ca0.png)
![order_by_title](https://user-images.githubusercontent.com/33124632/61569098-0a7fd480-aa86-11e9-879a-18bc3cf3efd5.png)

* De contenir une barre de recherche selon le titre d'article. </br>
![recherce](https://user-images.githubusercontent.com/33124632/61569168-60ed1300-aa86-11e9-8f2e-1888d0e24a27.png)

### Le deuxième écran affiche le détail de l'objet news:
* Un texte sur une seul ligne(title), une image, un texte de description. <br/>
![detail](https://user-images.githubusercontent.com/33124632/61569703-f093c100-aa88-11e9-8132-4bad5413c2c5.png)

### Gestion offline
L'application permet à consulter les articles quand il n'y a pas de connexion.

### Page de contact
Elle permet d'afficher contact d'AirWeb: titre, adresse, mail, tel. <br/>
![contact](https://user-images.githubusercontent.com/33124632/61569473-ce4d7380-aa87-11e9-8311-7294c4b850a7.png)

* Adresse: lancer le GoogleMap et faire l'itération en cliquant sur l'adresse. <br/>
![map](https://user-images.githubusercontent.com/33124632/61569539-05238980-aa88-11e9-824b-ed4cc3d73f29.png)

* Mail: cliquer dessus, ouvrir une appli pour envoyer un mail à l'adresse de mail. <br/>
![mail](https://user-images.githubusercontent.com/33124632/61569582-4025bd00-aa88-11e9-8b18-ab3271b1a7c8.png)

* Tel: en cliquant, configurer un appel à ce numéro. <br/>
![tele](https://user-images.githubusercontent.com/33124632/61569628-8a0ea300-aa88-11e9-864d-160a37022649.jpg)
