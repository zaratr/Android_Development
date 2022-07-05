package com.android.internal.taskmaster.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amplifyframework.datastore.generated.model.Task;
import com.android.internal.taskmaster.R;
import com.android.internal.taskmaster.activity.MainActivity;
import com.android.internal.taskmaster.activity.TaskDetail;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


// TODO: Step 1-4: Make a class whose sole purpose is to manage RecyclerViews: a RecyclerView.Adapter
// TODO: Step 3-1: Clean up the RecyclerView.Adapter references to actually use ProductListRecyclerViewAdapter
// (You'll need to change it in the methods below also)
public class TaskListRecyclerReviewAdapter extends RecyclerView.Adapter<TaskListRecyclerReviewAdapter.TaskListViewHolder> {

    // TODO: Step 2-3: Hand in data items
    List<Task> tasks;
    // TODO: Step 3-2: Hand in the activity context
    Context callingActivity;

    // TODO: Step 3-2: Hand in the activity context
    public TaskListRecyclerReviewAdapter(List<Task> _task, Context _callingActivity) {
        this.tasks = _task;
        this.callingActivity = _callingActivity;
    }


    @NonNull
    @Override
    // TODO: Step 3-1: change to ProductListViewHolder
    public TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // TODO: Step 1-7: Inflate fragment
        View taskFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task_list, parent, false);
        // TODO: Step 1-9: Attach fragment to ViewHolder
        return new TaskListViewHolder(taskFragment);
    }

    // TODO: Step 2-5: Bind data items to Fragments inside of ViewHolders
    // TODO: Step 3-1: change to ProductListViewHolder
    @Override
    public void onBindViewHolder(@NonNull TaskListRecyclerReviewAdapter.TaskListViewHolder holder, int position) {
        TextView taskFragmentTextView = holder.itemView.findViewById(R.id.taskListFragTextView);
        String taskName = tasks.get(position).getName();
        taskFragmentTextView.setText(position + ". " + taskName);

        // TODO: Step 3-3: Make OnClickHandler so we can interact with the RecyclerView items
        View taskViewHolder = holder.itemView;
        taskViewHolder.setOnClickListener(v ->{
            Intent goToTaskDetailIntent = new Intent(callingActivity, TaskDetail.class);
            goToTaskDetailIntent.putExtra(MainActivity.TASK_BODY, taskName);
            callingActivity.startActivity(goToTaskDetailIntent);
        });
    }

    @Override
    public int getItemCount() {
        // TODO: Step 1-10: For testing purposes, hardcode a large number of items
//        return 100;
        // TODO: Step 2-5: Make the size of the list dynamic
        return tasks.size();
    }

    // TODO: Step 1-8: Make a ViewHolder class to hold a fragment
    public static class TaskListViewHolder extends RecyclerView.ViewHolder{
        public TaskListViewHolder(View fragmentItemView) {
            super(fragmentItemView);
        }
    }

}
