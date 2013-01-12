package de.vawi.kuechenchefApp.lieferanten;

import de.vawi.kuechenchefApp.dateien.*;
import java.io.*;
import java.util.*;

class PreisListen implements Iterable<PreisListe>{

    private static final String ORDNER_PREISLISTEN = "importDatein";
    private List<PreisListe> preisListen = new ArrayList<>();
    
    void leseDateien() {
        File preisListenOrdner = oeffneOrdner();
        List<String> dateiNamen = leseDateiNamenAusOrdner(preisListenOrdner);
        for (String dateiName : dateiNamen) {
            Datei preisliste = leseDatei(dateiName);
            preisListen.add(new PreisListe(preisliste));
        }
    }

    public Iterator<PreisListe> iterator() {
        return preisListen.iterator();
    }

    protected File oeffneOrdner() {
        File preisListenOrdner = new File(ORDNER_PREISLISTEN);
        return preisListenOrdner;
    }

    protected List<String> leseDateiNamenAusOrdner(File preisListenOrdner) {
        List<String> dateiNamen = new ArrayList<String>();
        File[] preisListenDateien = preisListenOrdner.listFiles(new PreisListenDateiFilter());
        for (File file : preisListenDateien) {
            dateiNamen.add(file.getAbsolutePath());
        }
        return dateiNamen;
    }

    protected Datei leseDatei(String dateiName) {
        DateiLeser leser = new DateiLeser(dateiName);
        return leser.leseDatei();
    }

    class PreisListenDateiFilter implements FilenameFilter {

        @Override
        public boolean accept(File d, String name) {
            return name.toLowerCase().startsWith("preisliste") && name.toLowerCase().endsWith(".csv");
        }
    }
}
