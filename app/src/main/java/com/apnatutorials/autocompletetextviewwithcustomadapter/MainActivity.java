package com.apnatutorials.autocompletetextviewwithcustomadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    CustomerAdapter adapter = null;
    ArrayList<Customer> customers = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customers = new ArrayList<>();
        customers = populateCustomerData(customers);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        adapter = new CustomerAdapter(this, customers);
        autoCompleteTextView.setAdapter(adapter);

    }

    private ArrayList<Customer> populateCustomerData(ArrayList<Customer> customers) {
        customers.add(new Customer("Balendra", "Singh", 1, R.drawable.c1));
        customers.add(new Customer("Bipin", "Arora", 2, R.drawable.c2));
        customers.add(new Customer("Babulal", "Marandi", 3, R.drawable.c3));


        customers.add(new Customer("Aishwarya", "Rai", 8, R.drawable.c8));
        customers.add(new Customer("Asin", "Kaif", 9, R.drawable.c9));
        customers.add(new Customer("Arshi", "Khan", 10, R.drawable.c10));

        return customers;
    }
}
