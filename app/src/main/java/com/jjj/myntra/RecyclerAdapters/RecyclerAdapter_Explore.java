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

public class RecyclerAdapter_Explore extends RecyclerView.Adapter<RecyclerAdapter_Explore.MyViewHolder> {

    ArrayList<Modalclass> arrayListexplore;
    Context context;

    Recyclerclicklistener topRecyclerClickListener;

    public RecyclerAdapter_Explore(Context context, ArrayList<Modalclass> arrayListexplore){

        this.arrayListexplore=arrayListexplore;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapter_Explore.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listexplore_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter_Explore.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListexplore.get(position).getImg()).into(holder.imageexplore);
     //   holder.titleexplore.setText(arrayListexplore.get(position).getName());

        holder.imageexplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRecyclerClickListener.onclick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListexplore.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageexplore;
       // TextView titleexplore;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageexplore=itemView.findViewById(R.id.imageView_explore);
            //titleexplore=itemView.findViewById(R.id.textView_explore);

        }
    }

    public void  onitemclicklistener(Recyclerclicklistener topRecyclerClickListener){
        this.topRecyclerClickListener=topRecyclerClickListener;

    }
}
