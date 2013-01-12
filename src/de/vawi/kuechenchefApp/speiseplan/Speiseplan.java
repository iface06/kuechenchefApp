package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.speisen.Speise;
import java.util.*;
/**
 * Ein Speiseplan für eine Planungsperiode.
 * 
 * @author Beer 
 * @version (a version number or a date)
 */
public class Speiseplan implements Iterable<Tag>
{
    private List<Tag> tageMitGerichten;
    private Kantine kantine;
    
    public Speiseplan(Kantine kantine, List<Tag> tageMitGerichten){
        this.kantine = kantine;
        this.tageMitGerichten = tageMitGerichten;
    }

    /**
     * @return  Gibt die die Liste mit den Tagen der Planungsperiode zurück.
     */
    public List<Tag> getTageMitGerichten(){
        return this.tageMitGerichten;
    }  
    
    /**
     * @return  Gibt die zugeordnete Kantine zurück
     */
    public Kantine getKantine(){
        return this.kantine;
    }

    @Override
    public Iterator<Tag> iterator() {
        return tageMitGerichten.iterator();
    }
}
