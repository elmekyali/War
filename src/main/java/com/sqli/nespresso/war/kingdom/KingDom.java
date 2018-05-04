package com.sqli.nespresso.war.kingdom;

import com.sqli.nespresso.war.kingdom.countries.Country;
import com.sqli.nespresso.war.map.Map;

import java.util.*;
import java.util.stream.Collectors;

public class KingDom {
    private final String king;
    private List<Country> countryList;

    KingDom(String king, List<Country> countryList) {
        this.king = king;
        this.countryList = countryList;
    }

    public String report() {

        String countriesReport = countryList.stream()
                .map(Country::report)
                .collect(Collectors.joining(", "));

        return String.format("%s:|%s|", king, countriesReport);
    }

    public int currentPower() {
        return countryList.stream().mapToInt(Country::power).sum();
    }

    public void prepareAttack() {
        Country frontierCountry = getFrontierSide();
        int powerToAdd = countryList.stream()
                .filter(country -> !country.equals(frontierCountry))
                .mapToInt(Country::prepareAttack)
                .sum();
        frontierCountry.addSoldiersOnEdges(powerToAdd);
    }

    public Country getFrontierSide() {
        return countryList.stream()
                .filter(country -> country.equals(Map.getFrontierCountry(countryList)))
                .findFirst()
                .get();
    }
}
