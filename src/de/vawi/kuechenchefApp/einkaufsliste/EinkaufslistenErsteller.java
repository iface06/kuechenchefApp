package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.speiseplan.Speiseplan;
import de.vawi.kuechenchefApp.lieferanten.LieferantenVerwaltung;
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
    private LieferantenVerwaltung lieferanten;
    
    public EinkaufslistenErsteller(LieferantenVerwaltung lieferanten){
        this.lieferanten = lieferanten;
    }
    
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
        return new Einkaufsliste();
    }
    
    /**
     * Hinzufügen eines Speiseplans, der zur Erzeugung der Einkaufsliste berücksichtigt werden soll.
     * 
     * @param  plan     Speiseplan
     */
    public void add(Speiseplan plan){
        this.speiseplaene.add(plan);
    }
    
}
