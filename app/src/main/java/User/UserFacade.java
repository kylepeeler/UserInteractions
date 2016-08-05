package User;

import android.content.Context;
import android.view.View;

/**
 * Created by Kyle on 8/4/16.
 */
public class UserFacade {
    private Context context;
    private View view;

    public UserFacade(Context context, View view){
        this.context = context;
        this.view = view;
    }

    public void createUser(String name, String email){
        CreateUserTask createUserTask = new CreateUserTask(this.context, this.view);
        createUserTask.execute(new UserEndpoint.User().setName(name).setEmail(email));
    }

    public void updateUser(String _id, String name, String email){
        UpdateUserTask updateUserTask = new UpdateUserTask(this.context, this.view);
        updateUserTask.execute(new UserEndpoint.User().setId(_id).setName(name).setEmail(email));
    }

    public void listUsers(){
        GetUsersTask getUsersTask = new GetUsersTask(this.context, this.view);
        getUsersTask.execute();
    }

    public void deleteUsers(String userId){
        DeleteUserTask deleteUserTask = new DeleteUserTask(this.context, this.view);
        deleteUserTask.execute(new UserEndpoint.User().setId(userId));
    }

}
