package com.zoomcreativo.rapidogs2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    static private DataBaseManager Manager;


    public static DataBaseManager getManager() {
        return Manager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Manager = new DataBaseManager(this);
        Button btncargar = (Button) findViewById(R.id.bedi);
        btncargar.setOnClickListener(this);
        Button btninsertar = (Button) findViewById(R.id.blug);
        btninsertar.setOnClickListener(this);
        Button btneliminar = (Button) findViewById(R.id.bmap);
        btneliminar.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if (id == R.id.edicion) {
            Edicion fragment = new Edicion();
            fragmentTransaction.replace(android.R.id.content,fragment).commit();
            return true;
        }
        if (id == R.id.lista) {
            FragmentRapidogs fragment = new FragmentRapidogs();
            fragmentTransaction.replace(android.R.id.content,fragment).commit();
            return true;
        }
        if (id == R.id.mapas) {
            Mapas fragment = new Mapas();
            fragmentTransaction.replace(android.R.id.content,fragment).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if (v.getId() == R.id.bedi) {
            Edicion fragment = new Edicion();
            fragmentTransaction.replace(android.R.id.content,fragment).commit();

        }
        if (v.getId() == R.id.blug) {
            FragmentRapidogs fragment = new FragmentRapidogs();
            fragmentTransaction.replace(android.R.id.content,fragment).commit();

        }
        if (v.getId() == R.id.bmap) {
            Mapas fragment = new Mapas();
            fragmentTransaction.replace(android.R.id.content,fragment).commit();

        }
    }
}
