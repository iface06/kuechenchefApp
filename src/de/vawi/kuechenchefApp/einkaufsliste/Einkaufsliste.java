package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.lieferanten.Lieferant;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import java.util.*;

/**
 * Die Einkaufsliste für die Speisepläne der Planungsperiode
 *
 * @author Lepping
 * @version (a version number or a date)
 */
public class Einkaufsliste implements Iterable<EinkaufslistenPosition> {

    private List<EinkaufslistenPosition> positionen = new ArrayList<EinkaufslistenPosition>();

    /**
     * Fügt eine Positione zu der Einkaufsliste hinzu.
     *
     * @param position Position der Einkaufsliste
     */
    public void hinzufügenEinkaufslistenPosition(EinkaufslistenPosition position) {
        positionen.add(position);
    }

    /**
     * @return Positionen der Einkaufsliste
     */
    public List<EinkaufslistenPosition> getPositionen() {
        return positionen;
    }

    EinkaufslistenPosition findePositionDurchNahrungsmittel(Nahrungsmittel nahrungsmittel) {
        for (EinkaufslistenPosition position : positionen) {
            if (position.getName().equals(nahrungsmittel.getName())) {
                return position;
            }
        }

        EinkaufslistenPosition position = new EinkaufslistenPosition(nahrungsmittel);
        hinzufügenEinkaufslistenPosition(position);
        return position;

    }

    @Override
    public Iterator<EinkaufslistenPosition> iterator() {
        return positionen.iterator();
    }

    /**
     * Gibt eine Liste an vorhandenen Lieferanten zurück
     */
    public Set<Lieferant> holeLieferanten() {
        Set<Lieferant> lieferanten = new HashSet<>();
        for (EinkaufslistenPosition position : positionen) {
            lieferanten.add(position.getLieferant());
        }
        return lieferanten;
    }

    /**
     * Gibt eine Liste an Einkaufslistenpositionen eines bestimmten Lieferanten zurück
     */
    public List<EinkaufslistenPosition> holeEinkaufslistenpositionenVonLieferant(Lieferant lieferant) {
        List<EinkaufslistenPosition> positionenZuLieferant = new ArrayList<>();
        for (EinkaufslistenPosition position : this.positionen) {
            if (position.getLieferant().equals(lieferant)) {
                positionenZuLieferant.add(position);
            }
        }

        return positionenZuLieferant;
    }
}
