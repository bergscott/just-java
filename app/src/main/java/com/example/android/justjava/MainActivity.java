package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        displayMessage(createOrderSummary(price, hasWhippedCream()));
    }

    /**
     * Calculates the price of the order.
     *
     * @return price of the order
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * Creates a message that summarizes the order
     *
     * @param totalPrice total price of the order
     * @param addWhippedCream true if whipped cream box was checked
     * @return message that summarizes the order
     */
    private String createOrderSummary(int totalPrice, boolean addWhippedCream) {
        String summary = "Name: Sailor Scott";
        summary += "\nAdd whipped cream? " + addWhippedCream;
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
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}