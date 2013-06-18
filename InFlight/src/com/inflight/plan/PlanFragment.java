package com.inflight.plan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.google.android.gms.maps.model.LatLng;
import com.inflight.Main;
import com.inflight.R;
import com.inflight.plan.db.DAO;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PlanFragment extends Fragment {
	

    private EditText inputField;
    private ListView lv;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

	    lv = (ListView) getView().findViewById(R.id.result_list);
	    lv.setAdapter(new PlanAdapter(getView().getContext(), new ArrayList<PlanPoint>()));

	    inputField = (EditText) getView().findViewById(R.id.plan_field);
	    
        ((ImageButton) getView().findViewById(R.id.search_button)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				search(arg0);
			}
        	
        });
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View newView = inflater.inflate(R.layout.fragment_plan, container, false);
        return newView;
    }
	
	public void search(View view) {
		try {
			String plan = inputField.getText().toString();
			ArrayList<PlanPoint> pts = new ArrayList<PlanPoint>(plan.length());
			for (String sub : plan.split(" ")) {
				pts.add(new PlanPoint(sub, Main.database.getPosition(sub)));
			}
			((com.inflight.Main)getActivity()).setPlan(pts);
			lv.setAdapter(new PlanAdapter(getView().getContext(), pts));
		} catch (RuntimeException e) {
			Toast.makeText(getView().getContext(), e.toString(), Toast.LENGTH_LONG).show();
		}
		
	}

}
