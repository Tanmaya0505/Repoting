package com.abstech.absreporting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.abstech.absreporting.Speedview.Activity1;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DashBoardActivity extends AppCompatActivity {
//BottomNavigationView bottomNavigationItemView;
private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        mToolbar = findViewById(R.id.toolbar);
        //FloatingActionButton fab = findViewById(R.id.fab);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            //getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            //getSupportActionBar().setTitle(R.string.title_advertisement);

//            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//
//                }
//            });
        }
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(DashBoardActivity.this, MyTaskActivity.class));
//            }
//        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,
                    new MyTaskFragment()).commit();
        }

    }
private BottomNavigationView.OnNavigationItemSelectedListener navListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_task:
                        selectedFragment = new MyTaskFragment();
                        break;
                    case R.id.navigation_report:
                        selectedFragment = new ReportFragment();
                        break;
                    case R.id.navigation_home:
                        selectedFragment = new HomeFragment();
                        break;

                    case R.id.navigation_account:
                        selectedFragment = new AccountFragment();
                        break;
                    case R.id.navigation_report_statistics:
//                        Intent myIntent = new Intent(DashBoardActivity.this, ReportStatisticsActivity.class);
//                        startActivity(myIntent);
                        selectedFragment = new ReportStatisticsFragment();
                        break;
//                    case R.id.navigation_home:
//                    Fragment HomeFragment = new HomeFragment();
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//                    getSupportActionBar().setDisplayShowHomeEnabled(false);
//                    HomeFragment.setRetainInstance(true);
//                    openFragment(HomeFragment);
//                    return true;
//                    case R.id.navigation_report:
//                        Fragment ReportFragment = new ReportFragment();
//                        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//                        getSupportActionBar().setDisplayShowHomeEnabled(false);
//                        ReportFragment.setRetainInstance(true);
//                        openFragment(ReportFragment);
//                        return true;


                }
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,
                        selectedFragment).commit();
                return true;
            }
        };
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.more_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.location:
                startActivity(new Intent(this, MapLocationActivity.class));
                return true;
            case R.id.speed:
//                startActivity(new Intent(this, SpeedViewActivity.class));
                startActivity(new Intent(this, Activity1.class));
                return true;
            case R.id.confpass:
//                startActivity(new Intent(this, SpeedViewActivity.class));
                startActivity(new Intent(this, ChangePasswordActivity.class));
                return true;
            case R.id.mynote:
//                startActivity(new Intent(this, SpeedViewActivity.class));
                startActivity(new Intent(this, MyNoteActivity.class));
                return true;
            case R.id.reportproblem:
//                startActivity(new Intent(this, SpeedViewActivity.class));
                startActivity(new Intent(this, ReportProblemActivity.class));
                return true;
            case R.id.notification:
//                startActivity(new Intent(this, SpeedViewActivity.class));
                startActivity(new Intent(this, NotificationActivity.class));
                return true;
            case R.id.calender:
//                startActivity(new Intent(this, SpeedViewActivity.class));
                startActivity(new Intent(this, CalenderHolidayListActivity.class));
                return true;
            case R.id.applyleave:
//                startActivity(new Intent(this, SpeedViewActivity.class));
                startActivity(new Intent(this, ApplyLeaveActivity.class));
                return true;
            case R.id.fuel:
//                startActivity(new Intent(this, SpeedViewActivity.class));
                startActivity(new Intent(this, FuelListActivity.class));
                return true;
            case R.id.attendance:
//                startActivity(new Intent(this, SpeedViewActivity.class));
                startActivity(new Intent(this, AttendanceListActivity.class));
                return true;
            case R.id.about:
//                startActivity(new Intent(this, SpeedViewActivity.class));
                startActivity(new Intent(this, AboutusActivity.class));
                return true;
            case R.id.logout:
//                startActivity(new Intent(this, SpeedViewActivity.class));
                startActivity(new Intent(this, LoginActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
    }

