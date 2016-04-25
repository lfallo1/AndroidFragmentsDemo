package com.lancefallon.smellslikebakin.activities;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lancefallon.smellslikebakin.R;
import com.lancefallon.smellslikebakin.activities.checkboxes.DualPaneFragment;
import com.lancefallon.smellslikebakin.activities.checkboxes.ViewPagerFragment;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnRecipeSelectedInterface, GridFragment.OnRecipeSelectedInterface {

    public static final String VIEWPAGER_FRAGMENT_TAG = "VIEWPAGER_FRAGMENT_TAG";
    public static final String LIST_FRAGMENT_TAG = "RECYCLERVIEW_FRAGMENT_TAG";
    private static final String GRID_FRAGMENT_TAG = "GRID_FRAGMENT_TAG";
    private static final String DUALPANE_FRAGMENT_TAG = "DUALPANE_FRAGMENT_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        Toast.makeText(MainActivity.this, isTablet ? "yes it's a tablet" : "nope, not a tablet", Toast.LENGTH_SHORT).show();

        if(!isTablet){
            ListFragment savedFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT_TAG);
            if(savedFragment == null){
                ListFragment listFragment = new ListFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.placeholder, listFragment, LIST_FRAGMENT_TAG);
                fragmentTransaction.commit();
            }
            return;
        }
        GridFragment savedFragment = (GridFragment) getSupportFragmentManager().findFragmentByTag(GRID_FRAGMENT_TAG);
        if(savedFragment == null){
            GridFragment gridFragment = new GridFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.placeholder, gridFragment, GRID_FRAGMENT_TAG);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onListRecipeSelected(int index) {
        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.EXTRA_RECIPE_INDEX, index);
        viewPagerFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeholder, viewPagerFragment, VIEWPAGER_FRAGMENT_TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onGridRecipeSelected(int index) {
        DualPaneFragment dualPageFragment = new DualPaneFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.EXTRA_RECIPE_INDEX, index);
        dualPageFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeholder, dualPageFragment, DUALPANE_FRAGMENT_TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
