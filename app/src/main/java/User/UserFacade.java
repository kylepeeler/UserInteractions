package User;

import android.os.AsyncTask;
import android.widget.ListView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by Kyle on 8/2/16.
 */
public class UserFacade extends AsyncTask<Void, Void, ArrayList<UserEndpoint.User>>{


    public ArrayList<UserEndpoint.User> getUsers() throws IOException{
        return UserGenerator.createService(UserEndpoint.class).listUsers().execute().body();
    }

    @Override
    protected ArrayList<UserEndpoint.User> doInBackground(Void... voids) {
        try {
            return getUsers();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<UserEndpoint.User> users) {
        if (users != null){
            for (UserEndpoint.User user : users){
                System.out.println("User: " + user.toString());
            }
        }else{
            System.out.println("Oops we got no users.");
        }
    }


}
