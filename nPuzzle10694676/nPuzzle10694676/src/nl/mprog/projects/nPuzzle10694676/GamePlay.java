package nl.mprog.projects.nPuzzle10694676;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class GamePlay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameplay);
		Intent intent = getIntent();
		final int position = (Integer) intent.getExtras().get("id");
		
		try{
		
			Bitmap background = BitmapFactory.decodeResource(this.getResources(), GridAdapter.puzzles[position]);
			
			Bitmap cropped = Bitmap.createBitmap(background, 0, 0, 200, 200);
			background.recycle();
			
			ImageView iv = (ImageView) findViewById(R.id.imageView1);
			iv.setImageBitmap(cropped);
			iv.setClickable(true);
			iv.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i = new Intent(getApplicationContext(), YouWin.class);
					i.putExtra("Image", position);
					startActivity(i);
				}
			});
		}
		catch (OutOfMemoryError e){
			
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.image_selection, menu);
        return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		   int id = item.getItemId();
	       if (id == R.id.action_settings) {
	            return true;
	       }
	       return super.onOptionsItemSelected(item);
	}
}
