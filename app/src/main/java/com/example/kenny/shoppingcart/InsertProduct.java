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
 * Insert Product class allows seller to insert a new product by entering necessary information
 */
public class InsertProduct extends ActionBarActivity implements View.OnClickListener {
    EditText item,price,cost,quantity;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);
        db=new Database(this);
        item=(EditText)findViewById(R.id.itemNameedit);
        price=(EditText)findViewById(R.id.priceedit);
        cost=(EditText)findViewById(R.id.costedit);
        quantity=(EditText)findViewById(R.id.quantityedit);
    }

    /**
     * Checks if every line is filled and places product in the database
     */
  private void check()
  {
      if(item.getText().toString().equals("") || price.getText().toString().equals("")
              || cost.getText().toString().equals("")|| quantity.getText().toString().equals(""))
      {
          Toast.makeText(getBaseContext(), "Fill out every line please", Toast.LENGTH_LONG).show();
      }
      else {
          Product pro = new Product(item.getText().toString(),
                  Double.parseDouble(price.getText().toString()), Double.parseDouble(cost.getText().toString()),
                  Integer.parseInt(quantity.getText().toString()));
           pro.SetId( db.createProduct(pro));
          Toast.makeText(getBaseContext(), "Product Added", Toast.LENGTH_SHORT).show();
          Intent intent = new Intent(this, Seller_Menu.class);
          startActivity(intent);
      }
  }

    @Override
    protected void onPause() {
        super.onPause();
        db.closeDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.closeDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_insert_product, menu);
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
            case R.id.OKbutton:
                check();
                break;
        }

    }
}
