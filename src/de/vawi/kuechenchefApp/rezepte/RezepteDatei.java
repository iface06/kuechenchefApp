
package de.vawi.kuechenchefApp.rezepte;

import de.vawi.kuechenchefApp.dateien.Datei;
import de.vawi.kuechenchefApp.dateien.DateiLeser;
import java.util.*;


public enum RezepteDatei implements Datei {
    HITLISTE("hitliste.csv"), REZEPTE("rezepte.csv");

    List<String> zeilen;
    DateiLeser leser = new DateiLeser();
    private final String dateiNameMitPfad;
    
    RezepteDatei(String dateiNameMitPfad){
        this.dateiNameMitPfad = dateiNameMitPfad;
        leseZeilenAusDatei();
    }
    
    @Override
    public String getDateinameMitPfad() {
        return "";
    }

    @Override
    public Iterator<String> iterator() {
        return zeilen.iterator();
    }

    private void leseZeilenAusDatei() {
        leser.setDatei(this);
        zeilen = leser.leseDatei();
    }

    
}
