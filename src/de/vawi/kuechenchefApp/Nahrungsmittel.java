package de.vawi.kuechenchefApp;


/**
 * Diese Klasse hält Informationen die für die Beschreibung eines Nahrungsmittels benötigt werden.
 * 
 * @author Struebe 
 * @version (a version number or a date)
 */
public class Nahrungsmittel
{

    private String name;
    private Einheit einheit;
    private NahrungsmittelKategorie kategorie;

    /**
     * @return     Einheit des Nahrungsmittels
     */
    public Einheit getEinheit() {
        return einheit;
    }

    /**
     * @param  einheit  Einheit des Nahrungsmittels
     */
    public void setEinheit(Einheit einheit) {
        this.einheit = einheit;
    }

    /**
     * @return     Die Kategorie des Nahrungsmittels (Fleisch, Fisch, Vegetarisch)
     */
    public NahrungsmittelKategorie getKategorie() {
        return kategorie;
    }

    /**
     * @param  kategorie    Die Kategorie des Nahrungsmittels (Fleisch, Fisch, Vegetarisch)
     */
    public void setKategorie(NahrungsmittelKategorie kategorie) {
        this.kategorie = kategorie;
    }

    /**
     * @return     Name des Nahrungsmittels
     */
    public String getName() {
        return name;
    }

    /**
     * @param  name     Name des Nahrungsmittels
     */
    public void setName(String name) {
        this.name = name;
    }

    
}