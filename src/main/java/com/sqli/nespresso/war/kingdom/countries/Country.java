package com.sqli.nespresso.war.kingdom.countries;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Country {
    private final String name;
    private List<City> cities;
    private int soldiersOnEdges;


    public Country(String name, List<City> cities) {
        this.name = name;
        this.cities = cities;
        this.soldiersOnEdges = 0;
    }

    public int power() {
        return cities.stream().mapToInt(City::getPower).sum();
    }

    public void addSoldiersOnEdges(int soldiers) {
        this.soldiersOnEdges += soldiers;
    }

    private void deductSoldiersOnEdges(int soldiers) {
        this.soldiersOnEdges -= soldiers;
    }

    public void attack(Country enemy) {
        this.deductSoldiersOnEdges(enemy.soldiersOnEdges);
        enemy.deductSoldiersOnEdges(enemy.soldiersOnEdges);
    }

    public String report() {
        char countryName = name.charAt(0);
        String citiesReport = IntStream.range(0, cities.size())
                .mapToObj(index -> String.format("%cc%d:%s", countryName, index + 1, cities.get(index).report()))
                .collect(Collectors.joining(","));
        return String.format("%c:<%s>%s", countryName, citiesReport, soldiersOnEdges == 0 ? "" : "-" + soldiersOnEdges);
    }

    public int prepareAttack() {
        return cities.stream().mapToInt(City::prepareAttack).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        return name.equals(country.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
