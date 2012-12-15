package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.lieferanten.Lieferant;


/**
 * Der Großhändler verrechnet pro Artikel einen Kostensatz für die Lieferung.
 * 
 * Z. B.: 4 * 25.00 € * 1.2 = 120.00€ 
 * 4 Stk. a 25.00 € Kosten 120.00€ inklusive Lieferung
 * 
 * @author Struebe 
 * @version (a version number or a date)
 */
public class Grosshaendler extends Lieferant
{

    private double kostensatzProArtikel;

    /**
     * @return     Kostensatz pro Artikel für die Lieferung 
     */
     public double getKostensatzProArtikel() {
        return kostensatzProArtikel;
    }

    /**
     * @param     Kostensatz pro Artikel für die Lieferung 
     */
    public void setKostensatzProArtikel(double kostensatzProArtikel) {
        this.kostensatzProArtikel = kostensatzProArtikel;
    }
    
}
