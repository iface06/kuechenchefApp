package de.vawi.kuechenchefApp.lieferanten;

import java.io.File;
import java.util.*;
import javax.swing.*;
import java.lang.*;



/**
 *
 * @author Sonja
 */
public class PreisListenOrdnerSuche {


    /**
     * @param args the command line arguments
     * 
     */
    public static String PreisListenOrdnerSuche() {
       
        ImageIcon icon = new ImageIcon("bild.jpg"); 
        JOptionPane.showMessageDialog(null, "Bitte Suchen Sie den Ordner, in dem Sie die Preislisten-Dateien abgelegt haben.", "KuechenChefApp", JOptionPane.INFORMATION_MESSAGE, icon);
        
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(null);
        File ordner;
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            ordner = fc.getSelectedFile();
            System.out.println(ordner.getAbsolutePath());
            
            return ordner.getAbsolutePath().toString();
        }
        else {
            return null;
        }
        
    }
}
