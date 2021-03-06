package com.example.nanasa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class studentAdapter extends RecyclerView.Adapter<studentAdapter.Holder> {

    private Context context;
    private ArrayList<Model_TM> arrayList;

    //database object
    DatabaseHelperMKASG databaseHelper;

    public studentAdapter(Context context, ArrayList<Model_TM> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        //inisialize dbheler
        databaseHelper = new DatabaseHelperMKASG(context);

    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_marks_studentview, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Model_TM model = arrayList.get(position);
        //get for view
        final String id = model.getId();
        final String assignment = model.getAssignment();
        final String studentid = model.getStudentid();
        final String name = model.getName();
        final String subject = model.getSubject();
        final String mark = model.getMarks();
        final String comment = model.getComment();

        //set views
        holder.AssignmentID.setText(assignment);
        holder.Studentid.setText(studentid);
        holder.Subject.setText(subject);
        holder.Mark.setText(mark);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class Holder extends  RecyclerView.ViewHolder{

        TextView AssignmentID, Studentid, Subject,Mark;

        public Holder(@NonNull View itemView) {
            super(itemView);

            AssignmentID = itemView.findViewById(R.id.assignments);
            Studentid = itemView.findViewById(R.id.students);
            Subject = itemView.findViewById(R.id.subs);
            Mark = itemView.findViewById(R.id.markcs);
        }
    }

}
