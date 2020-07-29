package com.example.C19.Auxilium;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Fdays extends AppCompatActivity {
    Button b1;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    DatabaseHelper db;
    ListView l1;
    CardView c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fdays);
        b1=(Button)findViewById(R.id.nextbutton);
        //l1=(ListView)findViewById(R.id.lstvw);
        db=new DatabaseHelper(this);

        c1=(findViewById(R.id.cardView1));
        c2=(findViewById(R.id.cardView2));
        c3=(findViewById(R.id.cardView3));
        c4=(findViewById(R.id.cardView4));
        c5=(findViewById(R.id.cardView5));
        c6=(findViewById(R.id.cardView6));
        c7=(findViewById(R.id.cardView7));
        c8=(findViewById(R.id.cardView8));
        c9=(findViewById(R.id.cardView9));
        c10=(findViewById(R.id.cardView10));
        c11=(findViewById(R.id.cardView11));
        c12=(findViewById(R.id.cardView12));
        c13=(findViewById(R.id.cardView13));
        c14=(findViewById(R.id.cardView14));

        final String[] days = {"Day1","Day2","Day3","Day4","Day5","Day6","Day7","Day8","Day9","Day10","Day11","Day12","Day13","Day14"};
        final String[] tips = {"Drink warm water throughout the day.","Increase the intake of Turmeric, Cumin, Coriander and garlic.","Avoid sugar and replace it with jaggery if needed.","Inhale steam with Mint leaves and Caraway seeds.","Do not overcook vegetables and fruit as this can lead to the loss of important vitamins.","Avoid foods (e.g. snacks) that are high in salt and sugar.","Choose white meat (e.g. poultry) and fish, which are generally low in fat, rather than red meat.","Choose fresh fruits instead of sweet snacks such as cookies, cakes and chocolate.","Limit your daily salt intake to less than 5 g (approximately 1 teaspoon), and use iodized salt.","Eat at home to reduce your rate of contact with other people and lower your chance of being exposed to COVID-19.","then don’t leave your home under any circumstance until your doctor says it’s okay to head out.","Face masks can offer some amount of protection because they can block liquid droplets. But, they don’t block aerosol particles that pass through the mask’s material.","Wet your hands in clean running water, apply a good antiseptic hand wash/soap, and lather your hands thoroughly. Massage the backs of the hands, between your fingers, and under your nails. Ensure that you scrub your hands for twenty seconds at least, and then rinse off the soap.","Keep social distancing."};
        ArrayList array_list = db.getdates();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);
        /*l1.setAdapter(arrayAdapter);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id = arg2;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setTitle(days[id]);
                alertDialog.setMessage(tips[id]);

                alertDialog.show();
            }
        });*/
        if(array_list.size()>=1){
            c1.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.swing_up_left);
            c1.startAnimation(animation);
            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView im = new ImageView(Fdays.this);
                    im.setImageResource(R.drawable.q1);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog.setTitle(days[0]);
                    //alertDialog.setMessage(tips[0]);
                    alertDialog.setView(im);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1080,1080);
                }
            });
        }
        if(array_list.size()>=2){
            c2.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.swing_up_left);
            c2.startAnimation(animation);
            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView im = new ImageView(Fdays.this);
                    im.setImageResource(R.drawable.im2);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog.setTitle(days[1]);
                    //alertDialog.setMessage(tips[1]);
                    alertDialog.setView(im);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1080,1080);
                }
            });
        }
        if(array_list.size()>=3){
            c3.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.swing_up_left);
            c3.startAnimation(animation);
            c3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView im = new ImageView(Fdays.this);
                    im.setImageResource(R.drawable.im3);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog.setTitle(days[2]);
                    //alertDialog.setMessage(tips[2]);
                    alertDialog.setView(im);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1080,1080);
                }
            });
        }
        if(array_list.size()>=4){
            c4.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.swing_up_left);
            c4.startAnimation(animation);
            c4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView im = new ImageView(Fdays.this);
                    im.setImageResource(R.drawable.im4);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog.setTitle(days[3]);
                    //alertDialog.setMessage(tips[3]);
                    alertDialog.setView(im);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1080,1080);
                }
            });
        }
        if(array_list.size()>=5){
            c5.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.swing_up_left);
            c5.startAnimation(animation);
            c5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView im = new ImageView(Fdays.this);
                    im.setImageResource(R.drawable.im5);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog.setTitle(days[4]);
                    //alertDialog.setMessage(tips[4]);
                    alertDialog.setView(im);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1080,1080);
                }
            });
        }
        if(array_list.size()>=6){
            c6.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.swing_up_left);
            c6.startAnimation(animation);
            c6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView im = new ImageView(Fdays.this);
                    im.setImageResource(R.drawable.im6);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog.setTitle(days[5]);
                    //alertDialog.setMessage(tips[5]);
                    alertDialog.setView(im);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1080,1080);
                }
            });
        }
        if(array_list.size()>=7){
            c7.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.swing_up_left);
            c7.startAnimation(animation);
            c7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView im = new ImageView(Fdays.this);
                    im.setImageResource(R.drawable.im7);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog.setTitle(days[6]);
                    //alertDialog.setMessage(tips[6]);
                    alertDialog.setView(im);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1080,1080);
                }
            });
        }
        if(array_list.size()>=8){
            c8.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.swing_up_left);
            c8.startAnimation(animation);
            c8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView im = new ImageView(Fdays.this);
                    im.setImageResource(R.drawable.im8);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog.setTitle(days[7]);
                    //alertDialog.setMessage(tips[7]);
                    alertDialog.setView(im);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1080,1080);
                }
            });
        }
        if(array_list.size()>=9){
            c9.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.swing_up_left);
            c9.startAnimation(animation);
            c9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView im = new ImageView(Fdays.this);
                    im.setImageResource(R.drawable.im9);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog.setTitle(days[8]);
                    //alertDialog.setMessage(tips[8]);
                    alertDialog.setView(im);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1080,1080);
                }
            });
        }
        if(array_list.size()>=10){
            c10.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.swing_up_left);
            c10.startAnimation(animation);
            c10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView im = new ImageView(Fdays.this);
                    im.setImageResource(R.drawable.im10);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog.setTitle(days[9]);
                    //alertDialog.setMessage(tips[9]);
                    alertDialog.setView(im);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1080,1080);
                }
            });
        }
        if(array_list.size()>=11){
            c11.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.swing_up_left);
            c11.startAnimation(animation);
            c11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView im = new ImageView(Fdays.this);
                    im.setImageResource(R.drawable.im11);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog.setTitle(days[10]);
                    //alertDialog.setMessage(tips[10]);
                    alertDialog.setView(im);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1080,1080);
                }
            });
        }
        if(array_list.size()>=12){
            c12.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.swing_up_left);
            c12.startAnimation(animation);
            c12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView im = new ImageView(Fdays.this);
                    im.setImageResource(R.drawable.im12);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog.setTitle(days[11]);
                    //alertDialog.setMessage(tips[11]);
                    alertDialog.setView(im);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1080,1080);
                }
            });
        }
        if(array_list.size()>=13){
            c13.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.swing_up_left);
            c13.startAnimation(animation);
            c13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView im = new ImageView(Fdays.this);
                    im.setImageResource(R.drawable.im13);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog.setTitle(days[12]);
                    //alertDialog.setMessage(tips[12]);
                    alertDialog.setView(im);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1080,1080);
                }
            });
        }
        if(array_list.size()>=14){
            c14.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.swing_up_left);
            c14.startAnimation(animation);
            c14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView im = new ImageView(Fdays.this);
                    im.setImageResource(R.drawable.im14);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fdays.this);
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    //alertDialog.setTitle(days[13]);
                    //alertDialog.setMessage(tips[13]);
                    alertDialog.setView(im);
                    alertDialog.show();
                    alertDialog.getWindow().setLayout(1080,1080);
                }
            });
        }
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(calendar.getTime());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkdate = db.checkdate(date);
                if(checkdate==true) {
                    Intent intent = new Intent(Fdays.this,Qentry.class);
                    intent.putExtra("tdate",date);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Fdays.this,"You Started The Day",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Fdays.this,Factivity.class);
        startActivity(intent);
        finish();
    }
}