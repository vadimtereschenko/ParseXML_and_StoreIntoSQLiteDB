package com.example.laboratory_work_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class InstitutionActivity extends AppCompatActivity {
    DatabaseHelper db;
    ArrayList<Institution> institutionArrayList = new ArrayList<Institution>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institution);
        listView = (ListView)findViewById(R.id.listView);
        db = new DatabaseHelper(this);
        XmlResourceParser parser = getResources().getXml(R.xml.data);
        try {
            processData(parser);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        institutionArrayList = db.getAllInstitutionRows();
        ArrayAdapter tableArrayAdapter = new ArrayAdapter<Institution>(InstitutionActivity.this, android.R.layout.simple_list_item_1, institutionArrayList);
        listView.setAdapter(tableArrayAdapter);
    }

    public void processData(XmlResourceParser parser) throws XmlPullParserException, IOException {
        ArrayList<Institution> institutionArrayList = new ArrayList<Institution>();
        int eventType = parser.getEventType();
        Institution currentInstitution = null;
        while (eventType != XmlResourceParser.END_DOCUMENT) {
            String eltName = null;
            if (eventType == XmlPullParser.START_TAG) {
                eltName = parser.getName();
                if (eltName.equals("institution")) {
                    currentInstitution = new Institution();
                    institutionArrayList.add(currentInstitution);
                    currentInstitution.key = parser.getAttributeValue(null, "key");
                } else if (currentInstitution != null) {
                    if (eltName.equals("url")) {
                        currentInstitution.url = parser.nextText();
                    }
                }
            }
            eventType = parser.next();
        }
        long result;
        for (int i = 0; i < institutionArrayList.size(); i++) {
            result = db.addInstitutionRow(institutionArrayList.get(i).url, institutionArrayList.get(i).key);
        }
    }
}