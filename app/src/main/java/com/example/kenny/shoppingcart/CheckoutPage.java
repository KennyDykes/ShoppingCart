package com.example.kenny.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *The CheckoutPage Activity allows a customer to fill out the EditTexts to completely checkout
 */

public class CheckoutPage extends ActionBarActivity implements View.OnClickListener {
    EditText name,address,card,pin;
    ArrayList<Product> pass;
    TextView total;
    double tot;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page);
        db=new Database(this);
        Intent i=getIntent();
        pass= (ArrayList<Product>) i
                .getSerializableExtra("arraylist");       //Gets the arraylist of selected items passed from the shopping cart using serialization

        tot=i.getDoubleExtra("total",0);                  //Gets total price passed in from shopping cart also

        total=(TextView)findViewById(R.id.chtotal);
        name=(EditText)findViewById(R.id.editName);
        address=(EditText)findViewById(R.id.editAdd);
        card=(EditText)findViewById(R.id.editCard);
        pin=(EditText)findViewById(R.id.editPin);
        total.setText(String.valueOf(tot));

    }

    /**
     * Checks for empty lines and/or transitions back to the shop activity
     */
    private void EmptyCheck()
    {
        if(name.getText().toString().equals("")|| address.getText().toString().equals("")||card.getText().toString().equals("")
                || pin.getText().toString().equals(""))
        {
            Toast.makeText(getBaseContext(), "Fill out every line please", Toast.LENGTH_LONG).show();
        }
        else
        {
            Loop();
            Intent intent=new Intent (this,Shop.class);
            Toast.makeText(getBaseContext(), "Your ordered has been submitted.Thanks for shopping", Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }

    /**
     * Passes all of the ordered products into our Orders table in the database
     * so that we can use it later to calculate the revenue of the seller
     */
    private void Loop()
    {
        for(int i=0;i<=pass.size()-1;i++)
        {
            db.createOrder(pass.get(i));
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
        getMenuInflater().inflate(R.menu.menu_checkout_page, menu);
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

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.submitbtn:
                EmptyCheck();

                 break;
        }

    }
}
