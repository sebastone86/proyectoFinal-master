package com.example.proyectofinal.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectofinal.R
import androidx.appcompat.widget.Toolbar

class RegisterActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var btnCancelar: Button

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

        btnCancelar.setOnClickListener {
            finish()
        }
    }

}
