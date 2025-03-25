package com.example.application1;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_Radiobutton_Java extends AppCompatActivity {

    TextView textview_answer_q01, textview_answer_q02;
    RadioButton radiobutton_01, radiobutton_02, radiobutton_03, radiobutton_04,radiobutton_05,radiobutton_06;
    RadioGroup radiogroup_01, radiogroup_02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //       EdgeToEdge.enable(this);
        setContentView(R.layout.activity_radiobutton_java);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        //           Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //          v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        //           return insets;
//        });

        textview_answer_q01= findViewById(R.id.textview_answer_q01);
        textview_answer_q02= findViewById(R.id.textview_answer_q02);

        radiobutton_01 = findViewById(R.id.radiobutton_01);
        radiobutton_02 = findViewById(R.id.radiobutton_02);
        radiobutton_03 = findViewById(R.id.radiobutton_03);
        radiobutton_04 = findViewById(R.id.radiobutton_04);
        radiobutton_05 = findViewById(R.id.radiobutton_05);
        radiobutton_06 = findViewById(R.id.radiobutton_06);

        radiogroup_01 = findViewById(R.id.radiogroup_01);
        radiogroup_02 = findViewById(R.id.radiogroup_02);

        radiogroup_01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId== R.id.radiobutton_01) {
                    textview_answer_q01.setText("最常去的網站是：" + radiobutton_01.getText().toString());
                } else if (checkedId == R.id.radiobutton_02) {
                    textview_answer_q01.setText("最常去的網站是：" + radiobutton_02.getText().toString());
                } else if (checkedId == R.id.radiobutton_03) {
                    textview_answer_q01.setText("最常去的網站是：" + radiobutton_03.getText().toString());
                }
            }
        });

        View.OnClickListener radioClickListener_01= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = radiogroup_02.getCheckedRadioButtonId();
                View vSelected = findViewById(checkedId);
                RadioButton rbSelected = (RadioButton) vSelected;
                textview_answer_q02.setText("最常去做的休閒活動是："+rbSelected.getText().toString());
            }
        };

        radiobutton_04.setOnClickListener(radioClickListener_01);
        radiobutton_05.setOnClickListener(radioClickListener_01);
        radiobutton_06.setOnClickListener(radioClickListener_01);
    }
}