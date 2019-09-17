package com.example.proyectofinal.ui.Activitys.Login


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal.R
import androidx.appcompat.widget.Toolbar
import com.example.proyectofinal.data.model.Usuario
import com.example.proyectofinal.ui.login.Utiles.IApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {

    var mAPIService: IApiServices? = null

    private lateinit var toolbar: Toolbar
    private lateinit var btnCancelar: Button
    private lateinit var btnAceptar: Button
    private lateinit var etNombre: EditText
    private lateinit var etApellido: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var etPass2: EditText


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
        etNombre     = findViewById(R.id.etNombre)
        etApellido   = findViewById(R.id.etApellido)
        etEmail      = findViewById(R.id.etEmail)
        etPass       = findViewById(R.id.etPass)
        etPass2      = findViewById(R.id.etPass2)
        toolbar      = findViewById(R.id.toolbarRegister)
        btnCancelar  = findViewById(R.id.btnCancelar)
        btnAceptar   = findViewById(R.id.btnAceptar)
        btnCancelar.setOnClickListener {
            finish()
        }

        btnAceptar.setOnClickListener {
          if(validarDatos()){
              if(existeUsuario(etEmail.toString())) {
                  val builder = AlertDialog.Builder(this@RegisterActivity)
                  builder.setTitle(R.string.ocurriounProblema)
                  builder.setMessage(R.string.existeUsuario)
                  builder.setPositiveButton(android.R.string.ok) { _,_ ->
                      Toast.makeText(applicationContext,
                          android.R.string.ok, Toast.LENGTH_SHORT).show()
                  }
                  etEmail.error = getString(R.string.validateEmailError)
              } else {
                  registerUser()
                  finish()
              }
          }
        }
    }

    private fun validarDatos() : Boolean{
        if(etPass.text != etPass2.text) {
            etPass2.error = getString(R.string.passwordIguales)
            Toast.makeText(this, getString(R.string.passwordIguales) , Toast.LENGTH_LONG).show()
            return false
        }

        if(etNombre.text.isNullOrEmpty()|| etNombre.text.isNullOrBlank()){
            etNombre.error = getString(R.string.validateEmpty)
            return false
        }
        if(etApellido.text.isNullOrEmpty()|| etApellido.text.isNullOrBlank()){
            etApellido.error = getString(R.string.validateEmpty)
            return false
        }
        if(etEmail.text.isNullOrEmpty()|| etEmail.text.isNullOrBlank()){
            etEmail.error = getString(R.string.validateEmpty)
            return false
        }
        if(etPass.text.isNullOrEmpty()|| etPass.text.isNullOrBlank()){
            etPass.error = getString(R.string.validateEmpty)
            return false
        }
        return true
    }

    private fun existeUsuario(email : String) : Boolean{
        var existeUsuario : Boolean = false
        mAPIService = IApiServices.ApiUtils.apiService

        mAPIService!!.getUserByEmail(email).enqueue(object : Callback<Usuario> {

            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful()) {
                    existeUsuario = true
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                existeUsuario = false
            }
        })

        return existeUsuario
    }

    private fun registerUser() {
        mAPIService = IApiServices.ApiUtils.apiService

        mAPIService!!.registerUserPost("SampleTest2@gamil.com", "123456", "nombre", "apellido").enqueue(object :
            Callback<Usuario> {

            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                val builder = AlertDialog.Builder(this@RegisterActivity)
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
                val builder = AlertDialog.Builder(this@RegisterActivity)
                builder.setTitle("Error")
                builder.setMessage("El usuario no se ha podido crear")
                builder.setNegativeButton(android.R.string.ok) { _,_ ->
                    Toast.makeText(applicationContext,
                        android.R.string.ok, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}