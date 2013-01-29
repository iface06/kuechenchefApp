package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.dateien.*;
import java.util.*;

class RezepteDatei implements Datei {

    private List<String> zeilen = new ArrayList<>();
    private final String dateiName = "rezepte.csv";

    /**
     *
     * @return Gibt den Datei-Namen wieder.
     */
    @Override
    public String getDateiname() {
        return dateiName;
    }

    /**
     *
     * @return Gibt ein Iterator-Objekt wieder, mit dem die Zeilen der
     * rezepte-Datei iteriert werden können.
     */
    @Override
    public Iterator<String> iterator() {
        return zeilen.iterator();
    }
}
