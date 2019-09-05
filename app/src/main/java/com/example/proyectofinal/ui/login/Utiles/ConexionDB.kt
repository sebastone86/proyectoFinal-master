package com.example.proyectofinal.ui.login.Utiles

import android.content.Context
import java.sql.Connection
import java.sql.DriverManager
import android.os.StrictMode
import android.widget.Toast
import java.security.AccessControlContext


class ConexionDB {
     private var instance: ConexionDB? = null


     fun initInstance() {

         if (instance == null) {
             // Create the instance
             instance = ConexionDB()
         }
     }

     fun getConnetion( context : Context) :Connection? {
         var conn: Connection? = null

         try {
             val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
             StrictMode.setThreadPolicy(policy)
             Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
             conn = DriverManager.getConnection(
                 ""
                         + "jdbc:jtds:sqlserver://localhost/proyecto_final;instance=SQL2018;"
                         + "user=sa;"
             );
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


