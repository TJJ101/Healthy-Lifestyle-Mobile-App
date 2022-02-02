package sg.edu.np.pfdassignment1;

import android.app.Activity;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class NotesDiaryListAdapter extends RecyclerView.Adapter<NotesDiaryListAdapter.NotesDiaryListViewHolder>{
    ArrayList<NotesDiary> nList;
    Activity activity;

    @NonNull
    @Override
    public NotesDiaryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.passwordlist_viewholder,
                parent,
                false);
        return new NotesDiaryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesDiaryListViewHolder holder, int position) {

    }

    public NotesDiaryListAdapter(ArrayList<NotesDiary> nList, Activity activity) {
        this.nList = nList;
        this.activity = activity;
    }

    @Override
    public int getItemCount() { return nList.size(); }
    @Override
    public long getItemId(int position) { return position; }
    @Override
    public int getItemViewType(int position) { return position; }

    public class NotesDiaryListViewHolder extends RecyclerView.ViewHolder {
        public TextView notesTitleTxt;
        public ImageButton moreNotesInfoBtn;
        public View view;

        public NotesDiaryListViewHolder(@NonNull View itemView) {
            super(itemView);
            notesTitleTxt = itemView.findViewById(R.id.notesTitleTxt);
            moreNotesInfoBtn = itemView.findViewById(R.id.moreNotesInfoBtn);
            view = itemView;
        }
    }

}