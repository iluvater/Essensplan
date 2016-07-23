package com.speicher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ture on 23.07.2016.
 */
public class Einkaufsplan {
    private List<Zutat> aktZutaten;
    private List<Zutat> altZutaten;

    public Einkaufsplan(){
        aktZutaten= new ArrayList<Zutat>();
        altZutaten = new ArrayList<Zutat>();
    }

    public List<Zutat> getAktZutaten() {
        return aktZutaten;
    }

    public List<Zutat> getAltZutaten() {
        return altZutaten;
    }

    public void setAktZutaten(List<Zutat> aktZutaten) {
        this.aktZutaten = aktZutaten;
    }

    public void setAltZutaten(List<Zutat> altZutaten) {
        this.altZutaten = altZutaten;
    }

    public void checkOut(Zutat z){
        if(aktZutaten.contains(z)){
            altZutaten.add(z);
            aktZutaten.remove(z);
        }
    }

    public void checkIn(Zutat z){
        if(altZutaten.contains(z)){
            aktZutaten.add(z);
            altZutaten.remove(z);
        }
    }

    public void addZutat(Zutat z){
        if(!aktZutaten.contains(z)){
            aktZutaten.add(z);
        }
    }

    public void deleteZutat(Zutat z){
        if(aktZutaten.contains(z)){
            aktZutaten.remove(z);
        }else{
            if(altZutaten.contains(z)){
                altZutaten.remove(z);
            }
        }
    }
}

