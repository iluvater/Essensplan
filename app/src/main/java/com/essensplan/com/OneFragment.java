package com.essensplan.com;

/**
 * Created by Ture on 29.08.2016.
 */
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.essensplan.R;
import com.speicher.EinkaufsDBDataSource;
import com.speicher.Mahlzeit;
import com.speicher.Rezept;
import com.speicher.Zutat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OneFragment extends Fragment{

    ArrayList<Mahlzeit> mahlzeiten;
    EinkaufsDBDataSource dataSource;
    int einkaufsplanid;

    public OneFragment() {
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



        mahlzeiten = (ArrayList<Mahlzeit>)dataSource.showAllMahlzeiten();
        //rezepte = new ArrayList<>();
        //mahlzeiten = new ArrayList<Mahlzeit>();

        final ArrayAdapter<Mahlzeit> adapter= new ArrayAdapter<Mahlzeit>(
                getActivity(),
                R.layout.rezepte_item_liste,
                R.id.rezepte_item_list,
                mahlzeiten
        );

        ListView listView = (ListView) view.findViewById(R.id.listViewRezepte);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                final Mahlzeit m = mahlzeiten.get(position);
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.popup_dialog_mahlzeiten);
                dialog.setTitle("Mahlzeit bearbeiten");

                final EditText eTDatum = (EditText) dialog.findViewById(R.id.editTextDatum);
                eTDatum.setText(m.getDatum());

                final Spinner spinner = (Spinner) dialog.findViewById(R.id.dropdownRezepte);
                List<Rezept> list = dataSource.showAllRezepte();
                final ArrayAdapter<Rezept> dataAdapter = new ArrayAdapter<Rezept>(getActivity(),
                        android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);

                Button button = (Button) dialog.findViewById(R.id.buttonMahlzeitenDia);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        m.setDatum(eTDatum.getText().toString());
                        m.setRezept((Rezept)spinner.getSelectedItem());
                        dataSource.updateMahlzeit(m);
                        dialog.dismiss();
                        adapter.notifyDataSetChanged();

                    }
                });


                dialog.show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int pos, long id) {
                final Mahlzeit m = mahlzeiten.get(pos);
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.popup_dialog_loeschen);
                dialog.setTitle("Eintrag löschen?");


                Button btn = (Button) dialog.findViewById(R.id.buttonDelDialog);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mahlzeiten.remove(m);
                        dataSource.deleteMahlzeit(m);
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
                Date d = new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                Mahlzeit m = dataSource.createMahlzeit(format.format(d), 0);
                mahlzeiten.add(m);
                adapter.notifyDataSetChanged();
            }
        };


        /** Setting the event listener for the add button */
        btn.setOnClickListener(listener);
        btn.setText("Neue Mahlzeit");


        return view;
    }

}
