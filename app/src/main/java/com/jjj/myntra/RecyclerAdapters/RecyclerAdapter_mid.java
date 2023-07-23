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

public class RecyclerAdapter_mid extends RecyclerView.Adapter<RecyclerAdapter_mid.MyViewHolder> {

    ArrayList<Modalclass> arrayListmid;
    Context context;
   // TopRecyclerClickListener topRecyclerClickListener;

    public RecyclerAdapter_mid(Context context, ArrayList<Modalclass> arrayListmid){

        this.arrayListmid=arrayListmid;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapter_mid.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclermid_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter_mid.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListmid.get(position).getImg()).into(holder.imagemid);

       /* holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRecyclerClickListener.onclick(holder.getAdapterPosition());
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return arrayListmid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imagemid;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagemid=itemView.findViewById(R.id.image_mid);

        }
    }

   /* public void  onitemclicklistener(TopRecyclerClickListener topRecyclerClickListener){
        this.topRecyclerClickListener=topRecyclerClickListener;

    }*/
}
