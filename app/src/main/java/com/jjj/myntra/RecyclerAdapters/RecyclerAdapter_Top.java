package com.jjj.myntra.RecyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jjj.myntra.Interface.Recyclerclicklistener;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;

import java.util.ArrayList;

public class RecyclerAdapter_Top extends RecyclerView.Adapter<RecyclerAdapter_Top.MyViewHolder> {

    ArrayList<Modalclass> arrayList;
    Context context;
    Recyclerclicklistener recyclerclicklistener;

    public RecyclerAdapter_Top(Context context,ArrayList<Modalclass> arrayList){

        this.arrayList=arrayList;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapter_Top.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclertop_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter_Top.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayList.get(position).getImg()).into(holder.image);
        holder.title.setText(arrayList.get(position).getName());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerclicklistener.onclick(holder.getAdapterPosition());
            }
        });

        holder.home_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerclicklistener.onclick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        LinearLayout home_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.profile_image);
            title=itemView.findViewById(R.id.textview);
            home_layout=itemView.findViewById(R.id.home_top_layout);
        }
    }

    public void  onitemclicklistener(Recyclerclicklistener recyclerclicklistener){
        this.recyclerclicklistener=recyclerclicklistener;

    }
}
