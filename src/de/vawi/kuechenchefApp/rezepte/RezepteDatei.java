
package de.vawi.kuechenchefApp.rezepte;

import de.vawi.kuechenchefApp.importData.Datei;
import de.vawi.kuechenchefApp.importData.Datei;


public enum RezepteDatei implements Datei {
    HITLISTE, REZEPTE;

    @Override
    public String getDateinameMitPfad() {
        return "";
    }
}
