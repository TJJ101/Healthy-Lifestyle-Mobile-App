package sg.edu.np.pfdassignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    boolean validInput = true;
    TextView emailTxt, passwordTxt;
    Global global = new Global();
    boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Hide the top title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        //Assigning layout fields to a variable
        emailTxt = findViewById(R.id.emailTxt);
        passwordTxt = findViewById(R.id.passwordTxt);
        Button loginBtn = findViewById(R.id.loginBtn);

        //Validating inputs
        Validate(emailTxt, passwordTxt);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global.hideKeyboard(LoginActivity.this);
                clearAllFocus();
                EmptyInputValidation(emailTxt, passwordTxt);
                if(validInput){
                    //For successful Login
                    String url = ApiClient.BASE_URL + "user/" + String.valueOf(emailTxt.getText());
                    UserService service = ApiClient.getClient().create(UserService.class);
                    Call<User> createCall = service.getUser(String.valueOf(emailTxt.getText()));
                    createCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User u = (User) response.body();
                            if (u != null && u.id != 0) {
                                byte[] encryptPassword1 = new byte[0];
                                encryptPassword1 = digest(String.valueOf(passwordTxt.getText()));
                                String encryptPassword = bytesToHex(encryptPassword1);
                                Log.d("Test EncryptPass", String.valueOf(encryptPassword));
                                if(u.password.equals(encryptPassword)) { successfulLogin(u); }
                                else { unsuccessfulLogin(); }
                            }
                            else { unsuccessfulLogin(); }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t){
                                t.printStackTrace();
                                unsuccessfulLogin();
                        }
                    });
                }
                else{ unsuccessfulLogin(); }
            }
        });

        //Perform Login when user click enter on keyboard
        passwordTxt.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    // Perform action on key press
                    global.hideKeyboard(LoginActivity.this);
                    clearAllFocus();
                    EmptyInputValidation(emailTxt, passwordTxt);
                    if(validInput) {
                        String url = ApiClient.BASE_URL + "user/" + String.valueOf(emailTxt.getText());
                        UserService service = ApiClient.getClient().create(UserService.class);
                        Call<User> createCall = service.getUser(String.valueOf(emailTxt.getText()));
                        createCall.enqueue(new Callback<User>() {
                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                User u = (User) response.body();
                                if (u != null && u.id != 0) {
                                    Log.d("TEST DATABASE", u.password);
                                    byte[] encryptPassword1 = new byte[0];
                                    encryptPassword1 = digest(String.valueOf(passwordTxt.getText()));
                                    String encryptPassword = bytesToHex(encryptPassword1);
                                    Log.d("Test EncryptPass", String.valueOf(encryptPassword));
                                    if(u.password.equals(encryptPassword)) { successfulLogin(u); }
                                    else { unsuccessfulLogin(); }
                                }
                                else { unsuccessfulLogin(); }
                            }
                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                t.printStackTrace();
                                unsuccessfulLogin();
                            }
                        });
                    }
                    else { unsuccessfulLogin(); }
                    return true;
                }
                return false;
            }
        });

        TextView toSignUpBtn = findViewById(R.id.toSignUp);
        //Make specific text bold
        String sourceString = "Don't have an account? <b>Sign Up</b>" ;
        toSignUpBtn.setText(Html.fromHtml(sourceString));
        //Add onclick listener to navigate to Sign Up page
        toSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
    }

    //For Validation
    private void EmptyInputValidation(TextView emailTxt, TextView passwordTxt){
        if(emailTxt.getText().toString().equals("")){
            TextView emailError = findViewById(R.id.eError2);
            emailError.setText("Please enter your email address");
            validInput = false;
        }
        if(passwordTxt.getText().toString().equals("")){
            TextView passwordError = findViewById(R.id.pError2);
            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) passwordError.getLayoutParams();
            passwordError.setText("Please enter your password");
            params.height = 50;
            passwordError.setLayoutParams(params);
            validInput = false;
        }
    }

    //Validation
    private void Validate(TextView email, TextView password){
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                TextView emailError = findViewById(R.id.eError2);
                if(!hasFocus){
                    if(!email.getText().toString().equals("") && !Global.isValidEmail(emailTxt.getText()))
                    {
                        emailError.setText("Please enter a valid email address");
                        validInput = false;
                    }
                }
                else{
                    emailError.setText("");
                    validInput = true;
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                TextView passwordError = findViewById(R.id.pError2);
                ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) passwordError.getLayoutParams();
                if(hasFocus){
                    passwordError.setText("");
                    validInput = true;
                    params.height = 0;
                    passwordError.setLayoutParams(params);
                }
            }
        });
    }
    private void clearAllFocus(){
        emailTxt.clearFocus();
        passwordTxt.clearFocus();
    }

    private void successfulLogin(User u) {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        loggedIn = true;
        Bundle bundle = new Bundle();
        bundle.putBoolean("Log in", loggedIn);
        bundle.putString("Email", u.email);
        i.putExtras(bundle);
        LoginActivity.this.startActivity(i);
    }

    private void unsuccessfulLogin() {
        Toast.makeText(LoginActivity.this, "Authentication failed.",
                Toast.LENGTH_SHORT).show();
        TextView passwordError = findViewById(R.id.pError2);
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) passwordError.getLayoutParams();
        passwordError.setText("Invalid email or password");
        params.height = 50;
        passwordError.setLayoutParams(params);
        loggedIn = false;
    }
    /*
    public byte[] digest(String message) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(message.getBytes(StandardCharsets.UTF_8));
    }
    */

    //For Sha-256 Hashing
    public byte[] digest(String input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input.getBytes());
        return result;
    }

    //Changing bytes to hex
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}