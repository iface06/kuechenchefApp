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
public interface DateiSchreiberManager {

    public void openOutFile() throws IOException;
        public void writeLine(String in_str);
        public void closeOutFile();
}
