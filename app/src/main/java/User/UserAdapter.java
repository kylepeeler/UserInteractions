package User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import io.kylepeeler.userinteractions.R;

/**
 * Created by Kyle on 8/4/16.
 */
public class UserAdapter extends ArrayAdapter<UserEndpoint.User>{
    public UserAdapter(Context context, ArrayList<UserEndpoint.User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final UserEndpoint.User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);
        // Populate the data into the template view using the data object
        tvName.setText("Username: " + user.name);
        tvEmail.setText("Email: " + user.email);
        // Return the completed view to render on screen
        return convertView;
    }
}
