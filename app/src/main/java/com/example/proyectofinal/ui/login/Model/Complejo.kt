package com.example.proyectofinal.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Complejo (
    @SerializedName("id")
    var id: Int?,
    @SerializedName("nombre")
    var nombre: String,
    @SerializedName("dueño")
    var dueño: Int
)