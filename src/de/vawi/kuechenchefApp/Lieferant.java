package de.vawi.kuechenchefApp;

import java.util.*;
/**
 * Write a description of class Lieferant here.
 * 
 * @author Struebe 
 * @version (a version number or a date)
 */
public abstract class Lieferant
{
    private String name;
    /**
     * @param  name Name des Lieferanten
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * @return     Name repräsentiert den Namen des Lieferanten 
     */
    
    public String getName()
    {
        return this.name;
    }
    
 
    
}
