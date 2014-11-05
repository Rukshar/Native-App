package nl.mprog.setup.hello10694676;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi") public class Move extends Activity implements OnTouchListener {
	TextView display;
	Button back;
	ImageView star;
	

	@Override
	protected void onCreate(Bundle moveIt) {
		// TODO Auto-generated method stub
		super.onCreate(moveIt);
		Intent intent = getIntent();
		setContentView(R.layout.move);
		display = (TextView) findViewById(R.id.textView2);
		back = (Button) findViewById(R.id.backbutton);
		star = (ImageView) findViewById(R.id.imageView1);
		star.setOnTouchListener(this);
	}
	
	public void navigateBack(View view){
		Intent back = new Intent(this, MainActivity.class);
		back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
		startActivity(back);
	}
	
	float x,y = 0.0f;
	boolean moveit = false;

	@SuppressLint("NewApi") @Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()){
		case MotionEvent.ACTION_DOWN:
			moveit = true;
			break;
		case MotionEvent.ACTION_MOVE:
			if(moveit){
				x = event.getRawX() - star.getWidth()/2;
				y = event.getRawY() - star.getHeight()*3 / 2;
				star.setX(x);
				star.setY(y);
			}
			break;
		case MotionEvent.ACTION_UP:
			moveit = false;
			break;
		}
		return true;
	}

}
