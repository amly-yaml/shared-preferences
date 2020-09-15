package com.example.sharedpreference_21apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText enterName;
    private Button saveButton, nextButton;
    private TextView textView;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "myPrefFile";  // internally file name


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setID();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Stringname = file_name, mode=0: file can be accessible level 0
                sharedPreferences = getSharedPreferences(PREFS_NAME, 0);

                // sharedpreference editor that adding item into our preference class
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("message", enterName.getText().toString());
                // save the editor
                editor.commit();

            }
        });
        // give the data back
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, 0);
        if (preferences.contains("message")){
            String message = preferences.getString("message", "Not Found");
            textView.setText("Message" + message);
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HoneyDo_Activity.class);
                startActivity(intent);
            }
        });



    }

    public void setID(){
        enterName = findViewById(R.id.editNameID);
        saveButton = findViewById(R.id.savedoButton);
        textView = findViewById(R.id.textViewID);
        nextButton = findViewById(R.id.nextButton);
    }
}
