
package de.vawi.kuechenchefApp.rezepte;

import de.vawi.kuechenchefApp.dateien.Datei;
import de.vawi.kuechenchefApp.dateien.Datei;


public enum RezepteDatei implements Datei {
    HITLISTE, REZEPTE;

    @Override
    public String getDateinameMitPfad() {
        return "";
    }
}
