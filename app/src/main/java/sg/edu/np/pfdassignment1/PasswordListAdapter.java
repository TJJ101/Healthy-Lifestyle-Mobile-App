package sg.edu.np.pfdassignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class PasswordListAdapter extends RecyclerView.Adapter<PasswordListAdapter.PasswordListViewHolder>{
    ArrayList<Password> pList = new ArrayList<>();
    Activity activity;

    @NonNull
    @Override
    public PasswordListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.passwordlist_viewholder,
                parent,
                false);
        return new PasswordListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordListViewHolder holder, int position) {
        Password data = pList.get(position);
        holder.passwordType.setText(data.getNameOfPassword());
        ImageButton moreBtn = holder.moreBtn;

        //For when clicking on a password
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(holder.itemView.getContext(), EditPasswordActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Position", position);
                bundle.putSerializable("Password Details", pList.get(position));
                in.putExtras(bundle);
                holder.itemView.getContext().startActivity(in);
                //Slide from Right to Left Transition
                activity.overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left);
            }
        });

        /*
        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.moreOptions.setVisibility(View.VISIBLE);
            }
        });
         */
    }

    public PasswordListAdapter(ArrayList<Password> pList, Activity activity) {
        this.pList = pList;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return pList.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class PasswordListViewHolder extends RecyclerView.ViewHolder {
        public TextView passwordType;
        public ImageButton moreBtn;
        public View view;
        public LinearLayout moreOptions;

        public PasswordListViewHolder(@NonNull View itemView) {
            super(itemView);
            passwordType = itemView.findViewById(R.id.passwordType);
            moreBtn = itemView.findViewById(R.id.moreBtn);
            //moreOptions = itemView.findViewById(R.id.moreOptions);
            view = itemView;
        }
    }
}
