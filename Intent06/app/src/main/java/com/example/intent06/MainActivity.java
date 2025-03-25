package com.example.intent06;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button_start_activity_one, button_start_activity_two;

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
        button_start_activity_one = findViewById(R.id.button_start_activity_one);
        button_start_activity_two = findViewById(R.id.button_start_activity_two);

        button_start_activity_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Activity_One.class);
                startActivity(i);
            }
        });

        button_start_activity_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Activity_Two.class);
                startActivity(i);
            }
        });


        Log.d("Intent06 : ", "MainActivity - OnCreate() 被執行!");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("Intent06 : ", "MainActivity - OnStart() 被執行!");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Intent06 : ", "MainActivity - OnResume() 被執行!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Intent06 : ", "MainActivity - OnPause() 被執行!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Intent06 : ", "MainActivity - OnStop() 被執行!");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Intent06 : ", "MainActivity - OnReStart() 被執行!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Intent06 : ", "MainActivity - OnDestroy() 被執行!");
    }


}