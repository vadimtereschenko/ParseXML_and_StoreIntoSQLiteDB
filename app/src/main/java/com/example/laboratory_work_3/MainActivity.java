package com.example.laboratory_work_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnGoToInstitution, btnGoToName, btnGoToLocation, btnGoToIdentifiers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoToInstitution = (Button)findViewById(R.id.btnGoToInstitution);
        btnGoToName = (Button)findViewById(R.id.btnGoToName);
        btnGoToLocation = (Button)findViewById(R.id.btnGoToLocation);
        btnGoToIdentifiers = (Button)findViewById(R.id.btnGoToIdentifiers);

        btnGoToInstitution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InstitutionActivity.class);
                startActivity(intent);
            }
        });

        btnGoToName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NameActivity.class);
                startActivity(intent);
            }
        });

        btnGoToLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });

        btnGoToIdentifiers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IdentifiersActivity.class);
                startActivity(intent);
            }
        });
    }
}