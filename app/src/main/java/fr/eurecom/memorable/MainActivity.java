package fr.eurecom.memorable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;


import fr.eurecom.memorable.home.HomeFragment;
import me.ertugrul.lib.SuperBottomBar;

public class MainActivity extends AppCompatActivity {

    SuperBottomBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationBar = findViewById(R.id.bottomBar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        bottomMenu();
    }

    private void bottomMenu() {
        bottomNavigationBar.setOnItemSelectListener(i -> {
            Fragment fragment = null;
            Log.e(i+" ", i+"logging button ");
            if (i == 0){
                fragment = new AddFriendsFragment();
            }
            else if (i == 1){
                fragment = new HomeFragment();
            }
            else if (i == 2){
                fragment = new SettingsFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        });
    }
}