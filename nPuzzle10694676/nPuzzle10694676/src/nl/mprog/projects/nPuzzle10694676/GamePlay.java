package nl.mprog.projects.nPuzzle10694676;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GamePlay extends Activity {
	public static int height;
	public static int width;
	public static int columns;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameplay);
		Intent intent = getIntent();
		final int position = (Integer) intent.getExtras().get("id");
		final ArrayList<Bitmap> crops = new ArrayList<Bitmap>();
		
		try{
			Bitmap background = BitmapFactory.decodeResource(this.getResources(), GridAdapter.puzzles[position]);
			Bitmap empty = BitmapFactory.decodeResource(this.getResources(), R.drawable.square);
			height = background.getHeight();
			width =  background.getWidth();
			columns = 4;
			//System.out.println(height);
			//System.out.println(width);
			//System.out.println(empty.getHeight());
			//System.out.println(empty.getWidth());
			
			for(int j = 0; j <= (height - (height/columns)); j += (height/columns)){
				for(int i = 0; i <= (width - (width/columns)); i += (width/columns)){
					//System.out.println(i);
					//System.out.println(j);
					Bitmap cropped = Bitmap.createBitmap(background, i, j, (width/columns), (height/columns));
					crops.add(cropped);	
				}
			}
			Bitmap croppedEmpty = Bitmap.createBitmap(empty, (width/columns)*(columns-1), (height/columns)*(columns-1), 500, 500);
			background.recycle();
			empty.recycle();
			crops.remove(crops.size()-1);
			crops.add(croppedEmpty);
			//System.out.println(crops);
			final PlayAdapter playAdapter = new PlayAdapter(getApplicationContext(), crops);
			
			GridView gvp = (GridView) findViewById(R.id.gridviewplay);
			gvp.setNumColumns(columns);
			gvp.setAdapter(playAdapter);
			final Bitmap emptyTile = crops.get(crops.size()-1);
			gvp.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					int emptySpot = crops.indexOf(emptyTile);
					if(position >= 0 && position < crops.size()){
						if(position - 1 == emptySpot && (position) % (columns) != 0){
							Collections.swap(crops, position, position-1);
							playAdapter.notifyDataSetChanged();
						}
						else if(position + 1 == emptySpot && (position+1) % columns != 0){
							Collections.swap(crops, position, position+1);
							playAdapter.notifyDataSetChanged();
						}
						else if(position - columns == emptySpot){
							Collections.swap(crops, position, position-columns);
							playAdapter.notifyDataSetChanged();
						}
						else if(position + columns == emptySpot){
							Collections.swap(crops, position, position+columns);
							playAdapter.notifyDataSetChanged();
						}
						System.out.println();
					}
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
