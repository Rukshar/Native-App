package nl.mprog.projects.nPuzzle10694676;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridAdapter extends BaseAdapter {
	private Context context;
	public static Integer[] puzzles = {R.drawable.puzzle_0, R.drawable.puzzle_1, R.drawable.puzzle_2};


	public GridAdapter(Context c){
		context = c;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return puzzles.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return puzzles[position];
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
		if (view == null){
			iview = new ImageView(context);
			iview.setLayoutParams(new GridView.LayoutParams(300,300));
			iview.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iview.setPadding(8, 8, 8, 8);
		} else{
			iview = (ImageView) view;
		}
		iview.setImageResource(puzzles[position]);
		return iview;
	}

}
