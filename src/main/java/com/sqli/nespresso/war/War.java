package com.sqli.nespresso.war;

import com.sqli.nespresso.war.kingdom.KingDom;
import com.sqli.nespresso.war.kingdom.countries.Country;
import com.sqli.nespresso.war.map.Map;

import java.util.Comparator;
import java.util.List;

public class War {

    private final Map map;
    private final List<KingDom> kingDoms;

    public War(Map map, List<KingDom> kingDoms) {
        this.map = map;
        this.kingDoms = kingDoms;
    }

    public void prepareAttack() {
        getKingDomWithMorePower()
                .prepareAttack();
    }

    public void start() {
        Country frontierCountry = getKingDomWithMorePower()
                .getFrontierSide();
        Country enemy = getKingDomWithLessPower()
                .getFrontierSide();
        frontierCountry.attack(enemy);
    }


    private KingDom getKingDomWithMorePower() {
        return kingDoms.stream()
                .sorted(Comparator.comparing(KingDom::currentPower).reversed())
                .findFirst()
                .get();
    }

    private KingDom getKingDomWithLessPower() {
        return kingDoms.stream()
                .sorted(Comparator.comparing(KingDom::currentPower))
                .findFirst()
                .get();
    }
}
