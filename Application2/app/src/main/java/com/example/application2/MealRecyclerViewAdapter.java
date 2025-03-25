package com.example.application2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MealRecyclerViewAdapter extends RecyclerView.Adapter<MealViewHolder> {

    private ArrayList<HashMap<String,String>> mealArrayList;
    private Context mContext;
    public MealRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_layout, parent, false);
        return new MealViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        holder.textview_menu_item.setText(mealArrayList.get(position).get("menu_item"));
        holder.textview_meal_name.setText(mealArrayList.get(position).get("meal_name"));
        holder.textview_meal_price.setText(mealArrayList.get(position).get("meal_price"));

    }

    @Override
    public int getItemCount() {

        if (mealArrayList == null) {
            return 0;
        }
        return mealArrayList.size();
    }

    public void setMealArrayList(ArrayList<HashMap<String,String>> inputArrayList){
        this.mealArrayList = inputArrayList;
        notifyDataSetChanged();
    }

    public ArrayList<HashMap<String,String>> getMealArrayList() {
        return  mealArrayList;
    }

}
