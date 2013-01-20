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
                // wenn die Nachkommastelle größer als 0,75 ist, dann runde auf und bestell zu viel
                else if (differenz > 0.75) {
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
            
            //berechneBenoetigteAnzahlAnGebinden (benoetigteMenge/Gebindegroesse = Anzahl an zu bestellenden Gebinden (abrunden oder aufrunden?)
            //Achtung! Falls abgerundet wird, muss Restmenge beachtet werden: 
            //Entscheidung nötig: Lieber bei diesem Lieferanten zuviel bestellen oder bei einem anderen zusätzlich?
            //vergleicheMitVorhandenerMenge und bestimmeBestellmengeFuerLieferanten
            //updateVonEinkaufslistenposition
            //wenn bestellMenge >= benoetigterMenge: nächste Position
            //wenn bestellMenge < benoetigterMenge: nächster Lieferant
            //berechneGesamtPreis();
            position.setPreis(3850);
        }
    }
    
    //private void berechneGesamtPreis() {
        
    //    int benoetigteMenge = 

    //}

    
    
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
    
    
    
    /*/1. Methode zum Auffinden des Preiswertesten Angebots
     2. Methode zum Vergleichen von benötigter Menge zu angbotener Menge
        Wenn optimale Bestellung nicht möglich, vielleicht zu viel bestellen.
        Preisunterschied mit nächstem Angeot vergleichen, dass bessere Gebindegrößen anbietet
     3. Methode zum Update der Einkaufslistenposition
        a) Neues Item für bestimmten Einkauf
        b) Position ohne Lieferant runterzählen
        c) Erneuter Aufruf von 1.
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
