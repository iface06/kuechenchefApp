package de.vawi.kuechenchefApp.lieferanten;

import java.util.Comparator;

/**
 *
 * @author Lepping
 * @version 30.01.2013
 */
class VergleichePreisListenPositionNachPreisProEinheit implements Comparator<PreisListenPosition> {

    @Override
    public int compare(PreisListenPosition t, PreisListenPosition t1) {
        return new Double(t.berechnePreisProEinheit()).compareTo(new Double(t1.berechnePreisProEinheit()));
    }
}
