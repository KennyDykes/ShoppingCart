package com.example.kenny.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * The Seller Menu activity allows a seller to view their revenue and select an action to perform based on
 * the button the user clicks
 */

public class Seller_Menu extends ActionBarActivity implements View.OnClickListener{

    Database db;
    ArrayList<Product> menu;
    double cost=0,price=0,revenue=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller__menu);
        db=new Database(this);
        menu=new ArrayList<Product>(db.getAllOrders());
        ChangeText();
        TextView results=(TextView)findViewById(R.id.resultsText);
        results.setText(String.valueOf(revenue));

    }

    /**
     * Calculates the revenue
     */
  private void ChangeText()
  {
      for(int i=0;i<=menu.size()-1;i++)
      {
          cost+=menu.get(i).GetCost();
          price+=menu.get(i).GetPrice();
      }
      revenue=price-cost;
  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seller__menu, menu);
        return true;
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
     * Changes to desired activity based on which button was clicked
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.Updatebutton:
                Intent intent=new Intent(this,UpdateProduct.class);
                startActivity(intent);
                break;
            case R.id.Addbutton:
                Intent in =new Intent(this,InsertProduct.class);
                startActivity(in);
                break;
            case R.id.Deletebutton:
                Intent d=new Intent(this,DeleteProduct.class);
                startActivity(d);
                break;
        }
    }
}
