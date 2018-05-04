package com.sqli.nespresso.war;

import com.sqli.nespresso.war.kingdom.KingDom;
import com.sqli.nespresso.war.map.Map;
import com.sqli.nespresso.war.map.MapParser;

import java.util.ArrayList;
import java.util.List;

public class WarBuilder {

    private Map map;
    private List<KingDom> kingDoms = new ArrayList<>();

    public WarBuilder addKingDom(KingDom kingdom) {
        this.kingDoms.add(kingdom);
        return this;
    }

    public WarBuilder addMap(String mapDetails) {
        MapParser mapParser = new MapParser();
        this.map = mapParser.parse(mapDetails);
        return this;
    }

    public War build() {
        return new War(map, kingDoms);
    }
}
