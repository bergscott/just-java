package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot order more than 100 coffees.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot order less than 1 coffee.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        boolean addWhippedCream = hasWhippedCream();
        boolean addChocolate = hasChocolate();
        int price = calculatePrice(addWhippedCream, addChocolate);
        String name = getName();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price, addWhippedCream, addChocolate,
                                                              name));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     *
     * @param addWhippedCream true if customer wants whipped cream topping
     * @param addChocolate true if customer wants chocolate topping
     * @return price of the order
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // base price is $5
        int price = 5;

        // add $1 for whipped cream if selected
        if (addWhippedCream) {
            price += 1;
        }

        // add $2 for chocolate if selected
        if (addChocolate) {
            price += 2;
        }

        // calculate total price by multiplying by quantity
        return quantity * price;
    }

    /**
     * Creates a message that summarizes the order
     *
     * @param totalPrice total price of the order
     * @param addWhippedCream true if user wants whipped cream topping
     * @param addChocolate true if user wants chocolate topping
     * @param name the name of the customer
     * @return message that summarizes the order
     */
    private String createOrderSummary(int totalPrice, boolean addWhippedCream,
                                      boolean addChocolate, String name) {
        String summary = "Name: " + name;
        summary += "\nAdd whipped cream? " + addWhippedCream;
        summary += "\nAdd chocolate? " + addChocolate;
        summary += "\nQuantity: " + quantity;
        summary += "\nTotal: $" + totalPrice;
        summary += "\nThank you!";
        return summary;
    }

    /**
     * Returns true if whipped cream check box is checked, otherwise returns false
     *
     * @return state of whipped cream check box
     */
    private boolean hasWhippedCream() {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        return whippedCreamCheckBox.isChecked();
    }

    /**
     * Returns true if chocolate check box is checked, otherwise returns false
     *
     * @return state of chocolate check box
     */
    private boolean hasChocolate() {
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        return chocolateCheckBox.isChecked();
    }

    /**
     * Returns the name entered in the name text field EditText view
     *
     * @return name of the customer
     */
    private String getName() {
        EditText nameEditText = (EditText) findViewById(R.id.name_text_field);
        return nameEditText.getText().toString();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
}