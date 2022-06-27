package com.android.internal.taskmaster.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.internal.taskmaster.R;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
    }
}