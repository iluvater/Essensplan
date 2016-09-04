package com.speicher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ture on 23.07.2016.
 */
public class Einkaufsplan {
    private List<Zutat> aktZutaten;

    public Einkaufsplan(){
        aktZutaten= new ArrayList<Zutat>();

    }

    public List<Zutat> getAktZutaten() {
        return aktZutaten;
    }

    public void setAktZutaten(List<Zutat> aktZutaten) {
        this.aktZutaten = aktZutaten;
    }


    public void addZutat(Zutat z){
        if(!aktZutaten.contains(z)){
            aktZutaten.add(z);
        }
    }

    public void deleteZutat(Zutat z){
        if(aktZutaten.contains(z)){
            aktZutaten.remove(z);
        }
    }
}

