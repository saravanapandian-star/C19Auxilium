package com.example.C19.Auxilium;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class mapfirstactivity extends AppCompatActivity {
    private FusedLocationProviderClient fusedLocationClient;
    ListView listView;
    Double lat,lng;
    String[] llat = new String[101];
    String[] llng = new String[101];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapfirstactivity);
        listView=(ListView)findViewById(R.id.lstv);
        listView.setVisibility(View.INVISIBLE);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        add();
    }
    public void add(){
        if (ActivityCompat.checkSelfPermission(mapfirstactivity.this, ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            reqpermission();
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            //Toast.makeText(MainActivity.this,String.valueOf(location),Toast.LENGTH_SHORT).show();
                            //t1.setText("Lat:"+String.valueOf(location.getLatitude()));
                            //t2.setText("Lng:"+String.valueOf(location.getLongitude()));
                            Locale locale = new Locale("en");
                            lat = location.getLatitude();
                            lng = location.getLongitude();
                            Geocoder geocoder = new Geocoder(mapfirstactivity.this, Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                addresses = geocoder.getFromLocation(lat,lng, 1);
                                //String cityName = addresses.get(0).getAddressLine(0);
                                String dst = addresses.get(0).getSubAdminArea();
                                new GetPlaces(mapfirstactivity.this, listView).execute();

                            } catch (IOException e) {
                                //e.printStackTrace();
                            }
                        }
                        else{
                            Toast.makeText(mapfirstactivity.this,"Please Go Back And Refresh",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    private void reqpermission(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
    class GetPlaces extends AsyncTask<Void, Void, Void> {

        private ProgressDialog dialog;
        private Context context;
        private String[] placeName;
        private String[] imageUrl;
        private ListView listView;

        public GetPlaces(Context context, ListView listView) {
            // TODO Auto-generated constructor stub
            this.context = context;
            this.listView = listView;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            dialog.dismiss();

            listView.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_expandable_list_item_1,placeName));
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setCancelable(true);
            dialog.setMessage("Loading..");
            dialog.isIndeterminate();
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            PlacesService service = new PlacesService("AIzaSyDtrJnZMxWGlXHg34U-Q9ggz1bFKZWnTsc");
            List<Place> findPlaces = service.findPlaces(lat,lng,"doctor");  // hospiral for hospital
            // atm for ATM

            placeName = new String[findPlaces.size()];
            imageUrl = new String[findPlaces.size()];

            for (int i = 0; i < findPlaces.size(); i++) {

                Place placeDetail = findPlaces.get(i);
                llat[i] = String.valueOf(placeDetail.getLatitude());
                llng[i] = String.valueOf(placeDetail.getLongitude());
                placeDetail.getIcon();

                //System.out.println(  placeDetail.getName());
                placeName[i] =placeDetail.getName();

                imageUrl[i] =placeDetail.getIcon();

            }
            Intent intent = new Intent(mapfirstactivity.this,MapActivity.class);
            intent.putExtra("latttt",llat);
            intent.putExtra("lngggg",llng);
            intent.putExtra("lnth",findPlaces.size());
            startActivity(intent);
            return null;
        }

    }

}