package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.nahrungsmittel.Einheit;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;


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

    @Override
    public boolean equals(Object obj) {
        Zutat zutat  = (Zutat) obj;
        return nahrungsmittel.equals(zutat.nahrungsmittel);
    }

    @Override
    public int hashCode() {
        return nahrungsmittel.hashCode();
    }
    
    public SpeisenUndNahrungsmittelKategorie getKategorie(){
        return nahrungsmittel.getKategorie();
    }
    
    public Einheit getEinheit(){
        return nahrungsmittel.getEinheit();
    }
    
    public String getName(){
        return nahrungsmittel.getName();
    }
}
