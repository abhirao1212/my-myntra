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
import com.jjj.myntra.Interface.Recyclerclicklistener;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;

import java.util.ArrayList;

public class RecyclerAdapterTop_Women extends RecyclerView.Adapter<RecyclerAdapterTop_Women.MyViewHolder> {

    ArrayList<Modalclass> arrayListwomen;
    Context context;
  //  Recyclerclicklistener recyclerclicklistener;

    public RecyclerAdapterTop_Women(Context context, ArrayList<Modalclass> arrayListwomen){

        this.arrayListwomen=arrayListwomen;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapterTop_Women.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rectopw_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterTop_Women.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListwomen.get(position).getImg()).into(holder.image_women);
        holder.title_women.setText(arrayListwomen.get(position).getName());
       // holder.cid.setText(arrayListmen.get(position).getId());

       /* holder.image_men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerclicklistener.onclick(holder.getAdapterPosition());
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return arrayListwomen.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image_women;
        TextView title_women;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_women=itemView.findViewById(R.id.img_subhomeW_top);
            title_women=itemView.findViewById(R.id.txt_subhomeW_top);
        }
    }

    /*public void  onitemclicklistener(Recyclerclicklistener recyclerclicklistener){
        this.recyclerclicklistener=recyclerclicklistener;

    }*/
}
