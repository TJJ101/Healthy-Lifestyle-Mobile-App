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
    EditText dateInput, timeInput;
    DatePickerDialog.OnDateSetListener datePickerListener;
    TimePickerDialog.OnTimeSetListener timePickerListener;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        //Init Buttons
        cancelBtn = findViewById(R.id.addNotesCancelBtn);
        saveBtn = findViewById(R.id.addNotesSaveBtn);

        //For Date Picker Dialog
        dateInput = findViewById(R.id.addNotesDateInput);
        dateInput.setInputType(0);
        dateInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    createDatePickerDialog(AddNotesActivity.this, year, month, day);
                }
                return true;
            }
        });
        datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                dateInput.setText(date);
            }
        };

        //For Time Picker
        timeInput = findViewById(R.id.addNotesTimeInput);
        timeInput.setInputType(0);
        timeInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    createTimePickerDialog(AddNotesActivity.this, timePickerListener, hour, minute, false);
                }
                return true;
            }
        });
        timePickerListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeInput.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

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
                onBackPressed();
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

    //Creating the Date Picker Dialog
    public void createDatePickerDialog(Context context, int year, int month, int day) {
        DatePickerDialog dialog = new DatePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog_MinWidth, datePickerListener, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    //Creating the time picker dialog
    public void createTimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener timeSetListener, int hour, int minute, Boolean twentyFourHour) {
        TimePickerDialog dialog = new TimePickerDialog(context, timeSetListener, hour, minute, twentyFourHour);
        dialog.show();
    }
}