package com.jjj.myntra.RecyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;

import java.util.ArrayList;

public class RecyclerAdapter_ad extends RecyclerView.Adapter<RecyclerAdapter_ad.MyViewHolder> {

    ArrayList<Modalclass> arrayListad;
    Context context;
   // TopRecyclerClickListener topRecyclerClickListener;

    public RecyclerAdapter_ad(Context context, ArrayList<Modalclass> arrayListad){

        this.arrayListad=arrayListad;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapter_ad.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerad_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter_ad.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListad.get(position).getImg()).into(holder.imagead);

       /* holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRecyclerClickListener.onclick(holder.getAdapterPosition());
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return arrayListad.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imagead;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagead=itemView.findViewById(R.id.image_ad);

        }
    }

   /* public void  onitemclicklistener(TopRecyclerClickListener topRecyclerClickListener){
        this.topRecyclerClickListener=topRecyclerClickListener;

    }*/
}
