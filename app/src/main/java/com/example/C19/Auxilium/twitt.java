package com.example.C19.Auxilium;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class twitt extends AppCompatActivity {

    ListView l1;
    private Context context;
    int flag=0;
    //TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitt);
        l1 = (findViewById(R.id.okok));
        new MyTask().execute();
    }
    public class MyTask extends AsyncTask<Void, Void, Void> {
        String result1;
        ArrayList<String> list = new ArrayList<String>();
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                ConfigurationBuilder builder = new ConfigurationBuilder();
                builder.setOAuthConsumerKey("Jex8oeBGBGHNCWrDa0WAtcqgO");
                builder.setOAuthConsumerSecret("GDTTZZZrtt4dti0d6OsAUcbOxUeJQRU0ROVEqA8pjGMecmMx7R");
                builder.setOAuthAccessToken("820884692656328704-84X9X6dOK940JfUl9YtgxF5Or6ohwPy");
                builder.setOAuthAccessTokenSecret("XoLB57n3O4scGxPRAQklCmbyOM4kx17m2j5InU4nOnh7E");
                Configuration configuration = builder.build();
                TwitterFactory factory = new TwitterFactory(configuration);
                Twitter twitter = factory.getInstance();
                Paging page = new Paging();
                page.setCount(50);
                List<twitter4j.Status> statuses = new ArrayList<twitter4j.Status>();
                statuses = twitter.getUserTimeline("Vijayabaskarofl", page);

                for (twitter4j.Status status : statuses) {
                    result1+=status.getText()+"\n\n";
                    list.add(status.getText());
                    flag=1;
                }
            } catch (TwitterException e){
                e.printStackTrace();
                result1 = e.toString();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            MyCustomActivity adapter = new MyCustomActivity(list, twitt.this);
            l1.setAdapter(adapter);
            if(flag==0){
                Toast.makeText(twitt.this,"Turn On Your Data",Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(aVoid);
        }
    }
}
