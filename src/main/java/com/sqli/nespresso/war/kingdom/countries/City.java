package com.sqli.nespresso.war.kingdom.countries;

class City {
    public static final double HALF_NBR_OF_SOLDIERS_IN_CITY = 0.5;
    private int nbrOfSoldiersInCity;
    private int nbrOfCitizenInCity;

    City(int nbrOfSoldiersInCity, int nbrOfCitizenInCity) {
        this.nbrOfSoldiersInCity = nbrOfSoldiersInCity;
        this.nbrOfCitizenInCity = nbrOfCitizenInCity;
    }

    int getPower() {
        return nbrOfSoldiersInCity;
    }

    String report() {
        return String.format("%d-%d", nbrOfSoldiersInCity, nbrOfCitizenInCity);
    }

    int prepareAttack() {
        return this.nbrOfSoldiersInCity *= HALF_NBR_OF_SOLDIERS_IN_CITY;
    }
}
