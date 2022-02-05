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

import org.w3c.dom.Text;

public class SignUpActivity extends AppCompatActivity {
    boolean validInput = true;
    TextView firstName, lastName, mobileNum;
    Button signUpBtn;
    Global global = new Global();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hide the top title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        firstName = findViewById(R.id.newFNameTxt);
        lastName = findViewById(R.id.newLNameTxt);
        mobileNum = findViewById(R.id.newMobileNumTxt);

        signUpBtn = findViewById(R.id.signUpBtn);

        Validation(mobileNum);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllFocus();
                EmptyFieldValidation(firstName, lastName, mobileNum);
                if(validInput) {
                    Intent i = new Intent(SignUpActivity.this, SignUp2Activity.class);
                    Bundle data = new Bundle();
                    data.putString("fName", String.valueOf(firstName.getText()));
                    data.putString("lName", String.valueOf(lastName.getText()));
                    data.putString("mobileNum", String.valueOf(mobileNum.getText()));
                    i.putExtras(data);
                    startActivity(i);
                }
            }
        });

        // Perform Login when user click enter on keyboard
        mobileNum.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    // Perform action on key press
                    global.hideKeyboard(SignUpActivity.this);
                    clearAllFocus();
                    EmptyFieldValidation(firstName, lastName, mobileNum);
                    if(validInput){
                        Intent i = new Intent(SignUpActivity.this, SignUp2Activity.class);
                        Bundle data = new Bundle();
                        data.putString("fName", String.valueOf(firstName.getText()));
                        data.putString("lName", String.valueOf(lastName.getText()));
                        data.putString("mobileNum", String.valueOf(mobileNum.getText()));
                        i.putExtras(data);
                        startActivity(i);
                    }
                    return true;
                }
                return false;
            }
        });


        TextView toLoginBtn = findViewById(R.id.userIdTxt);
        //Make specific text bold
        String sourceString = "Already have an account? <b>Login</b>" ;
        toLoginBtn.setText(Html.fromHtml(sourceString));
        //Add onclick listener to navigate to Login page
        toLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }
    //  Clear all EditText focus
    private void clearAllFocus(){
        firstName.clearFocus();
        lastName.clearFocus();
        mobileNum.clearFocus();
    }

    private void EmptyFieldValidation(TextView usernameTxt, TextView emailTxt, TextView mobileNumTxt){
        if(usernameTxt.getText().toString().equals("")){
            TextView fNameError = findViewById(R.id.fNameError);
            fNameError.setText("Please enter a first name");
            validInput = false;
        }
        if(emailTxt.getText().toString().equals("")){
            TextView lNameError = findViewById(R.id.lNameError);
            lNameError.setText("Please enter a last name");
            validInput = false;
        }
        if(mobileNumTxt.getText().toString().equals("")){
            TextView mobileNumError = findViewById(R.id.mobileNumError);
            mobileNumError.setText("Please enter a Mobile Number");
            validInput = false;
        }
    }

    private void Validation(TextView mobileNum) {
        mobileNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                TextView mobileNumError = findViewById(R.id.mobileNumError);
                int length = String.valueOf(mobileNum).length();
                if(length != 8) {
                    mobileNumError.setText("Invalid Mobile Number");
                    validInput = false;
                }
                else {
                    mobileNumError.setText("");
                    validInput = true;
                }
            }
        });
    }
}