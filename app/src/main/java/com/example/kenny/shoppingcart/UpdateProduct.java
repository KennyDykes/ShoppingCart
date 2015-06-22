package com.example.kenny.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Activity allows the seller to update a product's quantity and price
 */
public class UpdateProduct extends ActionBarActivity implements View.OnClickListener{
    EditText name, qupdate,pupdate;
    Database db;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
         db=new Database(this);
        name=(EditText)findViewById(R.id.namechange);
        qupdate=(EditText)findViewById(R.id.updatqquan);
        pupdate=(EditText)findViewById(R.id.priceedit);
    }

    /**
     * Passes product to be changed in to the database so it may be updated
     */
 private void Fill()
 {
     Product p=new Product(name.getText().toString(),
             Integer.parseInt(qupdate.getText().toString()),
            Double.parseDouble(  pupdate.getText().toString()));

           db.updateProduct(p);
     Intent intent=new Intent(this,Seller_Menu.class);
     startActivity(intent);

 }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_product, menu);
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
    protected void onPause() {
        super.onPause();
        db.closeDB();
    }
    /**
     * Closes the database when it's not needed
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.closeDB();
    }

    /**
     * Click Listener function that makes a function call to Fill() to check the EditTexts
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.button:
                Fill();
                break;

        }

    }
}
