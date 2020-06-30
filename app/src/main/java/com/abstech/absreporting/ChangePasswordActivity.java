package com.abstech.absreporting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class ChangePasswordActivity extends AppCompatActivity {
    private Toolbar mToolbar;

//    private Context mContext;
//    private Activity mActivity;
//
//    private LinearLayout linearLayout;
//    private Button mButton;
//
//    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

//        mContext = getApplicationContext();
//        mActivity = ChangePasswordActivity.this;
//        linearLayout = (LinearLayout) findViewById(R.id.lin2);
//        mButton = (Button) findViewById(R.id.next);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
//                View customView = inflater.inflate(R.layout.activity_custom_dialog,null);
//                mPopupWindow = new PopupWindow(
//                        customView,
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT
//                );
//                if(Build.VERSION.SDK_INT>=21){
//                    mPopupWindow.setElevation(5.0f);
//                }
//                mPopupWindow.showAtLocation(linearLayout, Gravity.CENTER,0,0);
//
//            }
//        });

        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.title_change);

            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();

                }
            });
        }
    }
}
