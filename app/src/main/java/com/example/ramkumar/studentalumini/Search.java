package com.example.ramkumar.studentalumini;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

public class Search extends AppCompatActivity {

    private EditText searchId;
    private Button search;

    DatabaseReference root,registerUser,index;

    private List<RowItemSelect> rowItemSelect;

    private String[] sStudentIdList = null;
    private String sStudentIdStr, sStudentNameStr, sStudentAgeStr, sGender, sDepartmentStr, sBatchStr, sIndexKey;
    private String searchIdStr;
    private String searchDummy = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        root = FirebaseDatabase.getInstance().getReference();
        registerUser = root.child("Register");
        rowItemSelect=new ArrayList<RowItemSelect>();

        searchId = (EditText) findViewById(R.id.searchIdEdit);
        search = (Button) findViewById(R.id.search);

        registerUser.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                sStudentIdStr = (String) dataSnapshot.child("StudentId").getValue();
                sStudentNameStr = (String) dataSnapshot.child("Name").getValue();
                sStudentAgeStr = (String) dataSnapshot.child("Age").getValue();
                sGender = (String) dataSnapshot.child("Gender").getValue();
                sDepartmentStr = (String) dataSnapshot.child("Department").getValue();
                sBatchStr = (String) dataSnapshot.child("Batch").getValue();
                sIndexKey = (String) dataSnapshot.child("indexKey").getValue();

                System.out.println("StudentId " + sStudentIdStr + "Name" + sStudentNameStr + "indexKey" +sIndexKey);

                RowItemSelect item = new RowItemSelect(sStudentIdStr, sStudentNameStr, sStudentAgeStr, sGender, sDepartmentStr, sBatchStr, sIndexKey, searchDummy);
                rowItemSelect.add(item);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        for(int i=0; i<rowItemSelect.size(); i++){
            sStudentIdList[i] = rowItemSelect.get(i).getsStudentIdStr();
        }
        //loadSpinnerData();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sStudentIdStr = searchId.getText().toString();
                Boolean status = false;

                for(int i=0; i<rowItemSelect.size(); i++){
                    if(sStudentIdStr.equals(rowItemSelect.get(i).getsStudentIdStr())){
                        Intent intent = new Intent(Search.this, SearchResult.class);
                        Toast.makeText(Search.this, "Name "+ rowItemSelect.get(i).getsStudentNameStr(), Toast.LENGTH_LONG).show();
                        intent.putExtra("Id", rowItemSelect.get(i).getsStudentIdStr());
                        intent.putExtra("Name", rowItemSelect.get(i).getsStudentNameStr());
                        intent.putExtra("Age", rowItemSelect.get(i).getsStudentAgeStr());
                        intent.putExtra("Batch", rowItemSelect.get(i).getsBatchStr());
                        intent.putExtra("Depart", rowItemSelect.get(i).getsDepartmentStr());
                        startActivity(intent);
                        finish();
                    }
                }


            }
        });

    }

    private void loadSpinnerData() {
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, sStudentIdList);
        //selectBatch.setAdapter(unitAdapter);

    }
}
