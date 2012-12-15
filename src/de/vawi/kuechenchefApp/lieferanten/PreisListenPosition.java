package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;


/**
 * Eine Position in der Preisliste des Lieferanten. 
 * 
 * @author Struebe
 * @version (a version number or a date)
 */
public class PreisListenPosition
{
    private Lieferant lieferant;
    private double gebindeGroesse;
    private Nahrungsmittel nahrungsmittel;
    private double preis;
    private int vorratsBestand;

    /**
     * @return     Lieferant der Position 
     */
    public Lieferant getLieferant() {
        return lieferant;
    }

    /**
     * @param  lieferant    Leiferant der Position
     */
    public void setLieferant(Lieferant lieferant) {
        this.lieferant = lieferant;
    }
    
    
    /**
     * @return     Gebindegrösse in der das Nahrungsmittel verkauft wird
     */
    public double getGebindeGroesse() {
        return gebindeGroesse;
    }
    
    /**
     * @param  gebindeGroesse   Gebindegrösse in der das Nahrungsmittel verkauft wird
     */
    public void setGebindeGroesse(double gebindeGroesse) {
        this.gebindeGroesse = gebindeGroesse;
    }

    /**
     * @return     Angebotenes Nahrungsmittel 
     */
    public Nahrungsmittel getNahrungsmittel() {
        return nahrungsmittel;
    }

    /**
     * @param  nahrungsmittel   Angebotenes Nahrungsmittel
     */
    public void setNahrungsmittel(Nahrungsmittel nahrungsmittel) {
        this.nahrungsmittel = nahrungsmittel;
    }

    /**
     * @return     Preis pro Gebindegrösse eines Nahrungsmittels 
     */
    public double getPreis() {
        return preis;
    }

    /** 
     * @param  preis    Preis pro Gebindegrösse eines Nahrungsmittels
     */
    public void setPreis(double preis) {
        this.preis = preis;
    }

    /**
     * @return     Vorratsbestand je Gebindegrösse 
     */
    public int getVorratsBestand() {
        return vorratsBestand;
    }

    /**
     * @param  vorratsBestand   Vorratsbestand je Gebindegrösse
     */
    public void setVorratsBestand(int vorratsBestand) {
        this.vorratsBestand = vorratsBestand;
    }
    
    
    
}