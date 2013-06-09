package com.inflight.maps;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.inflight.R;

public class MainMap extends MapFragment {
	@Override
	public void onCreate(Bundle savedInstanceBundle) {
		super.onCreate(savedInstanceBundle);
	}
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle ssis) {
		View inner = super.onCreateView(inflater, container, ssis);

		//Get the reference to the map
        GoogleMap mMap = getMap();
        
        //Check if we're resuming from a previously saved state
        if (ssis != null 
        		&& ssis.containsKey("CamBearing")) {
        	
        	//We are restoring a camera position.
        	CameraPosition cp = new CameraPosition(
        			new LatLng(
        					ssis.getDouble("CamTargetLat"),
        					ssis.getDouble("CamTargetLon")),
        					ssis.getFloat("CamZoom"),
        					ssis.getFloat("CamTilt"),
        					ssis.getFloat("CamBearing"));
        	
        	//Move the camera to the saved position
        	mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
        }


    	//Initalize the tile overlay
        TileOverlayOptions tpo = new TileOverlayOptions();
        tpo.tileProvider(new DropboxTileProvider());
        mMap.addTileOverlay(tpo);
        
        //Show only the tile overlay
        mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        
        //Return the map
		return inner;
		
	}
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
		//Get the camera position to save
        CameraPosition cp = getMap().getCameraPosition();
        
        //Save the data from it
		outState.putFloat("CamBearing", cp.bearing);
		outState.putFloat("CamTilt", cp.tilt);
		outState.putFloat("CamZoom", cp.zoom);
		outState.putDouble("CamTargetLat", cp.target.latitude);
		outState.putDouble("CamTargetLon", cp.target.longitude);
    }

}
