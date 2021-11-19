package sg.edu.np.pfdassignment1;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    int id;

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    @SerializedName("type")
    String type;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
