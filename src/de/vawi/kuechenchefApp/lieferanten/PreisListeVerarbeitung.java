package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import java.util.*;

/**
 *
 * @author Struebe
 * @version 30.12.2012
 */
public class PreisListeVerarbeitung {

    /**
     * Diese Methode zerlegt die eingelesenen Dateien in einzelne Zeilen, und
     * separiert aus den Zeilen die benötigten Werte, wie Name des
     * Nahrungsmittels, Einheit, etc.
     *
     * @param preisListenPositionsZeile Eine Zeile der einzulesenden
     * Preislisten-datei.
     * @param lieferant Lieferant, dessen Preisliste eingelsen wird.
     * @throws InvalidPropertiesFormatException Da für die
     * Nahrungsmittel-Kategorie und die Einheit Kürzel aus der Datei ins
     * Programm übersetzt werden, wird hier eine Exception geworfen, sobald die
     * Kürzel dem Programm nicht bekannt sind. TODO auch hier möchte er liever
     * switch case statt if TODO ich hab die Methode public gemacht, damit ich
     * keine Fehlermeldung mehr habe, aber sie war ursprünglich private??
     */
    public static void lesePreisListenPositionsZeile(String preisListenPositionsZeile, Lieferant lieferant) throws InvalidPropertiesFormatException {
        CsvZeileSeparator csvSepp;
        csvSepp = new CsvZeileSeparator();
        List<String> preisListenPositionsZellen = csvSepp.separiere(preisListenPositionsZeile);
        NahrungsmittelKategorie nahrungsmittelKategorie = NahrungsmittelKategorie.VEGETARISCH;
        if (preisListenPositionsZellen.get(3).equals("m")) {
            nahrungsmittelKategorie = NahrungsmittelKategorie.FLEISCH;
        } else if (preisListenPositionsZellen.get(3).equals("f")) {
            nahrungsmittelKategorie = NahrungsmittelKategorie.FISCH;
        } else if (preisListenPositionsZellen.get(3).equals("")) {
        } else {
            throw new InvalidPropertiesFormatException("Kann die Kategorie nicht lesen. Benötige f, m oder nichts.");
        }
        Einheit einheit = Einheit.STUECK;
        if (preisListenPositionsZellen.get(1).equals("g")) {
            einheit = Einheit.GRAMM;
        } else if (preisListenPositionsZellen.get(1).equals("l")) {
            einheit = Einheit.LITER;
        } else if (preisListenPositionsZellen.get(1).equals("")) {
            einheit = Einheit.STUECK;
        } else {
            throw new InvalidPropertiesFormatException("Kann die Einheit nicht lesen. Benötige g, l, oder nichts.");
        }
        Nahrungsmittel nahrungsmittel = new Nahrungsmittel(preisListenPositionsZellen.get(2), einheit, nahrungsmittelKategorie);

        PreisListenPosition preisListenPosition = new PreisListenPosition();
        preisListenPosition.setGebindeGroesse(Double.parseDouble(preisListenPositionsZellen.get(0).replace(',', '.')));
        preisListenPosition.setNahrungsmittel(nahrungsmittel);
        preisListenPosition.setPreis(Double.parseDouble(preisListenPositionsZellen.get(4).replace(',', '.')));
        preisListenPosition.setVorratsBestand(Integer.parseInt(preisListenPositionsZellen.get(5)));
        preisListenPosition.setLieferant(lieferant);
    }
}
