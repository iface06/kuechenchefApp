

package de.vawi.kuechenchefApp.dateien;


public class Parse {

    
    public static double toDouble(String value){
        try {
            return Double.parseDouble(value.replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new FehlerBeimParsen();
        }
    }

    public static int toInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new FehlerBeimParsen();
        }
    }
    
    public static class FehlerBeimParsen extends RuntimeException{
        
    }
    
}
