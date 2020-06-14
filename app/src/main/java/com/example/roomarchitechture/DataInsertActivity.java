package com.example.roomarchitechture;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class DataInsertActivity extends AppCompatActivity {
    
    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        editText = findViewById( R.id.edit_query );
        button = findViewById( R.id.buttonPanel );
        
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                Intent result = new Intent(  );
                if (TextUtils.isEmpty( editText.getText() )){
                    setResult( RESULT_CANCELED, result );
                }else {
                    String note = editText.getText().toString();
                    result.putExtra( "note", note );
                    setResult( RESULT_OK, result );
                }
                
                finish();
            }
        } );
    }
}
