package com.speicher;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ture on 01.09.2016.
 */
public class EinkaufsDBHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = EinkaufsDBHelper.class.getSimpleName();

    public static final String DB_NAME = "EinkaufsDB";
    public static final int DB_VERSION = 5;

    public static final String TABLE_ESSENSPLAN = "essensplan";
    public static final String TABLE_EINKAUFSPLAN = "einkaufsplan";
    public static final String TABLE_ZUTATEN = "zutaten";
    public static final String TABLE_MAHLZEITEN = "mahlzeiten";
    public static final String TABLE_REZEPTE = "rezepte";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MENGE = "menge";
    public static final String COLUMN_EINHEIT = "einheit";
    public static final String COLUMN_REZEPT = "rezept";
    public static final String COLUMN_EINKAUFSPLAN = "einkaufsplan";
    public static final String COLUMN_DATUM = "datum";
    public static final String COLUMN_ESSENSPLAN = "essensplan";

    public static final String[] TABLE_COLUMNS_ZUTATEN = {COLUMN_ID, COLUMN_NAME, COLUMN_EINHEIT, COLUMN_MENGE, COLUMN_REZEPT, COLUMN_EINKAUFSPLAN};
    public static final String[] TABLE_COLUMNS_REZEPTE = {COLUMN_ID, COLUMN_NAME};
    public static final String[] TABLE_COLUMNS_MAHLZEITEN = {COLUMN_ID, COLUMN_DATUM, COLUMN_ESSENSPLAN};
    public static final String[] TABLE_COLUMNS_EINKAUFSPLAN = {COLUMN_ID};
    public static final String[] TABLE_COLUMNS_ESSENSPLAN = {COLUMN_ID};


    public static final String CREATE_ESSENSPLAN = "CREATE TABLE "
            + TABLE_ESSENSPLAN + "(" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT);";

    public static final String CREATE_EINKAUFSPLAN = "CREATE TABLE "
            + TABLE_EINKAUFSPLAN + "(" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT);";

    public static final String CREATE_ZUTATEN = "CREATE TABLE "
            + TABLE_ZUTATEN + "(" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_EINHEIT + " TEXT, "
            + COLUMN_MENGE + " REAL, "
            + COLUMN_REZEPT + " INTEGER, "
            + COLUMN_EINKAUFSPLAN + " INTEGER, "
            + " FOREIGN KEY (" + COLUMN_EINKAUFSPLAN + ") REFERENCES "
            + TABLE_EINKAUFSPLAN + "(" + COLUMN_ID + ")" +
            " FOREIGN KEY (" + COLUMN_REZEPT + ") REFERENCES " +
            TABLE_REZEPTE + "(" + COLUMN_ID + "))";

    public static final String CREATE_MAHLZEITEN = "CREATE TABLE " +
            TABLE_MAHLZEITEN + "(" + COLUMN_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DATUM + " TEXT NOT NULL, "
            + COLUMN_ESSENSPLAN + " INTEGER, "
            + "FOREIGN KEY (" + COLUMN_ESSENSPLAN
            + ") REFERENCES " + TABLE_ESSENSPLAN
            + "(" + COLUMN_ID + "))";

    public static final String CREATE_REZEPTE = "CREATE TABLE "
            + TABLE_REZEPTE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL );";


    public EinkaufsDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + CREATE_ESSENSPLAN + " angelegt.");
            db.execSQL(CREATE_ESSENSPLAN);
        } catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }

        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + CREATE_EINKAUFSPLAN + " angelegt.");
            db.execSQL(CREATE_EINKAUFSPLAN);
        } catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }

        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + CREATE_MAHLZEITEN + " angelegt.");
            db.execSQL(CREATE_MAHLZEITEN);
        } catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }

        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + CREATE_REZEPTE + " angelegt.");
            db.execSQL(CREATE_REZEPTE);
        } catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }

        try {
            Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + CREATE_ZUTATEN + " angelegt.");
            db.execSQL(CREATE_ZUTATEN);
        } catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}