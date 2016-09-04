package com.essensplan.com;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.essensplan.R;
import com.speicher.EinkaufsDBDataSource;
import com.speicher.Rezept;
import com.speicher.Zutat;

/**
 * Created by Ture on 31.08.2016.
 */
public class RezeptEinzelActivity extends AppCompatActivity {
    ListView listView;
    Rezept rezept;
    final Context context = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rezept_einzel_uebersicht);
        final EinkaufsDBDataSource dataSource = new EinkaufsDBDataSource(this);
        dataSource.open();

        Intent i = getIntent();
        // Receiving the Data
        final int rezeptid = (int)i.getExtras().get("id");
        rezept =  dataSource.getRezept((long) rezeptid);


        listView = (ListView) findViewById(R.id.listViewZutaten);


        final ArrayAdapter<Zutat> adapter= new ArrayAdapter<Zutat>(
                getBaseContext(),
                R.layout.rezepte_item_liste,
                R.id.rezepte_item_list,
                rezept.getZutaten()
        );

        ((ListView) findViewById(R.id.listViewZutaten)).setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                final Zutat z = rezept.getZutaten().get(position);
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.popup_dialog_zutaten);
                dialog.setTitle("Zutat bearbeiten");

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
                        Zutat zutat = new Zutat(eTName.getText().toString(), Double.parseDouble(eTMenge.getText().toString()), eTEinheit.getText().toString() ,z.getId());
                        dataSource.updateZutat(zutat);
                        Rezept neuRezept= dataSource.getRezept(rezeptid);
                        rezept.getZutaten().clear();
                        rezept.getZutaten().addAll(neuRezept.getZutaten());
                        dialog.dismiss();
                        adapter.notifyDataSetChanged();

                    }
                });

                dialog.show();


            }
        });

        Button btn = (Button) findViewById(R.id.buttonaddZutat);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Zutat z1 = dataSource.createZutat("Neue Zutat", "", 0,rezeptid, 0);
                rezept.addZutat(z1);
                adapter.notifyDataSetChanged();
            }
        };

        /** Setting the event listener for the add button */
        btn.setOnClickListener(listener);



    }



}
