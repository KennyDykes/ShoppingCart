package com.example.kenny.shoppingcart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Login Activity allows user to login using a valid password or username
 */
public class Login extends ActionBarActivity implements View.OnClickListener{
    EditText username, password;
    String accessor_type,customer,seller;
    List<User>users;
     Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Context text=getApplication();
        Intent fetch=getIntent();
        username=(EditText) findViewById(R.id.usernameeditText);
        password=(EditText) findViewById(R.id.passwordeditText2);
        accessor_type=fetch.getStringExtra("type");  //access type is fetched(seller or customer)
        db =new Database(this);
        customer=new String("c");
        seller=new String("s");
       // User me=new User("Ken","pass","c");
        //db.createUser(me);
      //  User y=new User("K","piss","s");
      //  db.createUser(y);

    }

    /**
     * Checks what the user entered and compares it to the credentials in our our database
     */
   private void Credentials()
   {
       users=new ArrayList<User>(db.getAllUsers());
       User you=new User(username.getText().toString(),password.getText().toString(),accessor_type);

       for(int i=0;i<users.size();i++) {
           if (you.GetUsername().equals(users.get(i).GetUsername())&& you.GetPassword().equals(users.get(i).GetPassword())&& you.GetType().equals(seller)) {
               Intent in = new Intent(this, Seller_Menu.class);
               startActivity(in);

               break;

           } else if (you.GetUsername().equals(users.get(i).GetUsername())&& you.GetPassword().equals(users.get(i).GetPassword())&& you.GetType().equals(customer)) {
               Intent q=new Intent (this,Shop.class);
               startActivity(q);
               break;
           }
       }
    //   Toast.makeText(getBaseContext(), "Invalid Login", Toast.LENGTH_SHORT).show();

      return;

   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer_login, menu);
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
     * Closes the database when it's not needed
     */
    @Override
    protected void onPause()
    {super.onPause();
      db.closeDB();}
    /**
     * Closes the database when it's not needed
     */
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        db.closeDB();
    }


    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.Enterbutton:
                Credentials();
               // db.close();
                break;
            case R.id.register:
                Intent in=new Intent(this,RegisterPage.class);
                in.putExtra("tie",accessor_type);           //Passes the accessor type (seller or customer)
                startActivity(in);
        }

    }
}
