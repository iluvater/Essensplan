package com.speicher;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ture on 22.07.2016.
 */
public class Rezept implements Serializable{
    private String name;
    private List<Zutat> zutaten;
    private int id;

    public Rezept(){
        zutaten = new ArrayList<Zutat>();
    }
    public Rezept(String name){
        zutaten = new ArrayList<Zutat>();
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Zutat> getZutaten() {
        return zutaten;
    }

    public void setZutaten(List<Zutat> zutaten) {
        this.zutaten = zutaten;
    }

    public void addZutat(Zutat z){
        boolean found = false;
        for(int i= 0;i<zutaten.size();i++) {
            if(z.equals(zutaten.get(i))){
                found = true;
            }
        }
        if(!found){
            zutaten.add(z);
        }
    }

    public void deleteZutat(Zutat z){
        zutaten.remove(z);
    }

    public String toString(){
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
