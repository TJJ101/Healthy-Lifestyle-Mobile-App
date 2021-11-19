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
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddFoodActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText dateInput, timeInput;
    DatePickerDialog.OnDateSetListener datePickerListener;
    TimePickerDialog.OnTimeSetListener timePickerListener;
    int hour, minute;
    Spinner spinner;
    ArrayList<Food> foodList = new ArrayList<>();
    Button cancelBtn, saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        //Init Buttons
        cancelBtn = findViewById(R.id.addFoodCancelBtn);
        saveBtn = findViewById(R.id.addFoodSaveBtn);

        //For Date Picker Dialog
        dateInput = findViewById(R.id.dateInput);
        dateInput.setInputType(0);
        dateInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    createDatePickerDialog(AddFoodActivity.this, year, month, day);
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

        //For Time Picker Dialog
        timeInput = findViewById(R.id.timeInput);
        timeInput.setInputType(0);
        timeInput.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    createTimePickerDialog(AddFoodActivity.this, timePickerListener, hour, minute, false);
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

        //Populate Food list for the Spanner/Dropdown List
        foodList.add(new Food("Hokkien Mee", 555, 1.6, 753));
        foodList.add(new Food("Duck Rice", 673, 28, 545));
        foodList.add(new Food("Chicken Rice", 402, 4.2, 1355));
        foodList.add(new Food("Wonton Noodle", 270, 0, 620));
        foodList.add(new Food("Laksa", 613, 17, 2456));

        //Spinner
        spinner = findViewById(R.id.foodDropdownList);
        //spinner.setOnItemSelectedListener(AddFoodActivity.this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.foodType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        //For Cancel Button
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

    public void createDatePickerDialog(Context context, int year, int month, int day) {
        DatePickerDialog dialog = new DatePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog_MinWidth, datePickerListener, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void createTimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener timeSetListener, int hour, int minute, Boolean twentyFourHour) {
        TimePickerDialog dialog = new TimePickerDialog(context, timeSetListener, hour, minute, twentyFourHour);
        dialog.show();
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

    //For Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Change Code here for spinner
        String text = parent.getItemAtPosition(position).toString();
        Log.d("Test Spinner", text);

    }
    //For Spinner
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
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
}