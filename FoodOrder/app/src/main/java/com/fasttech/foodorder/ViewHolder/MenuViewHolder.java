package com.fasttech.foodorder.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasttech.foodorder.Interface.itemClickListener;
import com.fasttech.foodorder.R;

/**
 * Created by dell on 1/20/2018.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView menuName;
    public  ImageView imageView;

    private itemClickListener itemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);

        menuName = (TextView)itemView.findViewById(R.id.menu_name);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(itemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
