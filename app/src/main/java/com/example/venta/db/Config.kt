package com.example.venta.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.venta.model.Venta
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

/* Paso 1: Conexión y Lectura de Datos de la Base de Datos SQLite */

class Config(private val context: Context) {
    private val dbName = "ventas.db"

    /* Extraer la lista de ventas desde SQLite */
    fun obtenerVentas(): List<Venta> {
        val listaVentas = mutableListOf<Venta>()
        val dbFile = File(context.filesDir, dbName)

        Log.d("PRUEBA_SQLite", "INICIANDO obtenerVentas()")
        Log.d("PRUEBA_SQLite", "dbName=$dbName, ruta=${dbFile.absolutePath}")

        /* Copia de seguridad / inicialización de la DB desde assets */
        try {
            val inputStream: InputStream = context.assets.open(dbName)
            val outputStream: OutputStream = FileOutputStream(dbFile)
            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } > 0) {
                outputStream.write(buffer, 0, length)
            }
            outputStream.flush()
            outputStream.close()
            inputStream.close()
            Log.d("PRUEBA_SQLite", "DB copiada desde assets correctamente")
        } catch (e: Exception) {
            Log.e("PRUEBA_SQLite", "Error al copiar DB: ${e.message}")
        }

        /* Apertura de la conexión SQLite y ejecución de la consulta */
        try {
            val db = SQLiteDatabase.openDatabase(
                dbFile.absolutePath, null, SQLiteDatabase.OPEN_READONLY
            )
            val cursor = db.rawQuery(
                "SELECT codigo, nombre, precio, cantidad, tipo, fecha_venta FROM ventas", null
            )
            Log.d("PRUEBA_SQLite", "Datos Encontrados de ventas: ${cursor.count}")

            if (cursor.moveToFirst()) {
                do {
                    val codigo   = cursor.getString(cursor.getColumnIndexOrThrow("codigo"))
                    val nombre   = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                    val precio   = cursor.getDouble(cursor.getColumnIndexOrThrow("precio"))
                    val cantidad = cursor.getInt(cursor.getColumnIndexOrThrow("cantidad"))
                    val tipo     = cursor.getString(cursor.getColumnIndexOrThrow("tipo"))
                    val fecha    = cursor.getString(cursor.getColumnIndexOrThrow("fecha_venta"))

                    listaVentas.add(Venta(codigo, nombre, precio, cantidad, tipo, fecha))
                    Log.d("PRUEBA_SQLite", "Leído: $codigo - $nombre - S/ $precio - $tipo")
                } while (cursor.moveToNext())
            }
            cursor.close()
            db.close()
            Log.d("PRUEBA_SQLite", "!Conexión exitosa, Total de registros: ${listaVentas.size}")
        } catch (e: Exception) {
            Log.e("PRUEBA_SQLite", "Error al conectar la BD: ${e.message}")
        }
        return listaVentas
    }
}