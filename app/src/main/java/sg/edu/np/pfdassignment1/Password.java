package sg.edu.np.pfdassignment1;

import java.io.Serializable;

public class Password implements Serializable {
    private String nameOfPassword;
    public String getNameOfPassword(){return nameOfPassword;}
    public void setNameOfPassword(String nameOfPassword) { this.nameOfPassword = nameOfPassword; }

    private String username;
    public String getUsername(){return username;}
    public void setUsername(String username) {
        this.username = username;
    }

    private String password;
    public String getPassword() {return password;}
    public void setPassword(String password) {
        this.password = password;
    }

    public Password() {}
    public Password(String n, String u, String p) {
        this.nameOfPassword = n;
        this.username = u;
        this.password = p;
    }
}
