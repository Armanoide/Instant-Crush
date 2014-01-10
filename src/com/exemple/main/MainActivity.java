package com.exemple.main;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.instantcrush.R;
import com.instantcrush.sql.ORM;
import com.instantcrush.sql.object.ASqlObject;
import com.instantcrush.sql.object.User;

public class MainActivity extends Activity {

	ORM db = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView id = (TextView) findViewById(R.id.user_id);
		TextView mail = (TextView) findViewById(R.id.user_email);

		db = new ORM(this);
		ArrayList<User> list = getAllUser();
		id.setText(list.get(0).get_id());
		mail.setText(list.get(0).get_email());
	}

	public ArrayList<User> getAllUser()
	{
		ArrayList<Object> list = db.read(User.class, null);
		ArrayList<User> users = new ArrayList<User>();
		for (int i = 0; i < list.size(); i++) { users.add((User)list.get(i));}
		return users;
	}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
}

}
