package com.abstech.absreporting;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.location.Location;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

//import com.abstech.quarantine.face.LivePreviewActivity;


import com.abstech.absreporting.FaceDetectionUtil.ScannerActivity;
import com.abstech.absreporting.Utils.Imageutils;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity implements OnClickListener {
    private static View view;
    private static EditText fullName, emailId, mobileNumber, location,
            password, confirmPassword, pinCode, nearBy;
    private static TextView login;
    private static Button signUpButton;
    private static CheckBox cbHome, cbIAgree;
    private ProgressBar simpleProgressBar;
    private AlertDialog.Builder builder;
    private static Animation shakeAnimation;
    private RadioGroup radioGroup;
    private RadioButton radioMale, radioFemale, radioTransGender;
    private TextView fromDateEtxt;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    String locationAddress = "";
    boolean isAtHome = false;
    private double longitude = 0.0;
    private double latitude = 0.0;
    TextView edtDistrict;
    private String gender = "Male";
    private boolean IsFromLivePrievew = false;
    private boolean isPreviewMediaDone = false;
    private Toolbar mToolbar;
    /**
     * for district
     */

    private String[] districtName;
    private String districtId;

    private ImageView ivCamera;
    private CircleImageView ivProfileImage;
    private static final String IMAGE_DIRECTORY = "/quarantine";
    private int CAMERA = 2;
    final int PIC_CROP = 3;
    private String mCurrentPhotoPath;
    private Uri photoURI;
    Imageutils imageutils;


    String TAG = "SignupActivity";


    private static String[] permissionsRequiredList = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };
    private final static int MY_PERMISSIONS_REQUEST = 101;
    private RelativeLayout selectDistricLayout;
    private String masterID = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        dateFormatter = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        initViews();
        //setListeners();
        setDateTimeField();
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.title_sign_up);

            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();

                }
            });
        }
    }

    // Initialize all views
    private void initViews() {
        mToolbar = findViewById(R.id.toolbar);
        fullName = (EditText) findViewById(R.id.fullName);
        emailId = (EditText) findViewById(R.id.userEmailId);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        location = (EditText) findViewById(R.id.location);
        password = (EditText) findViewById(R.id.password);
        pinCode = (EditText) findViewById(R.id.pincode);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        signUpButton = (Button) findViewById(R.id.signUpBtn);
        simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        nearBy = findViewById(R.id.landmark);
        cbHome = findViewById(R.id.cb_home);
        radioGroup = findViewById(R.id.radioGroup);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        radioTransGender = findViewById(R.id.radioTransGender);
        fromDateEtxt = findViewById(R.id.etxt_date);
        edtDistrict = findViewById(R.id.tv_district);
        cbIAgree = findViewById(R.id.cb_iagree);
        selectDistricLayout = findViewById(R.id.select_district_layout);
        ivCamera = findViewById(R.id.iv_camera);
        ivProfileImage = (CircleImageView) findViewById(R.id.ic_image);
        fromDateEtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDatePickerDialog.show();
            }
        });
        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //imageutils.imagepicker(1);
                    //takePhotoFromCamera();
//                        IsFromLivePrievew = true;
//                        Intent intent = new Intent(SignUpActivity.this, LivePreviewActivity.class);
//                        startActivity(intent);

                Intent intent = new Intent(SignUpActivity.this, ScannerActivity.class);
                startActivity(intent);
            }
        });
        ivProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imageutils.imagepicker(1);
                //Toast.makeText(SignUpActivity.this, "We detected a face", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, ScannerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.set(Calendar.MONTH, Calendar.JANUARY);
        newCalendar.set(Calendar.DAY_OF_MONTH, 1);
        newCalendar.set(Calendar.YEAR, 1900);
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpBtn:

                // Call checkValidation method
                checkValidation();
                break;
        }

    }

    // Check Validation Method
    private void checkValidation() {

        // Get all edittext texts
        String getFullName = fullName.getText().toString();
        String getEmailId = emailId.getText().toString();
        String getMobileNumber = mobileNumber.getText().toString();
        String getLocation = location.getText().toString();
        String getPassword = password.getText().toString();
        String getConfirmPassword = confirmPassword.getText().toString();
        String getPinCode = pinCode.getText().toString();
        String getNearBy = nearBy.getText().toString();
        String getDOB = fromDateEtxt.getText().toString();
        // Pattern match for email id
        Pattern p = Pattern.compile(Utilss.regEx);
        Matcher m = p.matcher(getEmailId);

        //Pattern match for password
        Pattern password = Pattern.compile(Utilss.regExPassword);
        Matcher passw = password.matcher(getPassword);

        // Check if all strings are null or not
        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getLocation.equals("") || getLocation.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getPinCode.equals("") || getPinCode.length() == 0
                || getConfirmPassword.length() == 0
                || getNearBy.length() == 0 || getNearBy.equals("")
                || getDOB.length() == 0 || getDOB.equals("")) {
            Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show();
            //   loginLayout.startAnimation(shakeAnimation);
            // Check if email id valid or not
        } else if (mobileNumber.length() < 10) {
            Toast.makeText(this, "Invalid mobile number", Toast.LENGTH_SHORT).show();
        } else if (getPassword.length() < 6 || getPassword.length() > 10) {
            Toast.makeText(this, "Password should be between 6 to 10 character length", Toast.LENGTH_SHORT).show();
        } else if (!passw.find()) {
            Toast.makeText(this,
                    "Invalid password", Toast.LENGTH_SHORT).show();
        } else if (getPinCode.length() < 6) {
            Toast.makeText(this,
                    "Invalid pincode", Toast.LENGTH_SHORT).show();
        } else if (!m.find()) {
            Toast.makeText(this,
                    "Your Email Id is Invalid.", Toast.LENGTH_SHORT).show();
        } else if (getLocation.isEmpty()) {
            Toast.makeText(this,
                    "Your address should not be empty!.", Toast.LENGTH_SHORT).show();
        } else if (getPinCode.isEmpty()) {
            Toast.makeText(this,
                    "Pin code should not be empty!.", Toast.LENGTH_SHORT).show();
        } else if (getNearBy.isEmpty() || getNearBy.length() < 5) {
            Toast.makeText(this,
                    "Please enter valid near by location!.", Toast.LENGTH_SHORT).show();
        } else if (!cbIAgree.isChecked()) {
            Toast.makeText(this,
                    "Please agree on your information!", Toast.LENGTH_SHORT).show();
        } else if (edtDistrict.getText().toString().isEmpty()) {
            Toast.makeText(this,
                    "Please select valid district!", Toast.LENGTH_SHORT).show();
        } else if (!isPreviewMediaDone) {
            Toast.makeText(this,
                    "Please select profile image!", Toast.LENGTH_SHORT).show();
        }
        else{

        }

    }
}

