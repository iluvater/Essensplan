package com.essensplan.com;

/**
 * Created by Ture on 29.08.2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.essensplan.R;
import com.speicher.Rezept;
import com.speicher.Zutat;

import java.io.Serializable;
import java.util.ArrayList;



public class ThreeFragment extends Fragment{
    ArrayList<String> werteListe;
    ArrayList<Rezept>rezepte;

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_three, container, false);


        werteListe = new ArrayList<String>();
        werteListe.add("test");
        werteListe.add("test1");
        werteListe.add("test2");
        werteListe.add("test3");
        werteListe.add("test");
        werteListe.add("test1");
        werteListe.add("test2");
        werteListe.add("test3");
        werteListe.add("test");
        werteListe.add("test1");
        werteListe.add("test2");
        werteListe.add("test3");

        rezepte = new ArrayList<Rezept>();


        final ArrayAdapter<Rezept> adapter= new ArrayAdapter<Rezept>(
                getActivity(),
                R.layout.rezepte_item_liste,
                R.id.rezepte_item_list,
                rezepte
        );

        ListView listView = (ListView) view.findViewById(R.id.listViewRezepte);

       listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Rezept aktrez = rezepte.get(position);
                Intent myIntent = new Intent(view.getContext(), RezeptEinzelActivity.class);
                myIntent.putExtra("rezept", aktrez);
                startActivityForResult(myIntent, 0);


            }
        });

        Button btn = (Button) view.findViewById(R.id.addbuttonRezepte);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rezept r1 = new Rezept("Neues Rezept");
                rezepte.add(r1);
                adapter.notifyDataSetChanged();
            }
        };


        /** Setting the event listener for the add button */
        btn.setOnClickListener(listener);


        return view;
    }


}

