package com.prs.flixterv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
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
}
