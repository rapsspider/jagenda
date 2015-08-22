# jAgenda.readme #

Membre du projet :
  * david PELISSIER
  * jason BOURLARD

**trunk**
  * **jagenda**
    * **util**
      * `Config.java`
      * `Fichier.java`
      * `Saisie.java`
    * **test**
      * `TestConfig.java`
      * `TestFichier.java`
      * `TestHoraire.java`
      * `TestRendezVous.java`
      * `TestSaisie.java`
    * `Agenda.java`
    * `Horaire.java`
    * `Principale.java`
    * `RendezVous.java`


---

## Discussion ##

---

## Todo ##

---

## Notes de mise à jour ##

### Version 0.3 ###

**David & Jason** 20/05/2012 :
  * `Fichier.java` Modification de la méthode compareTo() ainsi que nbLigne()
  * `RendezVous.java` Ajout de compareTo(), equals(), hashCode()
  * `Horaire.java` Refonte de la classe.
  * `Config.java` Refonte de la classe en OO
  * `test` Ajout de nouveaux tests.

**David** 09/05/2012 :
  * `Horaire`
    * Refonte TOTALE de la classe `Horaire` avec donc des horaires instanciables (et plus abstract) et utilisation de `GregorianCalendar` ce qui donne au final `Horaire` extends `GregorianCalendar` extends `Calendar`
    * Polymorphisme de la méthode `toString` pour l'affichage formaté des `Horaires`
  * `RendezVous`
    * Refonte des constructeurs de `RendezVous` et instauration des vérification pour les modifications des paramètres tels que @libelle@ par exemple
  * Divers
    * Correction de plus de 100 fautes d'AURTAUGHRAFFE sur notamment `Fichier`
    * Légère correction du `estCorrect` en ne permettant plus le caractère ' ', problématique dans les libellés ou pour un nom de fichier ou encore pour la portabilité sur des systèmes tels que Linux.
    * Début de la refonte des `TestRendezVous`

> 
---

### Version 0.2 ###

**Jason** 09/05/2012 :
  * Amélioration de la classe `Fichier` et ajout de nouveaux tests `TestFichier`
  * Fichier.chemin et Fichier.nom deviennent des constantes Fichier.CHEMIN et Fichier.NOM
  * Ajout de méthode dite polymorphisme dans `Fichier`

**Jason** 08/05/2012 :
  * Amélioration de la classe `Fichier` à l'aide d'un test de performance ajouté au fichier de test `TestFichier`

**Jason** 01/05/2012 :
  * Amélioration de la classe `Fichier`

**David** 17/04/2012 :
  * Mise en place des levées d'exception au sein des méthodes de vérifications dans la classe `Horaire`
  * Ajout de la méthode `Horaire.estValide()` pour le format (jour, semaine, annee)
  * Ajout du mutateur `RendezVous.setHoraireDebut()` pour le format (jour, semaine, annee)
  * Ajout du mutateur `RendezVous.setHoraireFin()` pour le format (jour, semaine, annee)

**David** 17/04/2012 :
  * Passage à la version 0.2
  * Stockage de la version 0.1 dans Branches (développement parallèle)
  * Mise en place d'une classe `Horaire` de vérification de dates et heure basée sur `java.util.Calendar`

> 
---

### Version 0.1 ###

**Jason** 16/04/2012 :
  * Ajout de nouvelle méthode dans `Date`.
  * Ajout d'une classe de test au nom de `TestDate`.

**Jason** 15/04/2012 :
  * Modification et amélioration de la classe `Saisie` permettant la saisie par l'utilisateur sur l'entrée/sortie standart.
  * Ajout d'une classe de test `TestSaisie`.
  * Ajout d'une nouvelle classe `Date` avec des méthodes basic.
  * Modification de méthode `Horaire` en modifiant quelques petites erreurs.
  * Uncomment la variable date dans la classe `RendezVous`.
  * Amélioration de la classe `Fichier` suite au nouveau test de la classe `TestFichier`.

**Jason** 14/04/2012 :
  * Ajout d'une classe `Saisie` permettant la saisie par l'utilisateur sur l'entrée/sortie standart.
  * Modification de la classe de test `Horaire` pour ajout de test sur l'heure, et ajout d'un constructeur.
  * Ajout de nouveaux constructeurs dans la classe `Fichier`.
  * Ajout de nouvelles méthodes dans la classe `Fichier`.
  * Ajout de nouveaux test dans la classe `TestFichier`.

**Jason** 13/04/2012 :
  * La classe `Fichier` est terminé.
  * La classe de test `TestFichier` a été mis à jour.

**Jason** 12/04/2012 :
  * Mise en place de nouvelle méthode dans la classe `Fichier`.
  * Amélioration du fichier TestFichier.java.
  * Mise en place de nouvelle restriction dans la classe `Fichier`.
  * Correction de bug mineure sur la javaDoc.

**David** 11/04/2012 :
  * Remise à zéro du dépôt.
  * Corrections mineures sur `Fichier`.
  * Changement des entÃªtes de fichier.
  * Ajout de /dossier.
  * Création du diagramme de classe (provisoire).
  * Création de l'arborescence d'application (provisoire).
**Jason** 11/04/2012 :
  * Mis en place de `TestFichier`.
  * Amélioration de `Fichier`.

**David** 10/04/2012 :
  * Mise en place de l'arborescence et package.
  * Classe `RendezVous` crée.
  * Classe `Horaire` crée.
  * Classe de test `TestRendezVous` crée.