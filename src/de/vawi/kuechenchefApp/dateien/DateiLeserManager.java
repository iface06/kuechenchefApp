/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.vawi.kuechenchefApp.dateien;

import java.io.IOException;

/**
 *
 * @author Tatsch
 */
public interface DateiLeserManager {
    
    public void openInFile() throws IOException;
    public boolean eof();
    public void writeLine(String in_str);
    public String readLine() throws IOException;
    public void closeInFile() throws IOException;
    
}
