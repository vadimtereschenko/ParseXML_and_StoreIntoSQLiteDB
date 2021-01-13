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

public class IdentifiersActivity extends AppCompatActivity {
    DatabaseHelper db;
    ArrayList<Identifiers> identifiersArrayList = new ArrayList<Identifiers>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identifiers);
        XmlResourceParser parser = getResources().getXml(R.xml.data);
        listView = (ListView)findViewById(R.id.listView);
        db = new DatabaseHelper(this);
        try {
            processData(parser);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        identifiersArrayList = db.getAllIdertifiersRows();
        ArrayAdapter identifiersArrayAdapter = new ArrayAdapter<Identifiers>(IdentifiersActivity.this, android.R.layout.simple_list_item_1, identifiersArrayList);
        listView.setAdapter(identifiersArrayAdapter);
    }

    private void processData(XmlResourceParser parser) throws XmlPullParserException, IOException {
        ArrayList<Identifiers> identifiersArrayList = new ArrayList<Identifiers>();
        int eventType = parser.getEventType();
        Identifiers currentIdentifier = null;
        while (eventType != XmlResourceParser.END_DOCUMENT) {
            String eltName = null;
            if (eventType == XmlPullParser.START_TAG) {
                eltName = parser.getName();
                if (eltName.equals("institution")) {
                    currentIdentifier = new Identifiers();
                    identifiersArrayList.add(currentIdentifier);
                } else if (currentIdentifier != null && eltName.equals("id")) {
                    currentIdentifier.base = parser.getAttributeValue(null, "base");
                    currentIdentifier.identifier = parser.nextText();
                }
            }
            eventType = parser.next();
        }
        long result;
        for (int i = 0; i < identifiersArrayList.size(); i++) {
            result = db.addIdentifiersRow(identifiersArrayList.get(i).identifier, identifiersArrayList.get(i).base);
        }
    }
}