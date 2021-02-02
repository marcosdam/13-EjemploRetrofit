package com.marcosledesma.a13_ejemploretrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marcosledesma.a13_ejemploretrofit.R;
import com.marcosledesma.a13_ejemploretrofit.modelos.Comment;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentVH> {
    // Vars necesarias
    private List<Comment> objects;
    private int resource;
    private Context context;

    // Constructor completo
    public CommentsAdapter(List<Comment> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        View commentItem = LayoutInflater.from(context).inflate(resource, null);
        commentItem.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new CommentVH(commentItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentVH holder, int position) {
        Comment comment = objects.get(position);
        // Valores
        holder.txtPostId.setText(String.valueOf(comment.getPostID()));
        holder.txtId.setText(String.valueOf(comment.getID()));
        holder.txtEmail.setText(comment.getEmail());
        holder.txtBody.setText(comment.getBody());
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class CommentVH extends RecyclerView.ViewHolder {
        // Elementos de la fila
        TextView txtPostId, txtId, txtEmail, txtBody;
        View card;
        // Instanciarlo en su constructor
        public CommentVH(@NonNull View itemView) {
            super(itemView);

            txtPostId = itemView.findViewById(R.id.txtUserIdFilaPost);
            txtId = itemView.findViewById(R.id.txtIdFilaPost);
            txtEmail = itemView.findViewById(R.id.txtTitleFilaPost);
            txtBody = itemView.findViewById(R.id.txtBodyFilaPost);

            card = itemView;
        }
    }
}
