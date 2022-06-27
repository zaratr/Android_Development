package com.android.internal.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    //TaskListRecyclerReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //callling the action bar
        ActionBar actionBar = getSupportActionBar();
        //showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        setUpSettingsImageView();
        buttonGoTo();
        //setUpTaskListRecycleView();

    }

    @Override
    protected  void onResume(){

    super.onResume();
    String userNameUpdater = preferences.getString(SaveSettingsActivity.USER_NICKNAME_TAG, "Nothing in name tag");
    TextView userNameTextHome = findViewById(R.id.homeNickName);
    userNameTextHome.setText(userNameUpdater);

    }



    private void buttonGoTo()
    {
        //button1 = button add task but I'm lazy. next time
        Button button1 = MainActivity.this.findViewById(R.id.button1);
        Intent goToAddTasksFromIntent = new Intent(MainActivity.this, AddTaskActivity.class);
        setUpButton(button1, goToAddTasksFromIntent);

        //button2 = button all task but I'm lazy. next time
        Button buttonAlltask = findViewById(R.id.button2);
        Intent goToAllTaskIntent = new Intent(MainActivity.this, AllTasksActivity.class);
        setUpButton(buttonAlltask, goToAddTasksFromIntent);


        Button buttonTaskDetail = findViewById(R.id.buttonTaskDetail);
        Intent goToTaskDetailIntent = new Intent(MainActivity.this, TaskDetail.class);
        setUpButton(buttonTaskDetail, goToTaskDetailIntent);

    }

    private void setUpButton(Button button, Intent toGoToIntent)
    {
        button.setOnClickListener(v ->{
            startActivity(toGoToIntent);
        });

    }

    private void setUpSettingsImageView(){

        //Button three = settings
        ImageButton userSettingsImageButton = (ImageButton) findViewById(R.id.settingsButton);
        userSettingsImageButton.setOnClickListener( v ->{
            Intent goToSettingsIntent = new Intent(MainActivity.this, SaveSettingsActivity.class);
            startActivity(goToSettingsIntent);
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