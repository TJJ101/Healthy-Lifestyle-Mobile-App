package sg.edu.np.pfdassignment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PasswordFragment extends Fragment {

    static RecyclerView recyclerView;
    PasswordListAdapter adapter;

    static ArrayList<Password> passwordList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_password, container, false);
        FloatingActionButton addBtn = view.findViewById(R.id.passwordAdd);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddPasswordActivity.class);
                startActivity(i);
                //startActivityForResult(i, 1000);
            }
        });

        TextView emptyText = view.findViewById(R.id.emptyPasswordListText);
        if(!passwordList.isEmpty()) {
            emptyText.setVisibility(View.GONE);
        }
        else {
            emptyText.setVisibility(View.VISIBLE);
        }

        //For RecyclerView
        recyclerView = view.findViewById(R.id.passwordList);
        adapter = new PasswordListAdapter(passwordList, getActivity());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        passwordList.add((Password) data.getSerializableExtra("Password"));
    }
     */

    static public boolean passwordExist(ArrayList<Password> pList, Password data) {
        for(int i = 0; i < pList.size(); i++) {
            if(pList.get(i).equals(data)) {
                return true;
            }
        }
        return false;
    }
}