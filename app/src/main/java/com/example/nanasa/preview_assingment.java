package com.example.nanasa;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class preview_assingment extends AppCompatActivity {

    private ImageView aImageView;
    private TextView aNumberEt, aSubjectEt, aDeadLinEd, aDescriptionEt, aAddTimeEt, aUpdateTimeEt;
    Button saveInfo;
    ImageButton pri_image;


    private String id, number, subject, deadLine, description, addTimeStamp, updateTimeStamp, image, timeStamp;
    private DatabaseHelperMKASG dbHelper;

    private Uri imageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_preview_assingment);


        aImageView = findViewById(R.id.assignmentImage);
        aNumberEt = findViewById(R.id.assignemtNo);
        aSubjectEt = findViewById(R.id.assignmentSubject);
        aDeadLinEd = findViewById(R.id.assignemtDeadLine);
        aDescriptionEt = findViewById(R.id.assignemtDescription);
        aAddTimeEt = findViewById(R.id.add_date_time);
        aUpdateTimeEt = findViewById(R.id.update_date);

        pri_image = findViewById(R.id.pre_T_btn);

        saveInfo = findViewById(R.id.save_btn);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        subject = intent.getStringExtra("SUBJECT");
        number = intent.getStringExtra("NUMBER");
        deadLine = intent.getStringExtra("DEADLINE");
        description = intent.getStringExtra("DESCRIPTION");
        imageUri = Uri.parse(intent.getStringExtra("IMAGE"));
        addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
        updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

        aNumberEt.setText(number);
        aSubjectEt.setText(subject);
        aDeadLinEd.setText(deadLine);
        aDescriptionEt.setText(description);
        aImageView.setImageURI(imageUri);
        aAddTimeEt.setText(addTimeStamp);
        aUpdateTimeEt.setText(updateTimeStamp);



        pri_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent priImage = new Intent(preview_assingment.this, Pri_image.class);
                priImage.putExtra("IMAGE_pri", imageUri.toString());
                startActivity(priImage);
            }
        });

    }
    public void clickBack(View view) {
        Intent intentback = new Intent(this, makeAssingment.class);
        startActivity(intentback);
    }
}