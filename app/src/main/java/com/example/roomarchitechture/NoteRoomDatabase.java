package com.example.roomarchitechture;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//This @Database class has to include list of entities associated with it so within the annotation we will write the list of entities
// so we will write "list of entities name" entities AND we will take our "NoteEntity.class" 
// and with this we will also defined the version number "1"
// Version Number will help us in keeping track of the changes in this schema that we make in our database
@Database( entities = NoteEntity.class, version = 1)
//NoteRoomDatabase must be abstract class
public abstract class NoteRoomDatabase extends RoomDatabase {
    
    //Our database object is noteDoa();
    public abstract NoteDoa noteDoa();
    //Instance of our RoomDatabase is noteRoomDatabase. Here is another thing that we need to take care of that we should have single instance of database. To ensure that our database class should be SINGLETON 
    private static volatile NoteRoomDatabase noteRoomDatabase;
    
    static NoteRoomDatabase getNoteRoomDatabase(final Context context){
        if (noteRoomDatabase == null){
            synchronized (NoteRoomDatabase.class){
                if (noteRoomDatabase == null){
                    noteRoomDatabase = Room.databaseBuilder( context.getApplicationContext(),
                                    //the name of the database is "note_database"
                                    NoteRoomDatabase.class, "note_database").build();
                }
            }
        }
        return noteRoomDatabase;
    }
}