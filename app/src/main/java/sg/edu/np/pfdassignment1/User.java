package sg.edu.np.pfdassignment1;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    int id;
    public void setId (int id) { this.id = id;}
    public int getId() {return id;}

    @SerializedName("email")
    String email;
    public void setEmail(String email) {this.email = email;}
    public String getEmail() {return email;}

    @SerializedName("password")
    String password;
    public void setPassword(String password) {this.password = password;}
    public String getPassword() {return password;}

    @SerializedName("type")
    String type;

    public User() {}
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
