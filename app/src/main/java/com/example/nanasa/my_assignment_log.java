package com.example.nanasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class my_assignment_log extends AppCompatActivity {

    RecyclerView mRecyclerView;
    DatabaseHelperMKASG databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_my_assignment_log);

        mRecyclerView = findViewById(R.id.recylceView_s2);
        databaseHelper = new DatabaseHelperMKASG(this);


        showRercord();

    }

    private void showRercord() {

        Adapter_mkasg_s2 adapter = new Adapter_mkasg_s2(my_assignment_log.this, databaseHelper.getAllDataTable2(ConstantsMKASG.S_ADD_DATEnTIME + " DESC"));
        //because last add record is show on top
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        showRercord();
    }





    public void clickBack(View view) {
        Intent intentback = new Intent(this, student_home.class);
        startActivity(intentback);
    }

}