package sg.edu.np.pfdassignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

public class AddPasswordActivity extends AppCompatActivity {
    EditText nameOfPassword, username, password;
    Button saveBtn, cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_password);

        nameOfPassword = findViewById(R.id.nameInput);
        username = findViewById(R.id.usernameInput);
        password = findViewById(R.id.passwordInput);
        saveBtn = findViewById(R.id.addPasswordSaveBtn);
        cancelBtn = findViewById(R.id.addPasswordCancelBtn);

        //Alert Dialog for Cancel Button
        AlertDialog cancelBtnAlert = createCancelBtnAlert();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Password password1 = new Password();
                password1.setNameOfPassword(String.valueOf(nameOfPassword.getText()));
                password1.setUsername(String.valueOf(username.getText()));
                password1.setPassword(String.valueOf(password.getText()));
                //Intent i = new Intent(AddPasswordActivity.this, MainActivity.class);
                //i.putExtra("Password", password1);
                //setResult(1000, i);
                PasswordFragment.passwordList.add(password1);
                PasswordFragment.recyclerView.getAdapter().notifyDataSetChanged();
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { cancelBtnAlert.show(); }
        });

        //For ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    //For ActionBar (the one at the top of the app)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                //finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public AlertDialog createCancelBtnAlert() {
        AlertDialog.Builder cancelBtnBuilder = new AlertDialog.Builder(this);
        cancelBtnBuilder.setMessage("Are you sure? Any unsaved changes will be discarded.");
        cancelBtnBuilder.setCancelable(true);

        cancelBtnBuilder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                }
        );
        cancelBtnBuilder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
        );
        return cancelBtnBuilder.create();
    }
}