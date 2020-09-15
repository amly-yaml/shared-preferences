package com.example.sharedpreference_21apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class HoneyDo_Activity extends AppCompatActivity {
    private Button saveButton;
    private EditText enterMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honey_do_);

        saveButton = findViewById(R.id.savedoButton);
        enterMessage = findViewById(R.id.editText);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enterMessage.getText().toString().equals("")){
                    writeToFile(enterMessage.getText().toString());
                }
                else {
                    Toast.makeText(HoneyDo_Activity.this, "There is no list in the file.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        try {
            if (readFromFile() != null)
            {
                enterMessage.setText(readFromFile());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String message){
        // write the file using outputStreamWriter into the file called todoList.txt
        // mode is private which not sharing the data text file with other application
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("todoList.txt", Context.MODE_PRIVATE));
            // write the data in the text file
            outputStreamWriter.write(message);
            outputStreamWriter.close();  // always close your stream
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFromFile() throws IOException {

        String result = "";
        // open the file to read
        InputStream inputStream = openFileInput("todoList.txt");

        // read the file
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        // buffer the file to read faster
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        // create new string variable to get the data inside the file
        String temstring = "";
        // build the string which in the text file data
        StringBuilder stringBuilder = new StringBuilder();

        while ((temstring = bufferedReader.readLine()) != null){
            // logic, keep doing the sate until not true
            stringBuilder.append(temstring);

        }
        inputStream.close();
        result= stringBuilder.toString();
        return result;
    }
}
