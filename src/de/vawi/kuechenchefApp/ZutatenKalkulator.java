package de.vawi.kuechenchefApp;

import de.vawi.kuechenchefApp.speisen.*;
import de.vawi.kuechenchefApp.einkaufsliste.EinkaufslistenPosition;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import de.vawi.kuechenchefApp.speiseplan.Kantine;
import de.vawi.kuechenchefApp.speiseplan.Speiseplan;
import de.vawi.kuechenchefApp.speiseplan.Tag;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Matthias
 */
public class ZutatenKalkulator {

    private Map<Nahrungsmittel, Double> mengen = new HashMap<>();

    /**
     * Auf Basis eines Speiseplans werden in dieser Methode für jede angebotene Speise die Anzahl benötigter Gerichte berechnet.
     * Diese Berechnung erfolgt auf Grundlage der Beliebtheit und der jeweiligen Anzahl der Mitarbeiter einer Kantine.
     * @param plan (erstellter Speiseplan)
     * @return 
     */
    public Map<Nahrungsmittel, Double> berechneGesamtMengen(Speiseplan plan) {
        for (Tag tag : plan) {
            berechneSpeise(tag.getBeliebtesteSpeise(), plan.getKantine().berechneAnzahlFuerBeliebtestesGericht());
            berechneSpeise(tag.getZweitbeliebtesteSpeise(), plan.getKantine().berechneAnzahlFuerZweitBeliebtestesGericht());
            berechneSpeise(tag.getDrittbeliebtesteSpeise(), plan.getKantine().berechneAnzahlFuerDrittBeliebtestesGericht());
        }
        return mengen;

    }

    private void berechneSpeise(Speise speise, int anzahlGerichte) {
        List<Zutat> zutaten = speise.getZutaten();
        for (Zutat zutat : zutaten) {
            Double gesamtMenge = getGesamtMengeFuer(zutat.getNahrungsmittel());
            gesamtMenge += zutat.getMenge() * anzahlGerichte;
            mengen.put(zutat.getNahrungsmittel(), Math.ceil(gesamtMenge));
        }
    }

    private Double getGesamtMengeFuer(Nahrungsmittel nahrungsmittel) {
        // double berechneteMenge = zutat.getMenge();
        // Menge muss noch mit Sicherheitsfaktor multipliziert werden und anschließend gerundet werden
        Double gesamtMenge = mengen.get(nahrungsmittel);
        if(gesamtMenge == null){
            gesamtMenge = 0.0;
        }
        return gesamtMenge;
    }
}