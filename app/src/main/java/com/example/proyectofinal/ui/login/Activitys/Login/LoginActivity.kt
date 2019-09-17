
package com.example.proyectofinal.ui.Activitys.Login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.proyectofinal.R
import com.example.proyectofinal.data.model.Usuario
import com.example.proyectofinal.ui.login.Activitys.Canchas.CanchasAdminActivity
import com.example.proyectofinal.ui.login.Activitys.Canchas.CanchasUserActivity
import com.example.proyectofinal.ui.login.Activitys.Menu.MenuActivity
import com.example.proyectofinal.ui.login.Utiles.IApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    var administradores = arrayOf("Eric@istea.com", "Ale@istea.com", "Seba@istea.com")
    var mAPIService: IApiServices? = null

    lateinit var toolbar: Toolbar
    lateinit var etEmail: EditText
    lateinit var etPass: EditText
    lateinit var btnAceptar: Button
    lateinit var btnRegister: AppCompatTextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        setupUI()
        initializeToolbar()
    }



    private fun setupUI() {
        toolbar = findViewById(R.id.toolbarMain)
        etEmail = findViewById(R.id.etEmail)
        etPass = findViewById(R.id.etPass)
        btnAceptar = findViewById(R.id.btnAceptar)
        btnRegister = findViewById(R.id.btnRegister)
        btnRegister.setTextColor(Color.BLUE)

        btnAceptar.setOnClickListener {

            var intent: Intent
            if (validarCampos()/* && validarUsuarioRegistrado()*/) {
                /* SI ES ADMIN VA A LA ACTIVITY DE ADMIN Y SI NO A LA DE USUARIOS */
                if (validarAdmin()) {
                    intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                } else {
                    intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                }

            }

            intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initializeToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = getString(R.string.app_name)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayUseLogoEnabled(true)

    }

    private fun validarCampos():Boolean {
        val email = etEmail.text
        val password = etPass.text

        if(email.isNullOrEmpty() || email.isNullOrBlank() ) {
            Toast.makeText(this, getString(R.string.validateEmailError) , Toast.LENGTH_LONG).show()
            etEmail.error = getString(R.string.validateEmailError)
            return false
        }
        if(password.isNullOrEmpty() || password.isNullOrBlank() ) {
            Toast.makeText(this, getString(R.string.validatePassError) , Toast.LENGTH_LONG).show()
            etPass.error = getString(R.string.validatePassError)
            return false
        }
        return true
    }

    private fun validarUsuarioRegistrado() : Boolean {
        var existeUsuario : Boolean = false
        var email = etEmail.toString()
        var pass = etPass.toString()
        mAPIService = IApiServices.ApiUtils.apiService

        mAPIService!!.getUserByEmail(email).enqueue(object : Callback<Usuario> {

            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful()) {
                    if(response.body()?.password != pass) {
                        val builder = AlertDialog.Builder(this@LoginActivity)
                        builder.setTitle(R.string.ocurriounProblema)
                        builder.setMessage(R.string.passwordIncorrecto)
                        builder.setPositiveButton(android.R.string.ok) { _,_ ->
                            Toast.makeText(applicationContext,
                                android.R.string.ok, Toast.LENGTH_SHORT).show()
                        }
                        etPass.error = getString(R.string.passwordIncorrecto)
                        existeUsuario = false
                    } else {
                        existeUsuario = true
                    }
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                existeUsuario = false
            }
        })
        return existeUsuario
    }

    private fun validarAdmin(): Boolean {
        /* revisar esta validacion */
       val email =  etEmail.text.toString()
        for (item in administradores) {
            if (item == email) {
                return true
            }
        }
        return false
    }
}


