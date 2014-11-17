package nl.mprog.projects.nPuzzle10694676;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class GamePlay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameplay);
		Intent intent = getIntent();
		int position = (Integer) intent.getExtras().get("id");
		
		try{
		
			Bitmap background = BitmapFactory.decodeResource(this.getResources(), GridAdapter.puzzles[position]);
			
			Bitmap cropped = Bitmap.createBitmap(background, 0, 0, 200, 200);
			background.recycle();
			
			ImageView iv = (ImageView) findViewById(R.id.imageView1);
			iv.setImageBitmap(cropped);
		}
		catch (OutOfMemoryError e){
			
		}


	}
	
	

}
