package aluno.dsmobile.receitasapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import aluno.dsmobile.receitasapp.Adapter.MyViewPagerAdapter;
import aluno.dsmobile.receitasapp.Fragments.CarnivoreFragment;
import aluno.dsmobile.receitasapp.Fragments.PrincipalFragment;
import aluno.dsmobile.receitasapp.Fragments.VeganFragment;
import aluno.dsmobile.receitasapp.Fragments.VegetarianFragment;

public class MainActivity extends AppCompatActivity {

    private boolean isExpanded = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPrefs = getSharedPreferences("themePrefs", Context.MODE_PRIVATE);
        boolean isDarkMode = sharedPrefs.getBoolean("isDarkMode", false);

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(this);
        adapter.addFragment(new PrincipalFragment());
        adapter.addFragment(new VegetarianFragment());
        adapter.addFragment(new VeganFragment());
        adapter.addFragment(new CarnivoreFragment());

        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Todas");
                            break;
                        case 1:
                            tab.setText("Vegetariano");
                            break;
                        case 2:
                            tab.setText("Vegano");
                            break;
                        case 3:
                            tab.setText("Carnívoro");
                            break;
                    }
                }
        ).attach();

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        FloatingActionButton fabOption1 = findViewById(R.id.fabOption1);
        FloatingActionButton fabOption2 = findViewById(R.id.fabOption2);

        fab.setOnClickListener(v -> {
            if (isExpanded) {
                fabOption1.hide();
                fabOption2.hide();
            } else {
                fabOption1.show();
                fabOption2.show();
            }
            isExpanded = !isExpanded;
        });

        fabOption1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoriteActivity.class);
            startActivity(intent);
        });

        fabOption2.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPrefs.edit();
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor.putBoolean("isDarkMode", false);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor.putBoolean("isDarkMode", true);
            }
            editor.apply();
        });
    }

}
