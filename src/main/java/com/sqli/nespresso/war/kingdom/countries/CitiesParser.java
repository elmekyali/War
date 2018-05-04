package com.sqli.nespresso.war.kingdom.countries;

import java.util.ArrayList;
import java.util.List;

public class CitiesParser {
    public List<City> parse(String... cityComponents) {
        int index = 0;
        List<City> cities = new ArrayList<>();
        while (index < cityComponents.length) {
            int nbrOfSoldiersInCity = Integer.valueOf(cityComponents[index++]);
            int nbrOfCitizenInCity = Integer.valueOf(cityComponents[index++]);
            cities.add(new City(nbrOfSoldiersInCity, nbrOfCitizenInCity));
        }
        return cities;
    }
}
