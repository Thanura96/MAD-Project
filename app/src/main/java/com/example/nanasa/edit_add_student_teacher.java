package com.example.nanasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class edit_add_student_teacher extends AppCompatActivity {

    private DatabaseHelperMKASG dbHelper;
    private static String teacherEmail;

    EditText pSRid, pSRemail, pSRfname, pSRlname, pSRgrade;
    private static String sid, email, t_email, fname,lname,grade,addTimeStamp,updateTimeStamp,fkId;
    Button updateBtn;
    private boolean editMode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_edit_add_student_teacher);

        //db inisialize
        dbHelper = new DatabaseHelperMKASG(this);

        teacherEmail = getIntent().getStringExtra("emailT");

        pSRemail = findViewById(R.id.sr_email);
        pSRid = findViewById(R.id.sr_id);
        pSRfname = findViewById(R.id.sr_fName);
        pSRlname = findViewById(R.id.sr_lName);
        pSRgrade = findViewById(R.id.sr_grad);
        updateBtn = findViewById(R.id.s_updateBtn);

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("EditMode", editMode);
        sid = intent.getStringExtra("SR_ID");
        fkId = intent.getStringExtra("TFK_ID");
        email = intent.getStringExtra("SR_EMAIL");
        fname = intent.getStringExtra("SR_F_NAME");
        lname = intent.getStringExtra("SR_L_NAME");
        grade = intent.getStringExtra("SR_GRADE");
        addTimeStamp = intent.getStringExtra("SR_ADD_TIMESTAMP");
        updateTimeStamp = intent.getStringExtra("SR_UPDATE_TIMESTAMP");

        if(editMode){
            editMode = intent.getBooleanExtra("EditMode", editMode);
            sid = intent.getStringExtra("SR_ID");
            fkId = intent.getStringExtra("TFK_ID");
            email = intent.getStringExtra("SR_EMAIL");
            fname = intent.getStringExtra("SR_F_NAME");
            lname = intent.getStringExtra("SR_L_NAME");
            grade = intent.getStringExtra("SR_GRADE");
            addTimeStamp = intent.getStringExtra("SR_ADD_TIMESTAMP");
            updateTimeStamp = intent.getStringExtra("SR_UPDATE_TIMESTAMP");

            pSRemail.setText(email);
            pSRid.setText(sid);
            pSRfname.setText(fname);
            pSRlname.setText(lname);
            pSRgrade.setText(grade);

        }






        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //click the save button insert data to db
                getData();
                //dbHelper.getAllDataTable4(t_email);

            }
        });

    }



    private void getData() {


        email = "" + pSRemail.getText().toString().trim();
        sid = "" + pSRid.getText().toString().trim();
        t_email = teacherEmail;
        grade = "" + pSRgrade.getText().toString().trim();
        fname = "" + pSRfname.getText().toString().trim();
        lname = "" + pSRlname.getText().toString().trim();

        String test = " ";
        System.out.println("te__"+teacherEmail);

        if(editMode){

            if (email.isEmpty() || sid.isEmpty()) {
                Toast.makeText(this, "Please fill all the information", Toast.LENGTH_SHORT).show();
            }
            else{

                dbHelper.updateInfoTable_4_techer(

                        "" + sid,
                        "" + fkId,
                        "" + test,
                        "" + fname,
                        "" + lname,
                        "" + grade,
                        "" + test,
                        "" + test,
                        "" + test,
                        "" + email,
                        "" + email,
                        "" + getDateTime(),
                        "" + getDateTime()

                );

                Intent intent = new Intent(edit_add_student_teacher.this, studentRegisterPage.class);
                intent.putExtra("emailT", fkId);
                startActivity(intent);
                Toast.makeText(edit_add_student_teacher.this, "Update Successfull", Toast.LENGTH_SHORT).show();
            }


        }else{

            if (email.isEmpty() || sid.isEmpty()) {
                Toast.makeText(this, "Please fill all the information", Toast.LENGTH_SHORT).show();
            }
            else{

                dbHelper.insertInfoTable_4_from_teacher(

                        "" + sid,
                        "" + t_email,
                        "" + test,
                        "" + fname,
                        "" + lname,
                        "" + grade,
                        "" + test,
                        "" + test,
                        "" + test,
                        "" + email,
                        "" + email,
                        "" + addTimeStamp,
                        "" + updateTimeStamp

                );

                Intent intent = new Intent(edit_add_student_teacher.this, studentRegisterPage.class);
                intent.putExtra("emailT", fkId);
                startActivity(intent);
                Toast.makeText(edit_add_student_teacher.this, "Update Successfull", Toast.LENGTH_SHORT).show();
            }
        }


    }



    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }





    public void clickBack(View view) {
        Intent intentback = new Intent(this, add_student_form.class);
        intentback.putExtra("emailT", teacherEmail);
        startActivity(intentback);
    }
}