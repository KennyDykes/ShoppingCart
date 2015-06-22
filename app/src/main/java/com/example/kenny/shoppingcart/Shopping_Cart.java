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
 * Shopping Cart Activity allows customer to view selected items
 */

public class Shopping_Cart extends ActionBarActivity implements View.OnClickListener {
   private ArrayList<Product> fetch;
   private  double total;
   private Database db;
   private ListView list;
    TextView tot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping__cart);
        list=(ListView)findViewById(R.id.checkoutlistView);
        Intent i = getIntent();
       fetch = (ArrayList<Product>) i
                .getSerializableExtra("list");
        db=new Database(this);
        //Sets the Shopping Cart Adapter to populate arraylist
        ArrayAdapter<Product> adapter=new CartAdapter();
        list.setAdapter(adapter);
        list.setFastScrollEnabled(true);
        tot=(TextView)findViewById(R.id.totalsenrty);
        tot.setText(String.valueOf(Cashier(fetch)));
    }
    /**
     * Allows us to populate the listview of shop activity with items
     */
    private class CartAdapter extends ArrayAdapter<Product> {
        public CartAdapter() {
            super(Shopping_Cart.this, R.layout.added_item, fetch);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Product pro = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.added_item, parent, false);
            }
            TextView name = (TextView) convertView.findViewById(R.id.side_name);
            final TextView price = (TextView) convertView.findViewById(R.id.money);
            Button remove=(Button)convertView.findViewById(R.id.removebtn);
            name.setText(pro.GetPName());
            price.setText(pro.GetPrice().toString());

            remove.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    pro.SetQuantity(pro.GetQuantity() + 1);
                    Toast.makeText(getContext(), "Deleted", Toast.LENGTH_LONG).show();
                    fetch.remove(pro);
                    tot.setText(String.valueOf(Minus(pro)));
                    Updating(pro);
                }});
            return convertView;
        }
    }
    /**
     * Calculates the total price of order
     * @return
     */
    private double Cashier(ArrayList<Product>cet)
    {
        total=0;
        for(int i=0;i<=cet.size()-1;i++)
        {
            total+=cet.get(i).GetPrice();
            Updating(cet.get(i));
        }
        return total;
    }
    public void Updating(Product p)
    {
        db.updateProduct(p);
    }



    @Override
    protected void onStop() {
        super.onStop();
        db.closeDB();
    }

    @Override
    protected void onPause() {
        super.onPause();
        db.closeDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping__cart, menu);
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
     * Checks to see if a product was removed in shopping cart
     * so that we may update our total and list
     * @return
     */
private double Minus(Product p)
{
    total=total-p.GetPrice();
    if(total<0)
    {
        total=0;
    }
    return total;
}


    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.checkbtn :
            Intent intent=new Intent(this,CheckoutPage.class);
            intent.putExtra("arraylist", fetch);
            intent.putExtra("total",total);
            startActivity(intent);
            break;
        }
    }
}
