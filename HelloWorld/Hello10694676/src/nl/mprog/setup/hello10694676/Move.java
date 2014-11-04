package nl.mprog.setup.hello10694676;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Move extends Activity {
	TextView display;
	

	@Override
	protected void onCreate(Bundle moveIt) {
		// TODO Auto-generated method stub
		super.onCreate(moveIt);
		Intent intent = getIntent();
		setContentView(R.layout.move);
		display = (TextView) findViewById(R.layout.move);
		
	}
	

}
