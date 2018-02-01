package com.fasttech.foodorder.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasttech.foodorder.Interface.itemClickListener;
import com.fasttech.foodorder.R;

/**
 * Created by dell on 1/21/2018.
 */

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView foodName;
    public ImageView imageView;

    private com.fasttech.foodorder.Interface.itemClickListener itemClickListener;



    public FoodViewHolder(View itemView) {
        super(itemView);
        foodName = (TextView)itemView.findViewById(R.id.food_name);
        imageView = (ImageView)itemView.findViewById(R.id.food_image);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(itemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }


    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
