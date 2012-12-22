/**
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.dateien;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Max
 */
public class DateiSchreiber {
    
    private DateiManager dateiManager;
    private final Datei dateiName;
    

    /**
     *
     * @param dateiName
     */
    public DateiSchreiber(Datei dateiName) {
        this.dateiName = dateiName;
        
    }

    public void schreibe(String inhalt) {
        dateiManager = erzeugeDatei();
        oeffneDatei();
        schreiberInDatei(inhalt);
        schliesseDatei();
        
    }
    
    protected DateiManager erzeugeDatei() {
        return new DateiManager(dateiName.getDateinameMitPfad());
    }

    private void oeffneDatei() {
        try {
            dateiManager.openOutFile();
        } catch (IOException ex) {
            Logger.getLogger(DateiSchreiber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void schreiberInDatei(String inhalt) {
        dateiManager.writeLine(inhalt);
    }

    private void schliesseDatei() {
        dateiManager.closeOutFile();
    }

    
    
}
