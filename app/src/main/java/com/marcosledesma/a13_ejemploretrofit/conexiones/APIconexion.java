package com.marcosledesma.a13_ejemploretrofit.conexiones;

import com.marcosledesma.a13_ejemploretrofit.modelos.Comment;
import com.marcosledesma.a13_ejemploretrofit.modelos.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIconexion {
    // Métodos y tipos de conexiones que se van a generar

    // Obtener todos los post (GET)
    @GET("/posts")
    Call<ArrayList<Post>> doGetPosts();

    // Obtener un post (GET/id)
    @GET("/posts/{idPost}")
    Call<Post> doGetOnePost(@Path("idPost") String idPost);

    // Obtener un post (GET/id)
    // @GET("/posts/{idPost}/{idUser}")
    // Call<Post> doGetOnePost(@Path("idPost") String idPost,@Path("idUser") String idUser);

    // Obtener todos los comentarios de un post (GET)
    @GET("/posts/{idPost}/comments")
    Call<ArrayList<Comment>> doGetComments(@Path("idPost") String idPost);

    // Misma variable que espera la API (postId)
    @GET("/comments?")
    Call<ArrayList<Comment>> doGetCommentsParam(@Query("postId") String idPost);


    // Crear un nuevo post (POST)
    // ->  http://jsonplaceholder.typicode.com/guide/ -> Creating a resource
    // -> Envíar la info codificada por BODY (no FORM)
    @POST("/posts")
    Call<Post> doCreatePost(@Body Post post);


    // Ejemplo para Hacerlo con un FORM en lugar de con un BODY
    // @POST("/posts")
    // Call<Post> doCreatePostForm(@Field("userId") int userId, @Field("title") String title, @Field("body") String body);

}
