package com.sqli.nespresso.war.kingdom;

import com.sqli.nespresso.war.kingdom.countries.CitiesParser;
import com.sqli.nespresso.war.kingdom.countries.Country;

import java.util.*;

public class KingDomBuilder {

    private String king;
    private List<Country> countryList;

    public KingDomBuilder addKing(String king) {
        this.king = king;
        this.countryList = new ArrayList<>();
        return this;
    }

    public KingDomBuilder addCountry(String country, String... cityComponent) {
        CitiesParser citiesParser = new CitiesParser();
        Country newCountry = new Country(country, citiesParser.build(cityComponent));
        this.countryList.add(newCountry);
        return this;
    }

    public KingDomBuilder addSoldiersOnEdges(String soldiers) {
        countryList.forEach(country -> country.addSoldiersOnEdges(Integer.parseInt(soldiers)));
        return this;
    }

    public KingDom build() {
        return new KingDom(king, countryList);
    }
}
