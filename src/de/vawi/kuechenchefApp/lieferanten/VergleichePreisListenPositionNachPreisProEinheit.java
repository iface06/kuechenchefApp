package de.vawi.kuechenchefApp.lieferanten;

import java.util.Comparator;

/**
 *
 * @author Matthias
 */
class VergleichePreisListenPositionNachPreisProEinheit implements Comparator<PreisListenPosition> {

    @Override
    public int compare(PreisListenPosition t, PreisListenPosition t1) {
        return new Double(t.berechnePreisProEinheit()).compareTo(new Double(t1.berechnePreisProEinheit()));
    }
}
