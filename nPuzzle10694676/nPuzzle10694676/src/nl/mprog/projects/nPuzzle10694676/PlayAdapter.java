package nl.mprog.projects.nPuzzle10694676;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class PlayAdapter extends BaseAdapter {
	private Context context;
	public static ArrayList <Bitmap> crops = new ArrayList<Bitmap>();

	
	public PlayAdapter(Context con, ArrayList<Bitmap> tiles){
		context = con;
		crops = tiles;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return crops.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return crops.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView iv;
		int para1 = ImageSelectionActivity.screenWidth/GamePlayActivity.columns;
		
		if(view == null){
			iv = new ImageView(context);
			iv.setLayoutParams(new GridView.LayoutParams(para1,para1));
			iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iv.setPadding(3, 3, 3, 3);
		} else{
			iv = (ImageView) view;
		}
		iv.setImageBitmap(crops.get(position));
		return iv;
	}

}
