package de.vawi.kuechenchefApp.kostenaufstellung;

import de.vawi.kuechenchefApp.einkaufsliste.EinkaufslistenPosition;
import de.vawi.kuechenchefApp.lieferanten.Lieferant;
import java.util.*;

/**
 * Diese Klasse enthält alle errechneten Kosten zu jedem Nahrungsmittel und die
 * Gesamtkosten über alle Nahrungsmittel einer Planungserperiode von 3 Wochen.
 *
 * @author Struebe
 * @version 20.01.2013
 */
public class Kostenaufstellung {

    Lieferant lieferant;
    List<EinkaufslistenPosition> einkaufslistenPositionen;

    public Lieferant getLieferant() {
        return this.lieferant;
    }

    public void setLieferant(Lieferant lieferant) {
        this.lieferant = lieferant;
    }

    public List<EinkaufslistenPosition> getEinkaufslistenPositionsListe() {
        return this.einkaufslistenPositionen;
    }

    public void setEinkaufslistenPositionsListe(List<EinkaufslistenPosition> einkaufslistenPositionen) {
        this.einkaufslistenPositionen = einkaufslistenPositionen;
    }

    public double berechneGesamtKostenProLieferant() {
        double gesamtKostenProLieferant = 0.0;
        gesamtKostenProLieferant = berechneEinkaufsKostenProLieferant() + berechneLieferKostenProLieferant();
        return gesamtKostenProLieferant;
    }

    public double berechneEinkaufsKostenProLieferant() {
        double einkaufsKosten = 0.0;

        for (EinkaufslistenPosition position : einkaufslistenPositionen) {
            einkaufsKosten += position.getPreis();
        }
        return einkaufsKosten;
    }

    public double berechneLieferKostenProLieferant() {
        double lieferKosten = 0.0;
        lieferKosten = lieferant.berechneLieferkosten(berechneEinkaufsKostenProLieferant());
        return lieferKosten;
    }
}