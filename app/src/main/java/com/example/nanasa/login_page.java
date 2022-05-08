package com.example.nanasa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class login_page extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tab1,tab2;
    public PageAdapter3 pagerAdapter;
    private DatabaseHelperMKASG dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //remove titel bar
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        //initiate database object in main funtion
        dbHelper = new DatabaseHelperMKASG(this);


        setContentView(R.layout.activity_login_page);
        //assing variable
        drawerLayout =  findViewById(R.id.drawer_layout);

        //tabcontroler
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tab1 = (TabItem) findViewById(R.id.tab1);
        tab2 = (TabItem) findViewById(R.id.tab2);
        viewPager = findViewById(R.id.viewpager);

        pagerAdapter = new PageAdapter3(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 0){
                    pagerAdapter.notifyDataSetChanged();
                } else if(tab.getPosition() == 1) {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    public void clickBack(View view) {
        Intent intentback = new Intent(this, main_activity.class);
        startActivity(intentback);


    }

    public void clickMenu(View view){
        //open drawer
        main_activity.openDrawer(drawerLayout);
    }

    public void clickLogo(View view){
        //close drawer
        main_activity.closeDrawer(drawerLayout);
    }

    public void clickLogin(View view){
        //redirect activity to home
        main_activity.redirectActivity(this, login_page.class);
    }

    public void clickAboutus(View view){
        //redirect activity to about us
        main_activity.redirectActivity(this,aboutus.class);
    }


    public void clickRegister(View view){

        //recreate activity
        main_activity.redirectActivity(this,register.class);
    }
    public void clickLogout(View view){
        //close app
        main_activity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        main_activity.closeDrawer(drawerLayout);
    }
}