package sg.edu.np.pfdassignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                //Login thingy here when the database stuff is done
                if(validInput){
                    //For successful Login
                    Log.d("Log in", "Successful Login");
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    loggedIn = true;
                    i.putExtra("loggedIn", loggedIn);
                    LoginActivity.this.startActivity(i);
                }
                else{
                    Log.d("Log in", "Unsuccessful Login");
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    TextView passwordError = findViewById(R.id.pError2);
                    ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) passwordError.getLayoutParams();
                    passwordError.setText("Invalid email or password");
                    params.height = 50;
                    passwordError.setLayoutParams(params);
                    loggedIn = false;
                }
            }
        });
    }

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

    private void Validate(TextView email, TextView password){
        emailTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                TextView emailError = findViewById(R.id.eError2);
                if(!hasFocus){
                    if(!emailTxt.getText().toString().equals("") && !Global.isValidEmail(emailTxt.getText()))
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

        passwordTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
}