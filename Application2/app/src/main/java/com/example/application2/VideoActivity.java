package com.example.application2;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoview_main;
    private MediaController mController;
    private TextView textview_title;

    private String uriResourceTitle = "android.resource://";
    private String videoFileName="point21";
    private String strVideoResourceUri;
    private int intVideoResourceId;
    private  int intPauseTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        videoview_main = findViewById(R.id.videoview_main);
        textview_title = findViewById(R.id.textview_title);

        intVideoResourceId = getResources().getIdentifier(videoFileName,"raw",getPackageName());
        strVideoResourceUri=uriResourceTitle+getPackageName()+"/"+intVideoResourceId;

        videoview_main.setVideoURI(Uri.parse(strVideoResourceUri));
        mController=new MediaController(VideoActivity.this);
        videoview_main.setMediaController(mController);
        videoview_main.start();

    }

    @Override
    protected  void onPause(){
        super.onPause();

        if(videoview_main != null){
            if(videoview_main.isPlaying()){
                intPauseTime= videoview_main.getCurrentPosition();
                videoview_main.pause();
                textview_title.setText("功能 - 影片撥放器：撥放暫停於[ "+String.valueOf(intPauseTime)+" ]");
            }
        }
    }

    @Override
    protected  void onResume(){

        super.onResume();

        if(videoview_main != null){
            if(intPauseTime !=0){
                videoview_main.seekTo(intPauseTime);
            }
            videoview_main.start();
            //textview_title.setText("功能 - 影片撥放器：[ 繼續撥放 ]");
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        if(videoview_main != null){
            if(videoview_main.isPlaying()){
                videoview_main.stopPlayback();
            }

            videoview_main =null;
            mController=null;
        }

    }

}