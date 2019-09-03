package com.example.proyectofinal.ui.login

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatTextView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast

import com.example.proyectofinal.R
import java.lang.reflect.Array

class LoginActivity : AppCompatActivity() {

    var administradores = arrayOf("Eric", "Ale", "Seba")



    private lateinit var loginViewModel: LoginViewModel
    lateinit var etNombre: EditText
    lateinit var etEmail: EditText
    lateinit var etPass: EditText
    lateinit var btnAceptar: Button
    lateinit var btnRegister: AppCompatTextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val loading = findViewById<ProgressBar>(R.id.loading)

        setupUI()


        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            btnAceptar.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                etEmail.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                etPass.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        etEmail.afterTextChanged {
            loginViewModel.loginDataChanged(
                etEmail.text.toString(),
                etPass.text.toString()
            )
        }

        etPass.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    etEmail.text.toString(),
                    etPass.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            etEmail.text.toString(),
                            etPass.text.toString()
                        )
                }
                false
            }

            btnAceptar.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(etEmail.text.toString(), etPass.text.toString())
            }
        }
    }

    private fun setupUI() {
        etNombre = findViewById(R.id.etNombre)
        etEmail = findViewById(R.id.etEmail)
        etPass = findViewById(R.id.etPass)
        btnAceptar = findViewById(R.id.btnAceptar)
        btnRegister = findViewById(R.id.btnRegister)

        val nombre = etNombre.text.toString()
        val email = etEmail.text.toString()
        val password = etPass.text.toString()

        btnAceptar.setOnClickListener {
            if(validarCampos() && validarUsuarioRegistrado()){
                if(validarAdmin(nombre, email, password)){
                    Toast.makeText(this, "Bienvenido " + (etNombre.text.toString()), Toast.LENGTH_LONG).show()
                    val intent = Intent(this, CanchasAdminActivity::class.java)
                    intent.putExtra("Nombre", etNombre.text.toString())
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Bienvenido " + (etNombre.text.toString()), Toast.LENGTH_LONG).show()
                    val intent = Intent(this, CanchasUserActivity::class.java)
                    intent.putExtra("Nombre", etNombre.text.toString())
                    startActivity(intent)
                    finish()
                }

            }
        }

        /*btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }*/
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

    private fun validarCampos():Boolean {
        val nombre = etNombre.text
        val email = etEmail.text
        val password = etPass.text

        if(nombre.isNullOrEmpty() || nombre.isNullOrBlank() ) {
            Toast.makeText(this, getString(R.string.validateNameError) , Toast.LENGTH_LONG).show()
            etNombre.error = getString(R.string.validateNameError)
            return false
        }
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

    private fun validarUsuarioRegistrado():Boolean {
        /*val db = DBHelper(this@MainActivity)
        val nombre = etNombre.text.toString()
        val pass = etPass.text.toString()
        var usuarios: List<Usuario>
        usuarios = db.getUsuarios()

        usuarios = usuarios.filter { user -> user.username == nombre }

        if (usuarios.size == 0) {
            Toast.makeText(this, getString(R.string.usuarioSinRegistrar) , Toast.LENGTH_LONG).show()
            etNombre.error = getString(R.string.usuarioSinRegistrar)
            return false
        } else {
            if(usuarios[0].password != pass) {
                Toast.makeText(this, getString(R.string.passwordIncorrecto) , Toast.LENGTH_LONG).show()
                etPass.error = getString(R.string.passwordIncorrecto)
                return false
            }
        }*/
        return true
    }

    private fun validarAdmin(nombre: String, email: String, password: String): Boolean {
        for (item in administradores) {
            if (item == nombre) {
                return true
            }
        }
        return false
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
