package com.naver.helloworld.resort.android;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class GuestFinderModernFragment extends Fragment {
	private static final String TAG = "GuestFinderFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
		Log.d(TAG,"on create view");
		return inflater.inflate(99999, container, false);
	}
	
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	/* lookup views */
		Button calcButton = (Button) view.findViewById(0);
		Button sendButton = (Button) view.findViewById(0);

		/* bind event listeners */
		calcButton.setOnClickListener(v -> calculate());
		sendButton.setOnClickListener(v -> send());
    }
	
	private void calculate() {
	}
	
	private void send() {
	}	
}