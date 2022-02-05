package sg.edu.np.pfdassignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FoodDiaryListAdapter extends RecyclerView.Adapter<FoodDiaryListAdapter.FoodDiaryListViewHolder> {
    private ArrayList<FoodDiary> fList = new ArrayList<>();
    Activity activity;

    @NonNull
    @Override
    public FoodDiaryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.fooddiary_viewholder,
                parent,
                false);
        return new FoodDiaryListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodDiaryListAdapter.FoodDiaryListViewHolder holder, int position) {
        FoodDiary fData = fList.get(position);
        Log.d("food name", fData.getFoodItemName());
        Log.d("food name 2", String.valueOf((CharSequence) fData.getFoodItemName()));
        holder.foodNameTxt.setText(fData.getFoodItemName());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Log.d("Add Food Test Time", String.valueOf(fData.getDateTime()));
        Log.d("Add Food Test Time 2", sdf.format(fData.getDateTime()));
        holder.foodTimeTxt.setText(sdf.format(fData.getDateTime()));

        // for when clicking on food item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(holder.itemView.getContext(), EditFoodActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Position", position);
                bundle.putSerializable("Food Details", fList.get(position));
                in.putExtras(bundle);
                holder.itemView.getContext().startActivity(in);
                //Slide from Right to Left Transition
                activity.overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left);
            }
        });
    }

    public FoodDiaryListAdapter(ArrayList<FoodDiary> fList, Activity activity) {
        this.fList = fList;
        this.activity = activity;
    }

    @Override
    public int getItemCount() { return fList.size(); }
    @Override
    public long getItemId(int position) { return position; }
    @Override
    public int getItemViewType(int position) { return position; }

    public class FoodDiaryListViewHolder extends RecyclerView.ViewHolder {
        public TextView foodNameTxt, foodTimeTxt;
        public ImageButton moreFoodInfoBtn;
        public View view;

        public FoodDiaryListViewHolder(@NonNull View itemView) {
            super(itemView);
            foodNameTxt = itemView.findViewById(R.id.foodNameTxt);
            foodTimeTxt = itemView.findViewById(R.id.foodTimeTxt);
            moreFoodInfoBtn = itemView.findViewById(R.id.moreFoodInfoBtn);
            view = itemView;
        }
    }
}
