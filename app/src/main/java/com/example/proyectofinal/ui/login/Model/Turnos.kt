package com.example.proyectofinal.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Turnos (
    @SerializedName("id")
    var id: Int?,
    @SerializedName("canchaId")
    var canchaId: Int,
    @SerializedName("fecha")
    var fecha: Date,
    @SerializedName("email")
    var email: String
)