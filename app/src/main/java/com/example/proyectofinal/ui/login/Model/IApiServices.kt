package com.example.proyectofinal.ui.login.Model

import android.telecom.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IApiServices {


    @GET("posts/")
    fun getAllPosts(): Call<List<Post>>


    @GET( "posts/{id}" )
    fun getPostById(@Path("id") id:Int ) :Call<Post>

    @POST("posts/{id}")
    fun editPostById (@Path ("id") id:Int , @Body post: Post?) :Call<Post>

}