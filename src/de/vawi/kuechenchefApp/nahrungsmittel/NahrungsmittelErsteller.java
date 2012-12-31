

package de.vawi.kuechenchefApp.nahrungsmittel;

import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import java.util.List;


public class NahrungsmittelErsteller {
    public static final int NAHRUNGSMITTELBEZEICHNUNG = 3;
    public static final int ABKUERZUNG_EINHEIT = 2;
    
    public Nahrungsmittel erstelle(String rezeptZeile){
        List<String> cells = separiereRezeptZeile(rezeptZeile);
        Nahrungsmittel nahrungsmittel = erstelleNahrungsmittel(cells);
        
        
        return nahrungsmittel;
    }

    private List<String> separiereRezeptZeile(String rezeptZeile) {
        CsvZeileSeparator separator = new CsvZeileSeparator();
        List<String> cells = separator.separiere(rezeptZeile);
        return cells;
    }

    private Nahrungsmittel erstelleNahrungsmittel(List<String> cells) {
        Nahrungsmittel nahrungsmittel = new Nahrungsmittel();
        nahrungsmittel.setName(cells.get(NAHRUNGSMITTELBEZEICHNUNG));
        
        Einheit einheit = extrahiereEinheit(cells);
        nahrungsmittel.setEinheit(einheit);
        
        
        
        return nahrungsmittel;
    }

    private Einheit extrahiereEinheit(List<String> cells) {
        Einheit einheit = Einheit.nachAbkuerzung(cells.get(ABKUERZUNG_EINHEIT));
        return einheit;
    }

}
