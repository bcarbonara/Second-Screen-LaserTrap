package com.example.group_project_try1;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import networking.ChatMessage;
import networking.Client;
import networking.GameMessage;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.text.method.ScrollingMovementMethod;

public class ChatActivity extends Activity {
	
	StringBuilder stringBuilder = new StringBuilder();
	
	private ExecutorService ex;
	private final Handler h = new Handler();
	private Client clnt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		// Show the Up button in the action bar.
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		TextView textView = (TextView) findViewById(R.id.text_box);
		textView.setMovementMethod(new ScrollingMovementMethod());
		ex = Executors.newFixedThreadPool(3);
		ex.execute(new Runnable() {
			@Override
			public void run() {
				try {
					clnt = new Client("129.62.150.22", 5000);
					while(true) {
						GameMessage gm = clnt.read();
						if(gm.getOperation().compareTo(ChatMessage.OPERATION) == 0) {
							final String str = ( (ChatMessage) gm ).getMessage();
							h.post(new Runnable() {
								@Override
								public void run() {
									alterText(str);								
								}
							});
						}
					}
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_chat, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void alterText(String s) {
    	if (this.stringBuilder.length() > 0) {
    		this.stringBuilder.insert(0, "\n");
    	}
    	this.stringBuilder.insert(0, s);
		TextView textView = (TextView) findViewById(R.id.text_box);
	    textView.setText( this.stringBuilder.toString() );
	    textView.setScrollY(0);
	}
    
    public void sendMessage(View view) {
		ex.execute(new Runnable() {
			@Override
			public void run() {
		    	EditText editText = (EditText) findViewById(R.id.edit_message);
				clnt.write( editText.getText().toString() );
			}
		});
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	//this.alterText( editText.getText().toString() );
    	editText.setText("");
    }

}
