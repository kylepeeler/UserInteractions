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
public class GetUsersTask extends AsyncTask<Void, Void, ArrayList<UserEndpoint.User>>{

    private Context mContext;
    private View view;



    public GetUsersTask(Context context, View view){
        this.mContext = context;
        this.view = view;
    }
    public ArrayList<UserEndpoint.User> getUsers() throws IOException{
        return UserGenerator.createService(UserEndpoint.class).getUsers().execute().body();
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
            //reverse array list to show at top
            Collections.reverse(users);
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
