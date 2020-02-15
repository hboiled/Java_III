/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nestedclass;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 61406
 */
public class Country {
    private String name;
    private int population;
    private List<City> cities;

    public Country(String name, int population) {
        this.name = name;
        this.population = population;
        cities = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }
    
    public void addCity(String name, int population) {
        cities.add(new City(name, population));
    }

    public List<City> getCities() {
        return cities;
    }
    
    public String cityList() {
        String list = "";
        
        for (City c : cities) {
            list += c + "\n";
        }
        
        return list;
    }
    
    @Override
    public String toString() {
        return name + " - Population: " + population + "\nCities:\n" + cityList();
    }

    private class City {
        private String name;
        private int population;

        public City(String name, int population) {
            this.name = name;
            this.population = population;
        }
        
        public String getName() {
            return name;
        }

        public int getPopulation() {
            return population;
        }
        
        @Override
        public String toString() {
            return name + "- population: " + population;
        }
    }
}
