package com.example.roomarchitechture;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdaptor extends RecyclerView.Adapter<NoteAdaptor.ViewHolder> {
    
    public interface OnDeleteClickListener{
        void OnDeleteClickListener(NoteEntity myNoteEntity);
    }

    Context context;
    List<NoteEntity> list;
    OnDeleteClickListener onDeleteClickListener;
    

    public NoteAdaptor(Context context, OnDeleteClickListener listener) {
        this.onDeleteClickListener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from( context );
        View view = layoutInflater.inflate( R.layout.noteitem, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (list != null) {
            NoteEntity noteEntity = list.get( position );
            holder.setData( noteEntity.getNote(), position );
        } else {
            holder.textView.setText( "Data is not there" );
        }
        
        holder.imageEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context, DataUpdateActivity.class );
                intent.putExtra( "note_id", list.get( position ).getId() );
                ((Activity)context).startActivityForResult( intent, 2 );
            }
        } );

        holder.imageUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDeleteClickListener != null){
                    onDeleteClickListener.OnDeleteClickListener( list.get( position ) );
                }
            }
        } );

    }
    
    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public void setNotes(List<NoteEntity> noteEntities) {
        list = noteEntities;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        int mPosition;
        ImageView imageEdit, imageUpdate;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            textView = itemView.findViewById( R.id.noteText );
            imageEdit = itemView.findViewById( R.id.editUpdate );
            imageUpdate = itemView.findViewById( R.id.editText );
        }

        public void setData(String note, int position) {
            textView.setText( note );
            mPosition = position;
        }
    }
}
