package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.speisen.Speise;
import java.util.*;
/**
 * Ein Speiseplan für eine Planungsperiode.
 * 
 * @author Beer 
 * @version (a version number or a date)
 */
public class Speiseplan
{
    private List<Speise> speisen;
    private Kantine kantine;
    
    public Speiseplan(Kantine kantine, List<Speise> gerichte){
        this.kantine = kantine;
        this.speisen = gerichte;
    }

    /**
     * @return  Gibt die Gerichte zurück
     */
    public List<Speise> getSpeisen(){
        return this.speisen;
    }  
    
    /**
     * @return  Gibt die zugeordnete Kantine zurück
     */
    public Kantine getKantine(){
        return this.kantine;
    }
}
