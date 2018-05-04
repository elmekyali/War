package com.sqli.nespresso.war;

import com.sqli.nespresso.war.kingdom.KingDom;
import com.sqli.nespresso.war.kingdom.KingDomBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/************
 this exercice is about wars between kings,
 every king has one or many countries on his possesion,
 every countries has many cities,
 every city has citizens,
 the countries and cities are protected by soldiers
 every city has soldiers, and countries has soldiers on their edges,
 *************/

public class WarSimulationTest {


    @Test
    public void showKingDom() {
        KingDom kingdom = new KingDomBuilder()
                .addKing("Youness")
                .addCountry("France","20","100","50","200","100","100")  //(name, nbrOfSoldiersInCity1, nbrOfCitizenInCity1, .....)
                .addCountry("Spain","30","200","40","300")
                .build();

        assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>, S:<Sc1:30-200,Sc2:40-300>|",kingdom.report());
        //Fc1 : the presentation of the first city in France
    }


    /************
     every kingDom has a power it's the sum of soldiers power on all his kingdoms
     *************/

    @Test
    public void showKingDomPower() {
        KingDom kingdom = new KingDomBuilder()
                .addKing("Youness")
                .addCountry("France","20","100","50","200","100","100")
                .addCountry("Spain","30","200","40","300")
                .build();
        assertEquals(240, kingdom.currentPower());
    }

    /************
     a kingDom have soldiers on edges of each countries to protect or attack an other kingdoms
     *************/

    @Test
    public void aKingDomHaveSoldiersOnEdges() {
        KingDom kingdom1 = new KingDomBuilder()
                .addKing("Youness")
                .addCountry("France","20","100","50","200","100","100")
                .addCountry("Spain","30","200","40","300")
                .addSoldiersOnEdges("500")
                .build();

        KingDom kingdom2 = new KingDomBuilder()
                .addKing("Amine")
                .addCountry("USA","500","1000","400","500","200","300","2000","300")
                .build();

        assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-500, S:<Sc1:30-200,Sc2:40-300>-500|",kingdom1.report());
        assertEquals("Amine:|U:<Uc1:500-1000,Uc2:400-500,Uc3:200-300,Uc4:2000-300>|",kingdom2.report());

        assertEquals(240, kingdom1.currentPower());
        assertEquals(3100, kingdom2.currentPower());


    }

    /************
     a kingDom can prepare an attack an other kingdoms
     *************/

    @Test
    public void aKingDomCanPrepareAnAttackAnOther() {
        KingDom kingdom1 = new KingDomBuilder()
                .addKing("Youness")
                .addCountry("France","20","100","50","200","100","100")
                .addCountry("Spain","30","200","40","300")
                .addSoldiersOnEdges("500")
                .build();

        KingDom kingdom2 = new KingDomBuilder()
                .addKing("Amine")
                .addCountry("USA","30","200","40","300")
                .addSoldiersOnEdges("200")
                .build();

        assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-500, S:<Sc1:30-200,Sc2:40-300>-500|",kingdom1.report());
        assertEquals("Amine:|U:<Uc1:30-200,Uc2:40-300>-200|",kingdom2.report());
        assertEquals(240, kingdom1.currentPower());
        assertEquals(70, kingdom2.currentPower());


        War war = new WarBuilder()
                .addKingDom(kingdom1)
                .addKingDom(kingdom2)
                .addMap("France:100:Spain,France:1000:USA,Spain:1500:USA")
                .build();

        //the kingdoms which has more power prepare Attack on the nearest kingdoms
        //when a kingdoms prepare an attack he moves 50% of his army on each city to the countries's edge which is the nearest to the other kingdoms
        war.prepareAttack();

        assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-535, S:<Sc1:15-200,Sc2:20-300>-500|",kingdom1.report());
        assertEquals("Amine:|U:<Uc1:30-200,Uc2:40-300>-200|",kingdom2.report());
        assertEquals(205, kingdom1.currentPower());
        assertEquals(70, kingdom2.currentPower());
    }


    /************
     a kingDom can attack an other
     *************/

    @Test
    public void aKingDomCanAttackAttackAnOther() {
        KingDom kingdom1 = new KingDomBuilder()
                .addKing("Youness")
                .addCountry("France","20","100","50","200","100","100")
                .addCountry("Spain","30","200","40","300")
                .addSoldiersOnEdges("500")
                .build();

        KingDom kingdom2 = new KingDomBuilder()
                .addKing("Amine")
                .addCountry("USA","30","200","40","300")
                .addSoldiersOnEdges("200")
                .build();

        assertEquals(240, kingdom1.currentPower());
        assertEquals(70, kingdom2.currentPower());


        War war = new WarBuilder()
                .addKingDom(kingdom1)
                .addKingDom(kingdom2)
                .addMap("France:100:Spain,France:1000:USA,Spain:1500:USA")
                .build();

        //the kingdoms which has more power prepare Attack on the nearest kingdoms
        //when a kingdoms prepare an attack he moves 50% of his army on each city to the countries's edge which is the nearest to the other kingdoms
        war.prepareAttack();

        assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-535, S:<Sc1:15-200,Sc2:20-300>-500|",kingdom1.report());
        assertEquals("Amine:|U:<Uc1:30-200,Uc2:40-300>-200|",kingdom2.report());
        assertEquals(205, kingdom1.currentPower());
        assertEquals(70, kingdom2.currentPower());

        //the kingdoms attacks the soldiers on edge of the nearest countries
        war.start();

        assertEquals("Youness:|F:<Fc1:20-100,Fc2:50-200,Fc3:100-100>-335, S:<Sc1:15-200,Sc2:20-300>-500|",kingdom1.report());
        assertEquals("Amine:|U:<Uc1:30-200,Uc2:40-300>|",kingdom2.report());
        assertEquals(205, kingdom1.currentPower());
        assertEquals(70, kingdom2.currentPower());

    }
}