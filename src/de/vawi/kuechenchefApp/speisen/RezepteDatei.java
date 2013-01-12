
package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.dateien.*;
import java.util.*;


public enum RezepteDatei implements Datei {
    HITLISTE("hitliste.csv"), REZEPTE("rezepte.csv");

    List<String> zeilen;
    DateiLeser leser;
    Datei datei;
    private final String dateiNameMitPfad;
    
    RezepteDatei(String dateiNameMitPfad){
        this.dateiNameMitPfad = dateiNameMitPfad;
        leseZeilenAusDatei();
    }
    
    @Override
    public String getDateinameMitPfad() {
        return datei.getDateinameMitPfad();
    }

    @Override
    public Iterator<String> iterator() {
        return datei.iterator();
    }

    private void leseZeilenAusDatei() {
        leser = new DateiLeser(this.dateiNameMitPfad);
        datei = leser.leseDatei();
    }
}
