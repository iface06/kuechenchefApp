Vorraussetzungen für einen erfolgreichen Import
Folgende Dateien müssen vorhanden sein
    - hitliste.csv
    - rezepte.csv
    - preisliste_X.csv (wobei das X eine ganze Zahl sein muss; 1, 2, 3...)

hitliste.csv
Zeilenformat: 
Muss alle Rezepte in der rezepte.csv enthalten und platzieren.
Die Beliebtheit muss einmalig sein

rezepte.csv
Zeilenformat: 
Der Name des Rezepts muss in der rezepte.csv und der hitliste.csv gleich sein.
Wird eine Zutat zu einem Rezept nicht von mindestens einen Lieferant angeboten, so wird das Rezept nicht importiert
