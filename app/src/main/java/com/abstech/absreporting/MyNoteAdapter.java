package com.abstech.absreporting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNoteAdapter extends RecyclerView.Adapter<MyNoteAdapter.MyNoteListViewHolder> {
    public final ArrayList<MyNoteModel> mynoteReportList;
    private MyNoteAdapter.OnItemClickListener listener;
    public MyNoteAdapter(Context applicationContext, ArrayList<MyNoteModel> mynoteReportList, MyNoteAdapter.OnItemClickListener listener) {
        this.mynoteReportList = mynoteReportList;
        // this.employeeReportList = employeeReportList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyNoteAdapter.MyNoteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_my_note_adapter,parent,false);
        MyNoteAdapter.MyNoteListViewHolder vh = new MyNoteAdapter.MyNoteListViewHolder(V);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyNoteAdapter.MyNoteListViewHolder holder, int position) {
        holder.selectedReportsList = mynoteReportList.get(position);
        // holder.checkBox.setText(holder.selectedNotificationgrouplist.getNotificationCheckbox());
        holder.tvsocialmediatitle.setText(holder.selectedReportsList.getEventTitle());
        holder.tvdescription.setText(holder.selectedReportsList.getDescription());
        holder.tvday.setText(holder.selectedReportsList.getEventTime());
        holder.tvdate.setText(holder.selectedReportsList.getEventDate());
    }
    public class MyNoteListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public MyNoteModel selectedReportsList;

        @BindView(R.id.tv_social_media_title)
        TextView tvsocialmediatitle;

        @BindView(R.id.tv_description)
        TextView tvdescription;

        @BindView(R.id.tv_day)
        TextView tvday;

        @BindView(R.id.tv_date)
        TextView tvdate;



//        @BindView(R.id.profile)
//        ImageView profile;

        public MyNoteListViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }


        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public int getItemCount() {
        return mynoteReportList.size();
    }
    public interface OnItemClickListener {
        void onItemClick(MyNoteModel myNoteModel);
    }
}
