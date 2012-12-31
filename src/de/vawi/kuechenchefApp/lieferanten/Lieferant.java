package de.vawi.kuechenchefApp.lieferanten;

import java.util.*;

/**
 * Diese abstrakte Klasse bereitet für ihre zwei Unterklassen, Bauer und
 * Großhaendler, den Namen und den Lieferkostensatz für die spätere
 * Lieferkostenberechnung vor. Sie hält außerdem eine Liste mit Lieferanten und
 * ihren entsprechenden Preislisten-Positionen.
 *
 *
 * @author Struebe
 * @version 30.12.2012
 */
public abstract class Lieferant {

    private String name;
    private double lieferKostenFaktor;
    protected List<PreisListenPosition> preisListenPositionen;

/**
 *  Konstruktor für die Auflistung der Preislisten-Positionen.
 */
    Lieferant (String name, double lieferKostenFaktor) {
        this.name = name;
        this.lieferKostenFaktor = lieferKostenFaktor;
        preisListenPositionen = new ArrayList<>();
    }

    /**
     * @param name Name des Lieferanten
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * TODO Was ist der Unterschied hier zw. Parameter und return?
     * @param lieferKostenFaktor Wird der Lieferant als Bauer klassifiziert, steht hier die
     * Entfernung in km (z.B. 5 km), wird der Lieferant als Großhändler
     * klassifiziert, steht hier der Lieferkostenfaktor, mit dem der Gesamtpreis
     * der anzuliefernden Ware miltipliziert wird, um die Lieferkosten
     * abzudecken.
     * @return macht den lieferKostenFaktor öffentlich zugänglich.
     */
    public double getLieferKostenFaktor() {
        return this.lieferKostenFaktor;
    }

    /**
     * @return Name repräsentiert den Namen des Lieferanten
     */
    public String getName() {
        return this.name;
    }

    /**
     * Diese Methode berechnet in den Unterklassen die Lieferkosten, wobei der
     * parameter nur beim Großhaendler benötigt wird.
     *
     * @param einkaufsWert Stellt den Gesamtpreis der bei einem Großhändler
     * bestellten Ware dar.
     * @return Lieferkosten, die für die Bestellung anfallen.
     */
    abstract double berechneLieferkosten(double einkaufsWert);

    /**
     * Diese Methode fügt zum jeweiligen Lieferanten die entsprechenden
     * Positionen der Preisliste eines Lieferanten hinzu, nachdem geprüft wurde,
     * ob die Position noch nicht vorhanden ist.
     *
     * @param preisListenPosition Ein Objekt vom Typ PreisListenPosition mit den
     * entsprechenden Parametern.
     */
    public void hinzufuegenPreisListenPosition(PreisListenPosition preisListenPosition) {
        if (!preisListenPositionen.contains(preisListenPosition)) {
            preisListenPositionen.add(preisListenPosition);
        }
    }

    /**
     *
     * @param i Zählt die Preis-Listen-Positionen durch.
     * @return Gibt die i-te Position auf der Preisliste wider.
     */
    public PreisListenPosition getPreisListenPosition(int i) {
        return preisListenPositionen.get(i);
    }

    /**
     *
     * @return Gibt die Anzahl der Positionen auf einer Preisliste wider.
     */
    public int getPreisListenPositionAnzahl() {
        return preisListenPositionen.size();
    }

    /**
     *
     * @return Gibt die Unterklasse, in die der Lieferant einsortiert wurde
     * (Bauer oder Großhändler), und den Lieferantennamen aus. Beispiel: Bauer
     * 'Anglerkollektiv Nord-West', oder Grosshaendler 'Metri AG'.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName().toString() + " '" + this.name + "'";
    }
}
