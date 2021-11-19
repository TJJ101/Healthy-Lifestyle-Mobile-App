package sg.edu.np.pfdassignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    boolean validInput = true;
    TextView usernameTxt, emailTxt, passwordTxt, cfmPasswordTxt;
    Global global = new Global();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hide the top title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        usernameTxt = findViewById(R.id.newUsernameTxt);
        emailTxt = findViewById(R.id.newEmailTxt);
        passwordTxt = findViewById(R.id.newPasswordTxt);
        cfmPasswordTxt = findViewById(R.id.cfmPasswordTxt);

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
}