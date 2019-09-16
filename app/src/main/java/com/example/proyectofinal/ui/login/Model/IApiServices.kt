package com.example.proyectofinal.ui.login.Model

import com.example.proyectofinal.data.model.Usuario
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor;

interface IApiServices {

    /* Usuario */
    @GET("user/")
    fun getAllPosts(): Call<List<Usuario>>

    @GET( "user/{id}" )
    fun getPostById(@Query("id") id: Int) : Call<Usuario>

    @POST("register")
    @FormUrlEncoded
    fun registerUserPost(@Field("email") email: String,
                         @Field("password") password: String,
                         @Field("nombre") nombre: String,
                         @Field("apellido") apellido: String): Call<Usuario>







    /* Conection Service */
    object ApiUtils {

        val BASE_URL = "your_url"

        val apiService: IApiServices
            get() = RetrofitClient.getClient(BASE_URL)!!.create(IApiServices::class.java)

    }

    object RetrofitClient {
        var retrofit: Retrofit? = null

        fun getClient(baseUrl: String): Retrofit? {
            if (retrofit == null) {
                //TODO While release in Google Play Change the Level to NONE
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .build()

                retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }

}