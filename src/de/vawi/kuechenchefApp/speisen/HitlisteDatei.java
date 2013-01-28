
package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.dateien.Datei;
import java.util.*;

/**
 *
 * @author Tatsch
 */
public class HitlisteDatei implements Datei{
    
    private List<String> zeilen = new ArrayList<>();
    private final String dateiName = "hitliste.csv";
    
    @Override
    public String getDateiname() {
        return dateiName;
    }

    @Override
    public Iterator<String> iterator() {
        return zeilen.iterator();
    }

}
