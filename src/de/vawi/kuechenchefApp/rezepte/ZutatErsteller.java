

package de.vawi.kuechenchefApp.rezepte;

import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import java.util.List;


class ZutatErsteller {
    public static final int MENGE = 1;
    public static final int NAHRUNGSMITTEL_NAME = 3;
    NahrungsmittelVerwaltung nahrungsmittels = NahrungsmittelVerwaltung.getInstanz();

    Zutat erstelle(String rezeptZeile){
        List<String> zellen = separiereRezeptZeile(rezeptZeile);
        Zutat zutat = erstelleZutat(zellen);
        
        return zutat;
    }

    private List<String> separiereRezeptZeile(String rezeptZeile) {
        CsvZeileSeparator separator = new CsvZeileSeparator();
        List<String> zellen = separator.separiere(rezeptZeile);
        return zellen;
    }

    private Zutat erstelleZutat(List<String> zellen) throws NumberFormatException {
        Zutat zutat = new Zutat();
        zutat.setMenge(Integer.valueOf(zellen.get(MENGE)));
        Nahrungsmittel nahrungsmittel = findeNahrungsmittelAusRezept(zellen);
        zutat.setNahrungsmittel(nahrungsmittel);
        return zutat;
    }

    private Nahrungsmittel findeNahrungsmittelAusRezept(List<String> cells) {
        Nahrungsmittel nahrungsmittel = nahrungsmittels.findeDurchName(cells.get(NAHRUNGSMITTEL_NAME));
        return nahrungsmittel;
    }
    
}
