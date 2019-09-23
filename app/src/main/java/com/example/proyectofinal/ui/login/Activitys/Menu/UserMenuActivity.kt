package com.example.proyectofinal.ui.login.Activitys.Menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.data.model.Cancha
import com.example.proyectofinal.ui.login.Activitys.Interface_Adapter.CanchasAdapter
import com.example.proyectofinal.ui.login.Activitys.Interface_Adapter.OnCanchasClickListener

class UserMenuActivity : AppCompatActivity() {

    lateinit var rvCanchas: RecyclerView
    lateinit var rvReservas: RecyclerView
    lateinit var canchasAdapter: CanchasAdapter
    lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_menu)

        setupUI()
        setupToolbar()
    }

    private fun setupToolbar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setupUI() {
        rvCanchas = findViewById(R.id.rvCanchas)
        rvReservas = findViewById(R.id.rvReservas)
        toolbar = findViewById(R.id.tbMenuUsuario)

        canchasAdapter = createAdapterTareas()
        rvCanchas.adapter = canchasAdapter
    }


    private fun createAdapterTareas(): CanchasAdapter {
        return CanchasAdapter(ArrayList(), object : OnCanchasClickListener {

            override fun onItemClick(cancha: Cancha) {
               // resetViewFunction()

            }

            fun onLongItemClick(canchas: Cancha, view: View) {
                //selecciona tarea
                invalidateOptionsMenu()  // habilita botones de edicion y borrado

            }

        })
    }
}
