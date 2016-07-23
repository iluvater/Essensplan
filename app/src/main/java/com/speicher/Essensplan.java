package com.speicher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ture on 23.07.2016.
 */
public class Essensplan {
    private List<Mahlzeit> aktMahlzeit;
    private List<Mahlzeit> altMahlzeit;

    public Essensplan(){
        aktMahlzeit = new ArrayList<Mahlzeit>();
        altMahlzeit = new ArrayList<Mahlzeit>();
    }

    public List<Mahlzeit> getAktMahlzeit() {
        return aktMahlzeit;
    }

    public List<Mahlzeit> getAltMahlzeit() {
        return altMahlzeit;
    }

    public void setAktMahlzeit(List<Mahlzeit> aktMahlzeit) {
        this.aktMahlzeit = aktMahlzeit;
    }

    public void setAltMahlzeit(List<Mahlzeit> altMahlzeit) {
        this.altMahlzeit = altMahlzeit;
    }

    public void checkOut(Mahlzeit m){
        if(aktMahlzeit.contains(m)){
            altMahlzeit.add(m);
            aktMahlzeit.remove(m);
        }
    }

    public void checkIn (Mahlzeit m){
        if(altMahlzeit.contains(m)){
            aktMahlzeit.add(m);
            altMahlzeit.remove(m);
        }
    }

    public void addZutat(Mahlzeit m){
        if(!aktMahlzeit.contains(m)){
            aktMahlzeit.add(m);
        }
    }

    public void deleteZutat(Mahlzeit m){
        if(aktMahlzeit.contains(m)){
            aktMahlzeit.remove(m);
        }else{
            if(altMahlzeit.contains(m)){
                altMahlzeit.remove(m);
            }
        }
    }
}
