package com.example.application2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MusicActivity extends AppCompatActivity  implements MediaPlayer.OnCompletionListener{

    private Button button_start, button_pause, button_stop;
    private TextView textview_status;
    private MediaPlayer m_player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_music);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        button_start = findViewById(R.id.button_start);
        button_pause = findViewById(R.id.button_pause);
        button_stop = findViewById(R.id.button_stop);
        textview_status = findViewById(R.id.textview_status);

        m_player = MediaPlayer.create( MusicActivity.this,R.raw.goodnight);
        m_player.setOnCompletionListener(MusicActivity.this);

        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m_player != null){
                    if (m_player.isPlaying() != true){
                        m_player.start();
                        textview_status.setText("播放狀態：音樂放送中♪♫♪~♬♪~♬♪♪~");
                    }
                }

            }
        });

        button_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_player != null){
                    if(m_player.isPlaying()==true){
                        m_player.pause();
                        textview_status.setText("播放狀態：[音樂暫停⏸]");
                    }
                }
            }
        });

        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (m_player != null) {
                m_player.stop();
                textview_status.setText("播放狀態：音樂停止!!");
                 }
                    try {
                        m_player.prepareAsync();
                    } catch (IllegalStateException ex) {
                        textview_status.setText("異常狀態："+ex.getMessage());
                    }

            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        if(m_player != null){
            if(m_player.isPlaying()){
                m_player.stop();
            }
            m_player.release();
            m_player = null;
        }
    }


    @Override
    public void onCompletion(MediaPlayer mp) {
        textview_status.setText("[整首音樂已撥放完畢");
        mp.stop();
        mp.seekTo(0);

        try {
            if(mp != null ) {
                mp.prepareAsync();
            }
        } catch (IllegalStateException ex){
            textview_status.setText("異常狀態："+ex.getMessage());
        }
    }
}