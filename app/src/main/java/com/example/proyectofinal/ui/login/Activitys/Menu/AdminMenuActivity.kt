package com.example.proyectofinal.ui.login.Activitys.Menu

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.data.model.Complejo
import com.example.proyectofinal.ui.login.Activitys.Interface_Adapter.ComplejoAdapter
import com.example.proyectofinal.ui.login.Activitys.Interface_Adapter.OnComplejosClickListenr


class AdminMenuActivity : AppCompatActivity() {

    lateinit var rvComplejos: RecyclerView
    lateinit var complejosAdapter: ComplejoAdapter
    lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu)


        setupUI()
    }

    private fun setupUI() {
        rvComplejos = findViewById(R.id.rvComplejos)
        toolbar = findViewById(R.id.tbMenuAdmin)

        complejosAdapter = createComplejosAdapter()
        rvComplejos.adapter = complejosAdapter
    }


    private fun createComplejosAdapter(): ComplejoAdapter {
        return ComplejoAdapter(ArrayList(), object : OnComplejosClickListenr {

            override fun onItemClick(complejo: Complejo) {
                // resetViewFunction()

            }
        })
    }

}
