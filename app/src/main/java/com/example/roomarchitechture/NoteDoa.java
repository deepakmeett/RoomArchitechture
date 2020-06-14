package com.example.roomarchitechture;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDoa {
//Here we defined various SQL queries in form of function for various functions
    @Insert
    void insert(NoteEntity noteEntity);
    
    //It will return the list of LiveData
    // This is being used to show data in RecylerView.
    @Query( "SELECT * FROM NoteEntity" )
    LiveData<List<NoteEntity>> getAllNotes();
    
    //This is for like if click on edit button then it will take data from that CardView
    @Query( "SELECT * FROM NoteEntity WHERE id=:noteId" )
    LiveData<NoteEntity> getNote(String noteId);
    
    @Update
    void update(NoteEntity noteEntity);
    
    @Delete
    int delete(NoteEntity noteEntity);
}
