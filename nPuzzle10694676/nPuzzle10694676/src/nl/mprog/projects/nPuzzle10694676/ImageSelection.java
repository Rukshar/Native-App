package nl.mprog.projects.nPuzzle10694676;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class ImageSelection extends ActionBarActivity {
	public ArrayList<Bitmap> imagess;
	public GridAdapter gridAdapter;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selection);
        
        imagess = new ArrayList<Bitmap>();
        
        for (int i = 0; i <= 10; i++){
        	int res = getResources().getIdentifier("puzzle_" + i, "drawable", getPackageName());
        	System.out.println(res);
        	if (res != 0){
        		Bitmap b = BitmapFactory.decodeResource(getResources(), res);
        		Bitmap scaled = Bitmap.createScaledBitmap(b, 500, 500, true);
        		System.out.println(scaled);
        		imagess.add(scaled);
        		//System.out.println(imagess);
        	}
        }
        
       
		GridAdapter gridAdapter = new GridAdapter(getApplicationContext(), imagess);
        GridView gv = (GridView) findViewById(R.id.gridview);
        gv.setAdapter(gridAdapter);
        gv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), GamePlay.class);
				intent.putExtra("id", position);
				startActivity(intent);
			}
        });     
    }	
 
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.image_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
        	case (R.id.easy):
        		GamePlay.columns = 3;
        	return true;
        	
        	case (R.id.medium):
        		GamePlay.columns = 4;
        	return true;
        	
        	case (R.id.difficult):
        		GamePlay.columns = 5;
        	return true;
        	
        	default:
                return super.onOptionsItemSelected(item);
        }
    }
}
