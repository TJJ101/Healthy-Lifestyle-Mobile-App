package sg.edu.np.pfdassignment1;

import android.app.Activity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class NotesDiaryListAdapter extends RecyclerView.Adapter<NotesDiaryListAdapter.NotesDiaryListViewHolder>{
    ArrayList<NotesDiary> nList = new ArrayList<>();
    Activity activity;

    @NonNull
    @Override
    public NotesDiaryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.notediary_viewholder,
                parent,
                false);
        return new NotesDiaryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesDiaryListViewHolder holder, int position) {
        NotesDiary data = nList.get(position);
        String [] temp = data.getNotes().split("\\s+");
        String notes = "";
        if (temp.length > 3) {
            for(int i = 0;  i<5 ; i++) {

                notes = notes + " " + temp[i];
            }
        }
        else{
            notes = data.getNotes();
        }
        holder.notesTxt.setText(notes);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(holder.itemView.getContext(), EditNotesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Position", position);
                bundle.putString("Notes Details", nList.get(position).getNotes());
                in.putExtras(bundle);
                holder.itemView.getContext().startActivity(in);
                //Slide from Right to Left Transition
                activity.overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left);
            }
        });
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
        public TextView notesTxt;
        public ImageButton moreNotesInfoBtn;
        public View view;

        public NotesDiaryListViewHolder(@NonNull View itemView) {
            super(itemView);
            notesTxt = itemView.findViewById(R.id.notesTxt);
            moreNotesInfoBtn = itemView.findViewById(R.id.moreNotesInfoBtn);
            view = itemView;
        }
    }

}