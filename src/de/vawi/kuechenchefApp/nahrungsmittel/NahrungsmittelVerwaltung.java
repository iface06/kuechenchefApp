package de.vawi.kuechenchefApp.nahrungsmittel;

import java.util.HashSet;
import java.util.Set;

/**
 * Diese Klasse verwaltet die Nahrungsmittel.
 *
 * @author Struebe
 * @version 27.01.2013
 */
public class NahrungsmittelVerwaltung {

    public static NahrungsmittelVerwaltung INSTANZ;
    private Set<Nahrungsmittel> nahrungsmittels = new HashSet<>();

    /**
     * Diese Methode überprüft anhand des Namens eines Nahrungsmittels, ob es
     * bereits existiert.
     *
     * @param name Name des Nahrungsmittels
     * @return Gibt das Nahrungsmittel wider, das schon existiert. Existiert
     * noch kein Nahrungsmittel mit diesem Namen, wird Null wider gegeben.
     */
    public Nahrungsmittel findeDurchName(String name) {
        for (Nahrungsmittel nahrungsmittel1 : nahrungsmittels) {
            if (nahrungsmittel1.getName().contains(name)) {
                return nahrungsmittel1;
            }
        }
        return null;
    }

    /**
     * Diese Methode fügt ein neues Nahrungsmittel zu einem Hashset hinzu.
     *
     * @param nahrungsmittel ein Nahrungsmittel.
     */
    public void fuegeHinzu(Nahrungsmittel nahrungsmittel) {
        nahrungsmittels.add(nahrungsmittel);
    }

    public static NahrungsmittelVerwaltung getInstanz() {
        if (INSTANZ == null) {
            INSTANZ = new NahrungsmittelVerwaltung();
        }
        return INSTANZ;

    }
}
