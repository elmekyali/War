package com.sqli.nespresso.war.map;

import com.sqli.nespresso.war.kingdom.countries.Country;

import java.util.Arrays;

public class MapParser {
    public Map parse(String mapDetails) {
        Map map = new Map();
        Arrays.stream(mapDetails.split(","))
                .map(coordinate -> {
                    String[] components = coordinate.split(":");
                    Country firstCountry = new Country(components[0], null);
                    Country secondCountry = new Country(components[2], null);
                    return new Coordinate(firstCountry, secondCountry, Integer.parseInt(components[1]));
                })
                .forEach(map::addCoordinate);
        return map;
    }
}
