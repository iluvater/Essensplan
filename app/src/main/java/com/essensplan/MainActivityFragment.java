package com.essensplan;

/**
 * Created by Ture on 30.06.2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.speicher.Einkaufsplan;
import com.speicher.Essensplan;
import com.speicher.Zutat;

import java.util.ArrayList;
import java.util.List;

public class MainActivityFragment extends Fragment{

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Zutat zutat = new Zutat("Käse");
        zutat.setMenge(100);
        zutat.setEinheit("t");
        Einkaufsplan einkaufsplan = new Einkaufsplan();
        einkaufsplan.addZutat(zutat);
        List<String> zutatenstring = new ArrayList<>();
        for (int i = 0;i<einkaufsplan.getAktZutaten().size();i++) {
            zutatenstring.add(einkaufsplan.getAktZutaten().get(i).toString());
        }


        return inflater.inflate(R.layout.fragment_main, container, false);

    }

}
