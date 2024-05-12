package com.example.taskmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesapp.databinding.ActivityUpdateTaskBinding

// Activity for updating a task
class UpdateTask : AppCompatActivity() {

    // Declaring variables for binding, database helper, and task ID
    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db: TaskDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflating the layout using ViewBinding
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initializing the database helper
        db = TaskDatabaseHelper(this)

        // Retrieving the task ID from the intent
        noteId = intent.getIntExtra("task_id", -1)

        // If the task ID is invalid, finish the activity
        if (noteId == -1) {
            finish()
            return
        }

        // Retrieving the task from the database using its ID
        val note = db.getNoteByID(noteId)

        // Setting the title and content of the task to the EditText fields
        binding.updatetitleEditText.setText(note.title)
        binding.updatecontentEdittext.setText(note.content)

        // Setting click listener for the save button
        binding.updatesaveButton.setOnClickListener {
            val newTitle = binding.updatetitleEditText.text.toString().trim()
            val newContent = binding.updatecontentEdittext.text.toString().trim()

            // Validating input fields
            if (newTitle.isEmpty() || newContent.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Creating an updated Task object with new title and content
                val updatedNote = Task(noteId, newTitle, newContent)

                // Updating the task in the database
                db.updateNote(updatedNote)

                // Finishing the activity and displaying a toast message
                finish()
                Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
