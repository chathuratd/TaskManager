package com.example.taskmanager

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R

// The TaskAdapter class is responsible for binding Task data to RecyclerView
class TaskAdapter(private var tasks: List<Task>, context: Context) :
    RecyclerView.Adapter<TaskAdapter.NoteViewHolder>() {

    // Database helper instance for performing CRUD operations
    private val db: TaskDatabaseHelper = TaskDatabaseHelper(context)

    // ViewHolder class holds references to UI elements for each item in RecyclerView
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // References to UI elements in the item layout
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextview)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    // Creates ViewHolders for items in RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        // Inflate the item layout and create a ViewHolder
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_task, parent, false)
        return NoteViewHolder(view)
    }

    // Returns the number of items in the RecyclerView
    override fun getItemCount(): Int = tasks.size

    // Binds data to the UI elements in each item
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = tasks[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        // Set click listener for the update button
        holder.updateButton.setOnClickListener {
            // Start UpdateTask activity with note id as extra
            val intent = Intent(holder.itemView.context, UpdateTask::class.java).apply {
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        // Set click listener for the delete button
        holder.deleteButton.setOnClickListener {
            // Delete the note from the database and refresh the RecyclerView
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            // Show a toast message indicating note deletion
            Toast.makeText(holder.itemView.context, "Note deleted", Toast.LENGTH_SHORT).show()
        }
    }

    // Function to refresh the data in the adapter
    fun refreshData(newNotes: List<Task>) {
        tasks = newNotes
        notifyDataSetChanged() // Notify RecyclerView that data has changed
    }
}
