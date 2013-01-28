package de.vawi.kuechenchefApp.speisen;

import de.vawi.kuechenchefApp.nahrungsmittel.*;


public class DummySpeise {
    
    private Speise speise = new Speise();
    
    public DummySpeise mit(String name, int beliebtheit){
        speise.setBeliebtheit(beliebtheit);
        speise.setName(name);
        return this;
    }
    
    public DummySpeise mitZutat(String name, Einheit einheit, double menge, int vorrat){
        Zutat zutat = new Zutat();
        zutat.setNahrungsmittel(erstelleNahrungsmittel(name, einheit, vorrat));
        zutat.setMenge(menge);
        speise.addZutat(zutat);
        return this;
    }
    
    private Nahrungsmittel erstelleNahrungsmittel(String name, Einheit einheit, int vorrat){
        Nahrungsmittel nahrungsmittel = new Nahrungsmittel();
        nahrungsmittel.setName(name);
        nahrungsmittel.setEinheit(einheit);
        nahrungsmittel.setVerfuegbareGesamtMenge(vorrat);
        return nahrungsmittel;
    }

    public Speise erstelle(){
        return speise;
    }
}
