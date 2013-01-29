package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.dateien.Datei;
import java.util.*;

/**
 *
 * Hitliste Datei aus Importordner
 * 
 * @author Tatsch
 * @version 29.01.2013
 */
public class HitlisteDatei implements Datei {

    private List<String> zeilen = new ArrayList<>();
    private final String dateiName = "hitliste.csv";

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
     * @return Ein Iterator-Objekt, mit dem die Zeilen der Hitlisten-Datei
     * iteriert werden können.
     */
    @Override
    public Iterator<String> iterator() {
        return zeilen.iterator();
    }
}
