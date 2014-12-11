package nl.mprog.projects.nPuzzle10694676;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class GamePlay extends Activity {
	public static int height;
	public static int width;
	public static int columns;
	public static int screenWidth;
	private int cropsIndexSize;
	private int count;
	private int positionImage;
	private TextView steps;
	private Bitmap emptyTile;
	private ArrayList<Bitmap> crops;
	private ArrayList<Bitmap> arraytocrops;
	private ArrayList<Bitmap> solved;
	private ArrayList<Integer> cropsIndex;
	private ArrayList<Integer> newArrayIndex;
	private PlayAdapter playAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gameplay);
		Intent intent = getIntent();
		positionImage = (Integer) intent.getExtras().get("id");
		
		//Load all functions
		System.out.println(columns + "COLUMNS IN ONCREATE");
		Log.d("test", columns + "COLUMNS IN ONCREATE");
		load();
		TheScreenWidth();
	
		//LOAD VIEWS
		playAdapter = new PlayAdapter(getApplicationContext(), crops);
		GridView gvp = (GridView) findViewById(R.id.gridviewplay);
		steps = (TextView) findViewById(R.id.counts);
		gvp.setNumColumns(columns);
		gvp.setAdapter(playAdapter);
		sleep();
		gvp.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				playGame(position);
				winGame();	
			}
		});
	}
	
	// LOADS THE SAVED VALUES
	public void load()
	{
		SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
		columns = sharedPreferences.getInt("level", 4);
		System.out.println(columns + "DE COLUMNS IN LOAD");
		count = sharedPreferences.getInt("steps", 0);
		createTheBitmaps();
		newArrayIndex = new ArrayList<Integer>();
		cropsIndexSize = sharedPreferences.getInt("size", columns*columns);
		System.out.println(cropsIndexSize + "DE SIZE IN LOAD");
		
		for (int i = 0; i < cropsIndexSize; i++)
		{
			int value = sharedPreferences.getInt("cropsindex" + i, i);
			newArrayIndex.add(value);
		}
		System.out.println(newArrayIndex + "NEWARRAYOFINDEXES AFTER LOAD");
		System.out.println(solved + "SOLVED LIST");
		indexesToBitmaps();
	}
	
	// SETS ARRAYLIST OF INDEXES TO BITMAP ARRAYLIST
	public void indexesToBitmaps()
	{
		 arraytocrops = new ArrayList<Bitmap>();
		 for(int i = 0; i < newArrayIndex.size(); i++)
		 {
			 int theIndex = newArrayIndex.get(i);
			 Bitmap thebitmap = solved.get(theIndex);
			 arraytocrops.add(thebitmap);
			 if (theIndex == newArrayIndex.size()-1)
			 {
				 emptyTile = thebitmap;
			 }
		 }
		 crops = arraytocrops; // ARRAY TO CROPS BECOMES CROPS
	}
		
	// DETERMINES THE SCREENWIDTH OF DEVICE
	public void TheScreenWidth()
	{
		DisplayMetrics dm = getResources().getDisplayMetrics(); 
		screenWidth = dm.widthPixels;
	}
	
	// CREATES LISTS OF BITMAPS
	public void createTheBitmaps()
	{
		crops = new ArrayList<Bitmap>();
		solved = new ArrayList<Bitmap>();
		System.out.println(columns + "DE COLUMNS IN CREATEBITMAPS");
		try
		{
			Bitmap background = GridAdapter.puzzles.get(positionImage);
			height = background.getHeight();
			width =  background.getWidth();
		
			for(int j = 0; j <= (height - (height/columns)); j += (height/columns))
			{
				for(int i = 0; i <= (width - (width/columns)); i += (width/columns))
				{
					Bitmap cropped = Bitmap.createBitmap(background, i, j, (width/columns), (height/columns));
					crops.add(cropped);
					solved.add(cropped);
				}
			}
		}catch (OutOfMemoryError e)
		{
			e.printStackTrace();
		}
		createArrayOfIndexes(); //CREATE ALSO ARRAYLIST WITH INDEXES
		System.out.println(cropsIndex + "Arralist with indexes AFTER BITMAP");
		crops.remove(crops.size()-1);
		solved.remove(solved.size()-1);
		addEmptySpot();
	}
	
	//CREATES ARRAYLIST OF INDEXES
	public void createArrayOfIndexes()
	{
		cropsIndex = new ArrayList<Integer>();
		System.out.println(columns + "DE COLUMNS IN CREATEARRAYLISTOFINDEXES");
		 for(int i = 0; i < (columns*columns); i++)
		 {
			 cropsIndex.add(i);
		 }
	}
	
	//ADDS THE EMPTY SPOT
	public void addEmptySpot()
	{
		Bitmap empty = BitmapFactory.decodeResource(this.getResources(), R.drawable.square);
		final Bitmap croppedEmpty = Bitmap.createBitmap(empty, (width/columns)*(columns-1), (height/columns)*(columns-1), 500, 500);
		empty.recycle();
	 	crops.add(croppedEmpty);
		solved.add(croppedEmpty);
	}
	
	//WIN CONSTRAINT
	public void winGame(){
		if(crops.equals(solved))
			{
			Intent NewIntent = new Intent(getApplicationContext(), YouWin.class);
			NewIntent.putExtra("shuffles", count);
			NewIntent.putExtra("Images", positionImage);
			startActivity(NewIntent);
			}
	}
	
	// SHUFFLES THE BITMAPS
	public void shuffleBitmaps(){
		if (columns == 4){
			Collections.swap(crops, 0, 1);
			Collections.reverse(crops);
			Collections.rotate(crops.subList(0, (columns*columns)), -1);
			playAdapter.notifyDataSetChanged();
		}else{
			Collections.reverse(crops);
			Collections.rotate(crops.subList(0, (columns*columns)), -1);
			playAdapter.notifyDataSetChanged();
		}
	}
	
	// GAME CONSTRAINTS
	public void playGame(int position){
		int emptySpot = crops.indexOf(emptyTile);
		if(position >= 0 && position < crops.size()){
			if(position - 1 == emptySpot && (position) % (columns) != 0)
			{
				Collections.swap(crops, position, position-1);
				Collections.swap(cropsIndex, position, position-1);
				playAdapter.notifyDataSetChanged();
				count++;
			}
			else if(position + 1 == emptySpot && (position+1) % columns != 0)
			{
				Collections.swap(crops, position, position+1);
				Collections.swap(cropsIndex, position, position+1);
				playAdapter.notifyDataSetChanged();
				count++;
			}
			else if(position - columns == emptySpot)
			{
				Collections.swap(crops, position, position-columns);
				Collections.swap(cropsIndex, position, position-columns);
				playAdapter.notifyDataSetChanged();
				count++;
			}
			else if(position + columns == emptySpot)
			{
				Collections.swap(crops, position, position+columns);
				Collections.swap(cropsIndex, position, position+columns);
				playAdapter.notifyDataSetChanged();
				count++;
			}
			steps.setText("Number of shuffles: " + count);
			//System.out.println(cropsIndex + "cropsIndex");
		}
	}
	
	// SHOWS SOLVED PUZZLE FOR 3 SECONDS
	public void sleep(){
		if (count == 0){
			new Thread(new Runnable(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try{
						Thread.sleep(3000);
						
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					runOnUiThread(new Runnable(){
						@Override
						public void run() {
							// TODO Auto-generated method stub
							shuffleBitmaps();
							//addEmptySpot(); // ADDS EMPTY SPOT TO ARRAYLISTS
							//emptyTile = crops.get(crops.size()-1);
							//emptyTile = crops.get(crops.size()-1);
							//count = 0;
						}
					});	
				}	
			}).start();
			
		}

	}
	
	// SAVES VALUES
	public void saved()
	{
		SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		
		for (int i = 0; i < cropsIndex.size(); i++)
		{
			editor.putInt("cropsindex" + i, cropsIndex.get(i));
		}
		
		editor.putInt("level", columns);
		editor.putInt("steps", count);
		editor.putInt("size", cropsIndex.size());
		editor.commit();
		System.out.println(columns + "COLUMNS IN SAVED");
		System.out.println(cropsIndex.size() + "SIZE IN SAVED");
	}
	
	// SAVES VALUES WHEN APP IS KILLED
	protected void onPause()
	{
		super.onPause();
		System.out.println("I WAS HERE");
		saved();
	}

	
	// OPTIONS MENU
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.during_game, menu);
        return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		// TODO Auto-generated method stub
		   switch(item.getItemId())
		   {
		   	case(R.id.reshuffle):
		   		Collections.shuffle(crops);
		   		playAdapter.notifyDataSetChanged();
		   	return true;
		   	
		   	case(R.id.easygame):
				SharedPreferences sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
		   		sharedPreferences.edit().remove("size");
		   		columns = 3;
		   		count = 0;
		   		Intent intent = getIntent();
		   		startActivity(intent);
		   	return true;
		   	
		   	case(R.id.mediumgame):
		   		this.getSharedPreferences("Data", MODE_PRIVATE).edit().clear().commit();
		   		columns = 4;
		   		count = 0;
			   	Intent intentje = getIntent();
			   	finish();
			   	startActivity(intentje);
		   	return true;
		   	
		   	case(R.id.difficultgame):
		   		this.getSharedPreferences("Data", MODE_PRIVATE).edit().clear().commit();
		   		columns = 5;
		   		count = 0;
		   		Intent intents = getIntent();
		   		finish();
		   		startActivity(intents);
		   	return true;
		   	
		   	case(R.id.quit):
		   		count = 0;
		   		Intent myIntent = new Intent(getApplicationContext(),ImageSelection.class);
		   		startActivity(myIntent);
		   	
		   	default:
		   		return super.onOptionsItemSelected(item);
		   }
	}

}