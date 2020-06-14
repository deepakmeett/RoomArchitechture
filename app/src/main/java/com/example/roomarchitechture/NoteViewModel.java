package com.example.roomarchitechture;
import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
public class NoteViewModel extends AndroidViewModel {

    NoteDoa noteDoa;
    NoteRoomDatabase database;
    LiveData<List<NoteEntity>> allListData;

    public NoteViewModel(@NonNull Application application) {
        super( application );
        database = NoteRoomDatabase.getNoteRoomDatabase( application );
        noteDoa = database.noteDoa();
        allListData = noteDoa.getAllNotes();
    }

    public void insert(NoteEntity noteEntity) {
        new InsertAsyncTask( noteDoa ).execute( noteEntity );
    }

    // To get LiveData to the UI
    //In MainActivity we will use this "getAllListData" to get loaded data into RecyclerView
    //Since it is LiveData we will observe in MainActivity 
    LiveData<List<NoteEntity>> getAllListData() {
        return allListData;
    }

    public void update(NoteEntity noteEntity) {
        new UpdatedAsyncTask( noteDoa ).execute( noteEntity );
    }
    
    public void delete(NoteEntity noteEntity){
        new deleteAsyncTask(noteDoa).execute( noteEntity );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Toast.makeText( getApplication(), "ViewModel Destroyed", Toast.LENGTH_SHORT ).show();
    }

    private class InsertAsyncTask extends AsyncTask<NoteEntity, Void, Void> {

        NoteDoa noteDoa;

        public InsertAsyncTask(NoteDoa noteDoa) {
            this.noteDoa = noteDoa;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDoa.insert( noteEntities[0] );
            return null;
        }
    }

    private class UpdatedAsyncTask extends AsyncTask<NoteEntity, Void, Void> {

        NoteDoa noteDoa;

        public UpdatedAsyncTask(NoteDoa noteDoa) {
            this.noteDoa = noteDoa;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDoa.update( noteEntities[0] );
            return null;
        }
    }

    private class deleteAsyncTask extends AsyncTask<NoteEntity, Void, Void> {

        NoteDoa noteDoa;
        
        public deleteAsyncTask(NoteDoa noteDoa) {
            this.noteDoa = noteDoa;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            noteDoa.delete( noteEntities[0] );
            return null;
        }
    }
}
