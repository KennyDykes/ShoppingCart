package com.example.kenny.shoppingcart;

import java.io.Serializable;

/**
 * Created by Kenny on 6/19/2015.
 * Product class that defines a product's attributes
 */
public class Product implements Serializable{
    private String product_name;
    private Double price,cost;
    private int quantity;
    private long id;

    /**
     * Constructors
     */
     Product(){}
   Product(String name,int quan, double money)
    {this.product_name=name;
     this.price=money;
    this.quantity=quan;
    }

    Product(String name,double asking,double spent,int count)
    {
        this.product_name=name;
        this.price=asking;
        this.cost=spent;
        this.quantity=count;
    }

    /**
     * Accessor methods
     */
   public String GetPName()
    {return this.product_name;}
   public Double GetPrice()
    {return this.price;}
   public Double GetCost()
    {return this.cost;}
   public int GetQuantity()
    {return this.quantity;}
    public long GetId()
    {return this.id;}

    /**
     * Set methods
     */
   public void SetPName(String p)
    { this.product_name=p;}
   public void SetPrice(double p)
    { this.price=p;}
   public void SetCost(double c)
    {this.cost=c;}
    public void SetQuantity(int q)
    { this.quantity=q;}
    public void SetId(long i)
    { this.id=i;}


}
