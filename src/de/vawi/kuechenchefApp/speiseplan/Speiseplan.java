package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.speisen.Speise;
import java.util.*;
/**
 * Ein Speiseplan für eine Planungsperiode.
 * 
 * @author Beer 
 * @version 30.01.2013
 */
public class Speiseplan implements Iterable<Tag>
{
    private List<Tag> tageMitGerichten = new ArrayList<>();
    private Kantine kantine;
    
    public Speiseplan(){
        
    }
    
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

    public void setKantine(Kantine kantine) {
        this.kantine = kantine;
    }
    
    public void fuegeTagHinzu(Tag tag){
        this.tageMitGerichten.add(tag);
    }
    
    @Override
    public Iterator<Tag> iterator() {
        return tageMitGerichten.iterator();
    }

}
