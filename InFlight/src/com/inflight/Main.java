package com.inflight;

import java.io.IOException;
import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.inflight.maps.DropboxTileProvider;
import com.inflight.maps.MainMap;
import com.inflight.plan.PlanFragment;
import com.inflight.plan.PlanPoint;
import com.inflight.plan.db.DAO;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class Main extends Activity {

    public static DAO database;
    

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

		Log.d("Map", "Create");
        //Setup tabs

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);

        Tab tab = actionBar.newTab()
                .setText(R.string.menu_map)
                .setTabListener(new TabListener<MainMap>(
                        this, "menu_map", MainMap.class));
        
        actionBar.addTab(tab, true);
        

        tab = actionBar.newTab()
                .setText(R.string.menu_plan)
                .setTabListener(new TabListener<PlanFragment>(
                        this, "menu_plan", PlanFragment.class));
        actionBar.addTab(tab, false);
        
        if (savedInstanceState != null
        		&& savedInstanceState.containsKey("SelectedTab"))
        	actionBar.setSelectedNavigationItem(savedInstanceState.getInt("SelectedTab"));
        

	    Main.database = new DAO(this.getApplicationContext());
	    try {
			Main.database.createDataBase();
		} catch (IOException e) {
			e.printStackTrace();
		}
        //actionBar.addTab(tab);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public void onSaveInstanceState(Bundle in) {
    	super.onSaveInstanceState(in);
    	in.putInt("SelectedTab", getActionBar().getSelectedNavigationIndex());
    }

    List<PlanPoint> plan = null;
	public List<PlanPoint> getPlan() {
		return plan;
	}
	public void setPlan(List<PlanPoint> newPlan) {
		this.plan = newPlan;
	}
    
}
