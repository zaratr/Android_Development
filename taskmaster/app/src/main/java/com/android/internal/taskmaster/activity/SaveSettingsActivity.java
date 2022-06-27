package com.android.internal.taskmaster.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.internal.taskmaster.R;

public class SaveSettingsActivity extends AppCompatActivity {

    //setup shared preferences
    SharedPreferences preferences;
    //preference tag
    public static final String USER_NICKNAME_TAG= "temp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_settings);

        //i create the shared preference instance
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userNickname = preferences.getString(USER_NICKNAME_TAG, "");
        if(!userNickname.isEmpty())
        {
            EditText userNicknameEdited = findViewById(R.id.editUsernameBox);
            userNicknameEdited.setText(userNickname);
        }


        //get saved button
        Button userSaveButton = findViewById(R.id.saveButton);
        userSaveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //setting up the editor.
                SharedPreferences.Editor preferenceEditor = preferences.edit();
                EditText userNicknameText = findViewById(R.id.editUsernameBox);
                String userUsernameString = userNicknameText.getText().toString();
                preferenceEditor.putString(USER_NICKNAME_TAG, userUsernameString);
                preferenceEditor.apply();
            }
        });
    }
}