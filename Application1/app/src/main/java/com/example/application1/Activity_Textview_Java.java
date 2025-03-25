package com.example.application1;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_Textview_Java extends AppCompatActivity {

    TextView textview_01,textview_02,textview_03;
    String strText1;
    int colorText2;
    float sizeText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_textview_java);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        textview_01=findViewById(R.id.textview_01);
        textview_02=findViewById(R.id.textview_02);
        textview_03=findViewById(R.id.textview_03);

        strText1 = textview_01.getText().toString();
        colorText2=textview_02.getCurrentTextColor();
        sizeText3=textview_03.getTextSize() / getResources().getDisplayMetrics().scaledDensity;
        //用pixel/螢幕畫質後，取得sp

        textview_01.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==true){
                    textview_01.setText("目前焦點：Focus 在 TextView1");
                }   else {
                        textview_01.setText(strText1);
                }
            }
        });

        textview_02.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    textview_02.setTextColor(0xff0000ff);
                }else {
                    textview_02.setTextColor(colorText2);
                }
            }
        });

        textview_03.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    textview_03.setTextSize(30f);
                    //setTextSize()要放float
                }else {
                    textview_03.setTextSize(TypedValue.COMPLEX_UNIT_SP,sizeText3);

                }
            }
        });

    }
}