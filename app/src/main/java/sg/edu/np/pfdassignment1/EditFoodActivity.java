package sg.edu.np.pfdassignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class EditFoodActivity extends AppCompatActivity {
    int position;
    FoodDiary food;
    TextView editCalories, editSugar, editSodium, editFat, editDate, editTime;
    DatePickerDialog.OnDateSetListener datePickerListener;
    TimePickerDialog.OnTimeSetListener timePickerListener;
    int hour, minute, year, month, day;
    Button cancelBtn, updateBtn;
    ImageButton deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        food = (FoodDiary) bundle.getSerializable("Food Details");
        position = bundle.getInt("Position");

        //Init fields
        //editCalories = findViewById(R.id.ed);
        //editSugar = findViewById(R.id.);
        //editSodium = findViewById(R.id.);
        //editFat = findViewById(R.id.);
        //editDate = findViewById(R.id.);
        //editTime = findViewById(R.id.);

        AlertDialog cancelBtnAlert = createCancelBtnAlert();
        //AlertDialog deleteBtnAlert = createDeleteBtnAlert();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelBtnAlert.show();
            }
        });

        /*
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBtnAlert.show();
            }
        });

         */

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

    /*
    public AlertDialog createDeleteBtnAlert() {
        AlertDialog.Builder deleteBtnBuilder = new AlertDialog.Builder(this);
        deleteBtnBuilder.setMessage("Are you sure? This action cannot be undone.");
        deleteBtnBuilder.setCancelable(true);

        deleteBtnBuilder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FoodDiaryFragment.foodDiaryList.remove(position);
                        FoodDiaryFragment.recyclerView.getAdapter().notifyDataSetChanged();
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

     */

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
}