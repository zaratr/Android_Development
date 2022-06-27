package com.android.internal.taskmaster.models;

public class Task {

    String name;
    String paragraph;
    public Task(String paragraph, String name)
    {
        this.paragraph = paragraph;
        this.name = name;

    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
