package com.taskmaster2.taskmaster2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button add_button = findViewById(R.id.button_add_a_task);
        EditText editTextTitle = findViewById(R.id.edit_text_title);
        EditText editTextDescription = findViewById(R.id.edit_text_description);
        add_button.setOnClickListener(v ->{
            Intent goToMainFromIntent = new Intent(AddTaskActivity.this, MainActivity.class);
            goToMainFromIntent.putExtra("editTextTitle", editTextTitle.getText().toString());
            goToMainFromIntent.putExtra("editTextDescription", editTextDescription.getText().toString());
            startActivity(goToMainFromIntent);
        });
    }
}