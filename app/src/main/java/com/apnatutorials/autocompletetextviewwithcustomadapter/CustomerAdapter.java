package com.apnatutorials.autocompletetextviewwithcustomadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CustomerAdapter extends ArrayAdapter<Customer> {
    ArrayList<Customer> customers, tempCustomer, suggestions;

    public CustomerAdapter(Context context, ArrayList<Customer> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.customers = objects;
        this.tempCustomer = new ArrayList<Customer>(objects);
        this.suggestions = new ArrayList<Customer>(objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Customer customer = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customer_row, parent, false);
        }
        TextView txtCustomer = (TextView) convertView.findViewById(R.id.tvCustomer);
        ImageView ivCustomerImage = (ImageView) convertView.findViewById(R.id.ivCustomerImage);
        if (txtCustomer != null)
            txtCustomer.setText(customer.getFirstName() + " " + customer.getLastName());
        if (ivCustomerImage != null && customer.getProfilePic() != -1)
            ivCustomerImage.setImageResource(customer.getProfilePic());
        // Now assign alternate color for rows
        if (position % 2 == 0)
            convertView.setBackgroundColor(getContext().getColor(R.color.odd));
        else
            convertView.setBackgroundColor(getContext().getColor(R.color.even));

        return convertView;
    }


    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Customer customer = (Customer) resultValue;
            return customer.getFirstName() + " " + customer.getLastName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Customer people : tempCustomer) {
                    if (people.getFirstName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(people);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Customer> c = (ArrayList<Customer>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Customer cust : c) {
                    add(cust);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
