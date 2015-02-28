package com.chiunti.copeo;

/**
 * Created by chiunti on 26/02/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SingleItemView extends Activity {
    // Declare Variables
    TextView txtname;
    TextView txtdescription;
    TextView txtlatitude;
    TextView txtlongitude;
    String name;
    String description;
    String latitude;
    String longitude;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleitemview);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        // Get the results of name
        name = i.getStringExtra("name");
        // Get the results of description
        description = i.getStringExtra("description");
        // Get the results of latitude
        latitude = i.getStringExtra("latitude");
        // Get the results of longitude
        longitude = i.getStringExtra("longitude");

        // Locate the TextViews in singleitemview.xml
        txtname        = (TextView) findViewById(R.id.name);
        txtdescription = (TextView) findViewById(R.id.description);
        txtlatitude    = (TextView) findViewById(R.id.latitude);
        txtlongitude   = (TextView) findViewById(R.id.longitude);

        // Load the results into the TextViews
        txtname.setText(name);
        txtdescription.setText(description);
        txtlatitude.setText(latitude);
        txtlongitude.setText(longitude);
    }
}