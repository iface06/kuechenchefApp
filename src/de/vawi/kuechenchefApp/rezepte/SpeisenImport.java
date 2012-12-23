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
    
    private Datei rezepte; //= RezepteDatei.REZEPTE;
    private Datei hitliste; // = RezepteDatei.HITLISTE;
    private int SPEISENAME = 0;
    private SpeisenVerwaltung speisen = new SpeisenVerwaltung();
    
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
    public SpeisenVerwaltung importFiles() {
        
        Speise speise = leseNaechsteSpeiseVonDatei();
        
        
        return speisen;
    }


    public void setRezeptDatei(Datei rezeptDatei) {
        this.rezepte = rezeptDatei;
    }

    public void setHitliste(Datei hitliste) {
        this.hitliste = hitliste;
    }

    private Speise leseNaechsteSpeiseVonDatei() {
        List<Speise> speise = new ArrayList<Speise>();
        for (String zeile : rezepte) {
            List<String> zellen = separiereZieleInZellen(zeile);
            Speise s = speisen.findeSpeise(zellen.get(SPEISENAME));
            Zutat zutat = new Zutat();
            s.addZutat(zutat);
            
            
        }
        return new Speise();
    }

    private List<String> separiereZieleInZellen(String zeile) {
        return new CsvZeileSeparator().separiere(zeile);
        
    }
}
