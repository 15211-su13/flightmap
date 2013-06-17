package com.inflight.plan;

import com.inflight.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class PlanFragment extends Fragment {
	

    @Override
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

	    ListView lv = (ListView) getView().findViewById(R.id.result_list);
	    lv.setAdapter(new PlanAdapter(this.getActivity().getApplicationContext()));
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

}
