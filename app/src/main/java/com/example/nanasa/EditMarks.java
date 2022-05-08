package com.example.nanasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class EditMarks extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    Button button1;
    Button button2;
    private DatabaseHelperMKASG dbHelper;
    String id;
    String assignment;
    String studentid;
    String name;
    String subject;
    String marks;
    String comment;

    private boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_edit_marks);

        editText1=(EditText)findViewById(R.id.assignmentNoo);
        editText2=(EditText)findViewById(R.id.studentIDo);
        editText3=(EditText)findViewById(R.id.Fnameo);
        editText4=(EditText)findViewById(R.id.subjecto);
        editText5=(EditText)findViewById(R.id.markso);
        editText6=(EditText)findViewById(R.id.commento);
        button1=(Button) findViewById(R.id.submito);
        button2=(Button)findViewById(R.id.cancelo);
        dbHelper = new DatabaseHelperMKASG(EditMarks.this);

        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("EditMode", editMode);
        id = intent.getStringExtra("ID");
        assignment = intent.getStringExtra("assignment");
        studentid = intent.getStringExtra("studentID");
        name = intent.getStringExtra("name");
        subject = intent.getStringExtra("subject");
        marks = intent.getStringExtra("mark");
        comment = intent.getStringExtra("comment");

        if(editMode){
            editMode = intent.getBooleanExtra("EditMode", editMode);
            id = intent.getStringExtra("ID");
            assignment = intent.getStringExtra("assignment");
            studentid = intent.getStringExtra("studentID");
            name = intent.getStringExtra("name");
            subject = intent.getStringExtra("subject");
            marks = intent.getStringExtra("mark");
            comment = intent.getStringExtra("comment");

            editText1.setText(assignment);
            editText2.setText(studentid);
            editText3.setText(name);
            editText4.setText(subject);
            editText5.setText(marks);
            editText6.setText(comment);

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getData();
                    startActivity(new Intent(EditMarks.this, addMark.class));
                    Toast.makeText(EditMarks.this, "Update Successfull", Toast.LENGTH_SHORT).show();
                }

            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), addMark.class);
                    startActivity(intent);
                }

            });

        }
    }

    private void getData() {
        assignment = "" + editText1.getText().toString().trim();
        studentid = "" + editText2.getText().toString().trim();
        name = "" + editText3.getText().toString().trim();
        subject = "" + editText4.getText().toString().trim();
        marks = "" + editText5.getText().toString().trim();
        comment = "" + editText6.getText().toString().trim();


        if(editMode){

            if(assignment.isEmpty()){
                editText1.setError("Assignment ID is required");
            }else if(studentid.isEmpty()){
                editText2.setError("Student ID is required");
            }else if(name.isEmpty()){
                editText3.setError("Name ID is required");
            }else if(subject.isEmpty()){
                editText4.setError("Subject is required");
            }else if(marks.isEmpty()){
                editText5.setError("Marks is required");
            }else if(comment.isEmpty()){
                editText6.setError("Comment is required");
            }else {

                dbHelper.updateInfo_Table_5(
                        "" + id,
                        "" + assignment,
                        "" + studentid,
                        "" + name,
                        "" + subject,
                        "" + marks,
                        "" + comment);
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), addMark.class);
        startActivity(intent);
    }
}