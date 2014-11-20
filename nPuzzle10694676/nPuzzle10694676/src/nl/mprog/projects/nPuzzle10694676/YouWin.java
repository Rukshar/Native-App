package nl.mprog.projects.nPuzzle10694676;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class YouWin extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.youwin);
		Intent i = getIntent();
		int position = (Integer) i.getExtras().get("Image");
		
		Bitmap background = BitmapFactory.decodeResource(this.getResources(), GridAdapter.puzzles[position]);
		ImageView iv = (ImageView) findViewById(R.id.imageView2);
		iv.setImageBitmap(background);
		Button menu = (Button) findViewById(R.id.menu);
		menu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent mintent = new Intent(YouWin.this, ImageSelection.class);
				YouWin.this.startActivity(mintent);
				
			}
		});

	}
	

}
