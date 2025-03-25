package com.example.application1;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_Imageview_Java extends AppCompatActivity {

    ImageView imageView_01;

    TextView textview_change_pic,textview_change_scaletype;

    int imageIndex=0,scaleIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imageview_java);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        imageView_01 = findViewById(R.id.imageview_01);
        textview_change_pic=findViewById(R.id.textview_change_pic);
        textview_change_scaletype=findViewById(R.id.textview_change_scaletype);

        textview_change_pic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    if(imageIndex ==2) {
                        imageView_01.setImageResource(R.drawable.imageview_java_1);
                        imageIndex = 1;
                    } else {
                        imageView_01.setImageResource(R.drawable.imageview_java_2);
                        imageIndex=2;

                    }
                }

                return false;
            }
        });

        textview_change_scaletype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scaleIndex==2){
                    imageView_01.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    scaleIndex=1;
                } else {
                    imageView_01.setScaleType(ImageView.ScaleType.FIT_XY);
                    scaleIndex=2;
                }
            }
        });

    }
}