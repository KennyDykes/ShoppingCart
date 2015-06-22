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
 * Delete Product class allows a Seller to remove a product
 */

public class DeleteProduct extends ActionBarActivity implements View.OnClickListener{
    EditText delete;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);
        db=new Database(this);
        delete=(EditText)findViewById(R.id.DeleteText);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete_product, menu);
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
    protected void onPause() {
        super.onPause();
        db.closeDB();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.deleteBut:
                if (delete.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Fill out every line please", Toast.LENGTH_LONG).show();
                    break;
                } else {
                    db.deleteProduct(delete.getText().toString());    //Deletes a product based on product name
                    Toast.makeText(getBaseContext(), "Deleted", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(this,Seller_Menu.class);
                    startActivity(i);
                }
               break;
        }

    }
}
