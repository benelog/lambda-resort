package com.naver.helloworld.resort.android;

import android.R;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


public class ClassicFragment extends Fragment {
	private static final String TAG = "GuestFinderFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
		Log.d(TAG,"on create view");
		return inflater.inflate(0, container, false);
	}
	
    public void onViewCreated(View view, Bundle savedInstanceState) {
		Button calcButton = (Button) view.findViewById(R.id.button1);
		Button sendButton = (Button) view.findViewById(R.id.button2);

		calcButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
		sendButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }
	
	private void calculate() {
	}
	
	private void send() {
	}	
}