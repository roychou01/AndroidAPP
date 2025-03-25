package com.example.application2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerViewActivity extends AppCompatActivity {

    String[] strMeal_Name = { "赤肉麵線羹+脆皮炸雞" ,"五穀瘦肉粥+無骨雞塊" , "鮮酥雞肉羹+黃金鮮酥雞" , "五穀瘦肉粥+脆皮炸雞" , "鮮脆雞腿堡+玉米濃湯" ,
            "鮮酥雞肉羹+醬烤雞堡" , "醬烤雞堡+脆皮炸雞" , "香檸吉司豬排堡+脆皮炸雞" , "赤肉麵線羹+鮮脆雞腿堡" , "兩塊脆皮雉雞" ,
            "香檸吉司豬排堡+無骨雞塊" , "五穀瘦肉粥+黃金鮮穌雞" , "赤肉麵線羹+豬肉可樂餅" , "香檸吉司豬排堡+香酥米糕"};

    String[] strMeal_Price = { "104" , "97", "97", "104", "99", "103", "107", "114", "114", "108", "107", "94", "82" , "99"};

    RecyclerView recyclerview_main;
    SwipeRefreshLayout swipereflashlayout_main;
    ArrayList<HashMap<String,String>> mealArrayListOfHashMap = new ArrayList<>();

    MealRecyclerViewAdapter mealAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        recyclerview_main = findViewById(R.id.recyclerview_main);
        recyclerview_main.setLayoutManager(new LinearLayoutManager(RecyclerViewActivity.this));
        swipereflashlayout_main = findViewById(R.id.swipereflashlayout_main);


        prepareMealData();
        mealAdapter = new MealRecyclerViewAdapter(RecyclerViewActivity.this);
        mealAdapter.setMealArrayList(mealArrayListOfHashMap);
        recyclerview_main.setAdapter(mealAdapter);

        swipereflashlayout_main.setColorSchemeColors(0x0000ff);
        swipereflashlayout_main.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mealArrayListOfHashMap.clear();
                prepareMealData();
                mealAdapter.notifyDataSetChanged();
                swipereflashlayout_main.setRefreshing(false);

            }
        });

    }

    public void prepareMealData() {
        int i;

        for (i = 0; i < strMeal_Name.length; i++){
            HashMap<String, String> tempHashMap = new HashMap<>();
            tempHashMap.put("menu_item", (i+1)+"號餐");
            tempHashMap.put("meal_name", strMeal_Name[i]);
            tempHashMap.put("meal_price", strMeal_Price[i]);
            mealArrayListOfHashMap.add(tempHashMap);
        }
    }

}