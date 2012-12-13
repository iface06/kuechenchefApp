package de.vawi.kuechenchefApp;

import java.util.*;
/**
 * Enthält alle Funktionen die für die Verwaltung der Speisen benötigt.
 * 
 * @author Tatsch
 * @version (a version number or a date)
 */
public class SpeisenVerwaltung
{
    private List<Speise> speisen;
    
    public SpeisenVerwaltung(List<Speise> speisen){
        this.speisen = speisen;
    }

    /**
     * @return     gibt die Speisen zurück
     */
    public List<Speise> getSpeisen() {
        return speisen;
    }
}
