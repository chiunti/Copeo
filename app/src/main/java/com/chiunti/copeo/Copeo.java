package com.chiunti.copeo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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

import java.util.ArrayList;
import java.util.List;

import android.widget.ListView;


public class Copeo extends ActionBarActivity {

    // Declare Variables
    ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapter adapter;
    private List<lugares> lugaresList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.activity_copeo);
        // Execute RemoteDataTask AsyncTask
        new RemoteDataTask().execute();


        /*
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        //Parse.initialize(this, "h9CbWMKOVPCR0QklWX6pfanGFx0NWpmfUwdAZbhb", "UjfbJ5NZ87IMrvR3NW5Gipg2ohu5ul6dIOgPFWXf");
        Parse.initialize(this, "8h9rGBxp2yelNGDjr6fiemfVoP1HcbRIV3Zer81u", "B2oDwKIJsAL42kuUURK0v2qURpa2EmTPh3qKHDnU");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copeo);

        ListViewAdapter adapter;


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
        */

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


    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Copeo.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Parse.com Custom ListView Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            lugaresList = new ArrayList<lugares>();
            try {
                // Locate the class table named "Country" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("lugares");
                // Locate the column named "ranknum" in Parse.com and order list
                // by ascending
                // query.orderByAscending("ranknum");
                ob = query.find();
                for (ParseObject lugar : ob) {
                    lugares map = new lugares();
                    map.setName((String) lugar.get("name"));
                    map.setDescription((String) lugar.get("description"));
                    map.setLatitude ( Double.toString( ((ParseGeoPoint)lugar.get("position")).getLatitude() )  );
                    map.setLongitude( Double.toString( ((ParseGeoPoint)lugar.get("position")).getLongitude())  );

                    lugaresList.add(map);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listView);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(Copeo.this,
                    lugaresList);
            // Binds the Adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }


}
