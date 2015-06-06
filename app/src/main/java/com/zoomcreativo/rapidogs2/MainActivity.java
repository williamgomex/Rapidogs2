package com.zoomcreativo.rapidogs2;

import android.app.Activity;
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


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private DataBaseManager Manager;
    private ListView lista;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private EditText etext;
    private Button btext;
    String[] datosna;
    String[] datosla;
    String[] datoslo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Liga de botones a evento de la actividad
        etext = (EditText) findViewById(R.id.okedit);
        btext = (Button) findViewById(R.id.okbutton);
        btext.setOnClickListener(this);
        Button btncargar = (Button) findViewById(R.id.btndb);
        btncargar.setOnClickListener(this);
        Button btninsertar = (Button) findViewById(R.id.btninsertar);
        btninsertar.setOnClickListener(this);
        Button btneliminar = (Button) findViewById(R.id.btneliminar);
        btneliminar.setOnClickListener(this);
        Button btnactualizar = (Button) findViewById(R.id.btnactualizar);
        btnactualizar.setOnClickListener(this);

        Manager = new DataBaseManager(this);

        String[] from = new String[]{Manager.CN_NAME,Manager.CN_LAT,Manager.CN_LONG};
        int[] to = new int[]{R.id.text1,R.id.text2,R.id.text3};
        datafromdatabase();
        lista = (ListView) findViewById(android.R.id.list);
        cursor = Manager.cargarCursorContactos();
        adapter = new SimpleCursorAdapter(this,R.layout.three_texts,cursor,from,to,0);
        lista.setAdapter(adapter);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if( v.getId() == R.id.okbutton){
            new BuscarTask().execute();
        }
        if(v.getId()==R.id.btndb){ // BUSCAR

            lista = (ListView) findViewById(android.R.id.list);
            etext = (EditText) findViewById(R.id.okedit);

            String[] from = new String[]{Manager.CN_NAME,Manager.CN_LAT,Manager.CN_LONG};
            int[] to = new int[]{R.id.text1,R.id.text2,R.id.text3};

            cursor = Manager.cargarCursorContactos();

            adapter = new SimpleCursorAdapter(this,R.layout.three_texts,cursor,from,to,0);
            lista.setAdapter(adapter);

        }
        if (v.getId()==R.id.btninsertar){
            EditText nombre = (EditText) findViewById(R.id.EdNombre);
            EditText latitud = (EditText) findViewById(R.id.EdLatitud);
            EditText longitud = (EditText) findViewById(R.id.EdLongitud);
            Manager.insertar(nombre.getText().toString(),latitud.getText().toString(),longitud.getText().toString());
            nombre.setText("");
            latitud.setText("");
            longitud.setText("");
            Toast.makeText(getApplicationContext(), "Insertado", Toast.LENGTH_SHORT).show();
        }
        if(v.getId()==R.id.btneliminar){
            EditText nombre = (EditText) findViewById(R.id.EdNombre);
            Manager.eliminar(nombre.getText().toString());
            Toast.makeText(getApplicationContext(),"Eliminado", Toast.LENGTH_SHORT).show();
            nombre.setText("");
        }
        if (v.getId()==R.id.btnactualizar){
            EditText nombre = (EditText) findViewById(R.id.EdNombre);
            EditText latitud = (EditText) findViewById(R.id.EdLatitud);
            EditText longitud = (EditText) findViewById(R.id.EdLongitud);
            Manager.ModificarTelefono(nombre.getText().toString(),latitud.getText().toString(),longitud.getText().toString());
            Toast.makeText(getApplicationContext(),"Actualizado", Toast.LENGTH_SHORT).show();
            nombre.setText("");
            latitud.setText("");
            longitud.setText("");
        }
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

    public class BuscarTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(),"Buscando...",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(),"Finalizado...",Toast.LENGTH_SHORT).show();
            adapter.changeCursor(cursor);
            obtener();
        }

        @Override
        protected Void doInBackground(Void... params) {
            cursor = Manager.buscarContacto(etext.getText().toString());
            return null;
        }

        public void obtener () {

/* TextView Txnombre = (TextView) findViewById(R.id.Txnombre);

            TextView Txtelefono = (TextView) findViewById(R.id.Txtelefono);
            try{
                String dbnombre = cursor.getString(cursor.getColumnIndex(Manager.CN_NAME));
                Txnombre.setText(dbnombre);
                String dbtelefono = cursor.getString(cursor.getColumnIndex(Manager.CN_PHONE));
                Txtelefono.setText(dbtelefono);}
            catch(CursorIndexOutOfBoundsException e){
                Txnombre.setText("No Found");
                Txtelefono.setText("No Found");
            }*/
        }
    }


}
