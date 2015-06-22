package com.example.kenny.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Register Page allows a user to create an account if one isn't already created for them
 */
public class RegisterPage extends ActionBarActivity implements View.OnClickListener{
    EditText user,pass;
    Database db;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        db=new Database(this);
        Intent got=getIntent();
         type=got.getStringExtra("tie");
         user=(EditText)findViewById(R.id.registeruser);
         pass=(EditText)findViewById(R.id.regispassword);
    }

    /**
     * Inserts username and password into database and creates a new user
     */
private void Insert()
{
    if(user.getText().toString().equals("")||
           pass.getText().toString().equals(""))
    {
        Toast.makeText(getBaseContext(), "Fill out every line please", Toast.LENGTH_LONG).show();
    }
    else
    {
        User you=new User(user.getText().toString(),pass.getText().toString(),type);
        db.createUser(you);
        Intent in=new Intent(this,LoginMenu_.class);
        startActivity(in);
    }
}
    /**
     * Closes the database when it's not needed
     */
    @Override
    protected void onPause() {
        super.onPause();
        db.closeDB();
    }
    /**
     * Closes the database when it's not needed
     */
    @Override
    protected void onStop() {
        super.onStop();
        db.closeDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Click Listener function that inserts new user to the database and moves to another activity
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.registerbtn:
                Insert();
                break;
        }
    }
}
