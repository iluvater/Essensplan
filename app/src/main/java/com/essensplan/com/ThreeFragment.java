package com.essensplan.com;

/**
 * Created by Ture on 29.08.2016.
 */
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.essensplan.R;
import com.speicher.EinkaufsDBDataSource;
import com.speicher.Rezept;
import com.speicher.Zutat;

import java.io.Serializable;
import java.util.ArrayList;



public class ThreeFragment extends Fragment{
    ArrayList<String> werteListe;
    ArrayList<Rezept>rezepte;
    EinkaufsDBDataSource dataSource;

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
        dataSource = new EinkaufsDBDataSource(getActivity());
        dataSource.open();



       rezepte = (ArrayList<Rezept>) dataSource.showAllRezepte();
       // rezepte = new ArrayList<>();

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
                myIntent.putExtra("id", aktrez.getId());
                startActivityForResult(myIntent, 0);


            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                final Rezept r = rezepte.get(pos);
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.popup_dialog_rezept);
                dialog.setTitle("Rezept bearbeiten");

                final EditText eTName = (EditText) dialog.findViewById(R.id.editTextName);
                eTName.setText(r.getName());

                Button btn = (Button) dialog.findViewById(R.id.buttonDialog);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Rezept neuRezept = new Rezept(eTName.getText().toString());
                        neuRezept.setId(r.getId());
                        dataSource.updateRezept(neuRezept);
                        ArrayList<Rezept> neuRezepte = (ArrayList<Rezept>) dataSource.showAllRezepte();
                        rezepte.clear();
                        rezepte.addAll(neuRezepte);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });

                Button buttonDel = (Button) dialog.findViewById(R.id.buttonDelRezept);
                buttonDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rezepte.remove(r);
                        dataSource.deleteRezept(r);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });

                dialog.show();


                return true;
            }
        });

        Button btn = (Button) view.findViewById(R.id.addbuttonRezepte);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rezept r1 = dataSource.createRezept("Neues Rezept");
                rezepte.add(r1);
                adapter.notifyDataSetChanged();
            }
        };


        /** Setting the event listener for the add button */
        btn.setOnClickListener(listener);


        return view;
    }

}

