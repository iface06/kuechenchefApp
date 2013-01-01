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
     * 
     * Ebenfalls zu viele Verantwortungen in einer Methode. Static-Methode, muss nicht sein. 
     */
    public static void lesePreisListenPositionsZeile(String preisListenPositionsZeile, Lieferant lieferant) throws InvalidPropertiesFormatException {
        //Separieren einer Zeilte in Zellen
        CsvZeileSeparator csvSepp;
        csvSepp = new CsvZeileSeparator();
        List<String> preisListenPositionsZellen = csvSepp.separiere(preisListenPositionsZeile);
        //Identifizieren einer Kategorie.
        NahrungsmittelKategorie nahrungsmittelKategorie = NahrungsmittelKategorie.VEGETARISCH;
        //3 ist eine Magic Number: Was ist mit drei gemeint? Was steht da an vierter Stelle?
        if (preisListenPositionsZellen.get(3).equals("m")) {
            nahrungsmittelKategorie = NahrungsmittelKategorie.FLEISCH;
        } else if (preisListenPositionsZellen.get(3).equals("f")) {
            nahrungsmittelKategorie = NahrungsmittelKategorie.FISCH;
        } else if (preisListenPositionsZellen.get(3).equals("")) {
        } else {
            throw new InvalidPropertiesFormatException("Kann die Kategorie nicht lesen. Benötige f, m oder nichts.");
        }
        
        //Identifizieren der Einheit
        //ebenfalls Magic Number
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
        
        //Erstellen des Nahrungsmittels. Sind zwar "nur" drei Übergabeparameter für Nahrungsmittel, aber die sind eigentlich nicht notwendig.
        //Weil ein Nahrungsmittel das keinen Namen, Einheit und Kategorie hat erstmal nicht weh tut. Würde deshalb reichen, wenn die Attribute über
        //setter-Methoden gesetzt werden würden. Wie bei PreisListenPosition.
        Nahrungsmittel nahrungsmittel = new Nahrungsmittel(preisListenPositionsZellen.get(2), einheit, nahrungsmittelKategorie);

        //Erstelle PreislistenPosition
        PreisListenPosition preisListenPosition = new PreisListenPosition();
        preisListenPosition.setGebindeGroesse(Double.parseDouble(preisListenPositionsZellen.get(0).replace(',', '.')));
        preisListenPosition.setNahrungsmittel(nahrungsmittel);
        preisListenPosition.setPreis(Double.parseDouble(preisListenPositionsZellen.get(4).replace(',', '.')));
        preisListenPosition.setVorratsBestand(Integer.parseInt(preisListenPositionsZellen.get(5)));
        preisListenPosition.setLieferant(lieferant);
        
        //In Summe fünf Verantwortungen in einer Methode
        //Evtl. sollten die Verantwortungen für das Ermitteln der Einheit und Kategorie gar nicht hier in der Klasse sein, sondern direkt im jeweiligen Enum (habe Methoden dort schon implementiert)
        //Erstellen von Nahrungsmittel und PreisLIstenPosition kannst evtl. auch jeweils in eine eigene Klasse hinterlegt werden. 
    }
}
