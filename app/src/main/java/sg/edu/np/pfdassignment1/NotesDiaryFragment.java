package sg.edu.np.pfdassignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotesDiaryFragment extends Fragment {
    boolean clicked = false;
    FloatingActionButton expandBtn, addFoodBtn, addNotesBtn;
    public Animation fromBottom (Context context) {return AnimationUtils.loadAnimation(getContext(), R.anim.from_bottom_anim);}
    public Animation rotateOpen (Context context) {return AnimationUtils.loadAnimation(getContext(), R.anim.rotate_open_anim);}
    public Animation toBottom (Context context) {return AnimationUtils.loadAnimation(getContext(), R.anim.to_bottom_anim);}
    public Animation rotateClose (Context context) {return AnimationUtils.loadAnimation(getContext(), R.anim.rotate_close_anim);}

    static RecyclerView recyclerView;
    NotesDiaryListAdapter adapter;
    static ArrayList<NotesDiary> notesList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes_diary, container, false);
        addNotesBtn = view.findViewById(R.id.addNotesBtn);

        if(!notesList.isEmpty()) {
            TextView emptyDiaryText = view.findViewById(R.id.emptyDiaryText);
            emptyDiaryText.setVisibility(View.GONE);
        }
        else {
            TextView emptyDiaryText = view.findViewById(R.id.emptyDiaryText);
            emptyDiaryText.setVisibility(View.VISIBLE);
        }

        int random = new Random().nextInt(2);

        // Populate with data from Database
        UserService services = ApiClient.getClient().create(UserService.class);
        Call<NotesDiary> notesCall = services.getNotes(random);
        notesCall.enqueue(new Callback<NotesDiary>() {
            @Override
            public void onResponse(Call<NotesDiary> call, Response<NotesDiary> response) {
                if(response.isSuccessful()) {
                    NotesDiary data = (NotesDiary) response.body();
                    if(!data.equals(null)) {
                        if(!data.getNotes().equals(null)) {
                            if(!notesExist(notesList, data)) {
                                notesList.add(data);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<NotesDiary> call, Throwable t) {

            }
        });

        addNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddNotesActivity.class);
                startActivity(intent);
            }
        });

        /*
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddDiaryActivity.class);
                startActivity(i);
            }
        });

         */
        //For RecyclerView
        recyclerView = view.findViewById(R.id.notesDiaryList);
        adapter = new NotesDiaryListAdapter(notesList, getActivity());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public boolean notesExist(ArrayList<NotesDiary> nList, NotesDiary data) {
        for(int i=0; i<nList.size(); i++){
            NotesDiary temp = nList.get(i);
            if(temp.getUserId() == temp.getUserId()) {
                return true;
            }
        }
        return false;
    }
}