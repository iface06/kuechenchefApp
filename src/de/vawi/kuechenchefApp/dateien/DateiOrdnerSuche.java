package de.vawi.kuechenchefApp.dateien;

import java.io.File;
import java.util.*;
import javax.swing.*;
import java.lang.*;

/**
 *
 * @author Sonja
 * @version 26.01.2013
 */
public class DateiOrdnerSuche {


    /**
     * @param args the command line arguments
     * 
     */
    public String dateiOrdnerSuche() {
       
        ImageIcon icon = new ImageIcon("bild.jpg"); 
        JOptionPane.showMessageDialog(null, "Bitte Suchen Sie den Ordner, in dem Sie die Preislisten-Dateien, die Rezept-Datei und die Speisen-Datei abgelegt haben.", "KuechenChefApp", JOptionPane.INFORMATION_MESSAGE, icon);
        
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