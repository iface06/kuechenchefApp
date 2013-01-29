package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.dateien.Datei;
import java.util.Iterator;


/**
 *
 * 
 * 
 * @author Lepping
 * @version 29.01.2013
 */
public class EinkaufslistenDatei implements Datei{

    String lieferant;

    public EinkaufslistenDatei(String lieferant) {
        this.lieferant = lieferant;
    }
    
    @Override
    public String getDateiname() {
        return "einkaufsliste_" + lieferant;
    }

    @Override
    public Iterator<String> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
