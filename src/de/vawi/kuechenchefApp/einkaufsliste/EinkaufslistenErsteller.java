package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.ZutatenKalkulator;
import de.vawi.kuechenchefApp.lieferanten.*;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import de.vawi.kuechenchefApp.speiseplan.Speiseplan;
import java.util.*;

/**
 * Erstellt eine Einkaufsliste auf Basis der hinzugefügten Speisepläne.
 *
 * @author Lepping
 * @version (a version number or a date)
 */
public class EinkaufslistenErsteller {

    private Einkaufsliste liste = new Einkaufsliste();
    private List<Speiseplan> speiseplaene = new ArrayList<>();
    private LieferantenVerwaltung lieferanten = LieferantenVerwaltung.getInstanz();
    private List<EinkaufslistenPosition> zusaetzlichePositionen = new ArrayList<>();

    /**
     * Erzeugt eine Einkaufsliste anhand der hinzugefügten Speisepläne, nach folgdenden Regeln:
     *
     * 1. entsprechende Bestellmenge bei ausreichend Lieferanten vorhanden.
     * 2. günstigster Preis pro Nahrungsmittel
     *
     * Die Einkaufsliste ist nach Lieferanten sortiert.
     *
     * @return Einkaufsliste für Speisepläne
     */
    public Einkaufsliste erzeuge() {
        erstelleEinkaufslistePosition();
        findeGuenstigsteLieferanten();
        for (EinkaufslistenPosition einkaufslistenPosition : zusaetzlichePositionen) {
            liste.hinzufügenEinkaufslistenPosition(einkaufslistenPosition);
        }
        return liste;
    }

    private void erstelleEinkaufslistePosition() {
        for (Speiseplan speiseplan : speiseplaene) {
            Map<Nahrungsmittel, Double> mengen = new ZutatenKalkulator().berechneGesamtMengen(speiseplan);
            fuegeMengenInEinkaufslisteEin(mengen);
        }
    }

    private void findeGuenstigsteLieferanten() {
        for (EinkaufslistenPosition position : liste) {
            List<PreisListenPosition> angebote = lieferanten.findeDurchNahrungsmittel(position.getNahrungsmittel());
            // benoetigte Menge wird zwischengepeichert und auf 0 gesetzt, sobald die benoetigte Menge bestellt ist
            double benoetigteMenge = position.getAnzahlGebinde();
            // vorhandene Menge wird benoetigt, um zu prüfen, ob restliche Lieferanten genug auf Lager haben, falls bei einem Lieferanten nicht das komplette Angebot bestellt wird
            double vorhandeneMenge = position.getNahrungsmittel().getVerfuegbareGesamtMenge();
            double bestellMenge;
            //Positionsnummer in der Angebotsliste, wird hochgezählt
            int positionsnummer = 0;
            // laufe bis benotigteMenge gleich 0 ist
            while (benoetigteMenge != 0.0) {
                // Berechne Anzahl an benoetigten Gebinden    
                double benoetigteAnzahlAnGebinden = benoetigteMenge / angebote.get(positionsnummer).getGebindeGroesse();
                // Wenn mehr angeboten als benötigt wird, muss die Nachkommastelle beachtet werden
                if (benoetigteAnzahlAnGebinden < angebote.get(positionsnummer).getVorratsBestand()) {
                    // Berechne Bestellmenge
//                    double differenz = benoetigteAnzahlAnGebinden - Math.floor(benoetigteAnzahlAnGebinden);
                    // wenn es keine Nachkommastelle gibt, dann bestell die benoetigte Menge
//                    if (differenz == 0) {
                        bestellMenge = Math.ceil(benoetigteAnzahlAnGebinden);
                        fuegeLieferantInEinkaufsliste(angebote.get(positionsnummer).getLieferant(), angebote.get(positionsnummer).getNahrungsmittel(), bestellMenge, angebote.get(positionsnummer).getPreis() * bestellMenge, position);
                        benoetigteMenge = 0.0;
//                    } else {
//                        findeLieferantenFuerDifferenz(differenz, positionsnummer, position.getNahrungsmittel(), vorhandeneMenge, benoetigteAnzahlAnGebinden, position);
//                    }

                } // wenn weniger angeboten, als benoetigt wird, einfach alles bestellen, was benoetigt wird
                else {
                    bestellMenge = angebote.get(positionsnummer).getVorratsBestand();
                    fuegeLieferantInEinkaufsliste(angebote.get(positionsnummer).getLieferant(), angebote.get(positionsnummer).getNahrungsmittel(), bestellMenge, angebote.get(positionsnummer).getPreis() * bestellMenge, position);
                    benoetigteMenge = benoetigteMenge - angebote.get(positionsnummer).getVorratsBestand() * angebote.get(positionsnummer).getGebindeGroesse();
                    vorhandeneMenge = vorhandeneMenge - angebote.get(positionsnummer).getVorratsBestand() * angebote.get(positionsnummer).getGebindeGroesse();
                    positionsnummer = positionsnummer + 1;
                }

            }
        }
    }

