
package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.dateien.*;
import java.util.*;


class RezepteDatei implements Datei {

    private List<String> zeilen = new ArrayList<>();
    private final String dateiName = "rezepte.csv";
    
    @Override
    public String getDateiname() {
        return dateiName;
    }

    @Override
    public Iterator<String> iterator() {
        return zeilen.iterator();
    }
}
