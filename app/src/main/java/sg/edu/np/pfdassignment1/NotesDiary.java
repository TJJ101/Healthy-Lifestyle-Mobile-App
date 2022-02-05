package sg.edu.np.pfdassignment1;

import java.util.Date;
import com.google.gson.annotations.SerializedName;

public class NotesDiary{
    @SerializedName("id")
    int id;
    public int getNoteId() {return id;}
    public void setId(int id) { this.id = id;}

    @SerializedName("user_id")
    int userId;
    public int getUserId() {return userId;}
    public void setUserId(int uId) {this.userId = uId;}

    @SerializedName("data")
    String notes;
    public String getNotes() {return notes;}
    public void setNotes(String n) {this.notes = n;}

    public NotesDiary() {}
    public NotesDiary(int id, int uId, String data) {
        this.id = id;
        this.userId = uId;
        this.notes = data;
    }
}
