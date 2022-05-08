package com.example.nanasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class my_note_student extends AppCompatActivity {



    private EditText c_avarage,t_avarage,c_subjects,a_subjects;
    private TextView textLabel;
    private Button clearBtn;

    private Float cur_ava, tar_ava;
    private int cur_sub, ava_sub;

    private Button cal_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_my_note_student);

        c_avarage = findViewById(R.id.c_avarage);
        t_avarage = findViewById(R.id.t_avarage);
        c_subjects = findViewById(R.id.c_subjects);
        a_subjects = findViewById(R.id.a_subjects);

        textLabel = findViewById(R.id.textLabel);
        System.out.println(c_avarage);


        cal_btn = findViewById(R.id.calculate_btn);

        cal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()) {
                    generateMarks();
                }
            }
        });

        clearBtn = findViewById(R.id.clearBtn);

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c_avarage.setText("");
                t_avarage.setText("");
                c_subjects.setText("");
                a_subjects.setText("");
            }
        });





    }



    private void generateMarks(){
        cur_ava = Float.parseFloat("" + c_avarage.getText().toString().trim());
        tar_ava = Float.parseFloat("" + t_avarage.getText().toString().trim());
        cur_sub = Integer.parseInt("" + c_subjects.getText().toString().trim());
        ava_sub = Integer.parseInt("" + a_subjects.getText().toString().trim());

        float currentMarks = cur_ava * cur_sub;
        float expectedMarks = tar_ava * (ava_sub + cur_sub);

        float marksDiff = expectedMarks - currentMarks;
        float marksForSubject = marksDiff / ava_sub;

        if(marksForSubject < 100){
            //Toast.makeText(MainActivity.this, "Marks for Subject : " + String.valueOf(marksForSubject), Toast.LENGTH_SHORT).show();
            textLabel.setText("You should get average : " + String.valueOf(marksForSubject));
        }else{
            //Toast.makeText(MainActivity.this, "You cannot acheive this avarage", Toast.LENGTH_SHORT).show();
            textLabel.setText("You cannot acheive this avarage");
        }


        System.out.println("Marks for subject : " + String.valueOf(marksForSubject));
    }

    private boolean validateFields(){
        if(c_avarage.length() == 0){
            c_avarage.setError("This field is required");
            return false;
        }
        if(c_avarage.length() != 0){
            float c_ava = Float.parseFloat("" + c_avarage.getText().toString().trim());
            if(!(0< c_ava && c_ava < 100)){
                c_avarage.setError("Average should be between 0 and 100");
                return false;
            }
        }
        if(t_avarage.length() == 0){
            t_avarage.setError("This field is required");
            return false;
        }
        if(t_avarage.length() != 0){
            float t_ava = Float.parseFloat("" + t_avarage.getText().toString().trim());
            if(!(0< t_ava && t_ava < 100)){
                t_avarage.setError("Average should be between 0 and 100");
                return false;
            }
        }
        if(c_subjects.length() == 0){
            c_subjects.setError("This field is required");
            return false;
        }
        if(a_subjects.length() == 0){
            a_subjects.setError("This field is required");
            return false;
        }
        return true;
    }



    public void clickBack(View view) {
        Intent intentback = new Intent(this, student_home.class);
        startActivity(intentback);
    }
}