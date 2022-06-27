package com.android.internal.taskmaster.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.internal.taskmaster.R;
import com.android.internal.taskmaster.adapter.TaskListRecyclerReviewAdapter;
import com.android.internal.taskmaster.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TASK_PARAGRAPH_TAG = "BODY";
    SharedPreferences preferences;
    private TaskListRecyclerReviewAdapter adapter;
    //TaskListRecyclerReviewAdapter adapter;


    /**
     * OnCreate - Renders on start all attributes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * BackButtonOption 1 - allows overriding for back button. part 2 below
         * callling the action bar
         */
        ActionBar actionBar = getSupportActionBar();
        //showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        setUpSettingsImageView();
        buttonGoTo();
        setUpTaskListRecycleView();

    }


    /**
     * OnResume - reRenders page with newly typed attributes to text
     */
    @Override
    protected  void onResume(){

    super.onResume();
    String userNameUpdater = preferences.getString(SaveSettingsActivity.USER_NICKNAME_TAG, "Nothing in name tag");
    TextView userNameTextHome = findViewById(R.id.homeNickName);
    userNameTextHome.setText(userNameUpdater);

    }


    /**
     * Helper function Button Creation -  to create button and listeners
     */

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

    /**
     * Creates a image button and simlilar to button on creation
     */

    private void setUpSettingsImageView(){

        //Button three = settings
        ImageButton userSettingsImageButton = (ImageButton) findViewById(R.id.settingsButton);
        userSettingsImageButton.setOnClickListener( v ->{
            Intent goToSettingsIntent = new Intent(MainActivity.this, SaveSettingsActivity.class);
            startActivity(goToSettingsIntent);
        });
    }


    /**
     * Recycle View
     */
    private void setUpTaskListRecycleView() {
        RecyclerView taskListRecycleReview = (RecyclerView) findViewById(R.id.productRecyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        taskListRecycleReview.setLayoutManager(layoutManager);

        List<Task> taskList = new ArrayList<Task>();
        taskList.add(new Task("Peace", "clothes and blanket"));
        taskList.add(new Task("Prosperity", "drop a blanket on dem"));
        taskList.add(new Task("Inclusivity", "drop a blanket on dem"));
        adapter = new TaskListRecyclerReviewAdapter(taskList, this);
        taskListRecycleReview.setAdapter(adapter);
        /*
         */
    }




    /**
     * BackButton part 2- this even will enable the back function to the button on press
     * @param item
     * @return
     */
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