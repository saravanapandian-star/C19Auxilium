package com.example.C19.Auxilium;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;


public class safeornot extends FragmentActivity implements OnMapReadyCallback,
        LocationListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private GoogleMap mMap;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    JSONObject object;
    String docurl;
    StringBuilder content = new StringBuilder();
    //String lati[];
    //String longi[];
    public static String APIID = "CA315D2F-0910-348B-FF46-5B575E5A2200";
    public static String APIKEY = "1B5E709E-90E9-4F00-A32E-726F316BFDFB";
    int len;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safeornot);
        Backendless.initApp(this,APIID,APIKEY);
        //Intent intent = getIntent();
        //lati = intent.getStringArrayExtra("latttt");
        //longi = intent.getStringArrayExtra("lngggg");
        //len = intent.getIntExtra("lnth",0);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //List<Map> result = Backendless.Data.of( "Contact" ).find();
        //String ss = String.valueOf(result.get(0));
        //Toast.makeText(this,ss,Toast.LENGTH_SHORT).show();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mCurrLocationMarker = mMap.addMarker(markerOptions);
        searchthepat();
        /*for(int j=0;j<len;j++){
            LatLng lg = new LatLng(Double.parseDouble(lati[j]),Double.parseDouble(longi[j]));
            MarkerOptions markerOptionslg = new MarkerOptions();
            markerOptionslg.position(lg);
            mMap.addMarker(markerOptionslg);
        }*/
        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        //Toast.makeText(MapActivity.this,String.valueOf(len),Toast.LENGTH_SHORT).show();
        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(safeornot.this,Factivity.class);
        startActivity(intent);
        finish();
    }

    public void searchthepat(){
        Backendless.Data.of( "Contact" ).find( new AsyncCallback<List<Map>>(){
            @Override
            public void handleResponse( List<Map> foundContacts )
            {
                // every loaded object from the "Contact" table is now an individual java.util.Map
                Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.fnearpatient);
                Bitmap smallmarker = Bitmap.createScaledBitmap(b,100,100,false);
                BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallmarker);
                for(Map lst: foundContacts){
                    String ss = String.valueOf(lst.get("latt"));
                    LatLng lg = new LatLng(Double.parseDouble(lst.get("latt").toString()),Double.parseDouble(lst.get("longg").toString()));
                    MarkerOptions markerOptionslg = new MarkerOptions();
                    markerOptionslg.position(lg);
                    markerOptionslg.icon(smallMarkerIcon);
                    mMap.addMarker(markerOptionslg);
                    //Toast.makeText(safeornot.this,ss,Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        });
    }
}