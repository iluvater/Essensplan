package com.speicher;

/**
 * Created by Ture on 22.07.2016.
 */
public class Mahlzeit {
    private String datum;
    private Rezept rezept;

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
}
