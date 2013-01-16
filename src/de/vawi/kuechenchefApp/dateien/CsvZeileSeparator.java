package de.vawi.kuechenchefApp.dateien;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CsvZeileSeparator {

    List<String> cells;
    boolean cellStarted = false;
    StringBuffer buffer = new StringBuffer();

    public List<String> separiere(String zeile) {
        cells = new ArrayList<String>();
        for (int index = 0; index < zeile.length(); index++) {
            char ch = zeile.charAt(index);
            if (isStartOrEndSymbol(ch)) {
                cellStarted = !cellStarted;
            } else if ((isSeparatorSymbol(ch) && !cellStarted)) {
                addBufferedCharsToCells();
            } else {
                buffer.append(ch);
            }
        }
        addBufferedCharsToCells();
        
        return cells;
    }

    private boolean isStartOrEndSymbol(char ch) {
        return ch == '\"';
    }

    private boolean isSeparatorSymbol(char ch) {
        return ch == ',';
    }

    private void addBufferedCharsToCells() {
        cells.add(buffer.toString());
        buffer = new StringBuffer();
    }
}
