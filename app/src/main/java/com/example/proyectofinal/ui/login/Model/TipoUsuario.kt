package com.example.proyectofinal.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TipoUsuario (
    @SerializedName("id")
    var id: Int?,
    @SerializedName("tipoUsuario")
    var tipoUsuario: String
)