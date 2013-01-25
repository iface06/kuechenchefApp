

package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.dateien.Datei;
import java.util.Iterator;


class PreisListe implements Datei{
    
    private Datei datei;

    PreisListe(Datei datei) {
        this.datei = datei;
    }
    

    @Override
    public String getDateinameMitPfad() {
        return datei.getDateinameMitPfad();
    }

    /**
     * Diese Methode
     * 
     * @return 
     */
    public String getLieferantenZeile() {
        Iterator<String> iterator = iterator();
        if(iterator.hasNext()){
            return iterator.next();
        }
        return "";
        
    }
    
    public Iterator<String> preisListenPositionenIterator(){
        Iterator<String> iterator = iterator();
        if(iterator.hasNext()){
            iterator.next();
        }
        
        return iterator;
    }

    @Override
    public Iterator<String> iterator() {
        return datei.iterator();
        
    }

}
