package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.lieferanten.Lieferant;
import java.util.List;


/**
 *
 * @author Lepping
 * @version 29.01.2013
 */
class KostenRechner {

    public double berechneGesamtkosten(Einkaufsliste liste){
        return berechneGesamtkostenFuerNahrungsmittel(liste) + berechneGesamtkostenFuerLieferung(liste);
    }
    
    public double berechneGesamtkostenFuerNahrungsmittel(Einkaufsliste einkaufsliste) {
        return berechneGesamtkostenFuerNahrungsmittel(einkaufsliste.getPositionen());
    }

    public double berechneGesamtkostenFuerLieferung(Einkaufsliste einkaufsliste) {
        double lieferkosten = 0.0;
        for (Lieferant lieferant : einkaufsliste.holeLieferanten()) {
            List<EinkaufslistenPosition> positionen = einkaufsliste.holeEinkaufslistenpositionenVonLieferant(lieferant);
            double nahrungsmittelKosten = berechneGesamtkostenFuerNahrungsmittel(positionen);
            lieferkosten += lieferant.berechneLieferkosten(nahrungsmittelKosten);
        }
        return lieferkosten;
    }

    private double berechneGesamtkostenFuerNahrungsmittel(List<EinkaufslistenPosition> positionen) {
        double einkaufsKosten = 0.0;

        for (EinkaufslistenPosition position : positionen) {
            einkaufsKosten += position.getPreis();
        }
        
        return einkaufsKosten;
    }

}
