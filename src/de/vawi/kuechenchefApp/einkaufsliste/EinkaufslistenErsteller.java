package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.ZutatenKalkulator;
import de.vawi.kuechenchefApp.lieferanten.*;
import de.vawi.kuechenchefApp.speiseplan.Speiseplan;
import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import de.vawi.kuechenchefApp.speisen.Speise;
import de.vawi.kuechenchefApp.speisen.Zutat;
import de.vawi.kuechenchefApp.speiseplan.Tag;
import java.util.*;
/**
 * Erstellt eine Einkaufsliste auf Basis der hinzugefügten Speisepläne.
 * 
 * @author Lepping 
 * @version (a version number or a date)
 */
public class EinkaufslistenErsteller
{
    
    
    private Einkaufsliste liste = new Einkaufsliste();
    private List<Speiseplan> speiseplaene = new ArrayList<Speiseplan>();
    private LieferantenVerwaltung lieferanten = LieferantenVerwaltung.getInstanz();
    
    /**
     * Erzeugt eine Einkaufsliste anhand der hinzugefügten Speisepläne, nach folgdenden Regeln:
     * 
     * 1. entsprechende Bestellmenge bei ausreichend Lieferanten vorhanden.
     * 2. günstigster Preis pro Nahrungsmittel
     * 
     * Die Einkaufsliste ist nach Lieferanten sortiert.
     * 
     * @return     Einkaufsliste für Speisepläne 
     */
    public Einkaufsliste erzeuge()
    {
        erstelleEinkaufslistePosition();
        findeGuenstigsteLieferanten();
        ueberpruefeLieferkosten();
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
            double benoetigteMenge = position.getMenge();
            // vorhandene Menge wird benoetigt, um zu prüfen, ob restliche Lieferanten genug auf Lager haben, falls bei einem Lieferanten nicht das komplette Angebot bestellt wird
            double vorhandeneMenge = position.getNahrungsmittel().getVerfuegbareGesamtMenge();
            double bestellMenge = 0;
            //Positionsnummer in der Angebotsliste, wird hochgezählt
            int positionsnummer = 0;
            // laufe bis benotigteMenge gleich 0 ist
            while (benoetigteMenge != 0.0 ) {
            // Berechne Anzahl an benoetigten Gebinden    
            double benoetigteAnzahlAnGebinden = benoetigteMenge / angebote.get(positionsnummer).getGebindeGroesse();
            // Wenn mehr angeboten als benötigt wird, muss die Nachkommastelle beachtet werden
            if (benoetigteAnzahlAnGebinden < angebote.get(positionsnummer).getVorratsBestand()){
            // Berechne Bestellmenge
                double differenz = benoetigteAnzahlAnGebinden - Math.floor(benoetigteAnzahlAnGebinden);
                // wenn es keine Nachkommastelle gibt, dann bestell die benoetigte Menge
                if (differenz == 0){
                bestellMenge = benoetigteMenge;
                benoetigteMenge = 0.0;
                }
                
                else {
                findeLieferantenFuerDifferenz(differenz, positionsnummer, position.getNahrungsmittel(), vorhandeneMenge);
                }
                
                /*
                // wenn die Nachkommastelle größer als 0,75 ist, dann runde auf und bestell zu viel
                else if (differenz > 0.75) {
                // wenn diese Fkt umgesetzt ist, kann unten etwas gelöscht werden    
                //positionsnummerfuerrest = positionsnummer + 1;
                //   for (EinkaufslistenPosition position1 : liste)
                    
                    
                bestellMenge = Math.ceil(benoetigteAnzahlAnGebinden) * angebote.get(positionsnummer).getGebindeGroesse();
                benoetigteMenge = 0.0;
                }
                // wenn die Nachkommastelle kleiner als 0,75 ist, dann runde ab und bestell zu wenig (nicht optimal, da vielleicht zu teuer bestellt wird.
                // hier müsste noch eine Prüfung mehr eingebaut werden
                else {
                    // ist die vorhandene menge genug, obwohl ich einen lieferanten nicht komplett leer kaufe?
                    if (vorhandeneMenge - angebote.get(positionsnummer).getVorratsBestand() > benoetigteMenge - Math.floor(benoetigteAnzahlAnGebinden) * angebote.get(positionsnummer).getGebindeGroesse()){
                    bestellMenge = Math.floor(benoetigteAnzahlAnGebinden) * angebote.get(positionsnummer).getGebindeGroesse();
                    benoetigteMenge = benoetigteMenge - bestellMenge;
                    positionsnummer = positionsnummer + 1;
                    }
                    // vorhandene Menge nicht mehr genug, dann wird doch aufgerundet
                    else {
                    bestellMenge = Math.ceil(benoetigteAnzahlAnGebinden) * angebote.get(positionsnummer).getGebindeGroesse();
                    benoetigteMenge = 0.0;
                }
                
            }
            /*/    
                
            }
            // wenn weniger angeboten, als benoetigt wird, einfach alles bestellen, was benoetigt wird
            else {
            bestellMenge = angebote.get(positionsnummer).getVorratsBestand();
            benoetigteMenge = benoetigteMenge - angebote.get(positionsnummer).getVorratsBestand();
            vorhandeneMenge = vorhandeneMenge - angebote.get(positionsnummer).getVorratsBestand();
            positionsnummer = positionsnummer + 1;
            }
            
            if (position.getLieferant() == null){
            // Füge Lieferant, Menge, Preis hinzu
            // benoetigteMenge = benoetigteMenge-Menge
            }
            else {
            // Erstelle neue Einkaufsposition mit Nahrungsmittel, Lieferant, Menge und Preis
            // benoetigteMenge = benoetigeMenge-Menge
            }
            
            }
            position.setLieferant(angebote.get(0).getLieferant());
            position.setPreis(3850);
        }
    }
    
    private void findeLieferantenFuerDifferenz(double differenz, int positionsnummeralt, Nahrungsmittel nahrungsmittel, double vorhandeneMenge) {
        List<PreisListenPosition> angebote = lieferanten.findeDurchNahrungsmittel(nahrungsmittel);
        int positionsnummerneu = positionsnummeralt +1;
        double benoetigteMenge = angebote.get(positionsnummeralt).getGebindeGroesse() * differenz;
        while (benoetigteMenge != 0){
        if(angebote.get(positionsnummerneu).getGebindeGroesse() >= angebote.get(positionsnummeralt).getGebindeGroesse()) {
            if (benoetigteMenge > vorhandeneMenge - (angebote.get(positionsnummerneu).getGebindeGroesse() * angebote.get(positionsnummerneu).getVorratsBestand())) {
                // Bestell aufgerundete Menge von altem Lieferanten
                benoetigteMenge = 0;
            }
            else {
            positionsnummerneu = positionsnummerneu + 1;
            }

        }
        else {
        double anzahlBenoetigterGebindegroessen = benoetigteMenge / angebote.get(positionsnummerneu).getGebindeGroesse();
        if (angebote.get(positionsnummerneu).getVorratsBestand() > anzahlBenoetigterGebindegroessen) {
            if (angebote.get(positionsnummerneu).getPreis() * Math.ceil(anzahlBenoetigterGebindegroessen) > angebote.get(positionsnummeralt).getPreis()) {
            // Bestell aufgerundete Menge von altem Lieferanten
            benoetigteMenge = 0;
            }
            else {
            // Bestell abgerundete Menge von altem Lieferanten + aufgerundee Menge von neuem Lieferanten
            benoetigteMenge = 0;
            }
        }
        else {
            if (benoetigteMenge > vorhandeneMenge - (angebote.get(positionsnummerneu).getGebindeGroesse() * angebote.get(positionsnummerneu).getVorratsBestand())) {
            // Bestell aufgerundete Menge von altem Lieferanten
            benoetigteMenge = 0;
            }
            // benoetigte Menge < vorhandene
            else {
            positionsnummerneu = positionsnummerneu + 1;
            }
        }
        }
        }
        }
    
     private void ueberpruefeLieferkosten() {
   
    } 
  
    
    /**
     * Hinzufügen eines Speiseplans, der zur Erzeugung der Einkaufsliste berücksichtigt werden soll.
     * 
     * @param  plan     Speiseplan
     */
    public void add(Speiseplan plan){
        this.speiseplaene.add(plan);
    }

    private void fuegeMengenInEinkaufslisteEin(Map<Nahrungsmittel, Double> mengen) {
        for(Nahrungsmittel nahrungsmittel : mengen.keySet()){
            // double berechneteMenge = zutat.getMenge();
            // Menge muss noch mit Sicherheitsfaktor multipliziert werden und anschließend gerundet werden
            EinkaufslistenPosition position = liste.findePositionDurchNahrungsmittel(nahrungsmittel);
            Double gesamtMenge = position.getMenge() + mengen.get(nahrungsmittel);
            position.setMenge(gesamtMenge);
        }
    }
     
    
    /*/
     4. Opmimieren der Einkaufsliste zur Minimierung von Lieferkosten
        a) Überprüfe jede Einkaufslistenposition und überprüfe, ob durch Bestellung bei anderem Lieferanten durch Einsparung von Liefer-
          kosten Einsparungen möglich sind
           Falls ja: Ändere Lieferant, falls nein überprüfe nächste Position
    
    Idee: Berechne durchschnittliche Lieferkosten pro Artikel generell aus. Überprüfe nur, diejenigen, welche über diesem Durschnitt liegen
    * 
    * Stichpunkte:
    * - Vielleicht brauche ich eine Methode, die mir für eine bestimmte Zutat einen Lieferanten nennt, dessen Gebindegröße kleiner ist als die mir vorliegende
    *   Anschließend kann der Preisunterschied verglichen werden
    */

}
