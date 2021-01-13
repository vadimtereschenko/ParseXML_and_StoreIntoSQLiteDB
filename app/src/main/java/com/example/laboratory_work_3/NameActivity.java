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

public class NameActivity extends AppCompatActivity {
    DatabaseHelper db;
    ArrayList<Name> nameArrayList = new ArrayList<Name>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        XmlResourceParser parser = getResources().getXml(R.xml.data);
        listView = (ListView)findViewById(R.id.listView);
        db = new DatabaseHelper(this);
        try {
            processData(parser);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        nameArrayList = db.getAllNameRows();
        ArrayAdapter nameArrayAdapter = new ArrayAdapter<Name>(NameActivity.this, android.R.layout.simple_list_item_1, nameArrayList);
        listView.setAdapter(nameArrayAdapter);
    }

    private void processData(XmlResourceParser parser) throws XmlPullParserException, IOException {
        ArrayList<Name> nameArrayList = new ArrayList<Name>();
        int eventType = parser.getEventType();
        Name currentName = null;
        while (eventType != XmlResourceParser.END_DOCUMENT) {
            String eltName = null;
            if (eventType == XmlPullParser.START_TAG) {
                eltName = parser.getName();
                if (eltName.equals("institution")) {
                    currentName = new Name();
                    nameArrayList.add(currentName);
                } else if (currentName != null && eltName.equals("name")) {
                    currentName.label = parser.getAttributeValue(null, "label");
                    currentName.type = parser.getAttributeValue(null, "type");
                    currentName.name = parser.nextText();
                }
            }
            eventType = parser.next();
        }
        long result;
        for (int i = 0; i < nameArrayList.size(); i++) {
            result = db.addNameRow(nameArrayList.get(i).name, nameArrayList.get(i).label, nameArrayList.get(i).type);
        }
    }
}