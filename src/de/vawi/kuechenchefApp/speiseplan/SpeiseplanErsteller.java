package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.PlanungsPeriode;
import de.vawi.kuechenchefApp.ZutatenKalkulator;
import de.vawi.kuechenchefApp.nahrungsmittel.Nahrungsmittel;
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
    private List<Speise> speisenFuerEssen;
    private List<Speise> uebrigeSpeiesenEssen;
    private List<Speise> speisenFuerMuehlheim;
    private List<Speise> uebrigeSpeisenMuehlheim;
    
    Speiseplan speiseplanEssen;
    Speiseplan speiseplanMuehlheim;
    
    public SpeiseplanErsteller() {
        beliebtesteSpeisen = speisen.findeBeliebtesteSpeisenFuerPlanungsPeriode(new PlanungsPeriode());
       System.out.println(speisen.size());
        System.out.println(beliebtesteSpeisen.size());
        uebrigenSpeisen = speisen.findeUnbeliebtesteSpeisen(new PlanungsPeriode());
        System.out.println(uebrigenSpeisen.size());
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
        beliebtesteSpeisenPruefenUndAnpassen();
        erstelleSpeiseplaene();
        pruefeVerfuegbarkeit();
        if(kantine.equals(Kantine.ESSEN)) {
            return speiseplanEssen;
        } else {
            return speiseplanMuehlheim;
        }
//        return new Speiseplan(kantine, new ArrayList<Tag>());
    }
    
    protected void beliebtesteSpeisenPruefenUndAnpassen() {
        if(beliebtesteSpeisenBeinhaltenGenugFischgerichte()) {
            if(beliebtesteSpeisenBeinhaltenGenugVegGerichte()) {
                if(beliebtesteSpeisenBeinhaltenGenugFleischgerichte()) {
                    System.out.println("Ausgewählte Speisen erfüllen harte Kriterien!");
                } else {
                    System.out.println("Zu wenig Fleisch Gerichte an Bord!");
                    fuegeNeuesGerichtHinzu(SpeisenUndNahrungsmittelKategorie.FLEISCH);
                    beliebtesteSpeisenPruefenUndAnpassen();
                }
                
                //else Zweig für "zu wenig Vegetarische Gerichte in den beliebtesten Speisen"
            } else {
                System.out.println("Zu wenig Vegetarische Gerichte an Bord!");
                fuegeNeuesGerichtHinzu(SpeisenUndNahrungsmittelKategorie.VEGETARISCH);
                beliebtesteSpeisenPruefenUndAnpassen();
            }
            //else Zweig für "zu wenig Fischgerichte in den beliebtesten Speisen"
        } else {
            System.out.println("Zu wenig Fischgerichte Gerichte an Bord!");
            fuegeNeuesGerichtHinzu(SpeisenUndNahrungsmittelKategorie.FISCH);
            beliebtesteSpeisenPruefenUndAnpassen();
        }
    }
    
    private void fuegeNeuesGerichtHinzu(SpeisenUndNahrungsmittelKategorie speiseKategorie) {
        if(speiseKategorie.equals(SpeisenUndNahrungsmittelKategorie.FLEISCH)) {
            Speise unbeliebtestesVegGericht = ermittleUnbeliebtestesVegGericht();
            System.out.println("Unbeliebtestes Veggericht: " + unbeliebtestesVegGericht.getName());
            Speise neuesFleischGericht = ermittlebeliebtestesGericht(speiseKategorie);
            System.out.println("Neues Gericht: " + neuesFleischGericht.getName());            
            beliebtesteSpeisen.add(neuesFleischGericht);
            uebrigenSpeisen.add(unbeliebtestesVegGericht);
            beliebtesteSpeisen.remove(unbeliebtestesVegGericht);
            uebrigenSpeisen.remove(neuesFleischGericht);

        } else {
            Speise unbeliebtestesFleischGericht = ermittleUnbeliebtestesFleischGericht();
            System.out.println("Unbeliebtestes Fleischgericht: " + unbeliebtestesFleischGericht.getName());
            Speise neuesGericht = ermittlebeliebtestesGericht(speiseKategorie);
            System.out.println("Neues Gericht: " + neuesGericht.getName());
            beliebtesteSpeisen.add(neuesGericht);
            beliebtesteSpeisen.remove(unbeliebtestesFleischGericht);
            uebrigenSpeisen.add(unbeliebtestesFleischGericht);
            uebrigenSpeisen.remove(neuesGericht);
        }

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
            if(speise.getKategorie().equals(SpeisenUndNahrungsmittelKategorie.FLEISCH)) {
                counter++;
            }
        }
        System.out.println("Anzahl von Fleischgerichten: " + counter);
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
            if(speise.getKategorie().equals(SpeisenUndNahrungsmittelKategorie.FISCH)) {
                counter++;
            }
        }
        System.out.println("Anzahl von Fischgerichten: " + counter);
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
            if(speise.getKategorie().equals(SpeisenUndNahrungsmittelKategorie.VEGETARISCH)) {
                counter++;
            }
        }
        System.out.println("Anzahl von Veggerichten: " + counter);
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
            if(speise.getKategorie().equals(SpeisenUndNahrungsmittelKategorie.FLEISCH)) {
                if(speise.getBeliebtheit() > unbeliebtesteSpeise.getBeliebtheit()) {
                    unbeliebtesteSpeise = speise;
                }
            }
        }
        return unbeliebtesteSpeise;
    }
    
       private Speise ermittleUnbeliebtestesVegGericht() {
        Speise unbeliebtesteSpeise = new Speise();
        unbeliebtesteSpeise.setBeliebtheit(0);
        for(Speise speise : beliebtesteSpeisen) {
            if(speise.getKategorie().equals(SpeisenUndNahrungsmittelKategorie.VEGETARISCH)) {
                if(speise.getBeliebtheit() > unbeliebtesteSpeise.getBeliebtheit()) {
                    unbeliebtesteSpeise = speise;
                }
            }
        }
        return unbeliebtesteSpeise;
    }

    private Speise ermittlebeliebtestesGericht(SpeisenUndNahrungsmittelKategorie speiseKategorie) {
        Speise beliebtestesGericht = new Speise();
        beliebtestesGericht.setBeliebtheit(SpeisenVerwaltung.getInstanz().size());
        for(Speise speise : uebrigenSpeisen) {
            if(speise.getKategorie().equals(speiseKategorie)) {
                if(speise.getBeliebtheit() < beliebtestesGericht.getBeliebtheit()) {
                    beliebtestesGericht = speise;
                }
            }
        }
        return beliebtestesGericht;
    }

    private void pruefeVerfuegbarkeit() {
        ZutatenKalkulator kalkulator = new ZutatenKalkulator();
        Map<Nahrungsmittel, Double> mengenEssen = kalkulator.berechneGesamtMengen(speiseplanEssen);
        Map<Nahrungsmittel, Double> mengenMuehlheim = kalkulator.berechneGesamtMengen(speiseplanMuehlheim);
        
        List<Nahrungsmittel> problematischeNahrungsmittelEssen = new ArrayList<Nahrungsmittel>();
        List<Nahrungsmittel> problematischeNahrungsmittelMuehl = new ArrayList<Nahrungsmittel>();
        
        for(Nahrungsmittel nahrungsmittel : mengenEssen.keySet()) {
             
            Double gesamtMenge = mengenEssen.get(nahrungsmittel) + (mengenMuehlheim.containsKey(nahrungsmittel) ? mengenMuehlheim.get(nahrungsmittel) : 0.0);
            if(gesamtMenge > nahrungsmittel.getVerfuegbareGesamtMenge()) {
                if(mengenEssen.get(nahrungsmittel) > nahrungsmittel.getVerfuegbareGesamtMenge()) {
                    problematischeNahrungsmittelEssen.add(nahrungsmittel);
                }
                if((mengenMuehlheim.containsKey(nahrungsmittel) ? mengenMuehlheim.get(nahrungsmittel) : 0.0) > nahrungsmittel.getVerfuegbareGesamtMenge()) {
                    problematischeNahrungsmittelMuehl.add(nahrungsmittel);
                }
            }
        }
        
        if(problematischeNahrungsmittelEssen.size() != 0 || problematischeNahrungsmittelMuehl.size() != 0) {

            System.out.println("Verfügbarkeit reicht nicht aus!");
//            passeSpeisenDerVerfügbarkeitAn();            
        }

    }

    private void erstelleSpeiseplaene() {
        speisenFuerEssen = beliebtesteSpeisen;
        uebrigeSpeiesenEssen = uebrigenSpeisen;
        
        speisenFuerMuehlheim = beliebtesteSpeisen;
        uebrigeSpeisenMuehlheim = uebrigenSpeisen;
        
        speiseplanEssen = erstelleSpeiseplan(Kantine.ESSEN);
        speiseplanMuehlheim = erstelleSpeiseplan(Kantine.MUELHEIM_AN_DER_RUHR);
    }

    private Speiseplan erstelleSpeiseplan(Kantine kantine) {
        List<Speise> speisenFuerPlan;
        if(kantine.equals(Kantine.ESSEN)) {
            speisenFuerPlan = speisenFuerEssen;
        } else {
            speisenFuerPlan = speisenFuerMuehlheim;
        }
        List<Tag> tage = new ArrayList<Tag>();
        PlanungsPeriode periode = new PlanungsPeriode();
        //zuerst müssen die Fischtage erstellt werden!!
        for (int i = 1; i <= periode.getAnzahlWochen(); i++) {
            tage.add(erstelleFischTag(speisenFuerPlan, i * 5));
        }
        
        //Dann die "normalen" Tage
        for(int i = 1; i <= periode.getAnzahlWochen() * periode.getAnzahlTageProWoche(); i++) {
            if ( (i % 5) != 0) {
                tage.add(erstelleNormalenTag(speisenFuerPlan, i));
            }
        }
        return new Speiseplan(kantine, tage);
    }

    private Tag erstelleNormalenTag(List<Speise> speisenFuerPlan, int nummer) {
         Tag tag = new Tag(nummer);
        List<Speise> tagesSpeisen = new ArrayList<Speise>();
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        
        return verteileSpeisenAufTag(tag, tagesSpeisen);
    }

    private Tag erstelleFischTag(List<Speise> speisenFuerPlan, int nummer) {
        Tag tag = new Tag(nummer);
        List<Speise> tagesSpeisen = new ArrayList<Speise>();
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, SpeisenUndNahrungsmittelKategorie.FISCH));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, SpeisenUndNahrungsmittelKategorie.VEGETARISCH));
        tagesSpeisen.add(nimmEinGerichtAusListe(speisenFuerPlan, SpeisenUndNahrungsmittelKategorie.FLEISCH));
        
        return verteileSpeisenAufTag(tag, tagesSpeisen);
    }
    
    private Tag verteileSpeisenAufTag(Tag tag, List<Speise> tagesSpeisen) {
        
        tag.setBeliebtesteSpeise(findeBeliebtesteSpeise(tagesSpeisen));
        tagesSpeisen.remove(findeBeliebtesteSpeise(tagesSpeisen));
        tag.setZweitbeliebtesteSpeise(findeBeliebtesteSpeise(tagesSpeisen));
        tagesSpeisen.remove(findeBeliebtesteSpeise(tagesSpeisen));
        tag.setDrittbeliebtesteSpeise(findeBeliebtesteSpeise(tagesSpeisen));
       
        return tag;
    }
    
    private Speise findeBeliebtesteSpeise(List<Speise> speisen) {
        
        Speise beliebtesteSpeise = new Speise();
        beliebtesteSpeise.setBeliebtheit(new PlanungsPeriode().berechneAnzahlBenötigterSpeisen() + 1);
        for(Speise speise : speisen) {
            if(speise.getBeliebtheit() < beliebtesteSpeise.getBeliebtheit()) {
                beliebtesteSpeise = speise;
            }
        }
        return beliebtesteSpeise;
    }
    
    private Speise nimmEinGerichtAusListe(List<Speise> speisen, SpeisenUndNahrungsmittelKategorie kategorie) {
        
        for(Speise speise : speisen) {
            if(speise.getKategorie().equals(kategorie)) {
                speisen.remove(speise);
                return speise;
            }
        }
       if(kategorie.equals(SpeisenUndNahrungsmittelKategorie.FLEISCH)) {
           return nimmEinGerichtAusListe(speisen, SpeisenUndNahrungsmittelKategorie.FISCH);
       } else {
           return nimmEinGerichtAusListe(speisen, SpeisenUndNahrungsmittelKategorie.VEGETARISCH);
       }
        
    }


}
