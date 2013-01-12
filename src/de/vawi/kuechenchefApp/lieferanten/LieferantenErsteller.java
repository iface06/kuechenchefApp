

package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.dateien.Parse;
import java.util.List;


class LieferantenErsteller {
    private static final int LIEFERANTENTYP = 0;
    private static final int NAME = 1;
    private static final int LIEFERKOSTEN = 2;

    Lieferant erstelle(List<String> lieferantenZellen) throws Parse.FehlerBeimParsen {
        Lieferant lieferant = null;
        String type = lieferantenZellen.get(LIEFERANTENTYP);
        String name = lieferantenZellen.get(NAME);
        String lieferkosten = lieferantenZellen.get(LIEFERKOSTEN);
        
        if (type.equals("Grosshandel")) {
            lieferant = erstelleGroßhaendler(name, lieferkosten);
        } else if (type.equals("Bauer")) {
            lieferant = erstelleBauer(name, lieferkosten);
        }
        
        return lieferant;
    }

    private Lieferant erstelleGroßhaendler(String name, String lieferkosten) {
        Lieferant lieferant;
        lieferant = new Grosshaendler();
        lieferant.setName(name);
        lieferant.setLieferKostenFaktor(parseStringToDouble(lieferkosten));
        
        return lieferant;
    }

    private Lieferant erstelleBauer(String name, String lieferkosten) {
        Lieferant lieferant;
        lieferant = new Bauer();
        lieferant.setName(name);
        lieferant.setLieferKostenFaktor(parseStringToDouble(lieferkosten));
        return lieferant;
    }
    
    private double parseStringToDouble(String doubleValue) {
        return Parse.toDouble(doubleValue);
    }

}
