package com.jjj.myntra.RecyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;

import java.util.ArrayList;

public class RecyclerAdapterMid_Subhome extends RecyclerView.Adapter<RecyclerAdapterMid_Subhome.MyViewHolder> {

    ArrayList<Modalclass> arrayListmen_mid;
    Context context;

    public RecyclerAdapterMid_Subhome(Context context, ArrayList<Modalclass> arrayListmen_mid){

        this.arrayListmen_mid=arrayListmen_mid;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapterMid_Subhome.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recsubhome_middesign,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterMid_Subhome.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListmen_mid.get(position).getImg()).into(holder.image_men);

    }

    @Override
    public int getItemCount() {
        return arrayListmen_mid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image_men;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_men=itemView.findViewById(R.id.recmid_img);

        }
    }

   /* public void  onitemclicklistener(TopRecyclerClickListener topRecyclerClickListener){
        this.topRecyclerClickListener=topRecyclerClickListener;

    }*/
}
