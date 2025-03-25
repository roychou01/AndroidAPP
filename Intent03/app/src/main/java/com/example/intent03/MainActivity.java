package com.example.intent03;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edittext_input_opd1, edittext_input_opd2;
    Button button_choice_op;
    TextView textview_output;
    //    private static final int REQUEST_CODE_OP = 1001;
    //自訂用來識別，不重複的數值
    //改用不是廣播的方法如下
    ActivityResultLauncher launcher_callActivityOperate = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            int result_code = o.getResultCode();
            if (result_code == RESULT_OK) {
                Intent intent_resultReturn = o.getData();
                Bundle bundle_resultReturn = intent_resultReturn.getExtras();

                if (bundle_resultReturn != null) {
                    Double answer = bundle_resultReturn.getDouble("CALCULATE_RESULT");
                    textview_output.setText("運算結果 = " + answer.toString());
                }
            }
        }
    });


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        edittext_input_opd1 = findViewById(R.id.edittext_input_opd1);
        edittext_input_opd2 = findViewById(R.id.edittext_input_opd2);
        button_choice_op = findViewById(R.id.button_choice_op);
        textview_output = findViewById(R.id.textview_output);

        button_choice_op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_callActivityOperate = new Intent(MainActivity.this, ActivityOperate.class);

                Bundle bundle_operand = new Bundle();
                bundle_operand.putString("OPERAND_01", edittext_input_opd1.getText().toString());
                bundle_operand.putString("OPERAND_02", edittext_input_opd2.getText().toString());

                intent_callActivityOperate.putExtras(bundle_operand);
                //startActivityForResult(intent_callActivityOperate,REQUEST_CODE_OP);
                launcher_callActivityOperate.launch(intent_callActivityOperate);
            }
        });
    }
}

//    @Override
//    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

//        if(requestCode == REQUEST_CODE_OP ){
//            if(resultCode == RESULT_OK){
//                Bundle bundle_resultReturn = data.getExtras();
//                if (bundle_resultReturn != null){
//                    Double answer = bundle_resultReturn.getDouble("CALCULATE_RESULT");
//                    textview_output.setText("運算結果=" + answer.toString());
//                }
//            }
//        }

//    }

//}