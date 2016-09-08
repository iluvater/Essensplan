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

import com.essensplan.R;
import com.speicher.EinkaufsDBDataSource;
import com.speicher.Rezept;
import com.speicher.Zutat;

import java.util.ArrayList;


public class TwoFragment extends Fragment{

    ArrayList<Zutat>zutaten;
    EinkaufsDBDataSource dataSource;
    int einkaufsplanid;

    public TwoFragment() {
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



         zutaten = (ArrayList<Zutat>)dataSource.showAllZutatenEinkaufsliste();
        // rezepte = new ArrayList<>();

        final ArrayAdapter<Zutat> adapter= new ArrayAdapter<Zutat>(
                getActivity(),
                R.layout.rezepte_item_liste,
                R.id.rezepte_item_list,
                zutaten
        );

        ListView listView = (ListView) view.findViewById(R.id.listViewRezepte);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                final Zutat z = zutaten.get(position);
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.popup_dialog_zutaten);
                dialog.setTitle("Eintrag bearbeiten");

                final EditText eTName = (EditText) dialog.findViewById(R.id.editTextName);
                eTName.setText(z.getName());
                final EditText eTMenge = (EditText) dialog.findViewById(R.id.editTextMenge);
                eTMenge.setText(Double.toString(z.getMenge()));
                final EditText eTEinheit = (EditText) dialog.findViewById(R.id.editTextEinheit);
                eTEinheit.setText(z.getEinheit());
                Button button = (Button) dialog.findViewById(R.id.buttonDialog);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        z.setEinheit(eTEinheit.getText().toString());
                        z.setMenge(Double.parseDouble(eTMenge.getText().toString()));
                        z.setName(eTName.getText().toString());
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
                final Zutat z = zutaten.get(pos);
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.popup_dialog_loeschen);
                dialog.setTitle("Eintrag löschen?");


                Button btn = (Button) dialog.findViewById(R.id.buttonDelDialog);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        zutaten.remove(z);
                        dataSource.deleteZutat(z);
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
                Zutat z = dataSource.createZutat("Neuer Eintrag", "<Einheit>", 0, 0, einkaufsplanid);
                zutaten.add(z);
                adapter.notifyDataSetChanged();
            }
        };


        /** Setting the event listener for the add button */
        btn.setOnClickListener(listener);
        btn.setText("Neuer Eintrag");


        return view;
    }

}
