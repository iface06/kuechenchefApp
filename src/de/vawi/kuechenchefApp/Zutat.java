package de.vawi.kuechenchefApp;


/**
 * Diese Klasse repräsentiert eine Zutat eines Gerichtes. Dementsprechend hält diese die Informationen
 * über Art und Menge des Nahrungsmittels.
 * @author Tatsch
 * @version (a version number or a date)
 */
public class Zutat
{
    private double menge;
    private Nahrungsmittel nahrungsmittel;
    
    /**
     * @return     Menge die für das Zubereiten benötigt wird. 
     */
    public double getMenge() {
        return menge;
    }

    /**
     * @param  menge    Menge die für das Zubereiten benötigt wird. 
     */
    public void setMenge(double menge) {
        this.menge = menge;
    }

    /**
     * @return     Das zugrundeliegende Nahrungsmittel 
     */
    public Nahrungsmittel getNahrungsmittel() {
        return nahrungsmittel;
    }

    /**
     * @param  nahrungsmittel   Das zugrundeliegende Nahrungsmittel 
     */
    public void setNahrungsmittel(Nahrungsmittel nahrungsmittel) {
        this.nahrungsmittel = nahrungsmittel;
    }
}
