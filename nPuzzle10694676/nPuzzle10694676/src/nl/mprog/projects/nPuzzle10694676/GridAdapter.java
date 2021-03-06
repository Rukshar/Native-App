package nl.mprog.projects.nPuzzle10694676;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridAdapter extends BaseAdapter {
	private Context context;
	public static ArrayList<Bitmap> puzzles = new ArrayList<Bitmap>();



	public GridAdapter(Context c, ArrayList<Bitmap> puzim){
		context = c;
		puzzles = puzim;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return puzzles.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return puzzles.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView iview;
		int imageSize = ImageSelectionActivity.screenWidth/3;
		if (view == null){
			iview = new ImageView(context);
			iview.setLayoutParams(new GridView.LayoutParams(imageSize,imageSize));
			iview.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iview.setPadding(8, 8, 8, 8);
		} else{
			iview = (ImageView) view;
		}
		iview.setImageBitmap(puzzles.get(position));
		return iview;
	}

}

