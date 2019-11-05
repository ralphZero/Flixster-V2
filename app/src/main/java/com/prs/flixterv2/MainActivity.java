package com.prs.flixterv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.prs.flixterv2.Fragments.MoviesFragment;
import com.prs.flixterv2.Models.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.spCategory)
    Spinner spCategory;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame)
    FrameLayout frame;

    SharedPreferences sharedPreferences;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Manage Themes
        Utils.onActivityCreateSetTheme(this);
        //----
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.app_name));

        sharedPreferences = getSharedPreferences("Theme", Context.MODE_PRIVATE);


        spCategory.setLayoutParams(new Toolbar.LayoutParams(300, ViewGroup.LayoutParams.WRAP_CONTENT));

        String[] entries = new String[]{"Movies", "TV Shows"};
        spCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, entries));
        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    spCategory.setLayoutParams(new Toolbar.LayoutParams(300, ViewGroup.LayoutParams.WRAP_CONTENT));
                }else{
                    spCategory.setLayoutParams(new Toolbar.LayoutParams(350, ViewGroup.LayoutParams.WRAP_CONTENT));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frame.getId(), MoviesFragment.newInstance(0,"Movies"),"Frag1");
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_theme);
        menuItem.setTitle(sharedPreferences.getString("mode",getResources().getString(R.string.enable_dark_mode)));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.app_bar_theme){

            int position = sharedPreferences.getInt("state",99);
            switch (position){
                case 99:
                case 0:
                    FlixsterApplication.currentPosition = 1;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("state",FlixsterApplication.currentPosition);
                    editor.putString("mode",getResources().getString(R.string.disable_dark_mode));
                    editor.apply();
                    Utils.changeToTheme(MainActivity.this,FlixsterApplication.currentPosition);
                    break;
                case 1:
                    FlixsterApplication.currentPosition = 0;
                    SharedPreferences.Editor editor1 = sharedPreferences.edit();
                    editor1.putInt("state",FlixsterApplication.currentPosition);
                    editor1.putString("mode",getResources().getString(R.string.enable_dark_mode));
                    editor1.apply();
                    Utils.changeToTheme(MainActivity.this,FlixsterApplication.currentPosition);
                    break;
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
