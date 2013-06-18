package com.inflight.plan;

import com.google.android.gms.maps.model.LatLng;

public class PlanPoint {
	public PlanPoint(String name, LatLng loc) {
		this.name = name;
		this.location = loc;
	}
	
	public LatLng getLoc() {
		return this.location;
	}
	
	public String getName() {
		return this.name;
	}
	
	private final LatLng location;
	private final String name;
}
