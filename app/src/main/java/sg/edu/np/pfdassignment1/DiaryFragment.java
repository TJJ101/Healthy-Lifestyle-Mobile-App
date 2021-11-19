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

public class DiaryFragment extends Fragment {
    boolean clicked = false;
    FloatingActionButton expandBtn, addFoodBtn, addNotesBtn;
    public Animation fromBottom (Context context) {return AnimationUtils.loadAnimation(getContext(), R.anim.from_bottom_anim);}
    public Animation rotateOpen (Context context) {return AnimationUtils.loadAnimation(getContext(), R.anim.rotate_open_anim);}
    public Animation toBottom (Context context) {return AnimationUtils.loadAnimation(getContext(), R.anim.to_bottom_anim);}
    public Animation rotateClose (Context context) {return AnimationUtils.loadAnimation(getContext(), R.anim.rotate_close_anim);}

    ArrayList<Diary> diaryList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diary, container, false);
        expandBtn = view.findViewById(R.id.diaryExpandAddBtn);
        addFoodBtn = view.findViewById(R.id.addFoodBtn);
        addNotesBtn = view.findViewById(R.id.addNotesBtn);

        if(!diaryList.isEmpty()) {
            TextView emptyDiaryText = view.findViewById(R.id.emptyDiaryText);
            emptyDiaryText.setVisibility(View.GONE);
        }
        else {
            TextView emptyDiaryText = view.findViewById(R.id.emptyDiaryText);
            emptyDiaryText.setVisibility(View.VISIBLE);
        }

        //For expanding the floating button
        expandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onExpandClicked();}
        });

        //For Adding Food Entry
        addFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddFoodActivity.class);
                startActivity(intent);
            }
        });

        //For adding Notes Entry
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

    public void onExpandClicked(){
        setVisibility(clicked);
        setAnimation(clicked);
        setClickable(clicked);
        clicked = !clicked;
    }

    private void setVisibility(Boolean clicked){
        if (!clicked){
            addFoodBtn.setVisibility(View.VISIBLE);
            addNotesBtn.setVisibility(View.VISIBLE);
        }
        else {
            addFoodBtn.setVisibility(View.INVISIBLE);
            addNotesBtn.setVisibility(View.INVISIBLE);
        }
    }
    private void setAnimation(Boolean clicked){
        if(!clicked){
            addFoodBtn.startAnimation(fromBottom(getContext()));
            addNotesBtn.startAnimation(fromBottom(getContext()));
            expandBtn.startAnimation(rotateOpen(getContext()));
        }
        else{
            addFoodBtn.startAnimation(toBottom(getContext()));
            addNotesBtn.startAnimation(toBottom(getContext()));
            expandBtn.startAnimation(rotateClose(getContext()));
        }
    }

    private void setClickable(Boolean clicked){
        if(clicked){
            addFoodBtn.setClickable(false);
            addNotesBtn.setClickable(false);
        }
        else{
            addFoodBtn.setClickable(true);
            addNotesBtn.setClickable(true);
        }
    }
}