package com.example.application2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KcgMtbu2Activity extends AppCompatActivity {
    TextView kcg_mtbu_textview_title;
    Button kcg_mtbu_button_get_data,kcg_mtbu_button_clear_data;
    ListView kcg_mtbu_listview_apidata;
    ArrayList<String> kcg_mtbu_arrayListOfString;
    ArrayAdapter<String> kcg_mtbu_arrayAdapter;
    String strAPI_Url = "https://api.kcg.gov.tw/api/service/Get/4278fc6a-c3ea-4192-8ce0-40f00cdb40dd";
    String strKcgMtbu = "高雄紅橘線捷運車站中心座標";
    String strStationName,strStationNo, strLat,strLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kcg_mtbu);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        kcg_mtbu_textview_title = findViewById(R.id.kcg_mtbu_textview_title);
        kcg_mtbu_button_get_data=findViewById(R.id.kcg_mtbu_button_get_data);
        kcg_mtbu_button_clear_data=findViewById(R.id.kcg_mtbu_button_clear_data);
        kcg_mtbu_listview_apidata=findViewById(R.id.kcg_mtbu_listview_apidata);

        kcg_mtbu_arrayListOfString= new ArrayList<String>();

        kcg_mtbu_button_clear_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((kcg_mtbu_arrayListOfString != null) && (kcg_mtbu_arrayAdapter != null)){
                    kcg_mtbu_arrayListOfString.clear();
                    kcg_mtbu_arrayAdapter.notifyDataSetChanged();
                    kcg_mtbu_textview_title.setText(strKcgMtbu + "\nAPI資料已清空...");

                }
            }
        });
        kcg_mtbu_button_get_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    JsonObjectRequest request =new JsonObjectRequest(Request.Method.GET, strAPI_Url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    int i;

                                    try{
                                        JSONArray jsonArray_data = response.getJSONArray("data");

                                        for (i=0;i<jsonArray_data.length();i++){
                                            JSONObject jsonObject_station= jsonArray_data.getJSONObject(i);
                                            strStationNo= jsonObject_station.getString("車站編號");
                                            strStationName = jsonObject_station.getString("車站中文名稱");
                                            strLat = jsonObject_station.getString("車站緯度");
                                            strLng = jsonObject_station.getString("車站經度");
                                            kcg_mtbu_arrayListOfString.add((i+1)+". "+strStationNo+"-"+strStationName+"\n座標： "+strLat+","+strLng);
                                        }

                                        kcg_mtbu_arrayAdapter = new ArrayAdapter<String>(KcgMtbu2Activity.this,
                                                android.R.layout.simple_list_item_1,kcg_mtbu_arrayListOfString);

                                        kcg_mtbu_listview_apidata.setAdapter(kcg_mtbu_arrayAdapter);
                                        kcg_mtbu_textview_title.setText(strKcgMtbu+"\nAPI資料讀取解析成功!!");

                                        kcg_mtbu_listview_apidata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                String kcg_mtbu_ItemString = kcg_mtbu_arrayListOfString.get(position);
                                                int intStartOfGeo = kcg_mtbu_ItemString.indexOf("座標：");
                                                String strGeoPosition = kcg_mtbu_ItemString.substring(intStartOfGeo + 4);
                                                String[] strArrayLatLng = strGeoPosition.split(",");
                                                String strLat = strArrayLatLng[0];
                                                String strLng = strArrayLatLng[1];
                                                String strLabel = kcg_mtbu_ItemString.substring(0, intStartOfGeo);

                                                Uri gmmIntentUri = Uri.parse("geo:" + strLat + "," + strLng + "?q=" + Uri.encode(strLabel));
                                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                                mapIntent.setPackage("com.google.android.apps.maps");
                                                startActivity(mapIntent);
                                            }
                                        });


                                    }catch (JSONException ex) {
                                        kcg_mtbu_textview_title.setText(strKcgMtbu+"\nAPI資料解析失敗，錯誤訊息；\n"+ ex.getMessage());

                                    }
                                }
                            }, new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            kcg_mtbu_textview_title.setText(strKcgMtbu+
                                    "\n發生VolleyError，錯誤訊息：\n"+error.getMessage());
                        }
                    });

                    Volley.newRequestQueue( KcgMtbu2Activity.this).add(request);
                    kcg_mtbu_textview_title.setText(strKcgMtbu+"\nAPI資料正在讀取中...");

                } catch (Exception ex){
                    kcg_mtbu_textview_title.setText(strKcgMtbu+"\n發生錯誤訊息：\n"+ex.getMessage());
                }
            }
        });
    }
}