package de.vawi.kuechenchefApp.rezepte;

import java.util.*;
/**
 * Speisen aus der Rezeptedatei. Erweitert um die Bliebtheit aus der Hitliste.
 * 
 * @author Tatsch 
 * @version (a version number or a date)
 */
public class Speise
{
    private String name;
    private int beliebtheit;
    private List<Zutat> zutaten;
    
    /**
     * @return     Beliebtheit bei den Gästen
     */
    public int getBeliebtheit() {
        return beliebtheit;
    }

    /**
     * @param  beliebtheit  Beliebtheit bei den Gästen
     */
    public void setBeliebtheit(int beliebtheit) {
        this.beliebtheit = beliebtheit;
    }

    /**
     * @return Name des Speise
     */
    public String getName() {
        return name;
    }

    /**
     * @param  name     Name der Speise
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return     Alle Zutaten einer Speise 
     */
    public List<Zutat> getRezeptPositionen() {
        return this.zutaten;
    }

    /**
     * @param  zutaten  Alle Zutaten einer Speise
     */
    public void setRezeptPositionen(List<Zutat> zutaten) {
        this.zutaten = zutaten;
    }
}
