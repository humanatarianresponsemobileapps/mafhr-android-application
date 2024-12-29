package com.example.mafhr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.example.mafhr.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public static NavController navController;
    public static User currentUser;
    public static UserDatabase database;
    private UserViewModel userViewModel;
    private List<User> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Set the content view first

        // Find the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);

        // Set the Toolbar as the ActionBar
        setSupportActionBar(toolbar);

        // Get the NavHostFragment and NavController
        NavHostFragment navHostManager = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        navController = navHostManager.getNavController();

        // Link the Toolbar with NavController
        NavigationUI.setupActionBarWithNavController(this, navController);

        // Initialize the BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        database = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "userDB")
                .fallbackToDestructiveMigration()
                .build();

        userViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(UserViewModel.class);
        userViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> userList) {
                users = userList;
                Log.d("Ash", users.toString());
            }
        });

        // Uncomment and set up listeners if required
        // bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
        //     switch (item.getItemId()) {
        //         case R.id.nav_health:
        //             return true;
        //         case R.id.nav_map:
        //             return true;
        //         case R.id.nav_call:
        //             return true;
        //         case R.id.nav_home:
        //             return true;
        //         case R.id.nav_alerts:
        //             return true;
        //         default:
        //             return false;
        //     }
        // });
    }

}
