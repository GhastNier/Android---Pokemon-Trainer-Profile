package com.example.firstappandroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.firstappandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    public static final String themeKey = "themeKey";
    public static SharedPreferences theme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theme = getSharedPreferences(themeKey, Context.MODE_PRIVATE);
        com.example.firstappandroid.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        theme.edit().putString(themeKey, "Spring").apply();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    void setThemeValues(ImageView binding) {
        switch (theme.getString(themeKey, "")) {
            case "Spring": {
                binding.setImageDrawable(ContextCompat.getDrawable(binding.getContext(), R.drawable.spring));
                break;
            }
            case "Winter": {
                binding.setImageDrawable(ContextCompat.getDrawable(binding.getContext(), R.drawable.winter));
                break;
            }
            case "Summer": {
                binding.setImageDrawable(ContextCompat.getDrawable(binding.getContext(), R.drawable.summer));
                break;
            }
            case "Fall": {
                Log.println(Log.INFO, "Fall Set", String.valueOf(theme.getAll()));
                break;
            }
            default: {
                break;
            }
        }
    }

}