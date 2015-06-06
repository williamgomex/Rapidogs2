package com.zoomcreativo.rapidogs2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity  {
    static private DataBaseManager Manager;


    public static DataBaseManager getManager() {
        return Manager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Manager = new DataBaseManager(this);



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








}
