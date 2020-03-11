package com.example.androidstorage2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<ModelClass> modelClassList;

    public Adapter(List<ModelClass> modelClassList) {
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recordView = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout,parent,false);
        return new MyViewHolder(recordView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelClass modelClass = modelClassList.get(position);
        holder.address.setText(modelClass.getAddress());
        holder.mobile.setText(modelClass.getMobile());
        holder.name.setText(modelClass.getName());
    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name,mobile,address;
        public ImageButton edit,delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recordName);
            address = itemView.findViewById(R.id.recordAddress);
            mobile = itemView.findViewById(R.id.recordMobile);

            delete = itemView.findViewById(R.id.deleteButton);
            edit = itemView.findViewById(R.id.editButton);

            edit.setOnClickListener(this);
            delete.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v.equals(delete)){
                removeAt(getLayoutPosition(),v);
            }
        }
        public void removeAt(int position,View v){
            EmployeeDatabase db = EmployeeDatabase.getDatabase(v.getContext());
            modelClassList.remove(position);
            int empID = modelClassList.get(position).id;
            db.employeeDao().deleteByEmployeeID(empID);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,modelClassList.size());
        }
    }
}
