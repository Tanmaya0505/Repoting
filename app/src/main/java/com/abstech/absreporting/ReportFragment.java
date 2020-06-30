package com.abstech.absreporting;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportFragment extends Fragment implements ReportListAdapter.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    @BindView(R.id.recview)
    RecyclerView recyclerView;

    private ArrayList<ReportModel> reportsList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private ReportsViewModel slideshowViewModel;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Toolbar mToolbar;

    public ReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportFragment newInstance(String param1, String param2) {
        ReportFragment fragment = new ReportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        slideshowViewModel =
                ViewModelProviders.of(this).get(ReportsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_report, container, false);
        mToolbar =root.findViewById(R.id.toolbar);
        if (mToolbar != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.setSupportActionBar(mToolbar);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(true);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
            activity.getSupportActionBar().setTitle(R.string.title_reportlog);

            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.finish();

                }
            });
        }

        ButterKnife.bind(this,root);

        reportsList = fetchCreateGroupData();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
        //return inflater.inflate(R.layout.fragment_report, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        recyclerView.setAdapter(new ReportListAdapter(items, new ReportListAdapter.OnItemClickListener() {
//            @Override public void onItemClick(ContentItem item) {
//                Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
//            }
//        }));

        ReportListAdapter mAdapter = new ReportListAdapter(getActivity(), reportsList, (ReportListAdapter.OnItemClickListener) this);
        recyclerView.setAdapter(mAdapter);
        //Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(ReportModel reportModel) {
        startActivity(new Intent(getContext(), ReportDetailsActivity.class));
        //startActivity(new Intent(getContext(), AdvertismentBased.class));

    }
    private ArrayList<ReportModel> fetchCreateGroupData(){
        ArrayList<ReportModel> modelList = new ArrayList<>();
//        for(int i=0;i<10;i++){
            ReportModel model = new ReportModel("1","Marketing","1");
            ReportModel model1 = new ReportModel("2","Advertisement","5");
            ReportModel model2 = new ReportModel("3","Marketing","1");
            ReportModel model3 = new ReportModel("4","Advertisement","1");
            ReportModel model4 = new ReportModel("5","Advertisement","1");
            ReportModel model5 = new ReportModel("6","Marketing","8");
            ReportModel model6 = new ReportModel("7","Marketing","1");
            ReportModel model7 = new ReportModel("8","Marketing","1");
            ReportModel model8 = new ReportModel("9","Advertisement","10");
            ReportModel model9 = new ReportModel("10","Marketing","1");

            modelList.add(model);
            modelList.add(model1);
            modelList.add(model2);
            modelList.add(model3);
            modelList.add(model4);
            modelList.add(model5);
            modelList.add(model6);
            modelList.add(model7);
            modelList.add(model8);
            modelList.add(model9);
        //}
        return modelList;

    }

    public void onClickItem(){

    }
}
