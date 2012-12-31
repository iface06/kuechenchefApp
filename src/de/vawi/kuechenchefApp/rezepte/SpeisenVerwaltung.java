package de.vawi.kuechenchefApp.rezepte;

import java.util.*;
/**
 * Enthält alle Funktionen die für die Verwaltung der Speisen benötigt.
 * 
 * @author Tatsch
 * @version (a version number or a date)
 */
public class SpeisenVerwaltung
{
    private Set<Speise> speisen = new HashSet<>();
    
    public void addSpeise(Speise speise){
        speisen.add(speise);
    }
    
    public int size(){
        return speisen.size();
    }

    public Speise findeSpeise(String speisenName) throws SpeiseNichtGefunden{
        for (Speise speise : speisen) {
            if(speise.getName().equals(speisenName)){
                return speise;
            } 
        }
        throw new SpeiseNichtGefunden();
    }
    
    class SpeiseNichtGefunden extends RuntimeException{
        
    }
}
