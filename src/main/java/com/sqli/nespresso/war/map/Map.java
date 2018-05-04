package com.sqli.nespresso.war.map;

import com.sqli.nespresso.war.kingdom.countries.Country;

import java.util.*;

public class Map {
    private static List<Coordinate> map;

    Map() {
        map = new ArrayList<>();
    }

    void addCoordinate(Coordinate coordinate) {
        map.add(coordinate);
    }

    public static Country getFrontierCountry(final List<Country> countries) {
        return getShortestPath(countries)
                .getFrontierCountry(countries);
    }

    public static Country getEnemy(final List<Country> countries) {
        return getShortestPath(countries)
                .getEnemy(countries);
    }

    private static Coordinate getShortestPath(List<Country> countries) {

        return map.stream()
                .filter(coordinate -> coordinate.differentKingDoms(countries))
                .sorted(Comparator.comparing(Coordinate::getDistance))
                .findFirst()
                .get();
    }

}
