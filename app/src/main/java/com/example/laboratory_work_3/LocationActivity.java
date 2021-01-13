package com.example.laboratory_work_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity {
    DatabaseHelper db;
    ArrayList<Location> locationArrayList = new ArrayList<Location>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        XmlResourceParser parser = getResources().getXml(R.xml.data);
        listView = (ListView)findViewById(R.id.listView);
        db = new DatabaseHelper(this);
        try {
            processData(parser);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        locationArrayList = db.getAllLocationRows();
        ArrayAdapter locationArrayAdapter = new ArrayAdapter<Location>(LocationActivity.this, android.R.layout.simple_list_item_1, locationArrayList);
        listView.setAdapter(locationArrayAdapter);
    }

    private void processData(XmlResourceParser parser) throws XmlPullParserException, IOException {
        ArrayList<Location> locationArrayList = new ArrayList<Location>();
        int eventType = parser.getEventType();
        Location currentLocation = null;
        while (eventType != XmlResourceParser.END_DOCUMENT) {
            String eltName = null;
            if (eventType == XmlPullParser.START_TAG) {
                eltName = parser.getName();
                if (eltName.equals("institution")) {
                    currentLocation = new Location();
                    locationArrayList.add(currentLocation);
                } else if (currentLocation != null && eltName.equals("location")) {
                    currentLocation.country = parser.getAttributeValue(null, "country");
                    currentLocation.state = parser.getAttributeValue(null, "state");
                    currentLocation.city = parser.getAttributeValue(null, "city");
                    currentLocation.latitude = parser.getAttributeFloatValue(null, "lat", 0);
                    currentLocation.longitude = parser.getAttributeFloatValue(null, "lon", 0);
                    currentLocation.location = parser.nextText();
                }
            }
            eventType = parser.next();
        }
        long result;
        for (int i = 0; i < locationArrayList.size(); i++) {
            result = db.addLocationRow(locationArrayList.get(i).location, locationArrayList.get(i).country, locationArrayList.get(i).state, locationArrayList.get(i).city, locationArrayList.get(i).latitude, locationArrayList.get(i).longitude);
        }
    }
}