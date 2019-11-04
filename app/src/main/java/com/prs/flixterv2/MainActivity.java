package com.prs.flixterv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.prs.flixterv2.Fragments.MoviesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.spCategory)
    Spinner spCategory;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame)
    FrameLayout frame;

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.app_name));

        String[] entries = new String[]{"Movies", "TV Shows"};
        spCategory.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, entries));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frame.getId(), MoviesFragment.newInstance(0,"Movies"),"Frag1");
        transaction.commit();
    }
}
