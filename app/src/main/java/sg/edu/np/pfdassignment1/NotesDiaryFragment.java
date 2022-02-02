package sg.edu.np.pfdassignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NotesDiaryFragment extends Fragment {
    boolean clicked = false;
    FloatingActionButton expandBtn, addFoodBtn, addNotesBtn;
    public Animation fromBottom (Context context) {return AnimationUtils.loadAnimation(getContext(), R.anim.from_bottom_anim);}
    public Animation rotateOpen (Context context) {return AnimationUtils.loadAnimation(getContext(), R.anim.rotate_open_anim);}
    public Animation toBottom (Context context) {return AnimationUtils.loadAnimation(getContext(), R.anim.to_bottom_anim);}
    public Animation rotateClose (Context context) {return AnimationUtils.loadAnimation(getContext(), R.anim.rotate_close_anim);}

    ArrayList<NotesDiary> notesDiaryList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes_diary, container, false);
        addNotesBtn = view.findViewById(R.id.addNotesBtn);

        if(!notesDiaryList.isEmpty()) {
            TextView emptyDiaryText = view.findViewById(R.id.emptyDiaryText);
            emptyDiaryText.setVisibility(View.GONE);
        }
        else {
            TextView emptyDiaryText = view.findViewById(R.id.emptyDiaryText);
            emptyDiaryText.setVisibility(View.VISIBLE);
        }

        //For expanding the floating button
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
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}