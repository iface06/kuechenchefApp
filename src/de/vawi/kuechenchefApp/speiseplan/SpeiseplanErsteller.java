package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.PlanungsPeriode;
import de.vawi.kuechenchefApp.nahrungsmittel.SpeisenUndNahrungsmittelKategorie;
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
    //TODO uebrige Speisen muessen ermittelt werden evtl in der SpeisenVerwaltung
    private List<Speise> uebrigenSpeisen;
    
    public SpeiseplanErsteller() {
        beliebtesteSpeisen = speisen.findeBeliebtesteSpeisenFuerPlanungsPeriode(new PlanungsPeriode());
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
    
    private void beliebtesteSpeisenPruefenUndAnpassen() {
        if(beliebtesteSpeisenBeinhaltenGenugFischgerichte()) {
            if(beliebtesteSpeisenBeinhaltenGenugVegGerichte()) {
                if(beliebtesteSpeisenBeinhaltenGenugFleischgerichte()) {
                    pruefeVerfuegbarkeit();
                    //else Zweig für "zu wenig Fleisch Gerichte in den beliebtesten Speisen"
                } else {
                    fuegeNeuesGerichtHinzu(SpeisenUndNahrungsmittelKategorie.VEGETARISCH);
                    beliebtesteSpeisenPruefenUndAnpassen();
                }
                
                //else Zweig für "zu wenig Vegetarische Gerichte in den beliebtesten Speisen"
            } else {
                fuegeNeuesGerichtHinzu(SpeisenUndNahrungsmittelKategorie.VEGETARISCH);
                beliebtesteSpeisenPruefenUndAnpassen();
            }
            //else Zweig für "zu wenig Fischgerichte in den beliebtesten Speisen"
        } else {
            fuegeNeuesGerichtHinzu(SpeisenUndNahrungsmittelKategorie.FISCH);
            beliebtesteSpeisenPruefenUndAnpassen();
        }
    }
    
    private void fuegeNeuesGerichtHinzu(SpeisenUndNahrungsmittelKategorie speiseKategorie) {
        Speise unbeliebtestesFleischGericht = ermittleUnbeliebtestesFleischGericht();
        Speise neuesGericht = ermittlebeliebtestesGericht(speiseKategorie);
        beliebtesteSpeisen.add(neuesGericht);
        beliebtesteSpeisen.remove(unbeliebtestesFleischGericht);
        uebrigenSpeisen.add(unbeliebtestesFleischGericht);
        uebrigenSpeisen.remove(neuesGericht);
    }
    
    private int mindestAnzahlBenoetigterFleischgerichte() {
        PlanungsPeriode periode = new PlanungsPeriode();
        return periode.getAnzahlWochen() * 5;
    }
    
    /**
     * Prueft ob die beliebtesten Speisen schon genug Fleischgerichte beinhaltet. 
     * @return 
     */
    private boolean beliebtesteSpeisenBeinhaltenGenugFleischgerichte() {
        int counter = 0;
        for(Speise speise : beliebtesteSpeisen) {
            if(speise.getKategorie() == SpeisenUndNahrungsmittelKategorie.FLEISCH) {
                counter++;
            }
        }
        if(counter >= mindestAnzahlBenoetigterFleischgerichte()) {
            return true;
        }
        return false;
    }
    
    /**
     * Prueft ob die beliebtesten Speisen schon genug Fischgerichte beinhaltet. 
     * @return 
     */
    private boolean beliebtesteSpeisenBeinhaltenGenugFischgerichte() {
        int counter = 0;
        for(Speise speise : beliebtesteSpeisen) {
            if(speise.getKategorie() == SpeisenUndNahrungsmittelKategorie.FISCH) {
                counter++;
            }
        }
        if(counter >= mindestAnzahlBenoetigterFischgerichte()) {
            return true;
        }
        return false;
    }
    
    /**
     * Prueft ob die beliebtesten Speisen schon genug Vegetarischen Gerichte beinhaltet. 
     * @return 
     */
    private boolean beliebtesteSpeisenBeinhaltenGenugVegGerichte() {
        int counter = 0;
        for(Speise speise : beliebtesteSpeisen) {
            if(speise.getKategorie() == SpeisenUndNahrungsmittelKategorie.VEGETARISCH) {
                counter++;
            }
        }
        if(counter >= mindestAnzahlBenoetigterVegGerichte()) {
            return true;
        }
        return false;
    }

    private int mindestAnzahlBenoetigterFischgerichte() {
        PlanungsPeriode periode = new PlanungsPeriode();
        return periode.getAnzahlWochen();
    }

    private int mindestAnzahlBenoetigterVegGerichte() {
        PlanungsPeriode periode = new PlanungsPeriode();
        return periode.getAnzahlWochen() * 5;
    }

    private Speise ermittleUnbeliebtestesFleischGericht() {
        Speise unbeliebtesteSpeise = new Speise();
        unbeliebtesteSpeise.setBeliebtheit(0);
        for(Speise speise : beliebtesteSpeisen) {
            if(speise.getKategorie() == SpeisenUndNahrungsmittelKategorie.FLEISCH) {
                if(unbeliebtesteSpeise.getBeliebtheit() > unbeliebtesteSpeise.getBeliebtheit()) {
                    unbeliebtesteSpeise = speise;
                }
            }
        }
        return unbeliebtesteSpeise;
    }

    private Speise ermittlebeliebtestesGericht(SpeisenUndNahrungsmittelKategorie speiseKategorie) {
        Speise beliebtestesGericht = new Speise();
        beliebtestesGericht.setBeliebtheit(new PlanungsPeriode().berechneAnzahlBenötigterSpeisen() + 1);
        for(Speise speise : uebrigenSpeisen) {
            if(speise.getKategorie() == speiseKategorie) {
                if(speise.getBeliebtheit() < beliebtestesGericht.getBeliebtheit()) {
                    beliebtestesGericht = speise;
                }
            }
        }
        return beliebtestesGericht;
    }

//    private void erstelleSpeiseplan(Kantine kantine) {
//        List<Speise> speisenFuerSpeiseplan = beliebtesteSpeisen;
//        List<Tag> tage = new ArrayList<Tag>();
//        for(int i = 1; i <= new PlanungsPeriode().getAnzahlWochen(); i++) {
//            verteileGerichtefuerEineWoche(tage, speisenFuerSpeiseplan);
//        }
//    }


    private void pruefeVerfuegbarkeit() {
        
    }


}
