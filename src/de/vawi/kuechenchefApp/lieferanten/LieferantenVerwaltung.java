package de.vawi.kuechenchefApp.lieferanten;

import java.util.*;

/**
 * Diese Klasse verwaltet die importierten Lieferanten.
 *
 * @author Struebe
 * @version 30.12.2012
 */
public class LieferantenVerwaltung {

    private List<Lieferant> lieferanten;

    /**
     *
     * @param lieferanten Liste der Lieferanten
     */
    public LieferantenVerwaltung(List<Lieferant> lieferanten) {
        this.lieferanten = lieferanten;
    }

    /**
     * Diese Methode fügt zur Liste der Lieferanten weitere Lieferanten hinzu,
     * nachdem geprüft wurde, ob sie nocht nicht vorhanden sind.
     *
     * @param lieferant Liste der Lieferanten.
     */
    public void hinzufuegenLieferant(Lieferant lieferant) {
        if (!lieferanten.contains(lieferant)) {
            lieferanten.add(lieferant);
        }
    }
/**
 * 
 * @param i durchlaufender Zähler der Lieferanten-Liste
 * @return gibt den i-ten Lieferanten wider
 */
    public Lieferant getLieferant(int i) {
        return lieferanten.get(i);

    }
/**
 * 
 * @return Gibt die Anzahl der gelisteten Lieferanten wider.
 */
    public int getLieferantenAnzahl() {
        return lieferanten.size();
    }
}
