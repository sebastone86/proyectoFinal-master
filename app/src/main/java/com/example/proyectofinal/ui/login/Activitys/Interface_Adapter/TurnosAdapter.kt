package com.example.proyectofinal.ui.login.Activitys.Interface_Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.data.model.Turnos

class TurnosAdapter(var turnos: List<Turnos>, var listener: OnTurnosClickListener)
    : RecyclerView.Adapter<TurnosAdapter.TurnosViewHolder>(){


    override fun onBindViewHolder(holder: TurnosViewHolder, position: Int) {

        holder.txtNombre.text = turnos[position].canchaId.toString()
        holder.txtFechaReserva.text = turnos[position].fecha.toString()

    }

    class TurnosViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var txtNombre: TextView = view.findViewById(R.id.txtNombreCanchaReserva)
        var txtFechaReserva: TextView = view.findViewById(R.id.txtFechaReserva)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TurnosViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_canchas, viewGroup, false)
        //  context = viewGroup.context
        return TurnosViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return turnos.size
    }

}