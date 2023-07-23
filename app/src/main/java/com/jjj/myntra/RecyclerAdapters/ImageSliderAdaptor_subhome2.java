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

public class ImageSliderAdaptor_subhome2 extends RecyclerView.Adapter<ImageSliderAdaptor_subhome2.MyViewHolder> {

    private ViewPager2 viewPager;
    List<Modalclass> arraylistslider_men2;

    public ImageSliderAdaptor_subhome2(ViewPager2 viewPager, List<Modalclass>arraylistslider_men2){

        this.viewPager = viewPager;
        this.arraylistslider_men2=arraylistslider_men2;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.slidersubhome_design2,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(viewPager).load(arraylistslider_men2.get(position).getImg()).into(holder.image_slider_image);

        if (position == arraylistslider_men2.size()- 2){
            viewPager.post(runnable);
        }

    }

    @Override
    public int getItemCount() {return arraylistslider_men2.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image_slider_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_slider_image=itemView.findViewById(R.id.slider_subhome2);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            arraylistslider_men2.addAll(arraylistslider_men2);
            notifyDataSetChanged();
        }
    };
}
