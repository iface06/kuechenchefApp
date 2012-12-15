package de.vawi.kuechenchefApp.kostenaufstellung;

import java.util.*;
/**
 * Diese Klasse enthält alle errechneten Kosten zu jedem Nahrungsmittel und die Gesamtkosten über alle
 * Nahrungsmittel einer Planungserperiode von 3 Wochen.
 * 
 * @author Struebe 
 * @version (a version number or a date)
 */
public class Kostenaufstellung
{
    
   private List<KostenPosition> kostenPositionen = new ArrayList<KostenPosition>();
   private double gesamtKosten = 0.0;
   

    /**
     * Gibt die Liste aller Kostenpositionen einer Planungsperiode zurück.
     * 
     * @return     Liste aller Kostenpositionen
     */
    public List<KostenPosition> getKostenPositionen()
    {
        return this.kostenPositionen;
    }
    
    /**
     * Fügt eine KostenPosition zu der Kostenaufstellung hinzu und addiert die Kosten zu den Gesamtkosten hinzu.
     * 
     * @param  position     Position in der Kostenaufstellung
     */
    public void hinzufügenKostenPosition(KostenPosition position){
        gesamtKosten += position.getKosten();
        kostenPositionen.add(position);
    }
    
    /**
     * Gibt die Gesamtkosten über alle Nahrungsmittel ein Planungsperiode zurück.
     * 
     * @return     Gesamtkosten
     */
    public double getGesamtKosten(){
        return this.gesamtKosten;
    }
}
