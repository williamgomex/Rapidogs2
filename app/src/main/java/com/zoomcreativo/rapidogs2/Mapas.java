package com.zoomcreativo.rapidogs2;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Mapas extends Fragment {
    private DataBaseManager Manager;
    private Cursor cursor;
    String[] datosna;
    String[] datosla;
    String[] datoslo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_mapas, container, false);
        return RootView;
    }

    public void datafromdatabase(){
        cursor = Manager.cargarCursorContactos();
        datosla = new String[cursor.getCount()];
        datoslo = new String[cursor.getCount()];
        datosna = new String[cursor.getCount()];
        int k=0;
        cursor.moveToFirst();
        do {
            datosna[k]=cursor.getString(cursor.getColumnIndex(Manager.CN_NAME));
            datosla[k]=cursor.getString(cursor.getColumnIndex(Manager.CN_LAT));
            datoslo[k]=cursor.getString(cursor.getColumnIndex(Manager.CN_LONG));
            k++;
        }while(cursor.moveToNext());
    }

}
