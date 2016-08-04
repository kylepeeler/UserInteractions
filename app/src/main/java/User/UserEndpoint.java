package User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Kyle on 8/2/16.
 */
public interface UserEndpoint {
    static class User {
        String name;
        String email;

        @Override
        public String toString() {
            return "Name: " + name + ", Email: " + email;
        }
    }

    @GET("/user/")
    Call<ArrayList<User>> listUsers();
}
