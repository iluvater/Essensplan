package com.speicher;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Ture on 23.07.2016.
 */
public class LoadImpl implements LoadServ {

    @Override
    public Einkaufsplan getDataEinkaufsplan() {
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader("Einkaufsplan.json"));
            Einkaufsplan einkaufsplan = gson.fromJson(br, Einkaufsplan.class);
            return einkaufsplan;
        }catch (IOException e){
            return null;
        }
    }

    @Override
    public Essensplan getDataEssensplan() {
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader("Einkaufsplan.json"));
            Essensplan essensplan = gson.fromJson(br, Essensplan.class);
            return essensplan;
        }catch (IOException e){
            return null;
        }
    }
}
