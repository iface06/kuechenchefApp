package de.vawi.kuechenchefApp;

import de.vawi.kuechenchefApp.einkaufsliste.EinkaufslistenErsteller;
import de.vawi.kuechenchefApp.einkaufsliste.Einkaufsliste;
import de.vawi.kuechenchefApp.kostenaufstellung.Kostenaufstellung;
import de.vawi.kuechenchefApp.kostenaufstellung.KostenaufstellungErsteller;
import de.vawi.kuechenchefApp.speiseplan.SpeiseplanErsteller;
import de.vawi.kuechenchefApp.speiseplan.Speiseplan;
import java.util.*;

/**
 * Diese Klasse steuert den Prozess für die Erstellung der Speisepläne, Einkaufsliste und Kostenaufstellung.
 * @author Beer 
 * @version (a version number or a date)
 */
public class ProzessSteuerung{

    private SpeiseplanErsteller speiseplanErsteller;
    private EinkaufslistenErsteller einkaufslistenErsteller;
    private KostenaufstellungErsteller kostanaufstellungErsteller;
    
    private Einkaufsliste einkaufsliste;
    private List<Speiseplan> speiseplaene;
    private Kostenaufstellung kostenaufstellung;
    
    /**
     * Übergeben des SpeiseplanErsteller.
     * 
     * @param  ersteller    Ersteller-Klasse für den Speiseplan Sonja, Tobias, Matthias
     * 
     */
    public void setSpeiseplanErsteller(SpeiseplanErsteller ersteller){
    }
    
    /**
     * Übergeben des EinkaufslistenErsteller.
     * 
     * @param  ersteller     Ersteller-Klasse für die Einkaufsliste
     */
    public void setEinkaufslistenErsteller(EinkaufslistenErsteller ersteller){
    }
    
    /**
     * Übergeben des KostenaufstellungErsteller.
     * 
     * @param  ersteller  Ersteller-Klasse für die Kostenaufstellung
     */
    public void setKostenaufstellungErsteller(KostenaufstellungErsteller ersteller){
    }
    
    /**
     * Organisiert den Ablauf der Erstellung der Speisepläne, der dazugehörigen Einkaufsliste und die daraus resultierende Kostenaufstellung.
     * Funktionert nur wenn vorher die entsprechenden Ersteller für die Speisepläne, Einkaufsliste und Kostenaufstellung gesetzt wurden.
     */
    public void start()
    {
        
    }
    
    /**
     * Nach dem Ausführung der Methode "start", kann hier die Kostenaufstellung geholt werden.
     * 
     * @return     Kostenaufstellung
     */
    public Kostenaufstellung getKostenaufstellung(){
        return this.kostenaufstellung;
    }
    
    /**
     * Nach dem Ausführung der Methode "start", können hier die Speisepläne geholt werden.
     * 
     * @return     Liste von Speiseplänen
     */
    public List<Speiseplan> getSpeiseplaene(){
        return this.speiseplaene;
    }
    
    /**
     * Nach dem Ausführung der Methode "start", kann hier die Einkaufsliste geholt werden.
     * 
     * @return     Einkaufsliste
     */
    public Einkaufsliste getEinkaufsliste(){
        return this.einkaufsliste;
    }
    
    
    
}
