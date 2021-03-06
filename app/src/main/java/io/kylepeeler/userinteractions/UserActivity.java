package io.kylepeeler.userinteractions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import User.UserEndpoint;
import User.UserFacade;

public class UserActivity extends AppCompatActivity {
    private UserFacade userFacade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        userFacade = new UserFacade(this, findViewById(R.id.userListView));
        ListView lv = (ListView) findViewById(R.id.userListView);
        lv.setOnItemClickListener(mMessageClickedHandler);
    }

    public void onPostClick(View v){
        EditText nameField = (EditText) findViewById(R.id.tfName);
        EditText emailField = (EditText) findViewById(R.id.tfEmail);
        String userInputName = nameField.getText().toString();
        String userInputEmail = emailField.getText().toString();
        if (userInputName.matches("") || userInputEmail.matches("")){
            Toast.makeText(this, "You must provide both a name and email", Toast.LENGTH_SHORT).show();
        }else{
            userFacade.createUser(userInputName, userInputEmail);
        }
    }

    public void onPutClick(View v){
        EditText idField = (EditText) findViewById(R.id.tfID);
        EditText nameField = (EditText) findViewById(R.id.tfName);
        EditText emailField = (EditText) findViewById(R.id.tfEmail);
        String userInputID = idField.getText().toString();
        String userInputName = nameField.getText().toString();
        String userInputEmail = emailField.getText().toString();
        if (userInputID.matches("")){
            Toast.makeText(this, "You must provide an ID", Toast.LENGTH_SHORT).show();
        }else if (userInputName.matches("") && userInputEmail.matches("")){
                Toast.makeText(this, "You must provide a value to update", Toast.LENGTH_SHORT).show();
        }
        else{
                userFacade.updateUser(userInputID, userInputName, userInputEmail);
        }
    }

    public void onGetClick(View v){
        userFacade.listUsers();
    }

    public void onDeleteClick(View v){
        EditText idField = (EditText) findViewById(R.id.tfID);
        String userInputId = idField.getText().toString();
        if (userInputId.matches("")){
            Toast.makeText(this, "You must provide an ID", Toast.LENGTH_SHORT).show();
        }else{
            userFacade.deleteUsers(userInputId);
        }

    }

    // Fill in values when clicking on a list item
    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id)
        {
            UserEndpoint.User userToFill = (UserEndpoint.User) parent.getAdapter().getItem(position);
            //System.out.println("User to fill: " + userToFill.toString());
            EditText idField = (EditText) findViewById(R.id.tfID);
            EditText nameField = (EditText) findViewById(R.id.tfName);
            EditText emailField = (EditText) findViewById(R.id.tfEmail);
            idField.setText(userToFill._id);
            nameField.setText(userToFill.name);
            emailField.setText(userToFill.email);
        }
    };


}
