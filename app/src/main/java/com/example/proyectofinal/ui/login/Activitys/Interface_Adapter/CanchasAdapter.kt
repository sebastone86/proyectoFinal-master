package com.example.proyectofinal.ui.login.Activitys.Interface_Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.data.model.Cancha

class CanchasAdapter(var canchas: List<Cancha>, var listener: OnCanchasClickListener)
    : RecyclerView.Adapter<CanchasAdapter.CanchasViewHolder>(){


    override fun onBindViewHolder(holder: CanchasViewHolder, position: Int) {

        holder.txtNombre.text = canchas[position].nombre
        holder.txtDescripcion.text = ""// canchas[position].

      //  holder.txtCountTareas.text = getTareas(listas[position].id!!, holder.txtNombre.context).toString()

        holder.itemView.setOnClickListener {
            //row_index=-1  /* Seteamos -1 para dar a entender que no es ningun item de la lista*/
            listener.onItemClick(canchas[position])
        }

    }

    class CanchasViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var txtNombre: TextView = view.findViewById(R.id.txtNombreCancha)
        var txtDescripcion: TextView = view.findViewById(R.id.txtDescripcionCancha)
        //var txtCountTareas: TextView = view.findViewById(R.id.txtNumeroTareas)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CanchasViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_canchas, viewGroup, false)
      //  context = viewGroup.context
        return CanchasViewHolder(viewGroup)
    }

    override fun getItemCount(): Int {
        return canchas.size
    }

 }