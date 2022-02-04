package sg.edu.np.pfdassignment1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    boolean loggedIn = false;
    Fragment selectedFragment;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init bottom nav
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
            bottomNav.setOnNavigationItemSelectedListener(navListener);
        //to open password fragment when creating MainActivity
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment_container, new PasswordFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentSwitch(item);
            return true;
        };
    };

    private void fragmentSwitch(MenuItem item){
        selectedFragment = null;
        Bundle bundle = new Bundle();
        Intent i = getIntent();
        Bundle data = i.getExtras();
        email = bundle.getString("Email");
        switch (item.getItemId()){
            case R.id.nav_password:
                Log.d("Fragment", "Password Fragment");
                selectedFragment = new PasswordFragment();
                break;
            case R.id.nav_foodDiary:
                Log.d("Fragment", "Food Diary Fragment");
                selectedFragment = new FoodDiaryFragment();
                break;
            case R.id.nav_notesDiary:
                Log.d("Fragment", "Notes Diary Fragment");
                selectedFragment = new NotesDiaryFragment();
                break;
            case R.id.nav_account:
                Log.d("Fragment", "Account Fragment");
                selectedFragment = new AccountFragment();
                Bundle emailData = new Bundle();
                emailData.putString("Email", email);
                selectedFragment.setArguments(emailData);
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment_container, selectedFragment).commit();
    }
    protected void onStart() {
        super.onStart();
        loggedIn = getIntent().getBooleanExtra("Log in", false);
        Log.d("login", "Log in change");
        if(loggedIn == false){
            Log.d("Log in", "Log in fail cause not logged in");
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            //Slide right to Left Transition
            overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                //finish();
                return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
     */
}