    private void findeLieferantenFuerDifferenz(double differenz, int positionsnummeralt, Nahrungsmittel nahrungsmittel, double vorhandeneMenge, double anzahlGebindeAlt, EinkaufslistenPosition position) {
        List<PreisListenPosition> angebote = lieferanten.findeDurchNahrungsmittel(nahrungsmittel);
        int positionsnummerneu = positionsnummeralt + 1;
        double benoetigteMenge = angebote.get(positionsnummeralt).getGebindeGroesse() * differenz;
        while (benoetigteMenge != 0) {
            // Wenn die Gebindegroesse des neuen Lieferanten groesser oder gleich ist als die des alten kann er nicht preiswerter sein
            if (angebote.get(positionsnummerneu).getGebindeGroesse() >= angebote.get(positionsnummeralt).getGebindeGroesse()) {
                // wenn mit ignorieren des neuen Lieferanten die benötigte Menge nicht mehr erreicht werden kann, muss beim alten Lieferanten aufgerundet werden
                if (benoetigteMenge > vorhandeneMenge - (angebote.get(positionsnummerneu).getGebindeGroesse() * angebote.get(positionsnummerneu).getVorratsBestand())) {
                    fuegeLieferantInEinkaufsliste(angebote.get(positionsnummeralt).getLieferant(), nahrungsmittel, Math.ceil(anzahlGebindeAlt), angebote.get(positionsnummeralt).getPreis() * Math.ceil(anzahlGebindeAlt), position);
                    benoetigteMenge = 0;
                } // wenn trotzdem noch genug vorhanden ist, dann wird der nächste Lieferant betrachtet
                else {
                    // vorhandene Menge wird reduziert um Vorratsmenge des neuen Lieferanten
                    vorhandeneMenge = vorhandeneMenge - (angebote.get(positionsnummerneu).getGebindeGroesse() * angebote.get(positionsnummerneu).getVorratsBestand());
                    positionsnummerneu = positionsnummerneu + 1;
                }

            } // Wenn die Gebindegroesse kleiner ist dann wird der neue Lieferant interessant
            else {
                // Die benoetigte Anzahl an Gebinden fuer neuen Lieferanten wird berechnet
                double anzahlBenoetigterGebindegroessen = benoetigteMenge / angebote.get(positionsnummerneu).getGebindeGroesse();
                // Wenn der neue Lieferant genug auf Lager hat, dann vergleiche den Preis
                if (angebote.get(positionsnummerneu).getVorratsBestand() > anzahlBenoetigterGebindegroessen) {
                    // Wenn Preis mit neuem Lieferanten teurer ist, kaufe mehr vom alten
                    if (angebote.get(positionsnummerneu).getPreis() * Math.ceil(anzahlBenoetigterGebindegroessen) > angebote.get(positionsnummeralt).getPreis()) {
                        // Bestell aufgerundete Menge von altem Lieferanten
                        fuegeLieferantInEinkaufsliste(angebote.get(positionsnummeralt).getLieferant(), nahrungsmittel, Math.ceil(anzahlGebindeAlt), angebote.get(positionsnummeralt).getPreis() * Math.ceil(anzahlGebindeAlt), position);
                        benoetigteMenge = 0;
                    } // Wenn der Preis kleiner ist, dann bestell die abgerundete Menge vom alten Lieferanten und die aufgerundete vom Neuen
                    else {
                        // Bestell abgerundete Menge von altem Lieferanten + aufgerundete Menge von neuem Lieferanten
                        fuegeLieferantInEinkaufsliste(angebote.get(positionsnummeralt).getLieferant(), nahrungsmittel, Math.floor(anzahlGebindeAlt), angebote.get(positionsnummeralt).getPreis() * Math.floor(anzahlGebindeAlt), position);
                        fuegeLieferantInEinkaufsliste(angebote.get(positionsnummerneu).getLieferant(), nahrungsmittel, Math.ceil(anzahlBenoetigterGebindegroessen), angebote.get(positionsnummerneu).getPreis() * Math.ceil(anzahlBenoetigterGebindegroessen), position);
                        benoetigteMenge = 0;
                    }
                } // Wenn der neue Lieferant nicht genug auf Lager hat dann vergleiche vorhande Menge mit benötigter Menge
                else {
                    // Wenn benötigte Menge zu hoch, dann runde Menge des alten Lieferanten auf
                    if (benoetigteMenge > vorhandeneMenge - (angebote.get(positionsnummerneu).getGebindeGroesse() * angebote.get(positionsnummerneu).getVorratsBestand())) {
                        // Bestell aufgerundete Menge von altem Lieferanten
                        fuegeLieferantInEinkaufsliste(angebote.get(positionsnummeralt).getLieferant(), nahrungsmittel, Math.ceil(anzahlGebindeAlt), angebote.get(positionsnummeralt).getPreis() * Math.ceil(anzahlGebindeAlt), position);
                        benoetigteMenge = 0;
                    } // benoetigte Menge < vorhandene
                    else {
                        vorhandeneMenge = vorhandeneMenge - (angebote.get(positionsnummerneu).getGebindeGroesse() * angebote.get(positionsnummerneu).getVorratsBestand());
                        positionsnummerneu = positionsnummerneu + 1;
                    }
                }
            }
        }
    }

