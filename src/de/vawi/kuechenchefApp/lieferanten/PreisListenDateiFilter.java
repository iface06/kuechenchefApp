package de.vawi.kuechenchefApp.lieferanten;

import java.io.*;

/**
 * Diese Klasse bildet den Filter ab, mit dem die einzulesenden Dateien
 * ausgewählt werden sollen.
 *
 * @author Struebe
 * @version 30.12.2012
 */
public class PreisListenDateiFilter implements FilenameFilter {

    /**
     * TODO: kann ich das so schreiben?
     *
     * @param d vom Supertypen vorgegebender, aber hier nicht verwendeter
     * Parameter.
     * @param name Name der zu überprüfenden Datei.
     * @return Gibt aus, ob der überprüfte Dateiname mit 'preisliste' beginnt
     * und auf .csv endet.
     */
    @Override
    public boolean accept(File d, String name) {
        return name.toLowerCase().startsWith("preisliste") && name.toLowerCase().endsWith(".csv");
    }
}