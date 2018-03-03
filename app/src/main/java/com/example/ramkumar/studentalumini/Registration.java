package com.example.ramkumar.studentalumini;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registration extends AppCompatActivity {

    private EditText studentName, studentId, studentAge, department, batch;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button submit;

    DatabaseReference root,registerUser,index;

    private List<RowItemSelect> rowItemSelect;

    private String studentIdStr, studentNameStr, studentAgeStr, gender, departmentStr, batchStr;

    /*
    RadioButtn Seleted Text
    int selectedId = radioSexGroup.getCheckedRadioButtonId();
    radioSexButton = (RadioButton) findViewById(selectedId);
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        root = FirebaseDatabase.getInstance().getReference();
        registerUser = root.child("Register");
        rowItemSelect=new ArrayList<RowItemSelect>();

        /*  EditText Variables  */
        studentName = (EditText) findViewById(R.id.studentName);
        studentId = (EditText) findViewById(R.id.studentId);
        studentAge = (EditText) findViewById(R.id.studentAge);
        department = (EditText) findViewById(R.id.department);
        batch = (EditText) findViewById(R.id.batch);
        /*  RadioButton Variables   */
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        /*int selectedId = radioSexGroup.getCheckedRadioButtonId();
        radioSexButton = (RadioButton) findViewById(selectedId);*/
        /*  Button Varable  */
        submit = (Button) findViewById(R.id.submit);

        registerUser.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                studentIdStr = (String) dataSnapshot.child("StudentId").getValue();

                RowItemSelect item=new RowItemSelect( studentIdStr, studentNameStr, studentAgeStr, gender, departmentStr, batchStr);
                rowItemSelect.add(item);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                studentIdStr = (String) dataSnapshot.child("StudentId").getValue();

                RowItemSelect item=new RowItemSelect( studentIdStr, studentNameStr, studentAgeStr, gender, departmentStr, batchStr);
                rowItemSelect.add(item);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                studentIdStr = (String) dataSnapshot.child("StudentId").getValue();

                RowItemSelect item=new RowItemSelect( studentIdStr, studentNameStr, studentAgeStr, gender, departmentStr, batchStr);
                rowItemSelect.add(item);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        System.out.println("RowSize " + rowItemSelect.size());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean status=false;

                int selectedId=radioSexGroup.getCheckedRadioButtonId();
                radioSexButton=(RadioButton)findViewById(selectedId);

                studentIdStr = studentId.getText().toString();
                studentNameStr = studentName.getText().toString();
                studentAgeStr = studentAge.getText().toString();
                gender = radioSexButton.getText().toString();
                departmentStr = department.getText().toString();
                batchStr = batch.getText().toString();

                for(int i=0; i<rowItemSelect.size();i++){

                    if (studentIdStr.equals(rowItemSelect.get(i).getStudentIdStr())) {
                        System.out.println(rowItemSelect.get(i).getStudentIdStr());
                        status = true;
                        Toast.makeText(Registration.this, "Already Registered", Toast.LENGTH_LONG).show();

                    }
                }

                if(status == false){
                    index = registerUser.push();
                    Map<String, Object> map1 = new HashMap<String, Object>();

                    map1.put("StudentId", studentIdStr);
                    map1.put("Name", studentNameStr);
                    map1.put("Age", studentAgeStr);
                    map1.put("Gender", gender);
                    map1.put("Department", departmentStr);
                    map1.put("Batch", batchStr);
                    map1.put("indexKey", index.getKey());
                    index.updateChildren(map1);

                    sendNotification("Student Registeration","Student Has Been Registed" );


                    Intent intent = new Intent(Registration.this, MainActivity.class);
                    Toast.makeText(Registration.this, "Registered Sucessfully", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }

            }
        });
    }

    private void sendNotification(String title, String remoteMessage) {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        System.out.println(title + "\n" + remoteMessage);

        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_light)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage))
                .setContentTitle(title)
                .setContentText(remoteMessage)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());

    }
}
