package com.android.internal.taskmaster.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;
import com.android.internal.taskmaster.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class AddTaskActivity extends AppCompatActivity {
    List<Team> teams = new ArrayList<>();
    CompletableFuture<List<Team>> teamFuture;
    Spinner teamSpinner = null;

    public static final String TAG = "activity status im in";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        //Amplify.API.query(Team.class, )

        setUpSpinner();
        setUpTotalTask();
        setUpSaveButton();

    }

    private void setUpSpinner(){

        ArrayList<String> status = new ArrayList<>();
        String inProgress = "In Progress";
        String newish = "new";
        String assigned = "Assigned";
        String complete= "complete";

        //add to stack of arraylist
        status.add(inProgress);
        status.add(newish);
        status.add(assigned);
        status.add(complete);

        teamSpinner = findViewById(R.id.taskTeamSpinner);
        Amplify.API.query(
                ModelQuery.list(Team.class),
                success -> {
                    Log.i(TAG, "Read Teams successfully!");
                    ArrayList<String> teamNames = new ArrayList<>();

                    for (Team team : success.getData()) {
                        teams.add(team);
                        teamNames.add(team.getName());
                    }
                    teamFuture.complete(teams);

                    runOnUiThread(() -> {
                        teamSpinner.setAdapter(new ArrayAdapter<>(
                                this,
                                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                                teamNames));
                    });
                },
                failure -> {
                    teamFuture.complete(null);
                    Log.e(TAG, "Did not read teams successfully");
                }
        );



        Spinner taskCategorySpinner = findViewById(R.id.spinnerTaskCategoryInput);
        taskCategorySpinner.setAdapter(new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                status//TODO: check if this is correct syntax
        ));
    }

    private void setUpSaveButton()
    {
        Spinner taskCategorySpinner = findViewById(R.id.spinnerTaskCategoryInput);
        Button saveNewTaskButton = findViewById(R.id.buttonAddTask);
        saveNewTaskButton.setOnClickListener( v -> {


            //TODO: team relationship base setup - setup try catch

            String taskTitleInput = ((EditText)findViewById(R.id.editTaskTitle)).getText().toString();
            String taskBodyInput = ((EditText) findViewById(R.id.editTextDescription)).getText().toString();
            String currentDateString = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());
            try{
                teams = teamFuture.get();
            } catch (InterruptedException ie){
                Log.e(TAG, "InterruptedException while getting teams");
                Thread.currentThread().interrupt();
            } catch (ExecutionException ee) {
                Log.e(TAG, "ExecutionException while getting teams");
            }
            String selectedTeamString = teamSpinner.getSelectedItem().toString();
            Team selectedTeam = teams.stream().filter(team -> team.getName().equals(selectedTeamString)).findAny().orElseThrow(RuntimeException::new);



            Task newTask = Task.builder()
                    .name("Test Title")
                    .description("This is a test boddy")
                    .status("Complete")
                    .build();
            Amplify.API.mutate(
                    ModelMutation.create(newTask),  // making a GraphQL request to the cloud
                    successResponse -> Log.i(TAG, "ProductListActivity.onCreate(): made a product successfully"),  // success callback
                    failureResponse -> Log.i(TAG, "ProductListActivity.onCreate(): failed with this response: " + failureResponse)  // failure callback
            );
            Toast.makeText(AddTaskActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
            finish();
        });

    }

    private void setUpTotalTask(){
        Intent callingIntent = getIntent();
        int size = callingIntent.getIntExtra(MainActivity.TASK_SIZE, 0);
        TextView totalTaskView = findViewById(R.id.stringTotalTask);
        totalTaskView.setText("Total Tasks: " + size);
    }


    /**
     * this even will enable the back function to the button on press
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