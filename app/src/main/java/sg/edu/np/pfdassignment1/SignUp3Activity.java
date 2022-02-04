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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp3Activity extends AppCompatActivity {
    boolean validInput = true;
    TextView emailTxt, passwordTxt, cfmPasswordTxt;
    Button signUpBtn;
    Global global = new Global();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3);

        //Hide the top title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        emailTxt = findViewById(R.id.newEmailTxt);
        passwordTxt = findViewById(R.id.newPasswordTxt);
        cfmPasswordTxt = findViewById(R.id.cfmPasswordTxt);

        signUpBtn = findViewById(R.id.signUpBtn3);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global.hideKeyboard(SignUp3Activity.this);
                clearAllFocus();
                EmptyFieldValidation(emailTxt, passwordTxt, cfmPasswordTxt);
                if(validInput) {
                    try {
                        SignUp(emailTxt, passwordTxt);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //      Perform Login when user click enter on keyboard
        cfmPasswordTxt.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    // Perform action on key press
                    global.hideKeyboard(SignUp3Activity.this);
                    clearAllFocus();
                    EmptyFieldValidation(emailTxt, passwordTxt, cfmPasswordTxt);
                    if(validInput){
                        try {
                            SignUp(emailTxt, passwordTxt);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                }
                return false;
            }
        });

        TextView toLoginBtn = findViewById(R.id.userIdTxt3);
        //Make specific text bold
        String sourceString = "Already have an account? <b>Login</b>" ;
        toLoginBtn.setText(Html.fromHtml(sourceString));
        //Add onclick listener to navigate to Login page
        toLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp3Activity.this, LoginActivity.class));
            }
        });
    }

    //  Clear all EditText focus
    private void clearAllFocus(){
        emailTxt.clearFocus();
        passwordTxt.clearFocus();
        cfmPasswordTxt.clearFocus();
    }

    private void EmptyFieldValidation(TextView emailTxt, TextView passwordTxt, TextView cfmPasswordTxt) {
        if (emailTxt.getText().toString().equals("")) {
            TextView emailError = findViewById(R.id.eError);
            emailError.setText("Please enter an email address");
            validInput = false;
        }
        if (passwordTxt.getText().toString().equals("")) {
            TextView passwordError = findViewById(R.id.pError);
            passwordError.setText("Please enter a password");
            validInput = false;
        }
        if (!passwordTxt.getText().toString().equals("") && cfmPasswordTxt.getText().toString().equals("")) {
            TextView cfmPasswordError = findViewById(R.id.cError);
            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) cfmPasswordError.getLayoutParams();
            cfmPasswordError.setText("Please re-enter your password");
            params.height = 50;
            validInput = false;
        }
    }

    //  Validation for all fields after EditText lose focus (User change to different text box)
    private void Validation(TextView emailTxt, TextView passwordTxt, TextView cfmPasswordTxt){
        /*emailTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                TextView emailError = findViewById(R.id.eError);
                if(!hasFocus){
                    if(!emailTxt.getText().toString().equals("") && !global.isValidEmail(emailTxt.getText()))
                    {
                        emailError.setText("Please enter a valid email address");
                        validInput = false;
                    }
                }
                else {
                    emailError.setText("");
                    validInput = true;
                }
            }
        }); */

        passwordTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                TextView passwordError = findViewById(R.id.pError);
                if(!hasFocus){
                    if(!passwordTxt.getText().toString().equals("") && passwordTxt.getText().toString().length() < 6)
                    {
                        passwordError.setText("Password must have at least 6 characters");
                        validInput = false;
                    }
                }
                else {
                    passwordError.setText("");
                    validInput = true;
                }
            }
        });

        cfmPasswordTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                TextView cfmPasswordError = findViewById(R.id.cError);
                ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) cfmPasswordError.getLayoutParams();
                if(!hasFocus){
                    if(!cfmPasswordTxt.getText().toString().equals("") && !passwordTxt.getText().toString().equals(cfmPasswordTxt.getText().toString())){
                        cfmPasswordError.setText("Password does not match");
                        validInput = false;
                    }
                    params.height = 50;
                }
                else {
                    cfmPasswordError.setText("");
                    validInput = true;
                    params.height = 0;
                }
                cfmPasswordError.setLayoutParams(params);
            }
        });
    }
    private void SignUp(TextView emailTxt, TextView passwordTxt) throws JSONException {
        Bundle data = getIntent().getExtras();

        // Getting data from SignUpActivity & SignUp2Activity
        String fName = data.getString("fName");
        String lName = data.getString("lName");
        String nric = data.getString("nric");
        String mobileNum = data.getString("mobileNum");
        String addr = data.getString("address");
        Boolean disability = data.getBoolean("disability");

        int doctorId = (int) ( Math.random() * 2 + 1);

        JSONObject userDetails = new JSONObject();
        try {
            userDetails.put("first_name", fName);
            userDetails.put("last_name", lName);
            userDetails.put("email", String.valueOf(emailTxt.getText()));
            userDetails.put("mobileNum", mobileNum);
            userDetails.put("nric", nric);
            userDetails.put("addr", addr);
            userDetails.put("password1", String.valueOf(passwordTxt.getText()));
            userDetails.put("password2", String.valueOf(passwordTxt.getText()));
            userDetails.put("doctor_id", doctorId);
            userDetails.put("disabilities1", disability);
            userDetails.put("disabilities2", false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("JSON TEST", String.valueOf(userDetails));

        String url = ApiClient.BASE_URL + "register/" + userDetails.get("email") + userDetails;
        UserService service = ApiClient.getClient().create(UserService.class);
        Call<ResponseBody> createCall = service.create(userDetails.getString("email"), userDetails.toString());
        createCall.enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("SignUp Test Resp", String.valueOf(response.body()));
                Log.d("SignUp Test Call", String.valueOf(call.request()));
                Log.d("SignUP Test CreateCall", String.valueOf(createCall.request()));
                if(response.isSuccessful()) {
                    successfulLogin();
                }
                else {
                    unsuccessfulLogin();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("SignUP Test Fail", "FAIL  ON THE RESPONSE");
                t.printStackTrace();
                unsuccessfulLogin();
            }
        });
    }


    private void successfulLogin() {
        Intent i = new Intent(SignUp3Activity.this, MainActivity.class);
        boolean loggedIn = true;
        //Bundle bundle = new Bundle();
        //bundle.putBoolean("Log in", loggedIn);
        //bundle.putString("Email", u.email);
        i.putExtra("Log in", loggedIn);
        startActivity(i);
    }
    private void unsuccessfulLogin() {
        Toast.makeText(SignUp3Activity.this, "Authentication failed.",
                Toast.LENGTH_SHORT).show();
        boolean loggedIn = false;
    }
}
