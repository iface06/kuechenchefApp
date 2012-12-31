package de.vawi.kuechenchefApp.nahrungsmittel;

/**
 * Diese Klasse hält Informationen die für die Beschreibung eines
 * Nahrungsmittels benötigt werden.
 *
 * @author Struebe
 * @version 30.12.2012
 */
public class Nahrungsmittel {

    private String name;
    private Einheit einheit;
    private NahrungsmittelKategorie kategorie;

    public Nahrungsmittel(String name, Einheit einheit, NahrungsmittelKategorie kategorie) {
        this.name = name;
        this.einheit = einheit;
        this.kategorie = kategorie;
    }

    /**
     * @return Einheit des Nahrungsmittels
     */
    public Einheit getEinheit() {
        return einheit;
    }

    /**
     * @param einheit Einheit des Nahrungsmittels
     */
    public void setEinheit(Einheit einheit) {
        this.einheit = einheit;
    }

    /**
     * @return Die Kategorie des Nahrungsmittels (Fleisch, Fisch, Vegetarisch)
     */
    public NahrungsmittelKategorie getKategorie() {
        return kategorie;
    }

    /**
     * @param kategorie Die Kategorie des Nahrungsmittels (Fleisch, Fisch,
     * Vegetarisch)
     */
    public void setKategorie(NahrungsmittelKategorie kategorie) {
        this.kategorie = kategorie;
    }

    /**
     * @return Name des Nahrungsmittels
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Name des Nahrungsmittels inklusive seiner Kategorie.
     */
    public String toString() {
        return this.name + " (" + this.kategorie + ")";
    }

    /**
     * @param name Name des Nahrungsmittels
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param obj Das zu vergleichende Objekt.
     * @return Gibt aus, ob das zu vergleichende Objekt ein Nahrungsmittel ist,
     * und einen Namen hat.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            Nahrungsmittel nahrungsmittel = (Nahrungsmittel) obj;
            return nahrungsmittel.name.equals(this.name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
