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

public class RecyclerAdapterEnd_Subhome extends RecyclerView.Adapter<RecyclerAdapterEnd_Subhome.MyViewHolder> {

    ArrayList<Modalclass> arrayListmen_end;
    Context context;

    public RecyclerAdapterEnd_Subhome(Context context, ArrayList<Modalclass> arrayListmen_end){

        this.arrayListmen_end=arrayListmen_end;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapterEnd_Subhome.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recsubhome_middesign,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterEnd_Subhome.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListmen_end.get(position).getImg()).into(holder.image_men);

    }

    @Override
    public int getItemCount() {
        return arrayListmen_end.size();
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