    private void fuegeLieferantInEinkaufsliste(Lieferant lieferant, Nahrungsmittel nahrungsmittel, double bestellMenge, double preis, EinkaufslistenPosition position) {

        if (position.getLieferant() == null) {
            position.setLieferant(lieferant);
            position.setAnzahlGebinde(bestellMenge);
            position.setPreis(preis);
        } else {
            EinkaufslistenPosition neuePosition = new EinkaufslistenPosition(nahrungsmittel);
            neuePosition.setLieferant(lieferant);
            neuePosition.setAnzahlGebinde(bestellMenge);
            neuePosition.setPreis(preis);
            zusaetzlichePositionen.add(neuePosition);
        }

    }

    /**
     * Hinzufügen eines Speiseplans, der zur Erzeugung der Einkaufsliste berücksichtigt werden soll.
     *
     * @param plan Speiseplan
     */
    public void add(Speiseplan plan) {
        this.speiseplaene.add(plan);
    }

    private void fuegeMengenInEinkaufslisteEin(Map<Nahrungsmittel, Double> mengen) {
        for (Nahrungsmittel nahrungsmittel : mengen.keySet()) {
            // double berechneteMenge = zutat.getMenge();
            // Menge muss noch mit Sicherheitsfaktor multipliziert werden und anschließend gerundet werden
            EinkaufslistenPosition position = liste.findePositionDurchNahrungsmittel(nahrungsmittel);
            Double gesamtMenge = position.getAnzahlGebinde() + mengen.get(nahrungsmittel);
            position.setAnzahlGebinde(gesamtMenge);
        }
    }
}