package de.vawi.kuechenchefApp;


/**
 *  Der Bauer verrechnet die Lieferung nach der Enterfernung in km.
 *  
 *  Pro Kilometer 2,00€
 * git push https://github.com/iface06/kuechenchefApp.git +refs/heads/master:refs/heads/master
 * @author Struebe
 * @version (a version number or a date)
 */
public class Bauer extends Lieferant
{
    public static double PAUSCHALE_PRO_KILOMETER = 2.0;
    private double entfernungInKm;
    
     /**
     * @return     Entfernung in Kilometer (Km) zum Bauern 
     */
    public double getEntfernungInKm() {
        return entfernungInKm;
    }

     /**
     * @param  entfernungInKm   Entfernung in Kilometer (Km) zum Bauern 
     */
    public void setEntfernungInKm(double entfernungInKm) {
        this.entfernungInKm = entfernungInKm;
    }
}