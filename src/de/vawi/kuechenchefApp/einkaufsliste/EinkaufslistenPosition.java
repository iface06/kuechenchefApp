package de.vawi.kuechenchefApp.einkaufsliste;

import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import de.vawi.kuechenchefApp.lieferanten.Lieferant;


/**
 * Postion für eine Einkaufsliste
 * 
 * @author Lepping
 * @version (a version number or a date)
 */
public class EinkaufslistenPosition
{
    private double menge;
    private Nahrungsmittel nahrungsmittel;
    private Lieferant lieferant;
    private double preis;

    /**
     * @return     Ersteller-Klasse für den
     */
    public Lieferant getLieferant() {
        return lieferant;
    }

    /**
     * @param  lieferant    Ersteller-Klasse für den
     */
    public void setLieferant(Lieferant lieferant) {
        this.lieferant = lieferant;
    }

    /**
     * @return     Menge des eingekauften Nahrungsmittel
     */
    public double getMenge() {
        return menge;
    }

    /**
     * @param  menge   Menge des eingekauften Nahrungsmittel
     */
    public void setMenge(double menge) {
        this.menge = menge;
    }

    /**
     * @return     Bezeichnung des Nahrungsmittels
     */
    public String getNahrungsmittel() {
        return this.nahrungsmittel.getName();
    }
    
    /**
     * @return     Einheit der Menge zum Nahrungsmittel
     */
    public Einheit getEinheit(){
        return this.nahrungsmittel.getEinheit();
    }

    /**
     * @return     Preis für die Menge des Nahrungsmittels, exkl. Lieferkosten
     */
    public double getPreis() {
        return this.preis;
    }

    /**
     * @param  preis   Preis für die Menge des Nahrungsmittels, exkl. Lieferkosten
     */
    public void setPreis(double preis) {
        this.preis = preis;
    }
}
