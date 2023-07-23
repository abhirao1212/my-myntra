package com.jjj.myntra.RecyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;

import java.util.ArrayList;

public class RecyclerAdapterTop_Kids extends RecyclerView.Adapter<RecyclerAdapterTop_Kids.MyViewHolder> {

    ArrayList<Modalclass> arrayListkids;
    Context context;
  //  Recyclerclicklistener recyclerclicklistener;

    public RecyclerAdapterTop_Kids(Context context, ArrayList<Modalclass> arrayListkids){

        this.arrayListkids=arrayListkids;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapterTop_Kids.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclertop_kid_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterTop_Kids.MyViewHolder holder, int position) {

       // Glide.with(context).load(arrayListkids.get(position).getImg()).into(holder.image_kids);
        holder.button_kids.setText(arrayListkids.get(position).getName());
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
        return arrayListkids.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        //ImageView image_women;
       // TextView title_women;
        Button button_kids;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //image_women=itemView.findViewById(R.id.img_subhomeW_top);
           // title_women=itemView.findViewById(R.id.txt_subhomeW_top);

            button_kids=itemView.findViewById(R.id.kids_button);
        }
    }

    /*public void  onitemclicklistener(Recyclerclicklistener recyclerclicklistener){
        this.recyclerclicklistener=recyclerclicklistener;

    }*/
}
