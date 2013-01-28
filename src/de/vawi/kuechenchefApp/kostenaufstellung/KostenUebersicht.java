package de.vawi.kuechenchefApp.kostenaufstellung;

import java.util.List;


public class KostenUebersicht {
    private double gesamtKosten;
    private double lieferKostenGesamt;
    private List<Kostenaufstellung> kostenaufstellungenProLieferant;

    public double getGesamtKosten() {
        return gesamtKosten;
    }

    public void setGesamtKosten(double gesamtKosten) {
        this.gesamtKosten = gesamtKosten;
    }

    public double getLieferKostenGesamt() {
        return lieferKostenGesamt;
    }

    public void setLieferKostenGesamt(double lieferKostenGesamt) {
        this.lieferKostenGesamt = lieferKostenGesamt;
    }

    public List<Kostenaufstellung> getKostenaufstellungenProLieferant() {
        return kostenaufstellungenProLieferant;
    }

    public void setKostenaufstellungenProLieferant(List<Kostenaufstellung> kostenaufstellungenProLieferant) {
        this.kostenaufstellungenProLieferant = kostenaufstellungenProLieferant;
    }
}
