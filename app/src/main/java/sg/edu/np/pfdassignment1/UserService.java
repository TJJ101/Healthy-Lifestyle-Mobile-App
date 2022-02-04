package sg.edu.np.pfdassignment1;

import android.app.Application;
import android.util.JsonReader;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface UserService {
    @GET("user/{email}")
    Call<User> getUser(@Path("email") String email);

    @Headers({"Content-Type: application/json"})
    @POST("register/{email}")
    Call<ResponseBody> create(@Path("email") String email , @Body String userDetails);

    @Headers({"Content-Type: application/json"})
    @POST("{userId}")
    Call<Password> createPassword(@Path("userId") int userId , @Body Password passwordDetails);

}
