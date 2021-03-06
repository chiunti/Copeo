package com.chiunti.copeo;

/**
 * Created by chiunti on 26/02/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;

public class SingleItemView extends Activity {
    // Declare Variables
    ImageView imgPhoto;
    TextView txtname;
    TextView txtdescription;
    TextView txtlatitude;
    TextView txtlongitude;

    String name;
    String description;
    String latitude;
    String longitude;
    byte[] image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("info","Single Item View");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleitemview);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        // Get the results of name
        name = i.getStringExtra("name");
        // Get the results of description
        description = i.getStringExtra("description");
        // Get the results of latitude
        latitude = String.valueOf(i.getDoubleExtra("latitude",0));
        // Get the results of longitude
        longitude = String.valueOf(i.getDoubleExtra("longitude",0));
        // Get the results of image
        image = i.getByteArrayExtra("image");

        // Locate the TextViews in singleitemview.xml
        txtname        = (TextView) findViewById(R.id.name);
        txtdescription = (TextView) findViewById(R.id.description);
        txtlatitude    = (TextView) findViewById(R.id.latitude);
        txtlongitude   = (TextView) findViewById(R.id.longitude);
        imgPhoto       = (ImageView) findViewById(R.id.imagePhoto);

        // Load the results into the TextViews
        try {
            txtname.setText(name);
            txtdescription.setText(description);
            txtlatitude.setText(latitude);
            txtlongitude.setText(longitude);
            imgPhoto.setImageDrawable(Drawable.createFromStream(new ByteArrayInputStream(image), name));
        } catch (Exception e){
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
    }
}