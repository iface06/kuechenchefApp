package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.dateien.CsvZeileSeparator;
import de.vawi.kuechenchefApp.dateien.Parse;
import de.vawi.kuechenchefApp.nahrungsmittel.*;
import java.util.*;

/**
 * Diese Klasse erstellt die Preislisten-Positionen eines Lieferanten aus den
 * Zeilen der Preisliste.
 *
 * @author Struebe
 * @version 30.12.2012
 */
class PreisListenPositionErsteller {

    public static final int ABSCHNITT_GEBINDEGROESSE = 0;
    public static final int ABSCHNITT_EINHEIT = 1;
    public static final int ABSCHNITT_NAHRUNGSMITTELNAME = 2;
    public static final int ABSCHNITT_NAHRUNGSMITTELKATEGORIE = 3;
    public static final int ABSCHNITT_PREIS = 4;
    public static final int ABSCHNITT_VORRAT = 5;
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
     * Preislisten-datei, die nicht die Lieferanten-Zeile (sprich: erste Zeile
     * der Liste) ist.
     * @param lieferant Lieferant, dessen Preisliste eingelsen wird.
     * @throws InvalidPropertiesFormatException Da für die
     * Nahrungsmittel-Kategorie und die Einheit Kürzel aus der Datei ins
     * Programm übersetzt werden, wird hier eine Exception geworfen, sobald die
     * Kürzel dem Programm nicht bekannt sind.
     *
     */
    public PreisListenPosition erstelle(String preisListenPositionsZeile) throws FehlerBeimErstellenEinerPreislistenPosition {
        List<String> zellen = separiereZeile(preisListenPositionsZeile);
        return erstellePreisListenPosition(zellen);

    }

    /**
     * Diese Methode teilt die Preislisten-Positions-Zeilen (sprich Zeile 2 bis
     * n der Preisliste) in ihre Abschnitte, die durch Kommata getrennt sind.
     *
     * @param preisListenPositionsZeile Eine Zeile der Preisliste, die nicht die
     * Lieferanten-Zeile ist (Zeilen 2 bis n der Liste).
     * @return Gibt die einzelnen Abschnitte der Zeile wider.
     */
    private List<String> separiereZeile(String preisListenPositionsZeile) {
        CsvZeileSeparator csvSepp = new CsvZeileSeparator();
        List<String> preisListenPositionsAbschnitte = csvSepp.separiere(preisListenPositionsZeile);
        return preisListenPositionsAbschnitte;
    }

    /**
     * Diese Methode erstellt ein Objekt der Klasse PreislistenPosition anhand
     * der Preislisten-Positions-Zeilen (sprich: Zeilen 2 bis n der Preisliste).
     *
     * @param abschnitte die durch Kommata getrennten Abschnitte einer
     * Preislisten-Positions-Zeile.
     * @return ein Objekt der Klasse PreislistenPosition
     * @throws
     * de.vawi.kuechenchefApp.lieferanten.PreisListenPositionErsteller.FehlerBeimErstellenEinerPreislistenPosition
     * Kann eine Zeile nicht gelesen werden (bspw. weil der Syntax nicht stimmt)
     * wird diese Exception geworfen. Das Programm wird nicht beendet, sonder
     * läuft weiter.
     */
    private PreisListenPosition erstellePreisListenPosition(List<String> abschnitte) throws FehlerBeimErstellenEinerPreislistenPosition {
        PreisListenPosition preisListenPosition = new PreisListenPosition();
        try {
            preisListenPosition.setGebindeGroesse(parseStringToDouble(abschnitte.get(ABSCHNITT_GEBINDEGROESSE)));
            preisListenPosition.setNahrungsmittel(findeNahrungsmittel(abschnitte));
            preisListenPosition.setPreis(parseStringToDouble(abschnitte.get(ABSCHNITT_PREIS)));
            preisListenPosition.setVorratsBestand(parseStringToInteger(abschnitte.get(ABSCHNITT_VORRAT)));
            preisListenPosition.setNahrungsmittel(addiereVerfuegbareMenge(abschnitte, parseStringToInteger(abschnitte.get(ABSCHNITT_VORRAT)), parseStringToInteger(abschnitte.get(ABSCHNITT_GEBINDEGROESSE))));
            preisListenPosition.setLieferant(lieferant);
        } catch (Exception e) {
            throw new FehlerBeimErstellenEinerPreislistenPosition(lieferant, abschnitte);
        }

        return preisListenPosition;
    }

    private double parseStringToDouble(String value) {
        return Parse.toDouble(value);
    }

    private int parseStringToInteger(String value) {
        return Parse.toInteger(value);
    }
/**
 * Diese Methode 
 * @param zellen
 * @return 
 */
    private Nahrungsmittel findeNahrungsmittel(List<String> zellen) {
        Nahrungsmittel nahrungsmittel = nahrungsmittelVerwaltung.findeDurchName(zellen.get(ABSCHNITT_NAHRUNGSMITTELNAME));
        if (nahrungsmittel == null) {
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

    private Nahrungsmittel addiereVerfuegbareMenge(List<String> zellen, int vorratsBestand, int gebindeGroesse) {

        Nahrungsmittel nahrungsmittel = nahrungsmittelVerwaltung.findeDurchName(zellen.get(ABSCHNITT_NAHRUNGSMITTELNAME));
        nahrungsmittel.setVerfuegbareGesamtMenge(nahrungsmittel.getVerfuegbareGesamtMenge() + (vorratsBestand * gebindeGroesse));
        return nahrungsmittel;
    }

    public static class FehlerBeimErstellenEinerPreislistenPosition extends RuntimeException {

        Lieferant lieferant;
        List<String> zelle;

        public FehlerBeimErstellenEinerPreislistenPosition(Lieferant lieferant, List<String> zelle) {
            this.lieferant = lieferant;
            this.zelle = zelle;
        }
    }
}
