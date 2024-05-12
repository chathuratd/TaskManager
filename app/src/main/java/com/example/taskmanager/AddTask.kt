package com.example.taskmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesapp.databinding.ActivityAddTaskBinding


class AddTask : AppCompatActivity() {

    // Declaring variables for binding and database helper
    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var db: TaskDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initializing the binding object using ViewBinding
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing the database helper
        db = TaskDatabaseHelper(this)

        // Setting click listener for the save button
        binding.saveButton.setOnClickListener {
            // Retrieving title and content from EditText fields
            val title = binding.titleEditText.text.toString().trim()
            val content = binding.contentEdittext.text.toString().trim()

            // Checking if title or content is empty
            if (title.isEmpty() || content.isEmpty()) {
                // Showing a toast message if either field is empty
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // If both fields are filled, creating a Task object
                val note = Task(0, title, content)
                // Inserting the Task object into the database
                db.insertNote(note)
                // Finishing the activity
                finish()
                // Showing a toast message indicating successful task save
                Toast.makeText(this, "Task saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
