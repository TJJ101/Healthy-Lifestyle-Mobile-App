package sg.edu.np.pfdassignment1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FoodDiaryFragment extends Fragment {

    static RecyclerView recyclerView;
    FoodDiaryListAdapter adapter;

    static ArrayList<FoodDiary> foodDiaryList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_diary, container, false);
        FloatingActionButton addBtn = view.findViewById(R.id.foodDiaryAdd);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddFoodActivity.class);
                startActivity(i);
            }
        });

        TextView emptyText = view.findViewById(R.id.emptyFoodDiaryListText);
        if(!foodDiaryList.isEmpty()) {
            emptyText.setVisibility(View.GONE);
        }
        else {
            emptyText.setVisibility(View.VISIBLE);
        }

        //recycler view
        recyclerView = view.findViewById(R.id.foodDiaryList);
        adapter = new FoodDiaryListAdapter(foodDiaryList, getActivity());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}