package com.speicher;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Ture on 24.07.2016.
 */
public class SaveImpl implements SaveServ {
    @Override
    public void saveEinkaufplan(Einkaufsplan einkaufsplan) {
        try{
            Gson gson = new Gson();
            FileWriter fw = new FileWriter("@string/EinkaufsplanDatei");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(gson.toJson(einkaufsplan));
            bw.close();
        }catch (IOException e){

        }
    }

    @Override
    public void saveEssensplan(Essensplan essensplan) {
        try{
            Gson gson = new Gson();
            FileWriter fw = new FileWriter("@string/EssensplanDatei");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(gson.toJson(essensplan));
            bw.close();
        }catch (IOException e){

        }
    }
}
