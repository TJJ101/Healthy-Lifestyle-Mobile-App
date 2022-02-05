package sg.edu.np.pfdassignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class EditPasswordActivity extends AppCompatActivity {
    int position;
    Password currentPassword, oldPassword;
    TextView name, username, password;
    EditText nameInput, usernameInput, passwordInput;
    Button cancelBtn, saveBtn;
    ImageButton copyUsernameBtn, copyPasswordBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        //get the data from PasswordListAdapter
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        position = extras.getInt("Position");
        currentPassword = (Password) extras.getSerializable("Password Details");

        //init fields
        nameInput = findViewById(R.id.editNameInput);
        usernameInput = findViewById(R.id.editUsernameInput);
        passwordInput = findViewById(R.id.editPasswordInput);

        nameInput.setText(currentPassword.getNameOfPassword());
        usernameInput.setText(currentPassword.getUsername());
        passwordInput.setText(currentPassword.getPassword());

        saveBtn = findViewById(R.id.updateBtn);
        cancelBtn = findViewById(R.id.cancelBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        copyUsernameBtn = findViewById(R.id.copyUsernameBtn);
        copyPasswordBtn = findViewById(R.id.copyPasswordBtn);


        AlertDialog cancelBtnAlert = createCancelBtnAlert();
        AlertDialog deleteBtnAlert = createDeleteBtnAlert();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelBtnAlert.show();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBtnAlert.show();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPassword.setNameOfPassword(String.valueOf(nameInput.getText()));
                currentPassword.setUsername(String.valueOf(usernameInput.getText()));
                currentPassword.setPassword(String.valueOf(passwordInput.getText()));
                PasswordFragment.passwordList.set(position, currentPassword);
                PasswordFragment.recyclerView.getAdapter().notifyDataSetChanged();
                finish();
            }
        });

        copyUsernameBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        copyUsernameBtn.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        copyUsernameBtn.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        copyToClipboard("Username", currentPassword.getUsername());
                        //break;
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        copyUsernameBtn.getBackground().clearColorFilter();
                        copyUsernameBtn.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        copyPasswordBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        copyPasswordBtn.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        copyPasswordBtn.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        copyToClipboard("Password", currentPassword.getPassword());
                        //break;
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        copyPasswordBtn.getBackground().clearColorFilter();
                        copyPasswordBtn.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

        //For ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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

    public AlertDialog createDeleteBtnAlert() {
        AlertDialog.Builder deleteBtnBuilder = new AlertDialog.Builder(this);
        deleteBtnBuilder.setMessage("Are you sure? This action cannot be undone.");
        deleteBtnBuilder.setCancelable(true);

        deleteBtnBuilder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PasswordFragment.passwordList.remove(position);
                        PasswordFragment.recyclerView.getAdapter().notifyDataSetChanged();
                        finish();
                    }
                }
        );
        deleteBtnBuilder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
        );
        return deleteBtnBuilder.create();
    }
    public AlertDialog createBackBtnAlert() {
        AlertDialog.Builder cancelBtnBuilder = new AlertDialog.Builder(this);
        cancelBtnBuilder.setMessage("Are you sure? Any unsaved changes will be discarded.");
        cancelBtnBuilder.setCancelable(true);

        cancelBtnBuilder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        onBackPressed();
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

    public void copyToClipboard(String label, String copyData) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText(label, copyData);
        clipboard.setPrimaryClip(data);
    }

    //For ActionBar (the one at the top of the app)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AlertDialog backBtnAlert = createBackBtnAlert();
                backBtnAlert.show();
                //onBackPressed();
                //finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}