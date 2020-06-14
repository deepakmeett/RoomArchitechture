package com.example.roomarchitechture;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


//This is our Table Name("notes")
//This is case sensitive
//If we don't defined this then Room will use class name as Table Name by default
//If we want to customize our Table Name we should use it
@Entity(tableName = "notes")
public class NoteEntity {

    @PrimaryKey
    @NonNull
    private String id;
    
    @NonNull
    //If we want to change the column name for the particular field we can use the annotation of @ColumnInfo we will write name attribute and = "note" <- this column name
    @ColumnInfo(name = "note")
    private String mNote;

    public NoteEntity(@NonNull String id, @NonNull String note) {
        this.id = id;
        this.mNote = note;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getNote() {
        return this.mNote;
    }
}
