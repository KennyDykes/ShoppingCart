package com.example.kenny.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Shop activity displays all of the products we have in our sqllite database
 */

public class Shop extends ActionBarActivity  implements View.OnClickListener{
    Database db;
    ArrayList<Product> all,collect;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        //Intializes database
        db=new Database(this);
        //Gets all products from arraylist
        all=new ArrayList<Product>(db.getAllProducts());
        collect=new ArrayList<Product>();
        list=(ListView)findViewById(R.id.skatelistView);
        //array adapter that is filled by arraylist
        ArrayAdapter<Product>adapter=new ProductAdapter();
        list.setAdapter(adapter);
        list.setFastScrollEnabled(true);
      //  list.setFastScrollAlwaysVisible(true);
    }
    /**
     * Allows us to populate the listview of shop activity with items
     */
    private class ProductAdapter extends ArrayAdapter<Product> {
        public ProductAdapter() {
            super(Shop.this, R.layout.item_, all);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Product pro = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_, parent, false);
            }
            // Lookup view for Product population
            TextView name = (TextView) convertView.findViewById(R.id.product_entry);
            TextView price = (TextView) convertView.findViewById(R.id.price_entry);
            final TextView quantity=(TextView) convertView.findViewById(R.id.quantity_entry);
            final Button dummy=(Button)convertView.findViewById(R.id.entry_button);
            // Populate the Product into the template view using the Product object
            name.setText(pro.GetPName());
            price.setText(pro.GetPrice().toString());
            quantity.setText(String.valueOf(pro.GetQuantity()));
            dummy.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if(pro.GetQuantity()>0)
                    {
                        dummy.setText("Added");
                        pro.SetQuantity(pro.GetQuantity()-1);
                        quantity.setText(String.valueOf(pro.GetQuantity()));
                        collect.add(pro);}
                    else
                    {
                        Toast.makeText(getContext(), "We have ran out of stock of this product", Toast.LENGTH_LONG).show();
                    }
                }

            });
            return convertView;
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
        getMenuInflater().inflate(R.menu.menu_shop, menu);
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
     * OnClick Listener that passes the arraylist of selected items and passes them to the next class
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.cartbtn:
                Bundle bun=new Bundle();

                Intent intent = new Intent(this, Shopping_Cart.class);
                intent.putExtra("list", collect);

                startActivity(intent);

        }
        }
   }