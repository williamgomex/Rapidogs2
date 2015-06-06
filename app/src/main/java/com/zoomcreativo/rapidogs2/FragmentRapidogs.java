package com.zoomcreativo.rapidogs2;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class FragmentRapidogs extends Fragment {


    private DataBaseManager Manager;
    private ListView lista;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private EditText etext;
    private Button btext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment_rapidogs, container, false);
        Manager = MainActivity.getManager();
        lista = (ListView) rootView.findViewById(android.R.id.list);

        String[] from = new String[]{Manager.CN_NAME,Manager.CN_LAT,Manager.CN_LONG};
        int[] to = new int[]{R.id.text1,R.id.text2,R.id.text3};
        cursor = Manager.cargarCursorContactos();
        adapter = new SimpleCursorAdapter(rootView.getContext(),R.layout.three_texts,cursor,from,to,0);
        lista.setAdapter(adapter);

        etext = (EditText) rootView.findViewById(R.id.okedit);
        final Button button = (Button) rootView.findViewById(R.id.okbutton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new BuscarTask().execute();
            }
        });
        return rootView;
    }

    public class BuscarTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            Toast.makeText(getActivity().getApplicationContext(), "Buscando...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            /*Toast.makeText(RootView.getContext().getApplicationContext(),"Finalizado...",Toast.LENGTH_SHORT).show();
            adapter.changeCursor(cursor);
            obtener();*/
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
