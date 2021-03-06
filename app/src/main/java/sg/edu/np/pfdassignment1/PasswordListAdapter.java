package sg.edu.np.pfdassignment1;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        holder.passwordType.setOnClickListener(new View.OnClickListener() {
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

        if(holder.moreOptions.equals(null))
        {
            Log.d("Password Adapter", "NULL WTF");
        }
        else{
            holder.moreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.moreOptions.setVisibility(View.VISIBLE);
                    holder.moreOptions.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            if(!hasFocus) {
                                holder.moreOptions.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            });
        }

        if (holder.moreOptions.getVisibility() == View.VISIBLE) {
            holder.editPassword.setOnClickListener(new View.OnClickListener() {
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
            holder.copyUsername.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.copyPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder.deletePassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }


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
        public TextView passwordType, editPassword, copyUsername, copyPassword, deletePassword;
        public ImageButton moreBtn;
        public View view;
        public LinearLayout moreOptions;

        public PasswordListViewHolder(@NonNull View itemView) {
            super(itemView);
            passwordType = itemView.findViewById(R.id.passwordType);
            moreBtn = itemView.findViewById(R.id.moreBtn);
            moreOptions = itemView.findViewById(R.id.moreOptions);
            view = itemView;

            editPassword = itemView.findViewById(R.id.editPassword);
            copyPassword = itemView.findViewById(R.id.copyPassword);
            copyUsername = itemView.findViewById(R.id.copyUsername);
            deletePassword = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
