package com.speicher;

/**
 * Created by Ture on 22.07.2016.
 */
public class Mahlzeit {
    private String datum;
    private Rezept rezept;
    private int id;

    public Mahlzeit(String datum) {
        rezept = new Rezept();
        this.datum = datum;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Rezept getRezept() {
        return rezept;
    }

    public void setRezept(Rezept rezept) {
        this.rezept = rezept;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String toString(){
        String res = getDatum()+ " : ";
        if(null != rezept.getName()){
            res += rezept.getName();
        }else{
            res += "Neue Mahlzeit";
        }
        return res;
    }
}
