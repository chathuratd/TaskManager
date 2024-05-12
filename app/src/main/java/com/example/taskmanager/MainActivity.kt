package com.example.taskmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Declaring variables for binding, database helper, and adapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: TaskDatabaseHelper
    private lateinit var notesAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initializing the binding object using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing the database helper
        db = TaskDatabaseHelper(this)

        // Initializing the adapter with data from the database
        notesAdapter = TaskAdapter(db.getAllNotes(), this)

        // Setting layout manager and adapter for RecyclerView
        binding.taskRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.taskRecyclerView.adapter = notesAdapter

        // Setting click listener for the add button
        binding.addButton.setOnClickListener {
            // Creating an intent to start the AddTask activity
            val intent = Intent(this, AddTask::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refreshing data in the adapter when the activity resumes
        notesAdapter.refreshData(db.getAllNotes())
    }
}
