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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

public class Delete extends AppCompatActivity {

    private EditText deleteId;
    //private MaterialBetterSpinner deleteIdA;
    private Button delete;

    DatabaseReference root,registerUser,index;

    private List<RowItemSelect> rowItemSelect;

    private String[] studentIdList = null;
    private String studentIdStr, studentNameStr, studentAgeStr, gender, departmentStr, batchStr, indexKey;
    private String deleteIdStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        root = FirebaseDatabase.getInstance().getReference();
        registerUser = root.child("Register");
        rowItemSelect=new ArrayList<RowItemSelect>();

        deleteId = (EditText) findViewById(R.id.deleteId);
        //deleteIdA = (MaterialBetterSpinner) findViewById(R.id.deleteIdA);
        delete = (Button) findViewById(R.id.delete);

        registerUser.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                studentIdStr = (String) dataSnapshot.child("StudentId").getValue();
                studentNameStr = (String) dataSnapshot.child("Name").getValue();
                studentAgeStr = (String) dataSnapshot.child("Age").getValue();
                gender = (String) dataSnapshot.child("Gender").getValue();
                departmentStr = (String) dataSnapshot.child("Department").getValue();
                batchStr = (String) dataSnapshot.child("Batch").getValue();
                indexKey = (String) dataSnapshot.child("indexKey").getValue();

                System.out.println("StudentId " + studentIdStr + "Name" + studentNameStr + "indexKey" +indexKey);

                RowItemSelect item = new RowItemSelect(studentIdStr, studentNameStr, studentAgeStr, gender, departmentStr, batchStr, indexKey);
                rowItemSelect.add(item);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                studentIdStr = (String) dataSnapshot.child("StudentId").getValue();
                studentNameStr = (String) dataSnapshot.child("Name").getValue();
                studentAgeStr = (String) dataSnapshot.child("Age").getValue();
                gender = (String) dataSnapshot.child("Gender").getValue();
                departmentStr = (String) dataSnapshot.child("Department").getValue();
                batchStr = (String) dataSnapshot.child("Batch").getValue();
                indexKey = (String) dataSnapshot.child("indexKey").getValue();

                System.out.println("StudentId " + studentIdStr + "Name" + studentNameStr + "indexKey" +indexKey);

                RowItemSelect item = new RowItemSelect(studentIdStr, studentNameStr, studentAgeStr, gender, departmentStr, batchStr, indexKey);
                rowItemSelect.add(item);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                studentIdStr = (String) dataSnapshot.child("StudentId").getValue();
                studentNameStr = (String) dataSnapshot.child("Name").getValue();
                studentAgeStr = (String) dataSnapshot.child("Age").getValue();
                gender = (String) dataSnapshot.child("Gender").getValue();
                departmentStr = (String) dataSnapshot.child("Department").getValue();
                batchStr = (String) dataSnapshot.child("Batch").getValue();
                indexKey = (String) dataSnapshot.child("indexKey").getValue();

                System.out.println("StudentId " + studentIdStr + "Name" + studentNameStr + "indexKey" +indexKey);

                RowItemSelect item = new RowItemSelect(studentIdStr, studentNameStr, studentAgeStr, gender, departmentStr, batchStr, indexKey);
                rowItemSelect.add(item);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*System.out.println("RowItemSize " + rowItemSelect.size());
                for(int i=0; i<rowItemSelect.size(); i++){
                    studentIdList[i] = rowItemSelect.get(i).getStudentIdStr();
                }
                System.out.println(studentIdList);*/
                //loadSpinnerData();

                deleteIdStr = deleteId.getText().toString();
                Boolean status = false;

                try{

                    for(int i=0; i<rowItemSelect.size(); i++){

                        if(deleteIdStr.equals(rowItemSelect.get(i).getStudentIdStr())){
                            status = true;
                            final String key = rowItemSelect.get(i).getIndexKey();
                            Query pendingTask = registerUser.orderByChild("indexKey").equalTo(key);

                            System.out.println("deleteIdStr" + deleteIdStr + " rowItemSelect.get(i).getStudentIdStr() " +rowItemSelect.get(i).getStudentIdStr() );
                            System.out.println("key " + key);
                            pendingTask.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot deleteSnapshot: dataSnapshot.getChildren()) {
                                        deleteSnapshot.getRef().removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                            Intent intent = new Intent(Delete.this, MainActivity.class);
                            Toast.makeText(Delete.this, "Deleted Successfully...", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                        }

                    }

                }catch (Exception e){
                    e.printStackTrace();
                }

                if(status == false){

                    Toast.makeText(Delete.this, "StudentId is not present", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void loadSpinnerData() {
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, studentIdList);
        //deleteIdA.setAdapter(unitAdapter);

    }
}
