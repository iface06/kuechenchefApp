package de.vawi.kuechenchefApp.rezepte;

import java.util.*;
/**
 * Importiert die Dateien rezepte.csv und hitliste.csv aus dem Ordner "Rezepte". 
 * 
 * @author Tatsch 
 * @version (a version number or a date)
 */
public class SpeisenImport
{
    /**
     * Importiert die Dateien rezepte.csv und hitliste.csv aus dem Ordner "Rezepte". 
     * Auf Basis dieser Dateien wird die SpeisenVerwaltung für den SpeiseplanErsteller erstellt.
     * 
     * Die Datei mit den Rezepten muss den namen "rezepte.csv" haben und im Ordner "Rezepte" liegen.
     * 
     * Die Datei mit den beliebtesten Rezepten muss den Namen "hitliste.csv" haben und im Ordner "Rezepte" liegen.
     * 
     * Sind diese Dateien nicht vorhanden wird eine FileNotFoundException geworfen. Anschließend wird das Programm mit der Fehlermeldung beendet.
     * 
     * @return     Gibt die erstellte RezepteListe gekapselt in der Speisenverwaltung zurück. 
     */
    public SpeisenVerwaltung importFiles()
    {
        return new SpeisenVerwaltung(new ArrayList<Speise>());
    }
}
