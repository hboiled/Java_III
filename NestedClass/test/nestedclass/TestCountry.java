/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nestedclass;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

/**
 *
 * @author 61406
 */
public class TestCountry {

    @Test
    public void addCityShouldAddCityToList() {        
        Country tester = new Country("CountryA", 1000);
        tester.addCity("A", 1);
        assertTrue(!tester.getCities().isEmpty());
    }
    
    @Test
    public void usingGetCitiesCanAccessCityAttributes() {
        Country tester = new Country("CountryB", 10);
        tester.addCity("C", 5);
        assertNotNull(tester.getCities().get(0));
    }
}
