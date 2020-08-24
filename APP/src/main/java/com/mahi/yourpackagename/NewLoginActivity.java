package com.mahi.dkmart;

//Read all he comments carefully
// Read README file also.

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class NewLoginActivity extends AppCompatActivity {

    EditText Username,Password;
    TextView Signup;
    String str_username;
    String str_password;
	
//Your sile link must contain https
    String url = "https://martdk.yoursitename.com/application/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		
//hide title bar(no need it -- just for UI)
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_login);

        Username = findViewById(R.id.et_log_username);
        Password = findViewById(R.id.et_log_password);
        Signup = findViewById(R.id.tv_signup);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//Start Register activity here
            }
        });

    }

    public void Login(View view) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");

        if(Username.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(Password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{

            progressDialog.show();
            str_username = Username.getText().toString().trim();
            str_password = Password.getText().toString().trim();


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        progressDialog.dismiss();
                        JSONObject jsonObject = new JSONObject(String.valueOf(response));
                        Username.setText("");
                        Password.setText("");
                        if(jsonObject.getString("status").equals("1"))
                        {
                            Toast.makeText(NewLoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(NewLoginActivity.this,MainActivity.class));
                        }
                        else if(jsonObject.getString("status").equals("0"))
                        {
                            Toast.makeText(NewLoginActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                        }
                        else if(jsonObject.getString("status").equals("-1"))
                        {
                            Toast.makeText(NewLoginActivity.this, "Sign up with "+str_username, Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(NewLoginActivity.this, "Else executed", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(NewLoginActivity.this, "out try ", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        progressDialog.dismiss();
                        e.printStackTrace();
                        Toast.makeText(NewLoginActivity.this, "Error : "+e, Toast.LENGTH_LONG).show();
                    }
                }

            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(NewLoginActivity.this, "on Error Response", Toast.LENGTH_SHORT).show();
                    Toast.makeText(NewLoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

//Pass here your .php file parameters
                    params.put("username",str_username);
                    params.put("password",str_password);

                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(NewLoginActivity.this);
            requestQueue.add(request);

        }

    }

}