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
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Edit_teacher_record_book extends AppCompatActivity {

    private Button tr_save_btn;
    private Pattern pattern;
    private Matcher matcher;

    private static final String DATE_PATTERN =
            "(0?[1-9]|1[012]) [/.-] (0?[1-9]|[12][0-9]|3[01]) [/.-] ((19|20)\\d\\d)";


    private EditText tr_date, tr_subject, tr_titel, tr_outcomes, tr_donePoints, tr_exceptedPoint;
    private boolean editMode = false;

    private String id, date, subject, titel, outComes, donePoints, exceptionPoints, addTimes, updateTimes, timeStamp;
    private DatabaseHelperTRBnSRB dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_edit_teacher_record_book);


        //initiate database object in main funtion
        dbHelper = new DatabaseHelperTRBnSRB(this);

        tr_date = findViewById(R.id.tr_date);
        tr_subject = findViewById(R.id.tr_subject);
        tr_titel = findViewById(R.id.tr_titel);
        tr_outcomes = findViewById(R.id.tr_outcome);
        tr_donePoints = findViewById(R.id.tr_done_points);
        tr_exceptedPoint = findViewById(R.id.tr_expected_pont);

        tr_save_btn = findViewById(R.id.tr_updatet_btn);

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("EditMode", editMode);
        id = intent.getStringExtra("ID");
        subject = intent.getStringExtra("SUBJECT");
        date = intent.getStringExtra("DATE");
        titel = intent.getStringExtra("TITEL");
        outComes = intent.getStringExtra("OUTCOMES");
        donePoints = intent.getStringExtra("DONE_POINT");
        exceptionPoints = intent.getStringExtra("EXCEPTION_POINT");
        addTimes = intent.getStringExtra("ADD_TIMESTAMP");
        updateTimes = intent.getStringExtra("UPDATE_TIMESTAMP");


        if(editMode){

            editMode = intent.getBooleanExtra("EditMode", editMode);
            id = intent.getStringExtra("ID");
            subject = intent.getStringExtra("SUBJECT");
            date = intent.getStringExtra("DATE");
            titel = intent.getStringExtra("TITEL");
            outComes = intent.getStringExtra("OUTCOMES");
            donePoints = intent.getStringExtra("DONE_POINT");
            exceptionPoints = intent.getStringExtra("EXCEPTION_POINT");
            addTimes = intent.getStringExtra("ADD_TIMESTAMP");
            updateTimes = intent.getStringExtra("UPDATE_TIMESTAMP");

            tr_date.setText(date);
            tr_subject.setText(subject);
            tr_titel.setText(titel);
            tr_outcomes.setText(outComes);
            tr_donePoints.setText(donePoints);
            tr_exceptedPoint.setText(exceptionPoints);
        }




        tr_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData()){
                getData();
                startActivity(new Intent(Edit_teacher_record_book.this, TeacherRecordBook.class));
                Toast.makeText(Edit_teacher_record_book.this, "Add Successfull", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateData(){
        if(tr_date.length() == 0){
            tr_date.setError("This field is required");
            return false;
        }
        if(tr_date.length() != 0 ){
            String date = "" + tr_date.getText().toString().trim();

            String day = date.split("/")[0];
            String month = date.split("/")[1];
            int year = Integer.parseInt(date.split("/")[2]);

            if((0 < Integer.parseInt(day) && Integer.parseInt(day) < 32) && (0 < Integer.parseInt(month) && Integer.parseInt(month) < 13) && (Calendar.getInstance().get(Calendar.YEAR) <= year ) ) {
                if (day.equals("31") &&
                        (month.equals("4") || month.equals("6") || month.equals("9") ||
                                month.equals("11") || month.equals("04") || month.equals("06") ||
                                month.equals("09"))) {
                    tr_date.setError("Choose a month that has 31 days");
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                } else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if (year % 4 == 0) {
                        if (day.equals("30") || day.equals("31")) {
                            tr_date.setError("Date format is Invalid");
                            return false;
                        } else {
                            tr_date.setError("Date format is Invalid");
                            return true;
                        }
                    } else {
                        if (day.equals("29") || day.equals("30") || day.equals("31")) {
                            tr_date.setError("Date format is Invalid");
                            return false;
                        } else {
                            tr_date.setError("Date format is Invalid");
                            return true;
                        }
                    }
                }
            }else {
                tr_date.setError("Date format is Invalid");
                return false;
            }
        }

        if(tr_subject.length() == 0){
            tr_subject.setError("This field is required");
            return false;
        }

        if(tr_titel.length() == 0){
            tr_titel.setError("This field is required");
            return false;
        }

        if(tr_outcomes.length() == 0){
            tr_outcomes.setError("This field is required");
            return false;
        }
        System.out.println("" + tr_donePoints.getText().toString().trim());
        if(tr_donePoints.length() == 0){
            tr_donePoints.setError("This field is required");
            return false;
        }
        String donePoints = "" + tr_donePoints.getText().toString().trim();
        if(tr_donePoints.length() != 0){
            if(!(0 < Integer.parseInt(donePoints) && Integer.parseInt(donePoints) < 11)){
                tr_donePoints.setError("Value should be between 0 and 10");
                return false;
            }
        }
        if(tr_exceptedPoint.length() == 0){
            tr_exceptedPoint.setError("This field is required");
            return false;
        }

        String expPoints = "" + tr_exceptedPoint.getText().toString().trim();
        if(tr_exceptedPoint.length() != 0){
            if(!(0 < Integer.parseInt(expPoints) && Integer.parseInt(expPoints) < 11)){
                tr_exceptedPoint.setError("Value should be between 0 and 10");
                return false;
            }
        }

        return true;
    }

    private void getData() {
        date = "" + tr_date.getText().toString().trim();
        subject = "" + tr_subject.getText().toString().trim();
        titel = "" + tr_titel.getText().toString().trim();
        outComes = "" + tr_outcomes.getText().toString().trim();
        donePoints = "" + tr_donePoints.getText().toString().trim();
        exceptionPoints = "" + tr_exceptedPoint.getText().toString().trim();
        timeStamp = "" + getDateTime();

        if(editMode){

            dbHelper.updateInfo(

                    "" + id,
                    "" + date,
                    "" + subject,
                    "" + titel,
                    "" + outComes,
                    "" + donePoints,
                    "" + exceptionPoints,
                    "" + addTimes ,
                    "" + updateTimes

            );

        }else {
            dbHelper.insertInfo_TRB(
                    "" + date,
                    "" + subject,
                    "" + titel,
                    "" + outComes,
                    "" + donePoints,
                    "" + exceptionPoints,
                    "" + addTimes,
                    "" + updateTimes
            );
        }

    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void clickBack(View view) {
        Intent intentback = new Intent(this, TeacherRecordBook.class);
        startActivity(intentback);
    }
}