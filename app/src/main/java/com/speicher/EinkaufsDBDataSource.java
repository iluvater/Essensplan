package com.speicher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ture on 01.09.2016.
 */
public class EinkaufsDBDataSource {
    private static final String LOG_TAG = EinkaufsDBDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private EinkaufsDBHelper dbHelper;


    public EinkaufsDBDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new EinkaufsDBHelper(context);
    }

    public void open() {
        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    public Zutat createZutat(String name, String einheit, double menge, int rezeptID, int einkaufsplanID) {
        ContentValues values = new ContentValues();
        values.put(EinkaufsDBHelper.COLUMN_NAME, name);
        values.put(EinkaufsDBHelper.COLUMN_EINHEIT, einheit);
        values.put(EinkaufsDBHelper.COLUMN_MENGE, menge);
        if(rezeptID!=0) {
            values.put(EinkaufsDBHelper.COLUMN_REZEPT, rezeptID);
        }
        if(einkaufsplanID!=0) {
            values.put(EinkaufsDBHelper.COLUMN_EINKAUFSPLAN, einkaufsplanID);
        }
        long insertId = database.insert(EinkaufsDBHelper.TABLE_ZUTATEN, null, values);
        return getZutat(insertId);

    }

    public Zutat cursorToZutat(Cursor cursor) {
        int idName = cursor.getColumnIndex(EinkaufsDBHelper.COLUMN_NAME);
        int idEinheit = cursor.getColumnIndex(EinkaufsDBHelper.COLUMN_EINHEIT);
        int idMenge = cursor.getColumnIndex(EinkaufsDBHelper.COLUMN_MENGE);
        int idId = cursor.getColumnIndex(EinkaufsDBHelper.COLUMN_ID);

        String name = cursor.getString(idName);
        String einheit = cursor.getString(idEinheit);
        double menge = cursor.getDouble(idMenge);
        int id = cursor.getInt(idId);

        Zutat zutat = new Zutat(name, menge, einheit, id);
        return zutat;
    }

    public Rezept createRezept(String name){
        ContentValues values = new ContentValues();
        values.put(EinkaufsDBHelper.COLUMN_NAME, name);

        long insertId = database.insert(EinkaufsDBHelper.TABLE_REZEPTE, null, values);
        return getRezept(insertId);
    }

    public Rezept cursorToRezept(Cursor cursor){
        int idName = cursor.getColumnIndex(EinkaufsDBHelper.COLUMN_NAME);
        int idID =  cursor.getColumnIndex(EinkaufsDBHelper.COLUMN_ID);

        int id = cursor.getInt(idID);

        Rezept rezept = new Rezept(cursor.getString(idName));
        rezept.setId(id);
        Cursor ZutatenCursor = database.query(EinkaufsDBHelper.TABLE_ZUTATEN, EinkaufsDBHelper.TABLE_COLUMNS_ZUTATEN, EinkaufsDBHelper.COLUMN_REZEPT + "=" + id, null, null, null, null);

        ZutatenCursor.moveToFirst();
        while(!ZutatenCursor.isAfterLast()){
            Zutat z = cursorToZutat(ZutatenCursor);
            rezept.addZutat(z);
            ZutatenCursor.moveToNext();
        }
        ZutatenCursor.close();
        return rezept;
    }

    public Mahlzeit createMahlzeit(String datum, int rezeptid){
        ContentValues values =  new ContentValues();
        values.put(EinkaufsDBHelper.COLUMN_DATUM, datum);
        values.put(EinkaufsDBHelper.TABLE_REZEPTE, rezeptid);

        long insertId = database.insert(EinkaufsDBHelper.TABLE_MAHLZEITEN, null, values);
        return getMahlzeit(insertId);
    }

    public Mahlzeit cursorToMahlzeit(Cursor cursor){
        int idDatum = cursor.getColumnIndex(EinkaufsDBHelper.COLUMN_DATUM);
        int idRezept = cursor.getColumnIndex(EinkaufsDBHelper.TABLE_REZEPTE);

        Cursor cRezept = database.query(EinkaufsDBHelper.TABLE_REZEPTE, EinkaufsDBHelper.TABLE_COLUMNS_REZEPTE, EinkaufsDBHelper.COLUMN_ID + "=" + idRezept, null, null, null, null);

        Rezept rezept = cursorToRezept(cRezept);

        String datum = cursor.getString(idDatum);

        Mahlzeit mahlzeit = new Mahlzeit(datum);
        mahlzeit.setRezept(rezept);

        return mahlzeit;
    }

    public List<Rezept> showAllRezepte(){

        List<Rezept> list = new ArrayList<>();

        Cursor cursor = database.query(EinkaufsDBHelper.TABLE_REZEPTE,
                EinkaufsDBHelper.TABLE_COLUMNS_REZEPTE, null, null, null, null, null);
        if(cursor!=null) {
            cursor.moveToFirst();


            while (!cursor.isAfterLast()) {
                Rezept rezept;
                rezept = cursorToRezept(cursor);
                list.add(rezept);
                cursor.moveToNext();
            }

            cursor.close();
        }
        return list;
    }

    public List<Zutat> showAllZutaten(int rezeptid){
        List<Zutat> list = new ArrayList<>();

        Cursor cursor = database.query(EinkaufsDBHelper.TABLE_ZUTATEN,
                EinkaufsDBHelper.TABLE_COLUMNS_ZUTATEN, EinkaufsDBHelper.COLUMN_REZEPT + "=" + rezeptid, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Zutat z;
            z = cursorToZutat(cursor);
            list.add(z);
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }

    public List<Mahlzeit> showAllMahlzeiten(){
        List<Mahlzeit> list = new ArrayList<>();
        Cursor cursor = database.query(EinkaufsDBHelper.TABLE_MAHLZEITEN,
                EinkaufsDBHelper.TABLE_COLUMNS_MAHLZEITEN, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Mahlzeit m;
            m = cursorToMahlzeit(cursor);
            list.add(m);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public Mahlzeit getMahlzeit(long mahlzeitId){
        Cursor cursor = database.query(EinkaufsDBHelper.TABLE_MAHLZEITEN, EinkaufsDBHelper.TABLE_COLUMNS_MAHLZEITEN, EinkaufsDBHelper.COLUMN_ID + "=" + mahlzeitId, null, null, null, null);

        cursor.moveToFirst();

        Mahlzeit mahlzeit = cursorToMahlzeit(cursor);

        cursor.close();

        return mahlzeit;
    }

    public Rezept getRezept(long rezeptId){
        Cursor cursor = database.query(EinkaufsDBHelper.TABLE_REZEPTE, EinkaufsDBHelper.TABLE_COLUMNS_REZEPTE, EinkaufsDBHelper.COLUMN_ID + "=" + rezeptId, null, null, null, null);

        cursor.moveToFirst();
        Rezept rezept = cursorToRezept(cursor);
        cursor.close();
        return rezept;
    }

    public Zutat getZutat(long zutatId){
        Cursor cursor = database.query(EinkaufsDBHelper.TABLE_ZUTATEN, EinkaufsDBHelper.TABLE_COLUMNS_ZUTATEN, EinkaufsDBHelper.COLUMN_ID + "=" + zutatId, null, null, null, null);

        cursor.moveToFirst();

        Zutat zutat = cursorToZutat(cursor);

        cursor.close();

        return zutat;
    }

    public void updateZutat(Zutat z){
        ContentValues values = new ContentValues();
        values.put(EinkaufsDBHelper.COLUMN_NAME, z.getName());
        values.put(EinkaufsDBHelper.COLUMN_MENGE, z.getMenge());
        values.put(EinkaufsDBHelper.COLUMN_EINHEIT, z.getEinheit());

        database.update(EinkaufsDBHelper.TABLE_ZUTATEN, values, EinkaufsDBHelper.COLUMN_ID + "=" + z.getId(), null);
    }

    public void updateRezept(Rezept r){
        ContentValues values = new ContentValues();
        values.put(EinkaufsDBHelper.COLUMN_NAME, r.getName());

        database.update(EinkaufsDBHelper.TABLE_REZEPTE, values, EinkaufsDBHelper.COLUMN_ID + "=" + r.getId(), null);
    }
}