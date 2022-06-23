package com.android.internal.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //callling the action bar
        ActionBar actionBar = getSupportActionBar();
        //showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        /*
            badass way
        */
        //button1 = button add task but I'm lazy. next time
        Button button1 = MainActivity.this.findViewById(R.id.button1);
        button1.setOnClickListener(v ->{
            Intent goToAddTasksFromIntent = new Intent(MainActivity.this, AddTask.class);
            startActivity(goToAddTasksFromIntent);
        });

        /*
         another way to do it
        */

        //button2 = button all task but I'm lazy. next time
        Button buttonAlltask = findViewById(R.id.button2);
        buttonAlltask.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View view){
            Intent goToAllTaskIntent = new Intent(MainActivity.this, AllTasks.class);
            startActivity(goToAllTaskIntent);
        }

        });
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