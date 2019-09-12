package com.example.proyectofinal.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Usuario (
    @SerializedName("id")
    var id: Int?,
    @SerializedName("nombre")
    var nombre: String,
    @SerializedName("id")
    var apellido: String?,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("tipoUsuario")
    var tipoUsuario: TipoUsuario
)