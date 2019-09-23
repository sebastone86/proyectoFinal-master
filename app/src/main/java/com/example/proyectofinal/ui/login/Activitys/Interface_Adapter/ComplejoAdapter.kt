package com.example.proyectofinal.ui.login.Activitys.Interface_Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.data.model.Complejo

class ComplejoAdapter(var complejos: List<Complejo>, var listener: OnComplejosClickListenr)
    : RecyclerView.Adapter<ComplejoAdapter.ComplejosViewHolder>() {


    private var context: Context? = null
    var row_index: Int = -1
    var hold: ComplejosViewHolder? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ComplejosViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_complejo, viewGroup, false)
        context = viewGroup.context
        return ComplejosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return complejos.size
    }

    override fun onBindViewHolder(holder: ComplejosViewHolder, position: Int) {
        hold = holder

        holder.txtNombre.text = complejos[position].nombre
        holder.txtDescripcion.text = complejos[position].dueño.toString()


        //sacar del api cantidad de canchas
        holder.txtCountTareas.text = getTareas(complejos[position].id!!, holder.txtNombre.context).toString()

        holder.itemView.setOnClickListener {
            row_index = -1  /* Seteamos -1 para dar a entender que no es ningun item de la lista*/
            listener.onItemClick(complejos[position])
        }


    }

    class ComplejosViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var txtNombre: TextView = view.findViewById(R.id.txtNombreComplejo)
        var txtDescripcion: TextView = view.findViewById(R.id.txtDescripcionComplejo)
        var txtCountTareas: TextView = view.findViewById(R.id.txtNumeroCanchas)
    }

    private fun getTareas(idList: Int, context: Context): Int {
        //val db = DBHelper(context)
        return 1//db.getCountTareas(idList)
    }
}