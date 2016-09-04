package com.speicher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ture on 23.07.2016.
 */
public class Essensplan {
    private List<Mahlzeit> aktMahlzeit;

    public Essensplan(){
        aktMahlzeit = new ArrayList<Mahlzeit>();
    }

    public List<Mahlzeit> getAktMahlzeit() {
        return aktMahlzeit;
    }

    public void setAktMahlzeit(List<Mahlzeit> aktMahlzeit) {
        this.aktMahlzeit = aktMahlzeit;
    }



    public void addZutat(Mahlzeit m){
        if(!aktMahlzeit.contains(m)){
            aktMahlzeit.add(m);
        }
    }

    public void deleteZutat(Mahlzeit m){
        if(aktMahlzeit.contains(m)){
            aktMahlzeit.remove(m);
        }
    }
}
