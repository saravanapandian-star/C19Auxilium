package com.example.C19.Auxilium;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Qentry extends AppCompatActivity {
    DatabaseHelper db;
    TextView t1,t2,t3;
    Button b1;
    int num=0;
    double lat;
    double lng;
    public static String APIID = "CA315D2F-0910-348B-FF46-5B575E5A2200";
    public static String APIKEY = "1B5E709E-90E9-4F00-A32E-726F316BFDFB";
    private FusedLocationProviderClient fusedLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qentry);
        Backendless.initApp(this,APIID,APIKEY);
        t1=(TextView)findViewById(R.id.tdate);
        t2=(TextView)findViewById(R.id.dcount);
        t3=(TextView)findViewById(R.id.tip);
        b1=(Button)findViewById(R.id.fightbutton);
        Intent intent = getIntent();
        final String date = intent.getStringExtra("tdate");
        db=new DatabaseHelper(this);
        String[] days = {"Day1","Day2","Day3","Day4","Day5","Day6","Day7","Day8","Day9","Day10","Day11","Day12","Day13","Day14"};
        String[] tips = {"Drink warm water throughout the day.","Increase the intake of Turmeric, Cumin, Coriander and garlic.","Avoid sugar and replace it with jaggery if needed.","Inhale steam with Mint leaves and Caraway seeds.","Do not overcook vegetables and fruit as this can lead to the loss of important vitamins.","Avoid foods (e.g. snacks) that are high in salt and sugar.","Choose white meat (e.g. poultry) and fish, which are generally low in fat, rather than red meat.","Choose fresh fruits instead of sweet snacks such as cookies, cakes and chocolate.","Limit your daily salt intake to less than 5 g (approximately 1 teaspoon), and use iodized salt.","Eat at home to reduce your rate of contact with other people and lower your chance of being exposed to COVID-19.","then don’t leave your home under any circumstance until your doctor says it’s okay to head out.","Face masks can offer some amount of protection because they can block liquid droplets. But, they don’t block aerosol particles that pass through the mask’s material.","Wet your hands in clean running water, apply a good antiseptic hand wash/soap, and lather your hands thoroughly. Massage the backs of the hands, between your fingers, and under your nails. Ensure that you scrub your hands for twenty seconds at least, and then rinse off the soap.","Keep social distancing."};
        t1.setText(date);
        Cursor cursor = db.numofdays();
        while (cursor.moveToNext()) {
            num++;
        }
        t2.setText(days[num]);
        t3.setText(tips[num]);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                add();
            }
        }).start();
        //add();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean insert = db.insert(date);
                if(insert==true){
                    if(num==0)
                    {
                        add();
                    }
                    Toast.makeText(Qentry.this,"You Will Recover Soon",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(Qentry.this,Fdays.class);
                    startActivity(intent1);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Qentry.this,Fdays.class);
        startActivity(intent);
    }

    public void saveNewContact()
    {
        HashMap contact = new HashMap();
        contact.put( "latt", String.valueOf(lat) );
        contact.put( "longg", String.valueOf(lng) );

        // save object synchronously
        //Map savedContact = Backendless.Data.of( "Contact" ).save( contact );

        // save object asynchronously
        Backendless.Data.of( "Contact" ).save( contact, new AsyncCallback<Map>() {
            public void handleResponse( Map response )
            {
                //Toast.makeText(Qentry.this,"Data Added",Toast.LENGTH_SHORT).show();
            }

            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        });
    }

    public void add() {
        if (ActivityCompat.checkSelfPermission(Qentry.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
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
        if(ActivityCompat.checkSelfPermission(Qentry.this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            req1();
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            //Toast.makeText(MainActivity.this,String.valueOf(location),Toast.LENGTH_SHORT).show();
                            Locale locale = new Locale("en");
                            lat = location.getLatitude();
                            lng = location.getLongitude();
                            Geocoder geocoder = new Geocoder(Qentry.this, Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                addresses = geocoder.getFromLocation(lat,lng, 1);
                                saveNewContact();
                            } catch (IOException e) {
                                //e.printStackTrace();
                            }
                        }
                        else{
                            Toast.makeText(Qentry.this,"why",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void req1() {
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_COARSE_LOCATION},1);
    }

    private void reqpermission(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
}