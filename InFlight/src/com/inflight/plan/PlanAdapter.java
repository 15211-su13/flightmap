package com.inflight.plan;

import java.util.ArrayList;

import com.inflight.R;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class PlanAdapter implements Adapter, ListAdapter {
	private ArrayList<PlanPoint> points;
	private Context context;
	private LayoutInflater inflater = null;
	
	public PlanAdapter(Context context, ArrayList<PlanPoint> points) {
		this.context = context;
		this.points = points;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return points.size();
	}

	@Override
	public Object getItem(int i) {
		return points.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public int getItemViewType(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup parent) {
		View view = inflater.inflate(R.layout.simple_list_view, parent, false);
		
		((TextView)view.findViewById(R.id.name)).setText(points.get(arg0).getName());
		((TextView)view.findViewById(R.id.lat)).setText(points.get(arg0).getLoc().latitude + "");
		((TextView)view.findViewById(R.id.lon)).setText(points.get(arg0).getLoc().longitude + "");
		
		return view;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int arg0) {
		return true;
	}

}
