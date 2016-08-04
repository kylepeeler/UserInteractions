package io.kylepeeler.userinteractions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import User.UserFacade;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        UserFacade userFacade = new UserFacade();
        userFacade.execute();

    }
}
