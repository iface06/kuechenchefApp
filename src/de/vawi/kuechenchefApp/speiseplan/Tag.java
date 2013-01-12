package de.vawi.kuechenchefApp.speiseplan;

import de.vawi.kuechenchefApp.speisen.Speise;



/**
 *
 * @author Max
 */
public class Tag {

    private int nummer;
    private Speise beliebtesteSpeise;
    private Speise zweitbeliebtesteSpeise;
    private Speise drittbeliebtesteSpeise;

    public Tag(int nummer) {
        this.nummer = nummer;
    }

    public void setBeliebtesteSpeise(Speise beliebtesteSpeise) {
        this.beliebtesteSpeise = beliebtesteSpeise;
    }

    public void setZweitbeliebtesteSpeise(Speise zweitbeliebtesteSpeise) {
        this.zweitbeliebtesteSpeise = zweitbeliebtesteSpeise;
    }

    public void setDrittbeliebtesteSpeise(Speise drittbeliebtesteSpeise) {
        this.drittbeliebtesteSpeise = drittbeliebtesteSpeise;
    }
    
    
    
    public int getNummer() {
        return nummer;
    }

    public Speise getBeliebtesteSpeise() {
        return beliebtesteSpeise;
    }

    public Speise getZweitbeliebtesteSpeise() {
        return zweitbeliebtesteSpeise;
    }

    public Speise getDrittbeliebtesteSpeise() {
        return drittbeliebtesteSpeise;
    }


}
