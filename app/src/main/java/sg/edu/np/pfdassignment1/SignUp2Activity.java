package sg.edu.np.pfdassignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class SignUp2Activity extends AppCompatActivity {
    boolean validInput = true;
    boolean disability = false;
    TextView nric, address;
    Button signUpBtn;
    RadioButton disabilityTrue, disabilityFalse;
    Global global = new Global();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        //Hide the top title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        nric = findViewById(R.id.newNRICTxt);
        address = findViewById(R.id.newAddrTxt);
        disabilityFalse = findViewById(R.id.disabilityFalseBtn);
        disabilityTrue = findViewById(R.id.disabilityTrueBtn);

        if(disabilityFalse.isChecked()) {
            disability = false;
        }
        else if(disabilityTrue.isChecked()) {
            disability = true;
        }

        signUpBtn = findViewById(R.id.signUpBtn2);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllFocus();
                EmptyFieldValidation(nric, address, disabilityTrue, disabilityFalse);
                if (validInput) {
                    Intent i = new Intent(SignUp2Activity.this, SignUp3Activity.class);
                    Bundle data = new Bundle();
                    // Getting first name, last name, nric, and Nobile Num from SignUpActivity
                    Bundle oldData = getIntent().getExtras();

                    data.putString("fName", oldData.getString("fName"));
                    data.putString("lName", oldData.getString("lName"));
                    data.putString("mobileNum", oldData.getString("mobileNum"));
                    data.putString("nric", String.valueOf(nric.getText()));
                    data.putString("address", String.valueOf(address.getText()));
                    data.putBoolean("disability", disability);
                    i.putExtras(data);
                    startActivity(i);
                }
            }
        });

        TextView toLoginBtn = findViewById(R.id.userIdTxt2);
        //Make specific text bold
        String sourceString = "Already have an account? <b>Login</b>" ;
        toLoginBtn.setText(Html.fromHtml(sourceString));
        //Add onclick listener to navigate to Login page
        toLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp2Activity.this, LoginActivity.class));
            }
        });
    }

    //  Clear all EditText focus
    private void clearAllFocus(){
        nric.clearFocus();
        address.clearFocus();
        disabilityTrue.clearFocus();
        disabilityFalse.clearFocus();
    }

    private void EmptyFieldValidation(TextView nricTxt, TextView addressTxt, RadioButton disabilityTrue, RadioButton disabilityFalse){
        if(nricTxt.getText().toString().equals("")){
            TextView nricError = findViewById(R.id.nricError);
            nricError.setText("Please enter a NRIC");
            validInput = false;
        }
        if(addressTxt.getText().toString().equals("")){
            TextView addressError = findViewById(R.id.addrError);
            addressError.setText("Please enter an address");
            validInput = false;
        }
        if(!disabilityFalse.isChecked() && !disabilityTrue.isChecked()){
            TextView disabilityError = findViewById(R.id.disabilityError);
            disabilityError.setText("Please check disability");
            validInput = false;
        }
    }
}