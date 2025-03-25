package com.example.application2;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MealViewHolder extends RecyclerView.ViewHolder {

    TextView textview_menu_item, textview_meal_name, textview_meal_price;
    View item_view;

    public MealViewHolder(View itemView) {
        super(itemView);

        textview_menu_item = itemView.findViewById(R.id.textview_menu_item);
        textview_meal_name = itemView.findViewById(R.id.textview_meal_name);
        textview_meal_price = itemView.findViewById(R.id.textview_meal_price);
        item_view = itemView;
    }

}
