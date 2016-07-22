package com.speicher;

/**
 * Created by Ture on 22.07.2016.
 */
public class Zutat {
    private double menge;
    private String einheit;
    private String name;

    public Zutat(String name){
        this.name =name;
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
}
