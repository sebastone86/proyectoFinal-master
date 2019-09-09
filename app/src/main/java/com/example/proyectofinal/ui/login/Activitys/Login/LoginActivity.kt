
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

import com.example.proyectofinal.R
import com.example.proyectofinal.ui.login.Activitys.Canchas.CanchasAdminActivity
import com.example.proyectofinal.ui.login.Activitys.Canchas.CanchasUserActivity

class LoginActivity : AppCompatActivity() {

    var administradores = arrayOf("Eric@istea.com", "Ale@istea.com", "Seba@istea.com")


    lateinit var toolbar: Toolbar
    lateinit var etNombre: EditText
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

        val email = etEmail.text.toString()
        val password = etPass.text.toString()

        btnAceptar.setOnClickListener {

            var intent:Intent
            if (validarCampos()
                    && validarUsuarioRegistrado()
                ){
                /* SI ES ADMIN VA A LA ACTIVITY DE ADMIN SI NO A LA DE USUARIOS */
                if(validarAdmin()) {
                    intent =  Intent(this, CanchasAdminActivity::class.java)
                    startActivity(intent)
                }else{

                    intent = Intent(this, CanchasUserActivity::class.java)
                    startActivity(intent)
                }

            }
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

    private fun validarUsuarioRegistrado():Boolean {

        return true
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


    /* LECTURA DE LA DB */

    /*
    *   Statement comm;
        try {
        // create command to read data
            comm = conn.createStatement();
            ResultSet rs = comm.executeQuery("Select EmployeeID, Firstname From Employees");
            String msg = "";
        // read all row
            while (rs.next()) {
                msg += "nID: " + rs.getInt("EmployeeID") + " Name: "
                        + rs.getString("Firstname");
            }
            tv.setText(msg);
        } catch (SQLException e) {
            tv.setText(e.toString());
        }
    * */

    /* ESCRITURA EN LA DB*/

    /*
    * PreparedStatement comm;
        try {
            comm = conn.prepareStatement("insert into Employees("
                    + "firstname, lastname) values(?,?)");
            comm.setString(1, etFirst.getText().toString());
            comm.setString(2, etLast.getText().toString());
        // run commandto add new rocord
            comm.executeUpdate();
        } catch (SQLException e) {
            tv.setText(e.toString());
        }
    *
    * */
}


