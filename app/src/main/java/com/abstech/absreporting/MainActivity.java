package com.abstech.absreporting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private RadioGroup radioGroup;
    private RadioButton radioButton, radioButton2, radioButton4,radioButton6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.title_One);

            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();

                }
            });
        }

        Button btn = (Button)findViewById(R.id.Next);
        radioGroup = findViewById(R.id.radioGroup1);
        radioButton=findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton4 = findViewById(R.id.radioButton4);
        radioButton6 = findViewById(R.id.radioButton6);





//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//                if (checkedId == R.id.radioButton) {
//                    radioButton2.setChecked(false);
//                    radioButton4.setChecked(false);
//                    radioButton6.setChecked(false);
//                } else if (checkedId == R.id.radioButton2) {
//                    //startActivity(new Intent(MainActivity.this, RealStateBased.class));
//                    radioButton.setChecked(false);
//                    radioButton4.setChecked(false);
//                    radioButton6.setChecked(false);
//                } else if (checkedId == R.id.radioButton4) {
//                    //startActivity(new Intent(MainActivity.this, PharmaBased.class));
//                    radioButton.setChecked(false);
//                    radioButton2.setChecked(false);
//                    radioButton6.setChecked(false);
//                }else if (checkedId == R.id.radioButton6) {
//                    //startActivity(new Intent(MainActivity.this, AdvertismentBased.class));
//                    radioButton.setChecked(false);
//                    radioButton4.setChecked(false);
//                    radioButton2.setChecked(false);
//                }
//
//            }
//        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButton.isChecked()){
                    startActivity(new Intent(MainActivity.this, QuestionsMarketing.class));
                }else if(radioButton2.isChecked()){
                    startActivity(new Intent(MainActivity.this, RealStateBased.class));
                }
                else if(radioButton4.isChecked()){
                    startActivity(new Intent(MainActivity.this, PharmaBased.class));
                }
                else if(radioButton6.isChecked()){
                    startActivity(new Intent(MainActivity.this, AdvertismentBased.class));
                }


            }
        });
    }
    public void onClickradiobutton1(View view) {
        radioButton2.setChecked(false);
        radioButton4.setChecked(false);
        radioButton6.setChecked(false);
    }
    public void onClickradiobutton2(View view) {
        radioButton.setChecked(false);
        radioButton4.setChecked(false);
        radioButton6.setChecked(false);
    }
    public void onClickradiobutton3(View view) {
        radioButton.setChecked(false);
        radioButton2.setChecked(false);
        radioButton6.setChecked(false);
    }
    public void onClickradiobutton4(View view) {
        radioButton.setChecked(false);
        radioButton4.setChecked(false);
        radioButton2.setChecked(false);
    }
}
