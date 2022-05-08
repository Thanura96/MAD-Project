package com.example.nanasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class makeAssingment extends AppCompatActivity {

    FloatingActionButton fab;

    RecyclerView mRecyclerView;
    DatabaseHelperMKASG databaseHelper;
    private static String teacherEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_make_assingment);


        mRecyclerView = findViewById(R.id.recylceView);
        databaseHelper = new DatabaseHelperMKASG(this);
        //db inisialize

        teacherEmail = getIntent().getStringExtra("emailT");

        showRercord();

        fab = findViewById(R.id.addFabButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create a new activity
                //startActivity(new Intent(MainActivity.this, AddRecordActivity.class));

                Intent intent = new Intent(makeAssingment.this, Add_assignment_form_teacher.class);
                intent.putExtra("EditMode", false);
                intent.putExtra("ViewMode", false);
                startActivity(intent);
            }
        });


    }

    private void showRercord() {

        Adapter_mkasg adapter = new Adapter_mkasg(makeAssingment.this, databaseHelper.getAllData(ConstantsMKASG.A_ADD_TIMESTAMP + " DESC"));
        //because last add record is show on top
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRercord();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == event.KEYCODE_BACK){
            moveTaskToBack(true);
        }

        return super.onKeyDown(keyCode, event);
    }


    public void clickBack(View view) {
        Intent intentback = new Intent(this, main_activity.class);
        intentback.putExtra("emailT", teacherEmail);
        startActivity(intentback);
    }
}