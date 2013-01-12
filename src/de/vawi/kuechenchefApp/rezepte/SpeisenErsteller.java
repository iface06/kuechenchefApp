

package de.vawi.kuechenchefApp.rezepte;

import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import java.util.*;


class SpeisenErsteller {
    public static final int BELIEBTHEIT = 0;
    public static final int SPEISENNAME = 1;

    Speise erstelle(String hitlisteZeile) {
        List<String> cells = separiereHitlisteZeile(hitlisteZeile);
        Speise speise = erstelleSpeise(cells);
        
        return speise;
    }

    private List<String> separiereHitlisteZeile(String hitlisteZeile) {
        CsvZeileSeparator separator = new CsvZeileSeparator();
        List<String> cells = separator.separiere(hitlisteZeile);
        return cells;
    }

    private Speise erstelleSpeise(List<String> cells) throws NumberFormatException {
        Speise speise = new Speise();
        speise.setBeliebtheit(Integer.valueOf(cells.get(BELIEBTHEIT)));
        speise.setName(cells.get(SPEISENNAME));
        return speise;
    }
}
