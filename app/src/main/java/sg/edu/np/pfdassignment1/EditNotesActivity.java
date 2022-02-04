package sg.edu.np.pfdassignment1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class EditNotesActivity extends AppCompatActivity {
    int position;
    NotesDiary notes = new NotesDiary();
    EditText editNotes;
    Button cancelBtn, updateBtn;
    ImageButton deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        // Get Data from NotesDiaryListAdapter
        Bundle data = getIntent().getExtras();
        position = data.getInt("Position");
        notes.setNotes(data.getString("Notes Details"));

        //init
        editNotes = findViewById(R.id.editNotes);
        cancelBtn = findViewById(R.id.editNotesCancelBtn);
        updateBtn = findViewById(R.id.editNotesSaveBtn);
        deleteBtn = findViewById(R.id.editNotesdeleteBtn);

        editNotes.setText(notes.getNotes());

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

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes.setNotes(String.valueOf(editNotes.getText()));
                NotesDiaryFragment.notesList.set(position, notes);
                NotesDiaryFragment.recyclerView.getAdapter().notifyDataSetChanged();
                finish();
            }
        });
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

}