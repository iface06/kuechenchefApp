package de.vawi.kuechenchefApp.dateien;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateiLeser {

    protected String dateiName;
    private DateiManager manager;

    public DateiLeser(String dateiName) {
        this.dateiName = dateiName;
    }

    public Datei leseDatei() {
        manager = erstelleDateiManager();
        List<String> zeilen = leseZeilenAusDatei();
        Datei datei = erstelleDatei(zeilen);
        return datei;
    }

    protected DateiManager erstelleDateiManager() {
        return new DateiManager(dateiName);
    }
    private void oeffneDatei() throws IOException {
        manager.openInFile();
    }

    private List<String> leseAlleZeilenInDatei() throws IOException {
        List<String> zeilen = new ArrayList<>();
        while (!istEndeDerDateiErreicht()) {
            String zeile = leseNaechsteZeileEin();
            if (zeile != null) {
                zeilen.add(zeile);
            }
        }
        
        return zeilen;
    }

    private boolean istEndeDerDateiErreicht() {
        return manager.eof();
    }
    
    private String leseNaechsteZeileEin() throws IOException {
        return manager.readLine();
    }
    
    private void schliesseDatei() throws IOException {
        manager.closeInFile();
    }
    
    protected void behandelFehlerfall(IOException ex) {
        Logger.getLogger(DateiLeser.class.getName()).log(Level.SEVERE, null, ex);
    }

    private List<String> leseZeilenAusDatei() {
        List<String> zeilen = new ArrayList<>();
        try {
            oeffneDatei();
            zeilen = leseAlleZeilenInDatei();
            schliesseDatei();
        } catch (IOException ex) {
            behandelFehlerfall(ex);
        }
        return zeilen;
    }

    private Datei erstelleDatei(final List<String> zeilen) {
        Datei datei = new Datei() {

            @Override
            public String getDateinameMitPfad() {
                return dateiName;
            }

            @Override
            public Iterator<String> iterator() {
                return zeilen.iterator();
            }
        }; 
        return datei;
    }
}
