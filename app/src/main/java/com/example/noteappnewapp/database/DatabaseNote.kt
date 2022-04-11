package com.example.noteappnewapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteappnewapp.model.Note

@Database(entities = [Note::class], version = 1)
abstract class DatabaseNote:RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object{

        @Volatile
        private var instance: DatabaseNote?= null
        private var LOOK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOOK){
            instance?:
            createDatabase(context).also {
                instance = it
            }
        }
        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DatabaseNote::class.java,
            "note_database"
        ).build()
    }
}