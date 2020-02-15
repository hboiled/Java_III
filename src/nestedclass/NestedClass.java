/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nestedclass;

import java.util.LinkedList;

/**
 *
 * @author 61406
 */
public class NestedClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LinkedList<Country> countries = new LinkedList<>();
        
        Country PAK = new Country("Pakistan", 220892340);
        PAK.addCity("Lahore", 11126000);
        PAK.addCity("Karachi", 14910352);
        
        Country RUS = new Country("Russia", 145934462);
        RUS.addCity("Moscow", 13200000);
        RUS.addCity("Saint Petersburg", 5351000);
        
        Country SA = new Country("South Africa", 59308690);
        SA.addCity("Cape Town", 3740026);
        SA.addCity("Durban", 3442361);
        
        Country GRC = new Country("Greece", 10423054);
        GRC.addCity("Kallithea", 100050);
        GRC.addCity("Nea Smyrni", 73090);
        
        countries.addFirst(SA);
        countries.addFirst(RUS);
        countries.addLast(GRC);
        countries.addFirst(PAK);
        
        for (Country c : countries) {
            System.out.println(c);
        }
    }
    
}
