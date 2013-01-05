package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import de.vawi.kuechenchefApp.nahrungsmittel.NahrungsmittelKategorie;
import java.util.List;

class NahrungsmittelErsteller {

    public static final int ZELLE_EINHEIT = 1;
    public static final int ZELLE_NAHRUNGSMITTELNAME = 2;
    public static final int ZELLE_NAHRUNGSMITTELKATEGORIE = 3;

    public Nahrungsmittel erstelle(List<String> preisListenPosition) {
        Nahrungsmittel nahrungsmittel = erstelleNahrungsmittel(preisListenPosition);


        return nahrungsmittel;
    }

    private Nahrungsmittel erstelleNahrungsmittel(List<String> cells) {
        Nahrungsmittel nahrungsmittel = new Nahrungsmittel();
        nahrungsmittel.setName(cells.get(ZELLE_NAHRUNGSMITTELNAME));

        Einheit einheit = extrahiereEinheit(cells);
        nahrungsmittel.setEinheit(einheit);

        NahrungsmittelKategorie kategorie = extrahiereKategorie(cells);
        nahrungsmittel.setKategorie(kategorie);

        return nahrungsmittel;
    }

    private Einheit extrahiereEinheit(List<String> cells) {
        Einheit einheit = Einheit.nachAbkuerzung(cells.get(ZELLE_EINHEIT));
        return einheit;
    }

    private NahrungsmittelKategorie extrahiereKategorie(List<String> cells) {
        return NahrungsmittelKategorie.nachAbkuerzung(cells.get(ZELLE_NAHRUNGSMITTELKATEGORIE));
    }
}
