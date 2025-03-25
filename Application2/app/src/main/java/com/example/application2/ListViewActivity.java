package com.example.application2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewActivity extends AppCompatActivity {

    String[] strMenu_item = {"赤肉麵線羹+脆皮炸雞","五穀瘦肉粥+無骨雞塊","鮮酥雞肉羹+黃金鮮酥雞","五穀瘦肉粥+脆皮炸雞",
            "鮮脆雞腿堡+玉米濃湯","鮮酥雞肉羹+烤醬雞堡","烤醬雞堡+脆皮炸雞","香檸吉司豬排堡+脆皮炸雞","赤肉麵線羹+鮮脆雞腿堡",
            "兩塊脆皮炸雞","香檸吉司豬排堡+無骨雞塊","五穀瘦肉粥+黃金鮮酥雞","赤肉麵線羹+豬肉可樂餅"};
    String[] strItem_price={"104","97","97","104","99","103","107","114","114","108","107","94","82"};

    ListView listView_main;

    SimpleAdapter simpleAdapterForListView;
    int i;

    String[] strTitle={"menu_item","item_price"};
    int[] intResourceID={R.id.menu_item, R.id.item_price };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        listView_main = findViewById(R.id.listview_main);
        ArrayList< HashMap<String, String>> arrayList_ItemDataSet = new ArrayList<>();

        for (i=0; i<strMenu_item.length; i++){
            HashMap<String, String>hashMap_itemData = new HashMap<>();
            hashMap_itemData.put("menu_item",strMenu_item[i]);
            hashMap_itemData.put("item_price",strItem_price[i]);
            arrayList_ItemDataSet.add(hashMap_itemData);
        }
        simpleAdapterForListView= new SimpleAdapter( ListViewActivity.this, arrayList_ItemDataSet, R.layout.item_layout, strTitle, intResourceID);
        listView_main.setAdapter(simpleAdapterForListView);

        listView_main.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String strItem= strMenu_item[position];
                String strPice = strItem_price[position];

                Toast t =Toast.makeText(ListViewActivity.this,"選擇的餐點："+strItem+"，價格："+strPice, Toast.LENGTH_LONG);
                t.show();
            }
        });

    }
}