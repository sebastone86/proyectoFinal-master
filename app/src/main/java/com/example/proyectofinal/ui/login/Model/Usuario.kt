package com.example.proyectofinal.data.model

import java.io.Serializable

class Usuario (var id: Int?, var nombre: String, var apellido: String?, var email: String, var password: String, var tipoUsuario: Int) : Serializable {}