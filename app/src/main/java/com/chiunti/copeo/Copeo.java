package com.chiunti.copeo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class Copeo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        //Parse.initialize(this, "h9CbWMKOVPCR0QklWX6pfanGFx0NWpmfUwdAZbhb", "UjfbJ5NZ87IMrvR3NW5Gipg2ohu5ul6dIOgPFWXf");
        Parse.initialize(this, "8h9rGBxp2yelNGDjr6fiemfVoP1HcbRIV3Zer81u", "B2oDwKIJsAL42kuUURK0v2qURpa2EmTPh3qKHDnU");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copeo);



        /*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("bar");
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                if (e == null) {

                    for(int i = 0; i <= parseObjects.size(); i++){
                        ParseObject obj = (ParseObject) parseObjects.get(i);

                        Log.i("DEBUG ", i + " : " + obj.get("name") + " descripcion : " + obj.get("description") + " Lat/Long : " + obj.get("latitude") + obj.get("longitude"));
                    }

                } else {
                    Log.d("DEBUG", "Error: " + e.getMessage());
                }
            }
        });
        */

        ParseQuery<ParseObject> query = ParseQuery.getQuery("lugares");
        //query.whereEqualTo("playerName", "Dan Stemkoski");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                    //for(int i = 0; i < scoreList.size(); i++){
                    for (ParseObject obj:scoreList){
                        //ParseObject obj = (ParseObject) scoreList.get(i);

                        Log.i("DEBUG ",  "Lugar : " + obj.get("name") +
                                         " descripcion : " + obj.get("description") +
                                         " Lat : " + ((ParseGeoPoint)obj.get("position")).getLatitude() +
                                         " Long : " + ((ParseGeoPoint)obj.get("position")).getLongitude() );
                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_copeo, menu);
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
}
