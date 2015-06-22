package com.example.kenny.shoppingcart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenny on 6/19/2015.
 */
public class Database extends SQLiteOpenHelper {
    /**
     * Database name
     */
    public static final String DATABASE_NAME = "Ebay.db";
    /**
     * Login Table that will hold username and password information
     */
    private static final String TABLE_Login = "Login";
    /**
     * Products Table will hold Product information
     */
    private static final String TABLE_Products = "Products";
    /**
     * Orders Table
     */
    private static final String TABLE_Orders = "Orders";

    /**
     * Login  Table Columns
     */
    private static final String _ID = "id";
    private static final String _NAME = "Username";
    private static final String _PASS = "Password";
    private static final String _TYPE = "cs";
    /**
     * Products Table Columns
     */
    private static final String PRODUCT_ID = "id";
    private static final String PRODUCT_NAME = "username";
    private static final String _PRICE = "Price";
    private static final String _COST = "Cost";
    private static final String _QUANTITY = "Quantity";
    /**
     *
     */
    private static final String O_ID = "id";
    private static final String O_COST = "Cost";
    private static final String O_PRICE = "Price";





    public Database(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    /**
     * Creates two tables to the database
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_Login + "("
                + _ID + " INTEGER PRIMARY KEY," + _NAME + " TEXT,"
                + _PASS + " TEXT," +_TYPE+" TEXT "+ ")";

        String CREATE_Products_TABLE = "CREATE TABLE " + TABLE_Products + "("
                + PRODUCT_ID + " INTEGER PRIMARY KEY," +PRODUCT_NAME + " TEXT,"
                + _PRICE + " REAL," +_COST +" REAL,"+ _QUANTITY + " INTEGER " + ")";

        String CREATE_Orders_TABLE = "CREATE TABLE " + TABLE_Orders + "("
                + O_ID + " INTEGER PRIMARY KEY,"
                + O_PRICE + " REAL," +O_COST +" REAL"+ ")";
        sqLiteDatabase.execSQL( CREATE_LOGIN_TABLE );
        sqLiteDatabase.execSQL(CREATE_Products_TABLE);
        sqLiteDatabase.execSQL(CREATE_Orders_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // TODO Auto-generated method stub
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+ TABLE_Login);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+ TABLE_Products);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+ TABLE_Orders);
        onCreate(sqLiteDatabase);
    }
    /**
     * Inserts a User
     */
    public long createUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
       Log.e("Entry",user.GetUsername());
        ContentValues values = new ContentValues();
        values.put(_NAME, user.GetUsername());
        values.put(_PASS, user.GetPassword());
        values.put(_TYPE,user.GetType());
        long user_id = db.insert(TABLE_Login, null, values);

        return user_id;
    }

    /**
     * Queries database for a specific user
     * @param
     * @return
     */
    public User getAUser(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_Login + " WHERE "
                + _NAME + " = " + user.GetUsername() +" AND " + _PASS + " = " + user.GetPassword();

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        User get = new User();
        get.SetUsername((c.getString(c.getColumnIndex(_NAME))));
        get.SetPassword(c.getString(c.getColumnIndex(_PASS)));
        get.SetType(c.getString(c.getColumnIndex(_TYPE)));
        return get;
    }
    public List<User> getAllUsers() {
        List<User> user = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_Login;

        Log.e("Users", selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                User u = new User();
                u.SetUsername(c.getString(c.getColumnIndex(_NAME)));
                u.SetPassword(c.getString(c.getColumnIndex(_PASS)));
                u.SetType(c.getString(c.getColumnIndex(_TYPE)));
                Log.e("Username",u.GetUsername());
                Log.e("Password",u.GetPassword());
                // adding to product list
                user.add(u);
            } while (c.moveToNext());
        }
        return user;
    }
    /**
     * Creates product and inserts it in the database
     * @param product
     * @return
     */
    public long createProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PRODUCT_NAME, product.GetPName());
        values.put(_PRICE, product.GetPrice());
        values.put(_COST,product.GetCost());
        values.put(_QUANTITY,product.GetQuantity());

        long product_id = db.insert(TABLE_Products, null, values);

        return product_id;
    }

    /**
     * Gets all products in the database
     * @return
     */
    public List<Product> getAllProducts() {
        List<Product> product = new ArrayList<Product>();
        String selectQuery = "SELECT  * FROM " + TABLE_Products;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Product p = new Product();
                p.SetPName(c.getString(c.getColumnIndex(PRODUCT_NAME)));
                p.SetPrice(c.getDouble(c.getColumnIndex(_PRICE)));
                p.SetCost(c.getDouble(c.getColumnIndex(_COST)));
                p.SetQuantity(c.getInt(c.getColumnIndex(_QUANTITY)));

                // adding to product list
                product.add(p);
            } while (c.moveToNext());
        }
        return product;
    }
    public long createOrder(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(O_PRICE, product.GetPrice());
        values.put(O_COST,product.GetCost());


        long product_id = db.insert(TABLE_Orders, null, values);

        return product_id;
    }

    public void deleteProduct(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Products, PRODUCT_NAME + " = ?",
                new String[] { String.valueOf(name) });
    }

    /**
     * Updates a product's quantity and price
     * @param item
     * @return
     */
    public int updateProduct(Product item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
         values.put(_QUANTITY, item.GetQuantity());
         values.put(_PRICE,item.GetPrice());

        // updating row
        return db.update(TABLE_Products, values, PRODUCT_NAME + " =?" ,new String[] { String.valueOf(item.GetPName()) });
    }
    public List<Product> getAllOrders() {
        List<Product> product = new ArrayList<Product>();
        String selectQuery = "SELECT  * FROM " + TABLE_Orders;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Product p = new Product();

                p.SetPrice(c.getDouble(c.getColumnIndex(O_PRICE)));
                p.SetCost(c.getDouble(c.getColumnIndex(O_COST)));

                // adding to product list
                product.add(p);
            } while (c.moveToNext());
        }
        return product;
    }
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
