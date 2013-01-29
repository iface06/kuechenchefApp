package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
import java.util.*;
/**
 * Speisen aus der Rezeptedatei. Erweitert um die Bliebtheit aus der Hitliste.
 * 
 * @author Tatsch 
 * @version 28.01.2013
 */
public class Speise implements Iterable<Zutat>
{
    private String name;
    private int beliebtheit;
    private Set<Zutat> zutaten = new HashSet<>();
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
    public List<Zutat> getZutaten() {
        return new ArrayList<Zutat>(this.zutaten);
    }
/**
 * 
 * @param zutat Zutat einer Speise
 */
    public void addZutat(Zutat zutat){
        zutaten.add(zutat);
    }

    @Override
    public boolean equals(Object obj) {
        Speise speise = (Speise) obj;
        return speise.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
/**
 * Diese Methode geht die Zutaten einer Sp
 * @return 
 */
    public SpeisenUndNahrungsmittelKategorie getKategorie() {
        Iterator<Zutat> iterator = zutaten.iterator();
        return iterator.hasNext() ? iterator.next().getKategorie() : SpeisenUndNahrungsmittelKategorie.VEGETARISCH;
    }

    @Override
    public Iterator<Zutat> iterator() {
        return zutaten.iterator();
    }
}
