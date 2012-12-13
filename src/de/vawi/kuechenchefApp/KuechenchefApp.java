package de.vawi.kuechenchefApp;


/**
 * Diese Klasse ist der Einstiegspunkt der Anwendung KüchenchefApp.
 * @author Tatsch 
 * @version (a version number or a date)
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
