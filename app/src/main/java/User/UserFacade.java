package User;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.kylepeeler.userinteractions.R;
import retrofit2.Call;

/**
 * Created by Kyle on 8/2/16.
 */
public class UserFacade extends AsyncTask<Void, Void, ArrayList<UserEndpoint.User>>{

    private Context mContext;
    private View view;



    public UserFacade (Context context, View view){
        this.mContext = context;
        this.view = view;
    }
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
    protected void onPostExecute(final ArrayList<UserEndpoint.User> users) {
        if (users != null){
            ListView lv1 = (ListView) view.findViewById(R.id.userListView);
            UserAdapter adapter = new UserAdapter(mContext, users);
            lv1.setAdapter(adapter);
            for (UserEndpoint.User user : users){
                System.out.println("User: " + user.toString());
            }
        }else{
            System.out.println("Oops we got no users.");
        }
    }


}
