package com.example.venta

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.venta.db.Config
import com.example.venta.iu.VentasAdapter

class MainActivity : AppCompatActivity() {

    //1. Declarar variables para el RecyclerView y el Adapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VentasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //2. Vinculamos el RecyclerView del XML y asignamos un layoutManager
        recyclerView = findViewById(R.id.recyclerViewVentas)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //3. Creamos el adapter inicialmente vacío
        adapter = VentasAdapter(emptyList())
        recyclerView.adapter = adapter

        // Invocamos al método
        cargarVentasDesdeDb()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun cargarVentasDesdeDb() {
        Thread {
            try {
                val config = Config(this)
                val lista = config.obtenerVentas()
                Log.d("PRUEBA_SQLite", "Carga Completa recibida MainActivity. Total: ${lista.size}")

                runOnUiThread {
                    adapter.updateData(lista)
                }

            } catch (e: Exception) {
                Log.e("PRUEBA_SQLite", "Error desde MainActivity: ${e.message}")
            }
        }.start()
    }
}