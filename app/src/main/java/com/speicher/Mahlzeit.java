package com.speicher;

import java.util.Date;

/**
 * Created by Ture on 22.07.2016.
 */
public class Mahlzeit {
    private Date datum;
    private Rezept rezept;

    public Mahlzeit(Date datum){
        rezept = new Rezept();
        this.datum = datum;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Rezept getRezept() {
        return rezept;
    }

    public void setRezept(Rezept rezept) {
        this.rezept = rezept;
    }
}
