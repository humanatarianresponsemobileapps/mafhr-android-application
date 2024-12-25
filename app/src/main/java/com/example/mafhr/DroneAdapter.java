package com.example.mafhr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DroneAdapter extends RecyclerView.Adapter<DroneAdapter.MyViewHolder> {
    private List<String> textList;
    private List<Integer> imgList;
    private final NavController navController;


    public DroneAdapter(List<String> textList, List<Integer> imgList, NavController navController) {
        this.textList = textList;
        this.imgList = imgList;
        this.navController = navController;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_griddronetext, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(textList.get(position));
        holder.btnIcon.setImageResource(imgList.get(position));

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("position", position); // Pass the position as an argument

            navController.navigate(R.id.action_drone_to_drone_video, bundle);

        });
    }


    @Override
    public int getItemCount() {
        return textList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView btnIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title_text);
            btnIcon = itemView.findViewById(R.id.btnIcon);
        }
    }
}

