package com.example.ramkumar.studentalumini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchResult extends AppCompatActivity {

    private TextView idS, nameS, ageS, batchS, departmentS;
    private Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        idS= (TextView) findViewById(R.id.studentIdTextView);
        nameS = (TextView) findViewById(R.id.studentNameTextView);
        ageS = (TextView) findViewById(R.id.studentAgeTextView);
        batchS = (TextView) findViewById(R.id.studentBatchTextView);
        departmentS = (TextView) findViewById(R.id.studentDepartTextView);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("Id");
        String name = bundle.getString("Name");
        String age = bundle.getString("Age");
        String batch = bundle.getString("Batch");
        String depart = bundle.getString("Depart");

        idS.setText(id);
        nameS.setText(name);
        ageS.setText(age);
        batchS.setText(batch);
        departmentS.setText(depart);

        home = (Button) findViewById(R.id.home1);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResult.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
