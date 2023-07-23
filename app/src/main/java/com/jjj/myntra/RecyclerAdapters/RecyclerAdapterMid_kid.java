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

public class RecyclerAdapterMid_kid extends RecyclerView.Adapter<RecyclerAdapterMid_kid.MyViewHolder> {

    ArrayList<Modalclass> arrayListkid_mid2;
    Context context;

    public RecyclerAdapterMid_kid(Context context, ArrayList<Modalclass> arrayListkid_mid2){

        this.arrayListkid_mid2=arrayListkid_mid2;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapterMid_kid.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_kid_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterMid_kid.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListkid_mid2.get(position).getImg()).into(holder.image_kid);

    }

    @Override
    public int getItemCount() {
        return arrayListkid_mid2.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image_kid;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_kid=itemView.findViewById(R.id.img_kid_mid2);

        }
    }

   /* public void  onitemclicklistener(TopRecyclerClickListener topRecyclerClickListener){
        this.topRecyclerClickListener=topRecyclerClickListener;

    }*/
}
