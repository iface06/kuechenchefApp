package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import java.util.*;

/**
 * Diese Klasse verwaltet die importierten Lieferanten.
 *
 * @author Struebe
 * @version 12.01.2013
 */
public class LieferantenVerwaltung {

    private static LieferantenVerwaltung INSTANZ;
    private List<PreisListenPosition> preisListenPositionen = new ArrayList<>();

    LieferantenVerwaltung() {
    }

    /**
     * Diese Methode überprüft, ob ein Objekt der LieferantenVerwaltung
     * existiert. Wenn nicht wird eines erstellt.
     *
     * @return Gibt ein Objekt der Lieferantenverwaltung wider.
     */
    public static LieferantenVerwaltung getInstanz() {
        if (INSTANZ == null) {
            INSTANZ = new LieferantenVerwaltung();
        }
        return INSTANZ;
    }

    /**
     * Diese Methode stellt alle Preislistenpositionen eines Lieferanten
     * zusammen.
     *
     * @param positionen Eine Position auf der Liste.
     */
    void hinzufuegenPreisListenPosition(List<PreisListenPosition> positionen) {
        preisListenPositionen.addAll(positionen);
    }

    /**
     * Mit dieser Methode werden alle Preislitenpositionen aufgelistet, die ein
     * bestimmtes Nahrungsmittel enthalten. So kann herausgefunden werden, bei
     * welchen Lieferanten die jeweiligen Nahrungsmittel inkl. ihrer Attribute
     * zur Verfügung stehen.
     *
     * @param nahrungsmittel Ein Objekt vom Typ Nahrungsmittel.
     * @return Gibt eine Auflistung der Positionen mit dem gesuchten
     * Nahrungsmittel wider.
     */
    List<PreisListenPosition> findeDurchNahrungsmittel(Nahrungsmittel nahrungsmittel) {
        List<PreisListenPosition> positionen = new ArrayList<>();
        for (PreisListenPosition position : preisListenPositionen) {
            if (position.getNahrungsmittel().equals(nahrungsmittel)) {
                positionen.add(position);
            }
        }

        return positionen;
    }
}
