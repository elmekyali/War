package com.sqli.nespresso.war.map;

import com.sqli.nespresso.war.kingdom.countries.Country;

import java.util.List;

class Coordinate {
    private final Country firstCountry, secondCountry;
    private int distance;

    Coordinate(Country firstCountry, Country secondCountry, int distance) {
        this.firstCountry = firstCountry;
        this.secondCountry = secondCountry;
        this.distance = distance;
    }

    int getDistance() {
        return distance;
    }

    boolean differentKingDoms(List<Country> countries) {
        return !(countries.contains(firstCountry) && countries.contains(secondCountry)) && (countries.contains(firstCountry) || countries.contains(secondCountry));
    }

    Country getFrontierCountry(List<Country> countries) {
        return countries.contains(firstCountry) ? firstCountry : secondCountry;
    }


    Country getEnemy(List<Country> countries) {
        return !countries.contains(firstCountry) ? firstCountry : secondCountry;
    }
}
