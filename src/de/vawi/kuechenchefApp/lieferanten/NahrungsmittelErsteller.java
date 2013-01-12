package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
import java.util.List;
/**
 * Diese Klasse erstellt ein Nahrungsmittel durch 
 * @author Struebe (12.01.2013)
 */
class NahrungsmittelErsteller {

    public static final int ZELLE_EINHEIT = 1;
    public static final int ZELLE_NAHRUNGSMITTELNAME = 2;
    public static final int ZELLE_NAHRUNGSMITTELKATEGORIE = 3;
    public static final int ZELLE_VERFUEGBAREGESAMTMENGE = 6;
/**
 * TODO versteh ich nicht.
 * @param preisListenPosition
 * @return 
 */
    public Nahrungsmittel erstelle(List<String> preisListenPosition) {
        Nahrungsmittel nahrungsmittel = erstelleNahrungsmittel(preisListenPosition);


        return nahrungsmittel;
    }

    private Nahrungsmittel erstelleNahrungsmittel(List<String> cells) {
        Nahrungsmittel nahrungsmittel = new Nahrungsmittel();
        nahrungsmittel.setName(cells.get(ZELLE_NAHRUNGSMITTELNAME));

        Einheit einheit = extrahiereEinheit(cells);
        nahrungsmittel.setEinheit(einheit);

        SpeisenUndNahrungsmittelKategorie kategorie = extrahiereKategorie(cells);
        nahrungsmittel.setKategorie(kategorie);
        
        

        return nahrungsmittel;
    }

    private Einheit extrahiereEinheit(List<String> cells) {
        Einheit einheit = Einheit.nachAbkuerzung(cells.get(ZELLE_EINHEIT));
        return einheit;
    }

    private SpeisenUndNahrungsmittelKategorie extrahiereKategorie(List<String> cells) {
        return SpeisenUndNahrungsmittelKategorie.nachAbkuerzung(cells.get(ZELLE_NAHRUNGSMITTELKATEGORIE));
    }


}
