package com.chiunti.copeo;

/**
 * Created by chiunti on 26/02/15.
 */

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<lugares> lugareslist = null;
    private ArrayList<lugares> arraylist;

    public ListViewAdapter(Context context,
                           List<lugares> lugareslist) {
        mContext = context;
        this.lugareslist = lugareslist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<lugares>();
        this.arraylist.addAll(lugareslist);
    }

    public class ViewHolder {
        TextView name;
        TextView description;
        TextView latitude;
        TextView longitude;
        ImageView photo;
    }

    @Override
    public int getCount() {
        return lugareslist.size();
    }

    @Override
    public lugares getItem(int position) {
        return lugareslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.description = (TextView) view.findViewById(R.id.description);
            holder.latitude = (TextView) view.findViewById(R.id.latitude);
            holder.longitude = (TextView) view.findViewById(R.id.longitude);
            holder.photo = (ImageView) view.findViewById(R.id.imagePhoto);


            //holder.population = (TextView) view.findViewById(R.id.population);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(lugareslist.get(position).getName());
        holder.description.setText(lugareslist.get(position).getDescription());
//        holder.latitude.setText(lugareslist.get(position).getLatitude());
//        holder.longitude.setText(lugareslist.get(position).getLongitude());
        holder.photo.setImageDrawable(Drawable.createFromStream(new ByteArrayInputStream(lugareslist.get(position).getImage()), lugareslist.get(position).getName()));
        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SingleItemView.class);
                // Pass all data name
                intent.putExtra("name",(lugareslist.get(position).getName()));
                // Pass all data description
                intent.putExtra("description",(lugareslist.get(position).getDescription()));
                // Pass all data latitude
                intent.putExtra("latitude",(lugareslist.get(position).getLatitude()));
                // Pass all data longitude
                intent.putExtra("longitude",(lugareslist.get(position).getLongitude()));

                // Pass all data imagen
                intent.putExtra("image",(lugareslist.get(position).getImage()));

                Log.i("info", "onClick antes del start activity");
                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });

        return view;
    }
}