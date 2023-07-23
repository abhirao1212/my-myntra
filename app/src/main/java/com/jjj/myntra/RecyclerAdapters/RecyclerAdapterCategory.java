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

public class RecyclerAdapterCategory extends RecyclerView.Adapter<RecyclerAdapterCategory.MyViewHolder> {

    ArrayList<Modalclass> arrayListcat;
    Context context;

    public RecyclerAdapterCategory(Context context,ArrayList<Modalclass> arrayListcat){

        this.arrayListcat=arrayListcat;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapterCategory.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclercategory_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterCategory.MyViewHolder holder, int position) {

        Glide.with(context).load(arrayListcat.get(position).getImg()).into(holder.imagecat);


    }

    @Override
    public int getItemCount() {
        return arrayListcat.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imagecat;
       // TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagecat=itemView.findViewById(R.id.category_image);
          //  title=itemView.findViewById(R.id.textview);
        }
    }
}
