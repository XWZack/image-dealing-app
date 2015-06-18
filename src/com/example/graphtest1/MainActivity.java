package com.example.graphtest1;

import com.example.view.HandWriteView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private HandWriteView hv;
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		hv = (HandWriteView) findViewById(R.id.image);
		btn = (Button) findViewById(R.id.clear);
		btn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				hv.clear();
			}
		});
	}
}
