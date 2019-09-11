package com.example.proyectofinal.ui.login.Model

import android.telecom.Call
import com.example.proyectofinal.data.model.Usuario
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface IApiServices {

    /* Usuario */
    @GET("user/")
    fun getAllPosts(): Call(List<Usuario>)

    @GET( "user/{id}" )
    fun getPostById(@Path("id") id:Int ) :Call(var u :Usuario )

    @POST("user/{id}")
    fun editPostById (@Path ("id") id:Int , @Body post: Usuario?) :Call<Usuario>

}