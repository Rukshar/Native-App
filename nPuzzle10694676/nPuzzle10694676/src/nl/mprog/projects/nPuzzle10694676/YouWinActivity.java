package nl.mprog.projects.nPuzzle10694676;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class YouWinActivity extends Activity {
	private int shuffles;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.youwin);
		Intent i = getIntent();
		int position = (Integer) i.getExtras().get("Images");
		shuffles = (Integer) i.getExtras().get("shuffles");
		
		// LOAD VIEWS
		Bitmap background = GridAdapter.puzzles.get(position);
		ImageView iv = (ImageView) findViewById(R.id.imageView2);
		TextView finalShuffles = (TextView) findViewById(R.id.steps);
		iv.setImageBitmap(background);
		finalShuffles.setText("Number of shuffles: " + shuffles);
		Button menu = (Button) findViewById(R.id.menu);
		menu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();	
			}
		});
	}
}