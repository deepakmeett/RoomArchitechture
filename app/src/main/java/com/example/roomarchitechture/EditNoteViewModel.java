package com.example.roomarchitechture;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
public class EditNoteViewModel extends AndroidViewModel {
    
    NoteDoa noteDoa;
    NoteRoomDatabase roomDatabase;

    public EditNoteViewModel(@NonNull Application application) {
        super( application );
        roomDatabase = NoteRoomDatabase.getNoteRoomDatabase( application );
        noteDoa = roomDatabase.noteDoa();
    }
    
    public LiveData<NoteEntity> getNote(String noteId){
        return noteDoa.getNote( noteId );
    }
}
