package com.abstech.absreporting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

//import butterknife.BindView;
//import butterknife.ButterKnife;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ReportViewHolder> {
   public final ArrayList<ReportModel> employeeReportList;
    private ReportListAdapter.OnItemClickListener listener;
    private CardView cardView;
    //cardView=findViewById(R.id.cardView);
    //Context context;
    public ReportListAdapter(Context applicationContext, ArrayList<ReportModel> employeeReportList, OnItemClickListener listener) {
        this.employeeReportList = employeeReportList;
        // this.employeeReportList = employeeReportList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public ReportListAdapter.ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View V = LayoutInflater.from(context)
        View V = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_report_adapter,parent,false);
        ReportListAdapter.ReportViewHolder vh = new ReportListAdapter.ReportViewHolder(V);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReportListAdapter.ReportViewHolder holder, int position) {

        holder.selectedReportsList = employeeReportList.get(position);
        // holder.checkBox.setText(holder.selectedNotificationgrouplist.getNotificationCheckbox());
      holder.tvSlno.setText(holder.selectedReportsList.getReportsSlNo());
      holder.tvtype.setText(holder.selectedReportsList.getReportsType());
        holder.tvno.setText(holder.selectedReportsList.getReportsNo());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(employeeReportList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return employeeReportList.size();
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ReportModel selectedReportsList;

        @BindView(R.id.tv_slno)
        TextView tvSlno;

        @BindView(R.id.tv_type)
        TextView tvtype;

        @BindView(R.id.tv_no)
        TextView tvno;

        @BindView(R.id.cardview)
        CardView cardview;



//        @BindView(R.id.profile)
//        ImageView profile;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View v) {
            //Toast.makeText(this,"The Item Clicked is: "+getPosition(),Toast.LENGTH_SHORT).show();
//            final Intent intent;
//            if (getAdapterPosition() == sth){
//                intent =  new Intent(context, DashBoardActivity.class);
//            }
        }
    }
    public interface OnItemClickListener {
        void onItemClick(ReportModel reportModel);
    }

}
