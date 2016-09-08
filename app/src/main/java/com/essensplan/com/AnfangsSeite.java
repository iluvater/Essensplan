package com.essensplan.com;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.essensplan.MainActivity;
import com.essensplan.R;
import com.speicher.Mahlzeit;
import com.speicher.Rezept;
import com.speicher.Zutat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ture on 05.09.2016.
 */
public class AnfangsSeite extends AppCompatActivity {
    ListView listView;
    Rezept rezept;
    final Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        EditText editText = (EditText) findViewById(R.id.editTextDatumStart);
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        editText.setText(format.format(d));
        editText.setEnabled(false);

        Button button1 = (Button) findViewById(R.id.buttonEssen);
        Button button2 = (Button) findViewById(R.id.buttonEinkauf);
        Button button3 = (Button) findViewById(R.id.buttonRezept);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        };

        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
    }

}

