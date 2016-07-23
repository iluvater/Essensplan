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
}

