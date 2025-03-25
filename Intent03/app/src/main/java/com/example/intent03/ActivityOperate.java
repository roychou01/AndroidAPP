package com.example.intent03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityOperate extends AppCompatActivity {

    RadioButton radiobutton_add, radiobutton_subtract, radiobutton_multiply, radiobutton_divide;
    Button button_calculate;
    CheckBox checkbox_divide;
    Double op_01, op_02, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_operate);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        radiobutton_add =findViewById(R.id.radiobutton_add);
        radiobutton_subtract=findViewById(R.id.radiobutton_subtract);
        radiobutton_multiply=findViewById(R.id.radiobutton_multiply);
        radiobutton_divide=findViewById(R.id.radiobutton_divide);
        button_calculate=findViewById(R.id.button_calculate);
        checkbox_divide=findViewById(R.id.checkbox_divide);

        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_getOperate = ActivityOperate.this.getIntent();
                Bundle bundle_getOperate = intent_getOperate.getExtras();

                if(bundle_getOperate != null){
                    op_01=Double.parseDouble(bundle_getOperate.getString("OPERAND_01"));
                    op_02=Double.parseDouble(bundle_getOperate.getString("OPERAND_02"));

                    if(radiobutton_add.isChecked()){
                        result = op_01 + op_02;
                    }
                    if(radiobutton_subtract.isChecked()){
                        result = op_01 - op_02;
                    }
                    if(radiobutton_multiply.isChecked()){
                        result = op_01 * op_02;
                    }
                    if(radiobutton_divide.isChecked()) {
                        if (checkbox_divide.isChecked()) {
                            result = Math.floor(op_01 / op_02);

                        } else {
                            result = op_01 / op_02;
                        }
                    }
                }
                Intent return_intent = new Intent();
                Bundle return_bundle= new Bundle();
                return_bundle.putDouble("CALCULATE_RESULT", result);
                return_intent.putExtras(return_bundle);
                setResult(RESULT_OK, return_intent);
                finish();

            }
        });

    }
}