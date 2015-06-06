package com.zoomcreativo.rapidogs2;

import android.app.Activity;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Mapas extends Fragment implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private DataBaseManager Manager;
    private Cursor cursor;
    String[] datosna;
    String[] datosla;
    String[] datoslo;
    private GoogleMap map;
    private CameraUpdate cameraUpdate;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;
    private LatLng currentLocation;

    private Boolean newLocationReady = false;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_mapas, container, false);
        Button ubiboton = (Button) RootView.findViewById(R.id.ubica);
        ubiboton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(newLocationReady){
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentLocation, 16);
                    map.animateCamera(cameraUpdate);
                }
            }
        });

        Manager = MainActivity.getManager();
        map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        datafromdatabase();

        for(int i=0;i<cursor.getCount();i++){
            map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(datosla[i]),Double.parseDouble(datoslo[i]))).title(datosna[i]).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        }


        buildGoogleApiClient();
        createLocationRequest();



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

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mLastLocation != null){
            setNewLocation(mLastLocation);
            newLocationReady = true;
        }
        else{
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest, (com.google.android.gms.location.LocationListener) this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        setNewLocation(location);
        newLocationReady = true;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void setNewLocation(Location location){
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        currentLocation = new LatLng(latitude,longitude);
        map.addMarker(new MarkerOptions().position(currentLocation).title("Find me here!!").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
    }
}
