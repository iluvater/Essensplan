package com.essensplan.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.speicher.Rezept;
import com.speicher.Zutat;

/**
 * Created by Ture on 31.08.2016.
 */
public class RezeptEinzelActivity extends AppCompatActivity {
    ListView listView;
    Rezept rezept;
    EditText eText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rezept_einzel_uebersicht);

        Intent i = getIntent();
        // Receiving the Data
        rezept =  (Rezept)i.getExtras().get("rezept");

        listView = (ListView) findViewById(R.id.listViewZutaten);


        final ArrayAdapter<Zutat> adapter= new ArrayAdapter<Zutat>(
                getBaseContext(),
                R.layout.rezepte_item_liste,
                R.id.rezepte_item_list,
                rezept.getZutaten()
        );

        ((ListView) findViewById(R.id.listViewZutaten)).setAdapter(adapter);


        Button btn = (Button) findViewById(R.id.buttonaddZutat);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Zutat z1 = new Zutat("Neue Zutat");
                rezept.addZutat(z1);
                adapter.notifyDataSetChanged();
            }
        };

        /** Setting the event listener for the add button */
        btn.setOnClickListener(listener);



    }





}
