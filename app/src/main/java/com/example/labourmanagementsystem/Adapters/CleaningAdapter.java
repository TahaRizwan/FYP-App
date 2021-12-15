package com.example.labourmanagementsystem.Adapters;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labourmanagementsystem.Models.PojoModel;
import com.example.labourmanagementsystem.R;
import com.example.labourmanagementsystem.SubClass.CarFurActivity;
import com.example.labourmanagementsystem.SubClass.CarWashActivity;
import com.example.labourmanagementsystem.SubClass.DomesticCleaningActivity;
import com.example.labourmanagementsystem.SubClass.DryCleaningActivity;

import java.util.ArrayList;

public class CleaningAdapter extends RecyclerView.Adapter<CleaningAdapter.viewHolder>{

    ArrayList<PojoModel> list;
    //Context context;

    public CleaningAdapter(ArrayList<PojoModel> list) {
        this.list = list;
        //  this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_rv,parent ,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        PojoModel model=list.get(position);
        holder.imageView.setImageResource(model.getPic());
        holder.textView.setText(model.getText());

        switch (position){

            case 0:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), CarFurActivity.class));
                    }
                });
                break;

            case 1:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), DomesticCleaningActivity.class));
                    }
                });
                break;

            case 2:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.getContext().startActivity(new Intent(v.getContext(), CarWashActivity.class));
                    }
                });
                break;

            case 3:
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                        v.getContext().startActivity(new Intent(v.getContext(), DryCleaningActivity.class));
                    }
                });
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
        }
    }

}
