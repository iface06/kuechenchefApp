package de.vawi.kuechenchefApp;

/**
 * 
 * Diese Klasse beinhaltet die Daten für die Planungsperiode.
 * Die Anzahl der Wochen, die Tage pro Woche und die Anzahl an Gerichten kann so zentral gepflegt werden.
 * 
 * @author Lepping
 * @version 29.01.2013
 */
public class PlanungsPeriode {
    
    private int anzahlWochen = 3;
    private int anzahlTageProWoche = 5;
    private int anzahlGerichteProTag = 3;

    /**
     *
     * @return Gibt die Anzahl an Wochen zurück
     */
    public int getAnzahlWochen() {
        return anzahlWochen;
    }

    /**
     *
     * @return Gibt die Anzahl an Tagen pro Woche zurück
     */
    public int getAnzahlTageProWoche() {
        return anzahlTageProWoche;
    }

    /**
     *
     * @return Gibt die Anzahl an Gerichten pro Tag zurück
     */
    public int getAnzahlGerichteProTag() {
        return anzahlGerichteProTag;
    }
    
    /**
     *
     * @return Gibt die Anzahl an benötigter Speisen für Planungsperiode zurück
     */
    public int berechneAnzahlBenötigterSpeisen() {
       return anzahlGerichteProTag * anzahlTageProWoche * anzahlWochen;
    }
    
}
