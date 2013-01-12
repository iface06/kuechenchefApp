package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.PlanungsPeriode;
import de.vawi.kuechenchefApp.speisen.*;

import java.util.*;
/**
 * Diese Klasse ist für das Erstellen eines Speiseplans verantwortlich.
 * 
 * @author Beer 
 * @version (a version number or a date)
 */
public class SpeiseplanErsteller
{
    // Ich benötige insgesamt 3 * 15 = 45 Gerichte also 
    private SpeisenVerwaltung speisen = SpeisenVerwaltung.getInstanz();
    private List<Speise> beliebtesteSpeisen;
    
    public SpeiseplanErsteller() {
       // beliebtesteSpeisen = speisen.findeBeliebtesteSpeisenFuerPlanungsPeriode(new PlanungsPeriode());
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
        return new Speiseplan(kantine, new ArrayList<Tag>());
    }
    
    /**
     * Prueft ob die 45 beliebtesten Speisen schon mindestens 15 Fleischgerichte beinhaltet. 
     * @return 
     */
    private boolean beliebtesteSpeisenBeinhalten15Fleischgerichte() {
        
        for(Speise speise : beliebtesteSpeisen) {
            
        }
        return true;
    }
    
    /**
     * Prueft ob die 45 beliebtesten Speisen schon mindestens 3 Fischgerichte beinhaltet. 
     * @return 
     */
    private boolean beliebtesteSpeisenBeinhalten3Fischgerichte() {
        return true;
    }
    
    /**
     * Prueft ob die 45 beliebtesten Speisen schon mindestens 15 Vegetarischen Gerichte beinhaltet. 
     * @return 
     */
    private boolean beliebtesteSpeisenBeinhalten15VegGerichte() {
        return true;
    }


}
