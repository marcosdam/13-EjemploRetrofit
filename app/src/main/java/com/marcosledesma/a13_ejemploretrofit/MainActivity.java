package com.marcosledesma.a13_ejemploretrofit;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.marcosledesma.a13_ejemploretrofit.adapters.CommentsAdapter;
import com.marcosledesma.a13_ejemploretrofit.adapters.PostsAdapter;
import com.marcosledesma.a13_ejemploretrofit.conexiones.APIconexion;
import com.marcosledesma.a13_ejemploretrofit.conexiones.RetrofitObjeto;
import com.marcosledesma.a13_ejemploretrofit.modelos.Comment;
import com.marcosledesma.a13_ejemploretrofit.modelos.Post;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    // Necesito para Retrofit (1 objeto tipo Retrofit y 1 objeto tipo APIconexion)
    private Retrofit retrofit;
    private APIconexion conexion;

    // Modelo datos para posts
    private ArrayList<Post> listaPosts;
    private int filaPost;
    private RecyclerView recyclerView;
    private PostsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Recycler y Adapter para la Card
        filaPost = R.layout.post_card;
        listaPosts = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        // Iniciar retrofit
        retrofit = RetrofitObjeto.getConexion();
        // Crear objeto en base a la interfaz (retrofit rellene los métodos de forma automática)
        conexion = retrofit.create(APIconexion.class);

        adapter = new PostsAdapter(listaPosts, filaPost, this);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        doGetPosts();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Post post = new Post();
                post.setTitle("No sé si estoy solo");
                post.setBody("Nadie me contesta, creo que están pero no estoy segurp");
                post.setUserID(1);
                doCreatePost(post);
            }
        });
    }


    // Func para traer los posts (HTTP petición)
    private void doGetPosts(){
        // PREPARA para poder conectar (no conecta)
        Call<ArrayList<Post>> getPosts = conexion.doGetPosts();
        // CONECTA
        getPosts.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                // Dependiendo del código http (status) podré continuar o no
                if (response.code() == HttpURLConnection.HTTP_OK){
                    listaPosts.clear();
                    ArrayList<Post> posts = response.body();
                    listaPosts.addAll(posts);   // Lista para el adapter (card)
                    adapter.notifyDataSetChanged();

                    for (Post p : posts) {
                        Log.d("POSTS", p.toString());
                    }
                }else{
                    Log.e("ERROR", response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    //
    private void doGetPost(String idPost) {
        Call<Post> getPost = conexion.doGetOnePost(idPost);
        // CONECTA
        getPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.code() == 200){
                    Post p = response.body();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }


    //
    ArrayList<Comment> comments = new ArrayList<>();
    private void doGetComments(String idPost){
        Call<ArrayList<Comment>> getComments = conexion.doGetComments(idPost);
        getComments.enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                if (response.code() == HttpURLConnection.HTTP_OK){
                    comments.addAll(response.body());

                    for (Comment c : comments) {
                        Log.d("COMMENTS", c.toString());
                    }
                }else{
                    Log.e("ERROR", response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {

            }
        });
    }

    //
    private void doCreatePost(Post post){
        Call<Post> createPost = conexion.doCreatePost(post);
        Log.d("POSTS: ", post.toString()); // Post que envío
        createPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                // Debe devolver un 201 - CREATED (no un 200 - OK)
                if(response.code() == HttpURLConnection.HTTP_CREATED){  // Recibo post con ID creado
                    Post p = response.body();
                    Log.d("POSTS: ", "CODE: " + response.code() + "\n" + p.toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

}