package com.example.C19.Auxilium;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Mydistrict extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FusedLocationProviderClient fusedLocationClient;
    CardView c1,c2,c3;
    TextView t1,t2,t3,t4;
    Button button;
    Spinner spinner;
    double lat;
    double lng;
    String[] all_district= new String[1001];
    String[] all_cases= new String[1001];
    String[] all_affcases = new String[1001];
    String chn="";
    String thiru="";
    String mad="";
    String other="";
    int l=0;
    String ncases=null;
    String userdistrict = "";
    //String URL="http://www.student.cs.uwaterloo.ca/~ayiu/revmenu.html";
    String URL = "http://www.oneindia.com/coronavirus-affected-cities-districts-in-india.html/";
    //String url = "http://jsoup.org/apidocs/org/jsoup/Connection.html";
    TextView tvR, tvPython, tvCPP, tvJava;
    PieChart pieChart;
    int flag=0;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydistrict);
        t1=(TextView)findViewById(R.id.lat);
        t2=(TextView)findViewById(R.id.lon);
        t3=(TextView)findViewById(R.id.yourdistrict);
        t4=(TextView)findViewById(R.id.cases);
        c1=(findViewById(R.id.cardViewGr));
        c2=(findViewById(R.id.cardViewG));
        c3=(findViewById(R.id.cardViewGraph));
        c1.setVisibility(View.GONE);
        c2.setVisibility(View.INVISIBLE);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        List<String> categories = new ArrayList<String>();
        categories.add("Dindigul");
        categories.add("Chennai");
        categories.add("Ariyalur");
        categories.add("Coimbatore");
        categories.add("Cuddalore");
        categories.add("Dharmapuri");
        categories.add("Erode");
        categories.add("Kallakurichi");
        categories.add("Kanchipuram");
        categories.add("Kanniyakumari");
        categories.add("Karur");
        categories.add("Krishnagiri");
        categories.add("Madurai");
        categories.add("Nagapattinam");
        categories.add("Namakkal");
        categories.add("Nilgiris");
        categories.add("Perambalur");
        categories.add("Pudukkottai");
        categories.add("Ramanathapuram");
        categories.add("Salem");
        categories.add("Sivaganga");
        categories.add("Thanjavur");
        categories.add("Theni");
        categories.add("Thoothukudi");
        categories.add("Tiruchirappalli");
        categories.add("Tirunelveli");
        categories.add("Tiruppur");
        categories.add("Tiruvallur");
        categories.add("Tiruvannamalai");
        categories.add("Tiruvarur");
        categories.add("Vellore");
        categories.add("Viluppuram");
        categories.add("Virudhunagar");
        tvR = findViewById(R.id.tvR);
        tvPython = findViewById(R.id.tvPython);
        tvCPP = findViewById(R.id.tvCPP);
        tvJava = findViewById(R.id.tvJava);
        pieChart = findViewById(R.id.piechart);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        add();
        getWebsite();
        //setData();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item

        for (int i=0;i<l;i++)
        {
            if(all_district[i].equals(item))
            {
                AlertDialog.Builder dialog=new AlertDialog.Builder(this);
                dialog.setMessage("Total cases:  "+all_cases[i]);
                dialog.setTitle("Covid cases");
                dialog.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {

                            }
                        });

                AlertDialog alertDialog=dialog.create();
                alertDialog.show();
            }
        }

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void add() {
        if (ActivityCompat.checkSelfPermission(Mydistrict.this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
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
        if(ActivityCompat.checkSelfPermission(Mydistrict.this, Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
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
                            t1.setText("Lat:"+String.valueOf(location.getLatitude()));
                            t2.setText("Lng:"+String.valueOf(location.getLongitude()));
                            Locale locale = new Locale("en");
                            lat = location.getLatitude();
                             lng = location.getLongitude();
                            Geocoder geocoder = new Geocoder(Mydistrict.this, Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                addresses = geocoder.getFromLocation(lat,lng, 1);
                                //String cityName = addresses.get(0).getAddressLine(0);
                                userdistrict = addresses.get(0).getSubAdminArea();
                                String dst = addresses.get(0).getSubAdminArea();
                                String countryName = addresses.get(0).getAddressLine(2);
                                t3.setText("District:"+dst);
                            } catch (IOException e) {
                                //e.printStackTrace();
                            }
                        }
                        else{
                            Toast.makeText(Mydistrict.this,"Please Go Back And Refresh",Toast.LENGTH_SHORT).show();
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
    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    Document doc = Jsoup.connect("https://www.oneindia.com/coronavirus-affected-cities-districts-in-india.html").get();
                    String title = doc.title();

                    Element table = doc.select("table").get(0);
                    Elements rows = table.select("tr");
                    builder.append("The number of cases in ").append("\n");


                    for (int i = 1; i < rows.size()-1; i++) {
                        String district="";
                        String cases="";
                        String st1=rows.get(i).getElementsByTag("td").get(0).toString();
                        String st2=rows.get(i).getElementsByTag("td").get(1).toString();
                        int j=0;
                        char[] iyoo = st1.toCharArray();
                        char[] ohoo = st2.toCharArray();
                        for(j=0;j<iyoo.length;)
                        {
                            if(iyoo[j]=='<'){
                                while(iyoo[j]!='>'){
                                    j++;
                                }
                                j++;
                            }
                            else{
                                district+=String.valueOf(iyoo[j]);
                                j++;
                            }
                        }
                        all_district[l]=district;
                        for(j=0;j<ohoo.length;)
                        {
                            if(ohoo[j]=='<'){
                                while(ohoo[j]!='>'){
                                    j++;
                                }
                                j++;
                            }
                            else{
                                cases+=String.valueOf(ohoo[j]);
                                j++;
                            }
                        }
                        char[] cc = cases.toCharArray();
                        String onlycases="";
                        for(int k=0;k<cc.length;k++)
                        {
                            if(cc[k]!=' '){
                                onlycases+=cc[k];
                            }
                            else{
                                break;
                            }
                        }
                        all_cases[l]=cases;
                        all_affcases[l]=onlycases;
                        l++;

                        if(district.equals(userdistrict)){
                            ncases=cases;
                            builder.append(userdistrict+":");
                            builder.append(cases);}
                        if(district.equals("Tamil Nadu")){
                            tvR.setText(onlycases);
                        }
                        if(district.equals("Maharashtra")){
                            tvPython.setText(onlycases);
                        }
                        if(district.equals("Delhi")){
                            tvCPP.setText(onlycases);
                        }
                        if(district.equals("Karnataka")){
                            tvJava.setText(onlycases);
                        }
                    }
                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        t4.setText(builder.toString());
                        c2.setVisibility(View.VISIBLE);
                        Animation animation = AnimationUtils.loadAnimation(Mydistrict.this,R.anim.swing_up_left);
                        c2.startAnimation(animation);
                        setData();
                    }
                });
            }
        }).start();
    }
    private void setData()
    {
        String k1,k2,k3,k4;
        k1 = tvR.getText().toString();
        k2 = tvPython.getText().toString();
        k3 = tvCPP.getText().toString();
        k4 = tvJava.getText().toString();
        char k11[] = k1.toCharArray();
        char k12[] = k2.toCharArray();
        char k13[] = k3.toCharArray();
        char k14[] = k4.toCharArray();
        String aa = "";
        String ss = "";
        String dd = "";
        String ff = "";
        for(int m=0;m<k11.length;m++){
            if(k11[m]!=','){
                aa+=k11[m];
            }
        }
        for(int m=0;m<k12.length;m++){
            if(k12[m]!=','){
                ss+=k12[m];
            }
        }
        for(int m=0;m<k13.length;m++){
            if(k13[m]!=','){
                dd+=k13[m];
            }
        }
        for(int m=0;m<k14.length;m++){
            if(k14[m]!=','){
                ff+=k14[m];
            }
        }

        pieChart.addPieSlice(
                new PieModel(
                        "Tamilnadu",
                        Integer.parseInt(aa),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Maharastra",
                        Integer.parseInt(ss),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Delhi",
                        Integer.parseInt(dd),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Karnataka",
                        Integer.parseInt(ff),
                        Color.parseColor("#29B6F6")));

        pieChart.startAnimation();
    }
}
