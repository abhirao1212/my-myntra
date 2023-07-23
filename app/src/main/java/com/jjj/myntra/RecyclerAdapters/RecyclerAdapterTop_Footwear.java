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

public class RecyclerAdapterTop_Footwear extends RecyclerView.Adapter<RecyclerAdapterTop_Footwear.MyViewHolder> {

    ArrayList<Modalclass> arrayListfootwear;
    Context context;
  //  Recyclerclicklistener recyclerclicklistener;

    public RecyclerAdapterTop_Footwear(Context context, ArrayList<Modalclass> arrayListfootwear){

        this.arrayListfootwear=arrayListfootwear;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapterTop_Footwear.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.footwear_top_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterTop_Footwear.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListfootwear.get(position).getImg()).into(holder.image_footwear);
        holder.title_footwear.setText(arrayListfootwear.get(position).getName());
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
        return arrayListfootwear.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image_footwear;
        TextView title_footwear;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image_footwear=itemView.findViewById(R.id.footwear_top_img);
            title_footwear=itemView.findViewById(R.id.footwear_top_title);

        }
    }

    /*public void  onitemclicklistener(Recyclerclicklistener recyclerclicklistener){
        this.recyclerclicklistener=recyclerclicklistener;

    }*/
}
