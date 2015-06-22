package com.example.kenny.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * The Login Menu differentiates you from a customer or seller by the button you press
 */
public class LoginMenu_ extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case R.id.Sellerbutton:
                Intent in=new Intent(this,Login.class);
                in.putExtra("type", "s");            //passes a 's' to the next activity to indicate seller access
                startActivity(in);
                break;

            case R.id.Customerbutton:
                Intent cus=new Intent (this,Login.class);
                cus.putExtra("type","c");           //passes a 'c' to the next activity to indicate customer access
                startActivity(cus);
                break;
        }
    }



}
