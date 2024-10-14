package com.cloudandmobilecomputing.myapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat; // Import to format date
import java.util.ArrayList;
import java.util.Date; // Import to get current date

public class MainActivity extends ListActivity {
    private ArrayList<String> blogEntries; // To hold blog entries
    private EditText userNameInput;         // Input field for user name
    private EditText commentInput;           // Input field for comment
    private ArrayAdapter<String> adapter;    // Adapter for the ListView
    private EditText searchTextInput;  // Input field for searching text
    private EditText searchDateInput;   // Input field for searching by date

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

        // Initialize search input fields
        searchTextInput = findViewById(R.id.searchTextInput);
        searchDateInput = findViewById(R.id.searchDateInput);

        // Set up the adapter for the ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, blogEntries);
        setListAdapter(adapter); // No need to find the ListView again

        // Set up the Submit button's click listener
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> addEntry());

        // Set up the Search button's click listener
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> searchEntries());
    }

    // Method to add a new blog entry
    private void addEntry() {
        String userName = userNameInput.getText().toString().trim();
        String comment = commentInput.getText().toString().trim();

        // Check for empty fields
        if (userName.isEmpty() || comment.isEmpty()) {
            if (userName.isEmpty()) {
                userNameInput.setBackgroundColor(0xFFFF0000); // Red background
            }
            if (comment.isEmpty()) {
                commentInput.setBackgroundColor(0xFFFF0000); // Red background
            }
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Reset background colors
        userNameInput.setBackgroundColor(0xFFFFFFFF); // White background
        commentInput.setBackgroundColor(0xFFFFFFFF); // White background

        // Get the current date and time
        String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        // Format the new entry
        String newEntry = "Entry " + (blogEntries.size() + 1) + " - " + currentDateTime + ": " + userName + ": " + comment;

        // Add the new entry to the list and notify the adapter
        blogEntries.add(0, newEntry); // Add to the top of the list
        adapter.notifyDataSetChanged(); // Refresh the ListView

        // Show a toast with the current date and time
        Toast.makeText(this, "Entry added at " + currentDateTime, Toast.LENGTH_SHORT).show();

        // Clear input fields
        userNameInput.setText("");
        commentInput.setText("");
    }

    // Method to handle searching entries (not implemented yet)
    private void searchEntries() {
        String searchText = searchTextInput.getText().toString().trim();
        String searchDate = searchDateInput.getText().toString().trim();

        ArrayList<String> filteredEntries = new ArrayList<>(); // To hold filtered entries

        // Loop through all entries to check for matches
        for (String entry : blogEntries) {
            boolean matchesText = searchText.isEmpty() || entry.toLowerCase().contains(searchText.toLowerCase());
            boolean matchesDate = searchDate.isEmpty() || entry.contains(searchDate); // Check if date is part of entry

            if (matchesText && matchesDate) {
                filteredEntries.add(entry); // Add entry if it matches search criteria
            }
        }

        // Update the ListView with filtered entries
        adapter.clear(); // Clear current list
        adapter.addAll(filteredEntries); // Add filtered entries
        adapter.notifyDataSetChanged(); // Notify the adapter of data change

        // Show message if no entries found
        if (filteredEntries.isEmpty()) {
            Toast.makeText(this, "No entries found for the given search criteria.", Toast.LENGTH_SHORT).show();
        }
    }

    // Handle list item clicks
    @Override
    public void onListItemClick(ListView parent, View v, int position, long id) {
        // Display a Toast message showing the selected blog entry
        Toast.makeText(this, "You have selected: " + blogEntries.get(position), Toast.LENGTH_SHORT).show();
    }
}
