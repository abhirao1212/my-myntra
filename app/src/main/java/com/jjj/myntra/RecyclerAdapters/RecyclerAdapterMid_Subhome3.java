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

public class RecyclerAdapterMid_Subhome3 extends RecyclerView.Adapter<RecyclerAdapterMid_Subhome3.MyViewHolder> {

    ArrayList<Modalclass> arrayListmen_mid3;
    Context context;

    public RecyclerAdapterMid_Subhome3(Context context, ArrayList<Modalclass> arrayListmen_mid3){

        this.arrayListmen_mid3=arrayListmen_mid3;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapterMid_Subhome3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recsubhome_middesign3,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterMid_Subhome3.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListmen_mid3.get(position).getImg()).into(holder.image_men);

    }

    @Override
    public int getItemCount() {
        return arrayListmen_mid3.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image_men;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_men=itemView.findViewById(R.id.recmid_img3);

        }
    }

   /* public void  onitemclicklistener(TopRecyclerClickListener topRecyclerClickListener){
        this.topRecyclerClickListener=topRecyclerClickListener;

    }*/
}
