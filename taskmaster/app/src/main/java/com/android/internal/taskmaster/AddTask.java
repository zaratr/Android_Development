package com.android.internal.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //callling the action bar
        ActionBar actionBar = getSupportActionBar();
        //showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        //buttons
        Button addTaskButton = findViewById(R.id.buttonAddTask);
        //eventlistener
       // addTaskButton.setOnClickListener(view -> ((TextView) findViewById(R.id.stringTotalTask)).setText(R.string.stringSubmitted));

    }

    // this even will enable the back function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}