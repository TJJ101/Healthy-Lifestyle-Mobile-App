package sg.edu.np.pfdassignment1;

import java.util.Date;

public class NotesDiary {
    private Date dateTime;
    public Date getDateTime() {return dateTime;}
    public void setDateTime(Date dt) {this.dateTime = dt;}

    private String title;
    public String getTitle() {return title;}
    public void setTitle(String t) {this.title = t;}

    private  String notes;
    public String getNotes() {return notes;}
    public void setNotes(String n) {this.notes = n;}
}
