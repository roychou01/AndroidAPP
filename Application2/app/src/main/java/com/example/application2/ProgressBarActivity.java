package com.example.application2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProgressBarActivity extends AppCompatActivity {

    ProgressBar progressBar_spinner, progressBar_horizontal;
    ProgressDialog progressDialog_spinner;
    Button button_start_progress_spinner, button_start_progress_horizontal,button_run_progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_progress_bar);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        progressBar_spinner = findViewById(R.id.progress_bar_spinner);
        progressBar_horizontal=findViewById(R.id.progress_bar_horizontal);
        button_start_progress_spinner=findViewById(R.id.button_start_progress_spinner);
        button_start_progress_horizontal=findViewById(R.id.button_start_progress_horizontal);
        button_run_progress = findViewById(R.id.button_run_progress);

        button_start_progress_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar_spinner.setVisibility(View.VISIBLE);
                progressBar_spinner.setMax(100);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i;
                        for( i=0; i<100 ; i++){
                            progressBar_spinner.setProgress(i);
                            SystemClock.sleep(100);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar_spinner.setVisibility(View.GONE);
                            }
                        });
                    }
                }).start();

            }
        });
        button_start_progress_horizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar_horizontal.setVisibility(View.VISIBLE);
                progressBar_horizontal.setMax(100);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i;
                        for( i=0; i<100 ; i++){
                            progressBar_horizontal.setProgress(i);
                            SystemClock.sleep(100);
                        }
                    }
                }).start();

            }
        });

        button_run_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog_spinner = new ProgressDialog( ProgressBarActivity.this);
                progressDialog_spinner.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog_spinner.setTitle("進度執行中...");
                progressDialog_spinner.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i;
                        for(i=0; i<100; i++){
                            progressDialog_spinner.setMessage("進度已執行："+i+"%");
                            SystemClock.sleep(100);
                        }
                    }
                }).start();

            }
        });
    }
}