package User;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import io.kylepeeler.userinteractions.R;

/**
 * Created by Kyle on 8/2/16.
 */
public class UpdateUserTask extends AsyncTask<UserEndpoint.User, Void, ArrayList<UserEndpoint.User>>{

    private Context mContext;
    private View view;

    public UpdateUserTask(Context context, View view){
        this.mContext = context;
        this.view = view;
    }

    public UserEndpoint.User updateUser(UserEndpoint.User user) throws IOException{
        return UserGenerator.createService(UserEndpoint.class).putUser(user).execute().body();
    }

    public ArrayList<UserEndpoint.User> getUsers() throws IOException{
        return UserGenerator.createService(UserEndpoint.class).getUsers().execute().body();
    }

    @Override
    protected ArrayList<UserEndpoint.User> doInBackground(UserEndpoint.User... users) {
        for (UserEndpoint.User user : users) {
            try {
                updateUser(user);
            } catch (IOException e) {
                System.err.println("Could not update user");
                e.printStackTrace();
            }
        }
        try {
            return getUsers();
        } catch (IOException e) {
            System.err.println("Could not get users after updating.");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(final ArrayList<UserEndpoint.User> users) {
        if (users != null){
            ListView lv1 = (ListView) view.findViewById(R.id.userListView);
            //reverse array list to show at top
            Collections.reverse(users);
            UserAdapter adapter = new UserAdapter(mContext, users);
            lv1.setAdapter(adapter);
            System.out.println("Printing Users\n--------------------");
            for (UserEndpoint.User user : users){
                System.out.println(user.toString());
            }
        }else{
            System.out.println("Oops we got no users.");
        }
    }

}
