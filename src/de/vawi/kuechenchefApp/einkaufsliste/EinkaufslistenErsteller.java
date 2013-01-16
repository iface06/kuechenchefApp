package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.speiseplan.Speiseplan;
import de.vawi.kuechenchefApp.lieferanten.LieferantenVerwaltung;
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
        Einkaufsliste liste = new Einkaufsliste();
        
        for (Speiseplan speiseplan : speiseplaene) {
            for (Tag tag : speiseplan) {
                fuegeInEinkaufsliste(tag.getBeliebtesteSpeise(), liste);
                fuegeInEinkaufsliste(tag.getZweitbeliebtesteSpeise(), liste);
                fuegeInEinkaufsliste(tag.getDrittbeliebtesteSpeise(), liste);
            }
            
        }
        return liste;
    }
    
    /**
     * Hinzufügen eines Speiseplans, der zur Erzeugung der Einkaufsliste berücksichtigt werden soll.
     * 
     * @param  plan     Speiseplan
     */
    public void add(Speiseplan plan){
        this.speiseplaene.add(plan);
    }

    private void fuegeInEinkaufsliste(Speise speise, Einkaufsliste liste) {
        List<Zutat> zutaten = speise.getZutaten();
        for (Zutat zutat : zutaten) {
            
            // double berechneteMenge = zutat.getMenge();
            // Menge muss noch mit Sicherheitsfaktor multipliziert werden und anschließend gerundet werden
            EinkaufslistenPosition position = liste.findePositionDurchNahrungsmittel(zutat.getNahrungsmittel());
            position.setMenge(position.getMenge() + zutat.getMenge());
        }
    }
    
    // 1. Methode zum Auffinden des Preiswertesten Angebots
    // 2. Methode zum Vergleichen von benötigter Menge zu angbotener Menge
    //    Wenn optimale Bestellung nicht möglich, vielleicht zu viel bestellen.
    //    Preisunterschied mit nächstem Angeot vergleichen, dass bessere Gebindegrößen anbietet
    // 3. Methode zum Update der Einkaufslistenposition
    //    a) Neues Item für bestimmten Einkauf
    //    b) Position ohne Lieferant runterzählen
    //    c) Erneuter Aufruf von 1.
    // 4. Opmimieren der Einkaufsliste zur Minimierung von Lieferkosten
    //    a) Überprüfe jede Einkaufslistenposition und überprüfe, ob durch Bestellung bei anderem Lieferanten durch Einsparung von Liefer-
    //       kosten Einsparungen möglich sind
    //       Falls ja: Ändere Lieferant, falls nein überprüfe nächste Position
    
    //Idee: Berechne durchschnittliche Lieferkosten pro Artikel generell aus. Überprüfe nur, diejenigen, welche über diesem Durschnitt liegen
    
}
