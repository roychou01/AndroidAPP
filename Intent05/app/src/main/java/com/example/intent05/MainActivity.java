package com.example.intent05;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button_web_search,button_Choice_contact;
    EditText edittext_keyword;

    private static final int GET_CONTACT= 1002;

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
        button_web_search = findViewById(R.id.button_web_search);
        button_Choice_contact = findViewById(R.id.button_choice_contact);
        edittext_keyword = findViewById(R.id.edittext_keyword);

        button_web_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strKeyword = edittext_keyword.getText().toString();
                Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
                i.putExtra(SearchManager.QUERY, strKeyword);
                startActivity(i);
            }
        });

        button_Choice_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType(ContactsContract.Contacts.CONTENT_TYPE);
    //            startActivityForResult(i, GET_CONTACT);     舊版
                launcher_1.launch(i);
            }
        });
    }

    ActivityResultLauncher launcher_1 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

        @Override
        public void onActivityResult(ActivityResult o) {
            if(o.getResultCode() == RESULT_OK ){
                String strContact = o.getData().getData().toString();

                Toast.makeText(MainActivity.this, strContact, Toast.LENGTH_LONG).show();
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(strContact));
                startActivity(i);
            }

        }
    });

    // 舊版寫法
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data){
//            super.onActivityResult(requestCode, resultCode, data);
//
//            if(requestCode == GET_CONTACT) {
//                if(resultCode == RESULT_OK ){
//                    String strContact = data.getData().toString();
//                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(strContact));
//
//                    Toast.makeText(MainActivity.this, strContact, Toast.LENGTH_LONG).show();
//                    startActivity(i);
//                }
//            }
//        }
}