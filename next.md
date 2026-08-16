# 📜 Système de Miasme & Résidus Alchimiques

---

## 1. Vue d'Ensemble
Le **Miasme** représente la pollution magique et la saturation toxique liées à l'alchimie. Il agit sur deux plans complémentaires :
- **Environnemental :** Dépôts physiques et toxiques apparaissant près des postes de travail.
- **Corporel :** Jauge interne du joueur (de **0% à 100%**) limitant l'abus de potions et sanctionnant les imprudences.

---

## 2. Le Bloc : Dépôt Miasmatique (*Miasmatic Residue*)

### A. Apparition
* **Déclencheur :** À chaque utilisation d'un **Alambic** ou mélange dans le **Chaudron**, une couche de *Dépôt Miasmatique* a une chance d'apparaître sur un bloc adjacent au sol (rayon de 1 à 2 blocs).
* **Propriétés :** Bloc fin de surface émettant de légères volutes de fumée sombre/violacée.

### B. Effets Environnementaux
* **Zone de danger :** Marcher sur le bloc ou rester à proximité immédiate augmente la jauge de Miasme du joueur (**+2% par seconde**).
* **Flétrissement :** S'il reste au sol trop longtemps, il détruit les fleurs et transforme l'herbe adjacente en terre stérile.

### C. Manipulation & Sécurité
* ❌ **Destruction à main nue (Critique) :** Casser le bloc sans outil adapté libère un nuage concentré qui inflige instantanément **+80% à +90% de Miasme** au joueur.
*  **Collecte sécurisée :** Faire un **clic droit avec une Fiole Vide (*Glass Bottle*)** retire proprement le bloc et donne une **Fiole de Résidu Miasmatique** (*Miasmatic Flask*).
* 🗑️ **Élimination :** Jeter la fiole dans la lave, le feu ou un cactus pour s'en débarrasser définitivement.

---

## 3. La Jauge Corporelle de Miasme

### A. Sources d'Augmentation

| Action / Source                           |                   Gain de Miasme                    |
|:------------------------------------------|:---------------------------------------------------:|
| **Boire une potion classique**            | 0.0025 %/tick d'effet + 10 %/niveau d'amplification |
| **Consommer un élixir permanent**         |                     +50% à +70%                     |
| **Rester près d'un Dépôt Miasmatique**    |                    +2% / seconde                    |
| **Casser un Dépôt Miasmatique à la main** |             **+80% à +90% (Critique)**              |

---

### B. Paliers de Gravité & Malus (0% à 100%)

|   Palier (%)   | État               | Effets de Gameplay                                                                    |
|:--------------:|:-------------------|:--------------------------------------------------------------------------------------|
|  **0% – 24%**  | **Sain**           | • Aucun malus.<br>• Métabolisme stable.                                               |
| **25% – 49%**  | **Irritation**     | • donne le mob effect hungry<br>• Régénération naturelle ralentie.                    |
| **50% – 74%**  | **Intoxication**   | • donne les mob effects cold, slowness et mining fatigue                              |
| **75% – 94%**  | **Empoisonnement** | • donne les mob effect paranoïa, clumsiness et asthma                                 |
| **95% – 100%** | **Overdose**       | • donne les mob effect nausea, poison<br> • supprime tous les mobs effects benefiques |

les effets se stack par palier (on subit les malus de toutes les zones en dessous et de celle présente)

> tous les mobs effects existent déjà en vanilla ou dans mon mod

---

### C. Dissipation & Traitement

1. **Décroissance Naturelle :** La jauge diminue lentement avec le temps (**-1% toutes les 4 à 5 secondes**) en zone saine.
2. **Remèdes Rapides :**
    * **Seau de Lait :** Réduit de **-25%**.
    * **L'effet purification** réduit, en plus de son effet actuel, de **-35%** si le taux de miasme dans le corps dépasse 50%.

--- 

### D. Autre

- penser à ajouter toutes les constantes dans le config screen dans une nouvelle catégorie