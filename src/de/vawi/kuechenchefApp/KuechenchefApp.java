package de.vawi.kuechenchefApp;

import de.vawi.kuechenchefApp.einkaufsliste.EinkaufslistenExport;
import de.vawi.kuechenchefApp.kostenaufstellung.KostenaufstellungExport;
import de.vawi.kuechenchefApp.rezepte.SpeiseplanExport;
import de.vawi.kuechenchefApp.einkaufsliste.EinkaufslistenErsteller;
import de.vawi.kuechenchefApp.kostenaufstellung.KostenaufstellungErsteller;
import de.vawi.kuechenchefApp.speiseplan.SpeiseplanErsteller;
import de.vawi.kuechenchefApp.lieferanten.PreisListeImport;
import de.vawi.kuechenchefApp.lieferanten.LieferantenVerwaltung;
import de.vawi.kuechenchefApp.rezepte.SpeisenVerwaltung;
import de.vawi.kuechenchefApp.rezepte.SpeisenImport;


/**
 * Diese Klasse ist der Einstiegspunkt der Anwendung KüchenchefApp.
 * Hallo Tobi, Sonja und Matthias
 * @author Tatsch 
 * @version Beta
 * 
 * 
 */
public class KuechenchefApp
{
    

    /**
     * Diese Methode ist der Einstiegspunkt der Anwendung KüchenchefApp. 
     * 
     * @param args Keine Argumente bislang notwendig!
     */
    public static void main(String[] args)
    {
        SpeisenVerwaltung speisen = new SpeisenImport().importFiles();
        LieferantenVerwaltung lieferanten = new PreisListeImport().importFiles();
        
        ProzessSteuerung prozess = new ProzessSteuerung();
        prozess.setSpeiseplanErsteller(new SpeiseplanErsteller(speisen));
        prozess.setEinkaufslistenErsteller(new EinkaufslistenErsteller(lieferanten));
        prozess.setKostenaufstellungErsteller(new KostenaufstellungErsteller());
        prozess.start();
        
        new SpeiseplanExport().export(prozess.getSpeiseplaene());
        new EinkaufslistenExport().export(prozess.getEinkaufsliste());
        new KostenaufstellungExport().export(prozess.getKostenaufstellung());
    }
    
    
}
