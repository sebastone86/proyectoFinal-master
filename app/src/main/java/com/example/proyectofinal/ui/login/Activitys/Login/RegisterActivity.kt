package com.example.proyectofinal.ui.Activitys.Login


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal.R
import androidx.appcompat.widget.Toolbar
import com.example.proyectofinal.data.model.Usuario
import com.example.proyectofinal.ui.login.Model.IApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {

    var mAPIService: IApiServices? = null
    val builder = AlertDialog.Builder(this)

    private lateinit var toolbar: Toolbar
    private lateinit var btnCancelar: Button
    private lateinit var btnAceptar: Button
    private lateinit var etNombre: EditText
    private lateinit var etApellido: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setupUI()
        initializeToolbar()
    }

    private fun initializeToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun setupUI() {
        etNombre= findViewById(R.id.etNombre)
        etApellido= findViewById(R.id.etApellido)
        etEmail= findViewById(R.id.etEmail)
        etPass= findViewById(R.id.etPass)
        toolbar = findViewById(R.id.toolbarRegister)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnAceptar = findViewById(R.id.btnAceptar)
        btnCancelar.setOnClickListener {
            finish()
        }

        btnAceptar.setOnClickListener {
          if(  validarDatos() ){
              registerUser()
              finish()
          }
        }
    }

    private fun registerUser() {
        mAPIService = IApiServices.ApiUtils.apiService

        //Some Button click
        mAPIService!!.registerUserPost("SampleTest2@gamil.com", "123456", "nombre", "apellido").enqueue(object :
            Callback<Usuario> {

            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                Log.i("", "post submitted to API." + response.body()!!)

                if (response.isSuccessful()) {
                    Log.i("", "post registration to API" + response.body()!!.toString())
                    builder.setTitle("Maravilloso")
                    builder.setMessage("El usuario se creo correctamente")
                    builder.setPositiveButton(android.R.string.ok) { _,_ ->
                        Toast.makeText(applicationContext,
                            android.R.string.ok, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                builder.setTitle("Error")
                builder.setMessage("El usuario no se ha podido crear")
                builder.setNegativeButton(android.R.string.ok) { _,_ ->
                    Toast.makeText(applicationContext,
                        android.R.string.ok, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun validarDatos():Boolean{

        if(etNombre.text.isNullOrEmpty()){
            etNombre.error = getString(R.string.validateEmpty)
            return false
        }
        if(etApellido.text.isNullOrEmpty()){
            etApellido.error = getString(R.string.validateEmpty)
            return false
        }
        if(etEmail.text.isNullOrEmpty()){
            etEmail.error = getString(R.string.validateEmpty)
            return false
        }
        if(etPass.text.isNullOrEmpty()){
            etPass.error = getString(R.string.validateEmpty)
            return false
        }
        return true
    }
}