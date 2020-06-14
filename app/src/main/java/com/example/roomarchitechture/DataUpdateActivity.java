package com.example.roomarchitechture;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class DataUpdateActivity extends AppCompatActivity {
    
    EditText editText;
    Button updateButton;
    EditNoteViewModel viewModel;
    Bundle bundle;
    String noteId;
    LiveData<NoteEntity> noteLiveData;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main3 );
        editText = findViewById( R.id.edit_update );
        updateButton = findViewById( R.id.buttonPanel1 );
        
        bundle = getIntent().getExtras(  );
        if (bundle != null){
            noteId = bundle.getString("note_id");
        }
        
        viewModel = ViewModelProviders.of( this ).get( EditNoteViewModel.class );
        
        noteLiveData = viewModel.getNote( noteId );
        noteLiveData.observe( this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(NoteEntity noteEntity) {
                editText.setText( noteEntity.getNote() );
            }
        } );
        
        updateButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updateData = editText.getText().toString();
                Intent resultIntent = new Intent(  );
                resultIntent.putExtra( "note_id", noteId );
                resultIntent.putExtra( "note_text", updateData );
                setResult( RESULT_OK, resultIntent );
                finish();
            }
        } );
        
    }
}