package com.example.proyectofinal.ui.Activitys.Login


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal.R
import androidx.appcompat.widget.Toolbar
import com.example.proyectofinal.ui.login.Model.IApiServices
import com.example.proyectofinal.ui.login.Utiles.ConexionDB
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

class RegisterActivity : AppCompatActivity() {

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
        instanceRetroFitService()
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
              agregarUsuario()
              finish()
          }
        }
    }

    private fun instanceRetroFitService() : IApiServices? {
        val service : IApiServices
        val retrofit = Retrofit.Builder()
            .baseUrl("") //URL DEL SERVICIOOOO
            .addConverterFactory(GsonConverterFactory.create())
            .build()

            service = retrofit.create(IApiServices::class.java)

        return service
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
    private fun agregarUsuario(){
        var comm: PreparedStatement
        var conn : Connection?

        conn = ConexionDB().getInstance()?.getConnetion(applicationContext)
        try {

            if (conn != null) {
                comm = conn.prepareStatement("insert into Usuario("
                        + "nombre, apellido , email , pass , tipo_usuario ) values(?,?,?,?,?)")
                comm.setString(1, etNombre.getText().toString())
                comm.setString(2, etApellido.getText().toString())
                comm.setString(3, etEmail.getText().toString())
                comm.setString(4, etPass.getText().toString())
                comm.setString(5, "USUARIO")
                // run commandto add new rocord
                comm.executeUpdate()
            }

        } catch ( e : SQLException) {
            Toast.makeText(applicationContext , e.message , Toast.LENGTH_LONG).show()
        }
    }
}