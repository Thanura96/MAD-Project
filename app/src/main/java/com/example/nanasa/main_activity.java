package com.example.nanasa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class main_activity extends AppCompatActivity {


    //initialize variables
    DrawerLayout drawerLayout;
    TextView MKassing;
    private static String teacherEmail;
    TextView proBtn;
    private DatabaseHelperMKASG dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        //proBtn = findViewById(R.id.probtn);

        //assing variable
        drawerLayout = findViewById(R.id.drawer_layout);

        ImageSlider imageSlider = findViewById(R.id.slider);

        List<SlideModel>slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.welcom, "Welcome To Panthiya"));
        slideModels.add(new SlideModel(R.drawable.welcomtow, "The Class Management System"));
        slideModels.add(new SlideModel(R.drawable.welcometree, "Knowledge Is Power"));
        slideModels.add(new SlideModel(R.drawable.welcomfore, "Make Assignment Easily"));
        slideModels.add(new SlideModel(R.drawable.welcomesix, "Check You Score"));

        imageSlider.setImageList(slideModels, true);

        teacherEmail = getIntent().getStringExtra("emailT");

        dbHelper = new DatabaseHelperMKASG(this);



    }

    public void clickMenu(View view){
        //open drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void clickLogo(View view){
        //close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer layout
        //check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //when drawer is open
            //close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void clickHome(View view){
        //recreate activitry
        recreate();
    }

    public void clickProfile(View view){
        //rederect to activity to profile
        redirectActivity(this, profile.class);
        Intent intent = new Intent(main_activity.this,profile.class);
        intent.putExtra("emailT", teacherEmail);
        startActivity(intent);
    }



    public void clickRegister(View view){
        //rederect to activity to dashboard
        redirectActivity(this, register.class);
        Intent intent = new Intent(main_activity.this,register.class);
        intent.putExtra("emailT", teacherEmail);
        startActivity(intent);
    }


    public void clickAboutus(View view){
        //redirect actiity to about us
        redirectActivity(this, aboutus.class);
        Intent intent = new Intent(main_activity.this,profile.class);
        intent.putExtra("emailT", teacherEmail);
        startActivity(intent);
    }

    public void clickLogout(View view){
        //close app
        logout(this);
    }

    public static void logout(Activity activity) {
        //initializ alaert dialog
        AlertDialog.Builder builder =  new AlertDialog.Builder(activity);
        //set title
        builder.setTitle("Logout");
        //set massage
        builder.setMessage("Are you sure, you want to logout ?");
        //positive yes button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish activity
                activity.finishAffinity();
                //exit app
                System.exit(0);
            }
        });

        //Negetive no button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //dismiss dialog
                dialog.dismiss();
            }
        });

        //show dialog
        builder.show();

    }



    public static void redirectActivity(Activity activity, Class aClass) {
        //initilize intent
        Intent intent =  new Intent(activity,aClass);
        intent.putExtra("emailT", teacherEmail);
        //setfalg
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);



        //satart activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        closeDrawer(drawerLayout);
    }

    //student register activity
    public void clickStudentRegister(View view) {
        Intent intentstudentReg = new Intent(this,studentRegisterPage.class);
        intentstudentReg.putExtra("emailT", teacherEmail);
        startActivity(intentstudentReg);

    }
    //make assingment
    public void clickMakeAssignment(View view) {
        Intent intentMakeAssingment = new Intent(this,makeAssingment.class);
        intentMakeAssingment.putExtra("emailT", teacherEmail);
        startActivity(intentMakeAssingment);
    }

    //check assingment
    public void clickCheckAssignment(View view) {
        Intent intentCheckAssingment = new Intent(this,check_assignment.class);
        //intentCheckAssingment.putExtra("emailT", teacherEmail);
        startActivity(intentCheckAssingment);
    }

    //add marks
    public void clickAddMarks(View view) {
        Intent intentAddMark = new Intent(this,addMark.class);
        startActivity(intentAddMark);
    }

    //techers recorde book
    public void clickCheckTeacherRecordBook(View view) {
        Intent intentTeacherRecordBook = new Intent(this,TeacherRecordBook.class);
        intentTeacherRecordBook.putExtra("emailT", teacherEmail);
        startActivity(intentTeacherRecordBook);
    }









}