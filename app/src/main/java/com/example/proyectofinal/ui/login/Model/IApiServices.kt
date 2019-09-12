package com.example.proyectofinal.ui.login.Model

import com.example.proyectofinal.data.model.Usuario
import retrofit2.Call
import retrofit2.Callback;
import retrofit2.http.*

interface IApiServices {

    /* Usuario */
    @GET("user/")
    fun getAllPosts(): Call<List<Usuario>>

    @GET( "user/{id}" )
    fun getPostById(@Query("id") id: Int) : Call<Usuario>

    @POST("user/{id}")
    fun editPostById (@Path ("id") id:Int , @Body post: Usuario?) : Call<Usuario>

}