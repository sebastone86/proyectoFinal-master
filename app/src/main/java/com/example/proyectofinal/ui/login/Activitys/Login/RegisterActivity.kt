package com.example.proyectofinal.ui.Activitys.Login


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal.R
import androidx.appcompat.widget.Toolbar
import com.example.proyectofinal.ui.login.Utiles.ConexionDB
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

class RegisterActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var btnCancelar: Button
    lateinit var btnAceptar: Button
    lateinit var etNombre: EditText
    lateinit var etApellido: EditText
    lateinit var etEmail: EditText
    lateinit var etPass: EditText



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
        toolbar = findViewById(R.id.toolbarRegister)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnAceptar = findViewById(R.id.btnAceptar)
        btnCancelar.setOnClickListener {
            finish()
        }
        btnAceptar.setOnClickListener {
            agregarUsuario()
            finish()
        }
    }

    private fun agregarUsuario(){
        var comm: PreparedStatement
        var conn : Connection?

        conn = ConexionDB().getInstance()?.getConnetion(applicationContext)
        try {

            if (conn != null) {
                comm = conn.prepareStatement("insert into Usuario("
                        + "nombre, apellido , email , pass , tipo_usuario ) values(?,?,?,?,?)")
                comm.setString(1, etNombre.getText().toString());
                comm.setString(2, etApellido.getText().toString());
                comm.setString(3, etEmail.getText().toString());
                comm.setString(4, etPass.getText().toString());
                comm.setString(5, "USUARIO");
                // run commandto add new rocord
                comm.executeUpdate();
            };

        } catch ( e : SQLException) {
            Toast.makeText(applicationContext , e.message , Toast.LENGTH_LONG).show()
        }
    }
}