package com.taskmaster2.taskmaster2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String TitleEditText;
    String DescriptionEditText;
    TextView title_name;
    TextView description_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAddTask = findViewById(R.id.button1);
        Button buttonAllTasks = findViewById(R.id.button2);

        intentManager(buttonAddTask, 1);
        intentManager(buttonAllTasks, 2);

        if(getIntent().hasExtra("editTextTitle") && getIntent().hasExtra("editTextDescription")){
            TitleEditText = getIntent().getStringExtra("editTextTitle");
            title_name = findViewById(R.id.title_name);
            title_name.setText(TitleEditText);

            DescriptionEditText = getIntent().getStringExtra("editTextDescription");
            description_name = findViewById(R.id.description_name);
            description_name.setText(DescriptionEditText);
        }
        else{
            title_name = null;
            description_name = null;
        }

    }

    public void intentManager(Button button, int finder){
        switch(finder){
            case 1:
                /*
                    option 1: intentCreation
                */
                button.setOnClickListener(v ->{
                    Intent goToAddTasksFromIntent = new Intent(MainActivity.this, AddTaskActivity.class);
                    startActivity(goToAddTasksFromIntent);
                });
                break;
            case 2:
                /*
                    option 2: intentCreation
                */
                button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent goToAllTaskIntent = new Intent(MainActivity.this, AddTaskActivity.class);
                        startActivity(goToAllTaskIntent);
                    }
                });
        }//swtich
    }//intentManager
}//onCreate