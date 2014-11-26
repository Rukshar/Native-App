package nl.mprog.projects.nPuzzle10694676;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

public class GamePlay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameplay);
		Intent intent = getIntent();
		final int position = (Integer) intent.getExtras().get("id");
		ArrayList<Bitmap> crops = new ArrayList<Bitmap>();
		
		try{
					
			for(int i = 0; i < 300; i += 100){
				for(int j = 0; j < 300; j +=100){
					Bitmap background = BitmapFactory.decodeResource(this.getResources(), GridAdapter.puzzles[position]);
					Bitmap cropped = Bitmap.createBitmap(background, i, j, 200, 200);
					background.recycle();
					crops.add(cropped);
				}
			}
			
			PlayAdapter playAdapter = new PlayAdapter(getApplicationContext(), crops);
			
			GridView gvp = (GridView) findViewById(R.id.gridviewplay);
			gvp.setAdapter(playAdapter);
			//gvp.setImageBitmap(cropped);
			//gvp.setClickable(true);
			//gvp.setOnClickListener(new View.OnClickListener() {
				
				//@Override
				//public void onClick(View v) {
					// TODO Auto-generated method stub
					//Intent i = new Intent(getApplicationContext(), YouWin.class);
					//i.putExtra("Image", position);
					//startActivity(i);
				//}
			//});
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
