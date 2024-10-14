package com.cloudandmobilecomputing.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ListActivity {
    private ArrayList<String> blogEntries; // To hold blog entries
    private EditText userNameInput;         // Input field for user name
    private EditText commentInput;           // Input field for comment
    private EditText dateInput;              // Input field for date
    private ArrayAdapter<String> adapter;    // Adapter for the ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the blog entries array list
        blogEntries = new ArrayList<>();

        // Initialize the layout
        setContentView(R.layout.activity_main); // Your layout file

        // Get references to input fields
        userNameInput = findViewById(R.id.userNameInput);
        commentInput = findViewById(R.id.commentInput);
        dateInput = findViewById(R.id.dateInput);

        // Set up the adapter for the ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, blogEntries);
        setListAdapter(adapter); // No need to find the ListView again

        // Set up the Submit button's click listener
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> addEntry());

        // Set up the Search button's click listener (currently not implemented)
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> searchEntries());
    }

    // Method to add a new blog entry
    private void addEntry() {
        String userName = userNameInput.getText().toString().trim();
        String comment = commentInput.getText().toString().trim();
        String date = dateInput.getText().toString().trim();

        // Check for empty fields
        if (userName.isEmpty() || comment.isEmpty() || date.isEmpty()) {
            if (userName.isEmpty()) {
                userNameInput.setBackgroundColor(0xFFFF0000); // Red background
            }
            if (comment.isEmpty()) {
                commentInput.setBackgroundColor(0xFFFF0000); // Red background
            }
            if (date.isEmpty()) {
                dateInput.setBackgroundColor(0xFFFF0000); // Red background
            }
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Reset background colors
        userNameInput.setBackgroundColor(0xFFFFFFFF); // White background
        commentInput.setBackgroundColor(0xFFFFFFFF); // White background
        dateInput.setBackgroundColor(0xFFFFFFFF); // White background

        // Format the new entry
        String newEntry = "Entry " + (blogEntries.size() + 1) + " - " + date + ": " + userName + ": " + comment;

        // Add the new entry to the list and notify the adapter
        blogEntries.add(0, newEntry); // Add to the top of the list
        adapter.notifyDataSetChanged(); // Refresh the ListView

        // Clear input fields
        userNameInput.setText("");
        commentInput.setText("");
        dateInput.setText("");
    }

    // Method to handle searching entries (not implemented yet)
    private void searchEntries() {
        // Future implementation for searching blog entries
        Toast.makeText(this, "Search functionality not implemented yet", Toast.LENGTH_SHORT).show();
    }

    // Handle list item clicks
    @Override
    public void onListItemClick(ListView parent, View v, int position, long id) {
        // Display a Toast message showing the selected blog entry
        Toast.makeText(this, "You have selected: " + blogEntries.get(position), Toast.LENGTH_SHORT).show();
    }
}
