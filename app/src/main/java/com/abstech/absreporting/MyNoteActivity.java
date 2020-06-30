package com.abstech.absreporting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNoteActivity extends AppCompatActivity implements MyNoteAdapter.OnItemClickListener {

    @BindView(R.id.recview)
    RecyclerView recyclerView;

    private Toolbar mToolbar;
    private ArrayList<MyNoteModel> reportsList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private ReportsViewModel slideshowViewModel;

    private Context mContext;
    private Activity mActivity;

    private LinearLayout linearLayout;
    private RelativeLayout relativeLayout;
    private Button mButton;

    private PopupWindow mPopupWindow;
    private AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_note);

        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.title_mynote);

            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();

                }
            });
        }
        mContext = getApplicationContext();
        mActivity = MyNoteActivity.this;
        relativeLayout=(RelativeLayout) findViewById(R.id.rel1);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
//                View customView = inflater.inflate(R.layout.activity_custom_dialog,null);
//                mPopupWindow = new PopupWindow(
//                        customView,
//                        RelativeLayout.LayoutParams.WRAP_CONTENT,
//                        RelativeLayout.LayoutParams.WRAP_CONTENT
//                );
//                if(Build.VERSION.SDK_INT>=21){
//                    mPopupWindow.setElevation(5.0f);
//                }
//                mPopupWindow.showAtLocation(relativeLayout, Gravity.CENTER,0,0);
                AlertDialog.Builder builder = new AlertDialog.Builder(MyNoteActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.activity_custom_dialog, viewGroup, false);
                Button okButton = dialogView.findViewById(R.id.noteok);
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                builder.setCancelable(true);
                builder.setView(dialogView);
                alertDialog = builder.create();
                alertDialog.show();

            }
        });

        ButterKnife.bind(this);
        reportsList=fetchCreateGroupData();
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        MyNoteAdapter adapter = new MyNoteAdapter(getApplicationContext(), reportsList, (MyNoteAdapter.OnItemClickListener) this);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<MyNoteModel> fetchCreateGroupData(){
        ArrayList<MyNoteModel> modelList = new ArrayList<>();
        for(int i=0;i<10;i++){
            MyNoteModel model=new MyNoteModel("Lorem Ipsum","29","Fri","Lorem Ipsum is simply dummy text of the printing and typesetting industry");
            //MyNoteModel model = new MyNoteModel("1","Marketing","1");
            modelList.add(model);
        }
        return modelList;

    }

    @Override
    public void onItemClick(MyNoteModel myNoteModel) {

    }
}
