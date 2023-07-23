package com.jjj.myntra.RecyclerAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.jjj.myntra.ModelClass.Modalclass;
import com.jjj.myntra.R;

import java.util.List;

public class ImageSliderAdaptor_gadget extends RecyclerView.Adapter<ImageSliderAdaptor_gadget.MyViewHolder> {

    private ViewPager2 viewPager;
    List<Modalclass> arraylistslider_gad;

    public ImageSliderAdaptor_gadget(ViewPager2 viewPager, List<Modalclass>arraylistslider_gad){

        this.viewPager = viewPager;
        this.arraylistslider_gad=arraylistslider_gad;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.slidergad_design,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(viewPager).load(arraylistslider_gad.get(position).getImg()).into(holder.image_slider_gad);

        if (position == arraylistslider_gad.size()- 2){
            viewPager.post(runnable);
        }

    }

    @Override
    public int getItemCount() {return arraylistslider_gad.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image_slider_gad;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_slider_gad=itemView.findViewById(R.id.slider_gad);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            arraylistslider_gad.addAll(arraylistslider_gad);
            notifyDataSetChanged();
        }
    };
}
