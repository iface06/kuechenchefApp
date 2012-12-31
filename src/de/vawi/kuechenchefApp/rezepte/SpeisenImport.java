package de.vawi.kuechenchefApp.rezepte;

import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import de.vawi.kuechenchefApp.dateien.Datei;
import de.vawi.kuechenchefApp.dateien.DateiLeser;
import java.util.*;
/**
 * Importiert die Dateien rezepte.csv und hitliste.csv aus dem Ordner "Rezepte". 
 * 
 * @author Tatsch 
 * @version (a version number or a date)
 */
public class SpeisenImport
{
    public static final int SPEISEN_NAME = 0;
    private Datei rezepte;
    private Datei hitliste;
    private CsvZeileSeparator separator = new CsvZeileSeparator();
    private SpeisenVerwaltung speisen = SpeisenVerwaltung.getInstanz();
    
    
    
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
    public void importFiles() {
        
        fuegeSpeisenVonHitlisteInSpeisenverwaltungEin();
        fuegeZutatenZuSpeisenAusRezepteDateiHinzu();
    }

    private void fuegeSpeisenVonHitlisteInSpeisenverwaltungEin() {
        for (String zeile : hitliste) {
            Speise speise = erstelleSpeise(zeile);
            speisen.addSpeise(speise);
        }
    }
    
    private Speise erstelleSpeise(String zeile) {
        SpeisenErsteller ersteller = new SpeisenErsteller();
        Speise speise = ersteller.erstelle(zeile);
        return speise;
    }

    private void fuegeZutatenZuSpeisenAusRezepteDateiHinzu() {
        for (String zeile : rezepte) {
            List<String> zellen = separator.separiere(zeile);
            Speise speise = speisen.findeSpeise(zellen.get(SPEISEN_NAME));
            Zutat zutat = new ZutatErsteller().erstelle(zeile);
            speise.addZutat(zutat);
        }
    }

    protected void setRezepte(Datei rezepte) {
        this.rezepte = rezepte;
    }

    protected void setHitliste(Datei hitliste) {
        this.hitliste = hitliste;
    }
}
