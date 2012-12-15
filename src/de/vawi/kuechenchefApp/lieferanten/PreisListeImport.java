package de.vawi.kuechenchefApp.lieferanten;

import java.util.*;

/**
 * Importiert alle Lieferanten und ihre Angebote aus den Preislistendateien.
 * 
 * 
 * @author Struebe 
 * @version (a version number or a date)
 */
public class PreisListeImport
{
    /**
     * Importiert die Preislistedateien aus dem Ordner "Lieferanten" und erstellt die LieferantenListe mit allen Lieferanten und Angeboten. 
     * 
     * Die Dateien müssen nach folgender Konvention benannt werden: preisliste_[lfd. Nummer].csv
     * [lfd. Nummer] = Aufeinander folgende Integer (1, 2, 3, ...)
     * Beispiel: preisliste_1.csv, preisliste_2.csv usw. 
     * 
     * Werden keine Dateien gefunden oder enthält der Ordner keine Dateien, so wird eine FileNotFoundException geworfen und das Programm anschließend beendet.
     * 
     * @return     Gibt die LieferantenListe zurzück.
     */
    public LieferantenVerwaltung importFiles()
    {
        LieferantenVerwaltung verwaltung = new LieferantenVerwaltung(new ArrayList<Lieferant>());
        return verwaltung;
    }
}
