package sg.edu.np.pfdassignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class AddNotesActivity extends AppCompatActivity {
    Button cancelBtn, saveBtn;
    EditText noteInput;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        noteInput = findViewById(R.id.addNotesInput);

        //Init Buttons
        cancelBtn = findViewById(R.id.addNotesCancelBtn);
        saveBtn = findViewById(R.id.addNotesSaveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesDiary notes = new NotesDiary();
                notes.setNotes(String.valueOf(noteInput.getText()));

                NotesDiaryFragment.notesList.add(notes);
                NotesDiaryFragment.recyclerView.getAdapter().notifyDataSetChanged();
                finish();
            }
        });

        //For Cancel Btn
        AlertDialog cancelBtnAlert = createCancelBtnAlert();
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelBtnAlert.show();
            }
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
                AlertDialog backBtnAlert = createBackBtnAlert();
                backBtnAlert.show();
                //onBackPressed();
                //finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //For Cancel Button dialog
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
}