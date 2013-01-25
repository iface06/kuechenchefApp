package de.vawi.kuechenchefApp.lieferanten;

import java.util.ArrayList;

/**
 *  Der Bauer verrechnet die Lieferung nach der Enterfernung in km.
 *  Pro Kilometer 2,00€
 * 
 * @author Struebe
 * @version 30.12.2012
 */
public class Bauer extends Lieferant {
    public static double PAUSCHALE_PRO_KILOMETER = 2.0;
  
     
    @Override
    public double berechneLieferkosten(double einkaufsWert) {
        return (PAUSCHALE_PRO_KILOMETER * getLieferKostenFaktor());
    }
}
