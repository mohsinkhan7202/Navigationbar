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

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.RoutineViewHolder>{
    private Context context;
    private List<Routine> routines;

    public RoutineAdapter(Context context, List<Routine> routines) {
        this.context = context;
        this.routines = routines;
    }

    @NonNull
    @Override
    public RoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from( context ).inflate( R.layout.routine_item,parent,false );
        return new RoutineViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull RoutineViewHolder holder, int position) {
        holder.textView.setText( routines.get( position ).getName() );
        Picasso.with( context ).load( routines.get( position).getImageUrl() ).into( holder.imageView );
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }


    public class RoutineViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        public RoutineViewHolder(@NonNull View itemView) {
            super( itemView );

            imageView=itemView.findViewById( R.id.imageView );
            textView=itemView.findViewById( R.id.txtTitle );
        }
    }

}
