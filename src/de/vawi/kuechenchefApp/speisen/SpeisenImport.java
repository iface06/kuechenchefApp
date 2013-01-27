package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import de.vawi.kuechenchefApp.dateien.Datei;
import de.vawi.kuechenchefApp.dateien.DateiLeser;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
import java.io.File;
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
    String dateiOrdner;
    
    
    // @todo Dieser Ordner muss noch irgendwo verwendet werden.
    public SpeisenImport(String dateiOrdner) {
        this.dateiOrdner = dateiOrdner;
    }
   
    
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
        sortiereZutatenNachKategorie();
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

    private void sortiereZutatenNachKategorie() {
        for (Speise speise : speisen) {
            Collections.sort(speise.getZutaten(), new Comparator<Zutat>() {

                @Override
                public int compare(Zutat o1, Zutat o2) {
                    SpeisenUndNahrungsmittelKategorie kategorie1 = o1.getNahrungsmittel().getKategorie();
                    SpeisenUndNahrungsmittelKategorie kategorie2 = o2.getNahrungsmittel().getKategorie();
                    return kategorie1.compareTo(kategorie2);
                }
            });
        }
    }
    

}
