package com.example.nanasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MarksSummaryActivity extends AppCompatActivity {

    DatabaseHelperMKASG databaseHelper;

    ArrayList<Model_TM> marksOriginal;
    teacherReportAdapter adapter;
    RecyclerView mRecyclerView;
    TextView tot, avg;
    int totalMarks;
    double averageMarks;
    int totalSubjs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_marks_summary);

        databaseHelper = new DatabaseHelperMKASG(MarksSummaryActivity.this);
        marksOriginal = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.studentmarks);
        tot = (TextView) findViewById(R.id.total);
        avg = (TextView) findViewById(R.id.avg);

        Intent from = getIntent();
        if (from != null) {
            String studentId = from.getStringExtra("studentId");

            try {
                marksOriginal = databaseHelper.getAStudentRecord(studentId);
                adapter = new teacherReportAdapter(MarksSummaryActivity.this, marksOriginal);
                mRecyclerView.setAdapter(adapter);

                for (Model_TM mark : marksOriginal) {
                    // totalMarks += Integer.parseInt(mark.marks);
                    totalMarks=addMarks(totalMarks,Integer.parseInt(mark.marks));
                    totalSubjs++;
                }

                System.out.println("totalMarks"+totalMarks);
                System.out.println("totalSubjs"+totalSubjs);

                tot.setText(String.valueOf(totalMarks));

                //find average
                averageMarks = multiplicationMarks(totalMarks ,totalSubjs)  / 100.0;
                avg.setText(String.valueOf(averageMarks).concat("%"));

            } catch (Exception d) {
//                System.out.println("selectQuery"+d.getMessage());
                Toast.makeText(this, d.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void clickBack(View view) {
        Intent intentback = new Intent(this, addMark.class);
        startActivity(intentback);
    }



    protected int addMarks(int x, int y){return x + y;}
    protected int multiplicationMarks(int x, int y){return x * y;}

}