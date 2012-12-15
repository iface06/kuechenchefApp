package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.rezepte.SpeisenVerwaltung;
import de.vawi.kuechenchefApp.rezepte.Speise;
import java.util.*;
/**
 * Diese Klasse ist für das Erstellen eines Speiseplans verantwortlich.
 * 
 * @author Beer 
 * @version (a version number or a date)
 */
public class SpeiseplanErsteller
{
    private SpeisenVerwaltung speisen;
    
    public SpeiseplanErsteller(SpeisenVerwaltung speisen){
        this.speisen = speisen;
    }
    
    /**
     * Erstellt auf Basis der Rezpete einen Speiseplan für eine der Kantinen, nach den Regeln:
     * 
     * 1. 3 Gerichte pro Tag
     * 2. Mindestens 1 Gericht davon vegetarisch
     * 3. Mindestens 1 Gericht davon mit Fleisch
     * 4. Einmal pro Woche ein Gericht mit Fisch
     * 5. Für einen Zeitraum von 3 Wochen
     * 6. Jedes Gericht maximal einmal pro 3 Wochen
     * 
     * @params     kantine  Kantine des Unternehmens
     * @return     Speiseplan 
     */
    public Speiseplan erzeuge(Kantine kantine)
    {
        return new Speiseplan(kantine, new ArrayList<Speise>());
    }
}
