package com.jjj.myntra.RecyclerAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;

import java.util.ArrayList;

public class RecyclerAdapterProfile_end extends RecyclerView.Adapter<RecyclerAdapterProfile_end.MyViewHolder> {

    ArrayList<Modalclass> arrayListend;
    Context context;

    public RecyclerAdapterProfile_end(Context context,ArrayList<Modalclass> arrayListend){

        this.arrayListend=arrayListend;
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerAdapterProfile_end.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerprofile_end_design,null,false);
       MyViewHolder myViewHolder=new MyViewHolder(view);
       return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterProfile_end.MyViewHolder holder, int position) {

        holder.titleprofileend.setText(arrayListend.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return arrayListend.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleprofileend;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleprofileend=itemView.findViewById(R.id.end_text);

        }
    }
}
