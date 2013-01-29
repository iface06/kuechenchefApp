

package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.dateien.*;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import java.util.List;

/**
 * Diese Klasse erstellt Zutaten.
 * 
 * @author Tatsch
 * @version 29.01.2013
 */
class ZutatErsteller {
    public static final int MENGE = 1;
    public static final int NAHRUNGSMITTEL_NAME = 3;
    NahrungsmittelVerwaltung nahrungsmittels = NahrungsmittelVerwaltung.getInstanz();
/**
 * Diese Methode erstellt eine Zuzaz aus einer Zeile der rezepte-Datei.
 * @param rezeptZeile Eine Zeile der Rezepte-Datei.
 * @return Gibt die neu erstellte Zutat wieder.
 */
    protected Zutat erstelle(String rezeptZeile){
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
        zutat.setMenge(Parse.toDouble(zellen.get(MENGE)));
        Nahrungsmittel nahrungsmittel = findeNahrungsmittelAusRezept(zellen);
        zutat.setNahrungsmittel(nahrungsmittel);
        return zutat;
    }

    private Nahrungsmittel findeNahrungsmittelAusRezept(List<String> cells) {
        Nahrungsmittel nahrungsmittel = nahrungsmittels.findeDurchName(cells.get(NAHRUNGSMITTEL_NAME));
        return nahrungsmittel;
    }
    
}
