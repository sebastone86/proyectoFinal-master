package com.example.proyectofinal.ui.login.Utiles

import android.annotation.SuppressLint
import android.content.Context
import java.sql.Connection
import java.sql.DriverManager
import android.os.StrictMode
import android.widget.Toast


class ConexionDB {
     private var instance: ConexionDB? = null


     fun initInstance() {

         if (instance == null) {
             // Create the instance
             instance = ConexionDB()
         }
     }
    @SuppressLint("NewApi")
     fun getConnetion( context : Context) :Connection? {
         var conn: Connection? = null

         try {
             val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
             StrictMode.setThreadPolicy(policy)
             Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
             conn = DriverManager.getConnection("jdbc:jtds:sqlserver://10.0.47.175:1433//proyecto_final;encrypt=true;instance=SQLEXPRESS;"
                        +"user=LAPTOP-37NOI2VQ\\ERGO FU5;")

         }catch ( e:Exception ){

             Toast.makeText( context , e.message, Toast.LENGTH_LONG).show()
         }
         return conn

     }

     fun getInstance(): ConexionDB? {
         // Return the instance

         initInstance()
         return instance
     }

 }


