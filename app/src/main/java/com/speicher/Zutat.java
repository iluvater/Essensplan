package com.speicher;

import java.io.Serializable;

/**
 * Created by Ture on 22.07.2016.
 */
public class Zutat implements Serializable{
    private double menge;
    private String einheit;
    private String name;
    private int id;

    public Zutat(String name, double menge, String einheit, int id){
        this.name =name;
        this.menge=menge;
        this.einheit=einheit;
        this.id = id;
    }

    public double getMenge() {
        return menge;
    }

    public String getEinheit() {
        return einheit;
    }

    public String getName() {
        return name;
    }

    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }

    public void setMenge(double menge) {
        this.menge = menge;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Zutat z){
        if(this.name.equals(z.getName())&&this.einheit.equals(z.getEinheit())&&this.menge==z.getMenge()){
            return true;
        }else{
            return false;
        }
    }

    public String toString(){
        return menge + " " + einheit + " " + name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
