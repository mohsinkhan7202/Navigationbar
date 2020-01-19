package com.example.navigationbar;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>{
    private Context context;
    private List<Notice> notices;

    public NoticeAdapter(Context context, List<Notice> notices) {
        this.context = context;
        this.notices = notices;
    }

    @NonNull
    @Override
    public NoticeAdapter.NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from( context ).inflate( R.layout.routine_item,parent,false );
        return new NoticeViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeAdapter.NoticeViewHolder holder, int position) {
        holder.textView.setText( notices.get( position ).getName() );
        Picasso.with( context ).load( notices.get( position).getImageUrl() ).into( holder.imageView );
    }

    @Override
    public int getItemCount() {
        return notices.size();
    }


    public class NoticeViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        public NoticeViewHolder(@NonNull View itemView) {
            super( itemView );

            imageView=itemView.findViewById( R.id.imageView );
            textView=itemView.findViewById( R.id.txtTitle );
        }
    }

}
