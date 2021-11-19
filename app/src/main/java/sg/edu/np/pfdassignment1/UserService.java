package sg.edu.np.pfdassignment1;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface UserService {
    @GET("user/{email}")
    Call<User> getUser(@Path("email") String email);

    @POST("user/new")
    Call<User> create(@Body User user);
}
