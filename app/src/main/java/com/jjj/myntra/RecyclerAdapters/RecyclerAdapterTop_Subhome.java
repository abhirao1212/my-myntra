package com.jjj.myntra.RecyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jjj.myntra.Interface.Recyclerclicklistener;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;
import java.util.ArrayList;

public class RecyclerAdapterTop_Subhome extends RecyclerView.Adapter<RecyclerAdapterTop_Subhome.MyViewHolder> {

    ArrayList<Modalclass> arrayListmen;
    Context context;
    Recyclerclicklistener recyclerclicklistener;

    public RecyclerAdapterTop_Subhome(Context context,ArrayList<Modalclass> arrayListmen){

        this.arrayListmen=arrayListmen;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapterTop_Subhome.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recsubhome_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterTop_Subhome.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListmen.get(position).getImg()).into(holder.image_men);
        holder.title_men.setText(arrayListmen.get(position).getName());
       // holder.cid.setText(arrayListmen.get(position).getId());

        holder.image_men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerclicklistener.onclick(holder.getAdapterPosition());
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerclicklistener.onclick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListmen.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image_men;
        TextView title_men;
        LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_men=itemView.findViewById(R.id.img_subhome_top);
            title_men=itemView.findViewById(R.id.txt_subhome_top);
            layout=itemView.findViewById(R.id.subhome_top_layout);
        }
    }

    public void  onitemclicklistener(Recyclerclicklistener recyclerclicklistener){
        this.recyclerclicklistener=recyclerclicklistener;

    }
}
