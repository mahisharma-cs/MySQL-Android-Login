package com.mahi.dkmart;

//Read all he comments carefully
// Read README file also.

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class NewLoginActivity extends AppCompatActivity {
	
//Logout button
	private Button Logout;
//Username 
	private TextView Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		
//hide title bar(no need it -- just for UI)
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
		
		Logout = findViewById(R.id.btn_logout);
		Username = findViewById(R.id.username);
		
		Username.setText(NewLoginActivity.str_username);
		
		Logout.setOnClickListener(new View.OnClickListener{
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(this, NewLoginActivity.class));
			}
		});
		
	}
	
	


}