package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import de.vawi.kuechenchefApp.dateien.Parse;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import java.util.*;

/**
 *
 * @author Struebe
 * @version 30.12.2012
 */
class PreisListenPositionErsteller {

    public static final int ZELLE_GEBINDEGROESSE = 0;
    public static final int ZELLE_EINHEIT = 1;
    public static final int ZELLE_NAHRUNGSMITTELNAME = 2;
    public static final int ZELLE_NAHRUNGSMITTELKATEGORIE = 3;
    public static final int ZELLE_PREIS = 4;
    public static final int ZELLE_VORRAT = 5;
    private final Lieferant lieferant;
    private NahrungsmittelVerwaltung nahrungsmittelVerwaltung = NahrungsmittelVerwaltung.getInstanz();

    PreisListenPositionErsteller(Lieferant lieferant) {
        this.lieferant = lieferant;
    }

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
     */
    public PreisListenPosition erstelle(String preisListenPositionsZeile) throws FehlerBeimErstellenEinerPreislistenPosition{
        List<String> zellen = separiereZeile(preisListenPositionsZeile);
        return erstellePreisListenPosition(zellen);

    }

    private List<String> separiereZeile(String preisListenPositionsZeile) {
        CsvZeileSeparator csvSepp = new CsvZeileSeparator();
        List<String> preisListenPositionsZellen = csvSepp.separiere(preisListenPositionsZeile);
        return preisListenPositionsZellen;
    }

    private PreisListenPosition erstellePreisListenPosition(List<String> zellen) throws FehlerBeimErstellenEinerPreislistenPosition {
        PreisListenPosition preisListenPosition = new PreisListenPosition();
        try {
            preisListenPosition.setGebindeGroesse(parseStringToDouble(zellen.get(ZELLE_GEBINDEGROESSE)));
            preisListenPosition.setNahrungsmittel(findeNahrungsmittel(zellen));
            preisListenPosition.setPreis(parseStringToDouble(zellen.get(ZELLE_PREIS)));
            preisListenPosition.setVorratsBestand(parseStringToInteger(zellen.get(ZELLE_VORRAT)));
            preisListenPosition.setLieferant(lieferant);
        } catch (Exception e) {
            throw new FehlerBeimErstellenEinerPreislistenPosition(lieferant, zellen);
        }

        return preisListenPosition;
    }

    private double parseStringToDouble(String value) {
        return Parse.toDouble(value);
    }

    private int parseStringToInteger(String value) {
        return Parse.toInteger(value);
    }

    private Nahrungsmittel findeNahrungsmittel(List<String> zellen) {
        Nahrungsmittel nahrungsmittel = nahrungsmittelVerwaltung.findeDurchName(zellen.get(ZELLE_NAHRUNGSMITTELNAME));
        if(nahrungsmittel == null){
            nahrungsmittel = erstelleNeuesNahrungsmittel(zellen);
            nahrungsmittelVerwaltung.fuegeHinzu(nahrungsmittel);
        }
        
        return nahrungsmittel;
    }

    private Nahrungsmittel erstelleNeuesNahrungsmittel(List<String> zellen) {
        NahrungsmittelErsteller ersteller = new NahrungsmittelErsteller();
        Nahrungsmittel mittel = ersteller.erstelle(zellen);
        return mittel;
    }
    
    public static class FehlerBeimErstellenEinerPreislistenPosition extends RuntimeException{
        Lieferant lieferant;
        List<String> zelle;

        public FehlerBeimErstellenEinerPreislistenPosition(Lieferant lieferant, List<String> zelle) {
            this.lieferant = lieferant;
            this.zelle = zelle;
        }
    }
}
