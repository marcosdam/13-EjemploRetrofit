package com.marcosledesma.a13_ejemploretrofit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marcosledesma.a13_ejemploretrofit.R;
import com.marcosledesma.a13_ejemploretrofit.modelos.Post;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostVH> {
    // Vars necesarias
    private List<Post> objects;
    private int resource;
    private Context context;

    // Constructor completo
    public PostsAdapter(List<Post> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public PostsAdapter.PostVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        View postItem = LayoutInflater.from(context).inflate(resource, null);
        postItem.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new PostsAdapter.PostVH(postItem);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.PostVH holder, int position) {
        Post post = objects.get(position);
        // Valores
        holder.txtUserId.setText(String.valueOf(post.getUserID()));
        holder.txtId.setText(String.valueOf(post.getID()));
        holder.txtEmail.setText(post.getTitle());
        holder.txtBody.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class PostVH extends RecyclerView.ViewHolder {
        // Elementos de la fila
        TextView txtUserId, txtId, txtEmail, txtBody;
        View card;
        // Instanciarlo en su constructor
        public PostVH(@NonNull View itemView) {
            super(itemView);

            txtUserId = itemView.findViewById(R.id.txtUserIdFilaPost);
            txtId = itemView.findViewById(R.id.txtIdFilaPost);
            txtEmail = itemView.findViewById(R.id.txtTitleFilaPost);
            txtBody = itemView.findViewById(R.id.txtBodyFilaPost);

            card = itemView;
        }
    }
}
