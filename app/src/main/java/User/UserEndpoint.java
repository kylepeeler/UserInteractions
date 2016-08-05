package User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Kyle on 8/2/16.
 */
public interface UserEndpoint {
    class User {
        public String _id;
        public String name;
        public String email;

        User setId(String _id){
            this._id = _id;
            return this;
        }

        User setName(String name){
            this.name = name;
            return this;
        }

        User setEmail(String email){
            this.email = email;
            return this;
        }

        @Override
        public String toString() {
            return "ID: " + _id + ", Name: " + name + ", Email: " + email;
        }
    }

    @GET("/user")
    Call<ArrayList<User>> getUsers();

    @PUT("/user")
    Call<User> putUser(
            @Body User user
    );

    @POST("/user")
    Call<User> postUser(
            @Body User user
    );

    @DELETE("/user/{userId}")
    Call<User> deleteUser(
            @Path("userId") String userId
    );
}
