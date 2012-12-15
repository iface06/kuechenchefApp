package de.vawi.kuechenchefApp.importData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class DateiLeser {

    protected Datei datei;
    private DateiManager manager;

    public void setDatei(Datei datei) {
        this.datei = datei;
    }

    public void setManager(DateiManager manager) {
        this.manager = manager;
    }

    public List<String> leseDatei() {
        List<String> zeilen = new ArrayList<>();
        manager = erstelleDateiManager();
        try {
            oeffneDatei();
            zeilen = leseAlleZeilen();
            schliesseDatei();
        } catch (IOException ex) {
            behandleFehlerfall(ex);
        }

        return zeilen;
    }

    protected DateiManager erstelleDateiManager() {
        return new DateiManager(datei.getDateinameMitPfad());
    }
    private void oeffneDatei() throws IOException {
        manager.openInFile();
    }

    private List<String> leseAlleZeilen() throws IOException {
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
    
    protected void behandleFehlerfall(IOException ex) {
        Logger.getLogger(DateiLeser.class.getName()).log(Level.SEVERE, null, ex);
    }
}
