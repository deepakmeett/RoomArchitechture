package com.example.roomarchitechture;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;
public class MainActivity extends AppCompatActivity implements NoteAdaptor.OnDeleteClickListener {

    Button button;
    RecyclerView recyclerView;
    NoteAdaptor noteAdaptor;
    private NoteViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        button = findViewById( R.id.buttonPanel );
        recyclerView = findViewById( R.id.recycler );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        noteAdaptor = new NoteAdaptor( this, this );
        recyclerView.setAdapter( noteAdaptor );
        model = ViewModelProviders.of( this ).get( NoteViewModel.class );
        model.getAllListData().observe( this, new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(List<NoteEntity> noteEntities) {
                noteAdaptor.setNotes( noteEntities );
            }
        } );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, DataInsertActivity.class );   
                startActivityForResult( intent, 1 );
            }
        } );
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == 1 && resultCode == RESULT_OK) {
            final String note_id = UUID.randomUUID().toString();
            NoteEntity noteEntity = new NoteEntity( note_id, data.getStringExtra( "note" ) );
            model.insert( noteEntity );
            Toast.makeText( this, "Data Saved", Toast.LENGTH_SHORT ).show();
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            NoteEntity noteEntity = new NoteEntity(
                    data.getStringExtra( "note_id" ),
                    data.getStringExtra( "note_text" )
            );

            model.update( noteEntity );
            Toast.makeText( this, "Updated Data Saved", Toast.LENGTH_SHORT ).show();

        } else {
            Toast.makeText( this, "Data note Saved", Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    public void OnDeleteClickListener(NoteEntity myNoteEntity) {
        model.delete( myNoteEntity );
    }
}
