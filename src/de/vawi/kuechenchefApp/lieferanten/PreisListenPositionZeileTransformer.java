package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import java.util.*;

/**
 *
 * @author Struebe
 * @version 30.12.2012
 */
public class PreisListenPositionZeileTransformer {
    public static final int ZELLE_GEBINDEGROESSE = 0;
    public static final int ZELLE_EINHEIT = 1;
    public static final int ZELLE_NAHRUNGSMITTELNAME = 2;
    public static final int ZELLE_NAHRUNGSMITTELKATEGORIE = 3;
    public static final int ZELLE_PREIS = 4;
    public static final int ZELLE_VORRAT = 5;

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
    public PreisListenPosition transformiere(String preisListenPositionsZeile) {
        List<String> zellen = separiereZeile(preisListenPositionsZeile);
        Nahrungsmittel nahrungsmittel = erstelleNahrungsmittel(zellen);
        return erstellePreisListenPosition(zellen, nahrungsmittel);
        
    }

    protected List<String> separiereZeile(String preisListenPositionsZeile) {
        //Separieren einer Zeilte in Zellen
        CsvZeileSeparator csvSepp = new CsvZeileSeparator();
        List<String> preisListenPositionsZellen = csvSepp.separiere(preisListenPositionsZeile);
        return preisListenPositionsZellen;
    }

    protected NahrungsmittelKategorie ermittleNahrungsmitteKategorie(List<String> preisListenPositionsZellen) {
        String abkuerzung = preisListenPositionsZellen.get(ZELLE_NAHRUNGSMITTELKATEGORIE);
        NahrungsmittelKategorie kategorie = NahrungsmittelKategorie.nachAbkuerzung(abkuerzung);
        return kategorie;
    }

    protected Einheit ermittleEinheit(List<String> preisListenPositionsZellen) {
        String abkuerzung = preisListenPositionsZellen.get(ZELLE_EINHEIT);
        Einheit einheit = Einheit.nachAbkuerzung(abkuerzung);
        return einheit;
    }

    protected Nahrungsmittel erstelleNahrungsmittel(List<String> zellen) {
        NahrungsmittelKategorie kategorie = ermittleNahrungsmitteKategorie(zellen);
        Einheit einheit = ermittleEinheit(zellen);
        
        Nahrungsmittel nahrungsmittel = new Nahrungsmittel();
        nahrungsmittel.setEinheit(einheit);
        nahrungsmittel.setKategorie(kategorie);
        nahrungsmittel.setName(zellen.get(ZELLE_NAHRUNGSMITTELNAME));
        return nahrungsmittel;
    }

    protected PreisListenPosition erstellePreisListenPosition(List<String> zellen, Nahrungsmittel nahrungsmittel) {
        PreisListenPosition preisListenPosition = new PreisListenPosition();
        try{
            preisListenPosition.setGebindeGroesse(parseStringToDouble(zellen.get(ZELLE_GEBINDEGROESSE)));
            preisListenPosition.setNahrungsmittel(nahrungsmittel);
            preisListenPosition.setPreis(parseStringToDouble(zellen.get(ZELLE_PREIS)));
            preisListenPosition.setVorratsBestand(parseStringToInteger(zellen.get(ZELLE_VORRAT)));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        return preisListenPosition;
    }

    protected double parseStringToDouble(String value){
        if(!value.isEmpty())
            return Double.parseDouble(value.replace(',', '.'));
        else 
            return 0.0;
    }

    protected int parseStringToInteger(String value){
        return Integer.parseInt(value);
    }
}
