package com.example.intent02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityTemperatureF extends AppCompatActivity {

    Button button_close;
    TextView textview_temp_f;
    Double c,f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_temperature_f);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        button_close = findViewById(R.id.button_close);
        textview_temp_f = findViewById(R.id.textview_temp_f);

        Intent intent_get = ActivityTemperatureF.this.getIntent();
        Bundle bundle_get = intent_get.getExtras();

        if (bundle_get != null){
            String s= bundle_get.getString("TEMP_C_01");
            c=Double.parseDouble(s);
            f=((9.0*c)/5) + 32 ;

            textview_temp_f.setText("轉換後華氏溫度值："+f.toString());

        }

        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}