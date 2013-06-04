package com.inflight.maps;

import java.util.List;

import com.inflight.R;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

public class MainMap extends MapFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    GoogleMap map = getMap();
	}
}
