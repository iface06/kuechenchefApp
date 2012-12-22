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
    
    private DateiManager datei;
    private final String dateiName;

    public DateiSchreiber(String dateiName) {
        this.dateiName = dateiName;
    }

    public void schreibe(String inhalt) {
        erzeugeDatei();
        oeffneDatei();
        schreiberInDatei(inhalt);
        schliesseDatei();
        
    }

    private void oeffneDatei() {
        try {
            datei.openOutFile();
        } catch (IOException ex) {
            Logger.getLogger(DateiSchreiber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void schreiberInDatei(String inhalt) {
        datei.writeLine(inhalt);
    }

    private void schliesseDatei() {
        datei.closeOutFile();
    }

    protected void erzeugeDatei() {
        datei = new DateiManager(dateiName);
    }
    
}
