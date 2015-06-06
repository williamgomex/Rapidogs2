package com.zoomcreativo.rapidogs2;

import android.app.Activity;
import android.content.DialogInterface;
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
import android.widget.Toast;


public class Edicion extends Fragment {
    private DataBaseManager Manager;
    private Cursor cursor;
    View RootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RootView = inflater.inflate(R.layout.fragment_edicion, container, false);
        Manager = MainActivity.getManager();

        final Button bins = (Button) RootView.findViewById(R.id.btninsertar);


        bins.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText nombre = (EditText) RootView.findViewById(R.id.EdNombre);
                EditText latitud = (EditText) RootView.findViewById(R.id.EdLatitud);
                EditText longitud = (EditText) RootView.findViewById(R.id.EdLongitud);
                Manager.insertar(nombre.getText().toString(),latitud.getText().toString(),longitud.getText().toString());
                nombre.setText("");
                latitud.setText("");
                longitud.setText("");
                Toast.makeText( RootView.getContext().getApplicationContext(), "Insertado", Toast.LENGTH_SHORT).show();
            }
        });

        final Button beli = (Button) RootView.findViewById(R.id.btneliminar);
        beli.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText nombre = (EditText) RootView.findViewById(R.id.EdNombre);
                Manager.eliminar(nombre.getText().toString());
                Toast.makeText(RootView.getContext().getApplicationContext(),"Eliminado", Toast.LENGTH_SHORT).show();
                nombre.setText("");
            }
        });

        final Button bactua = (Button) RootView.findViewById(R.id.btnactualizar);
        bactua.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText nombre = (EditText) RootView.findViewById(R.id.EdNombre);
                EditText latitud = (EditText) RootView.findViewById(R.id.EdLatitud);
                EditText longitud = (EditText) RootView.findViewById(R.id.EdLongitud);
                Manager.ModificarTelefono(nombre.getText().toString(),latitud.getText().toString(),longitud.getText().toString());
                Toast.makeText(RootView.getContext().getApplicationContext(),"Actualizado", Toast.LENGTH_SHORT).show();
                nombre.setText("");
                latitud.setText("");
                longitud.setText("");
            }
        });



        return RootView;
    }




}
