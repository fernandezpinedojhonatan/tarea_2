package com.example.venta.iu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.venta.R
import com.example.venta.model.Venta

class VentasAdapter(private var ventas: List<Venta>) :
    RecyclerView.Adapter<VentasAdapter.VentasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VentasViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_venta, parent, false)
        return VentasViewHolder(view)
    }

    override fun onBindViewHolder(holder: VentasViewHolder, position: Int) {
        val venta = ventas[position]
        holder.tvAvatar.text = venta.nombre.firstOrNull()?.uppercase() ?: "V"
        holder.tvNombre.text = venta.nombre
        holder.tvCodigo.text = "Código: ${venta.codigo}"
        holder.tvPrecio.text = "Precio: S/ ${venta.precio}"
        holder.tvCantidad.text = "Cantidad: ${venta.cantidad}"
        holder.tvTipo.text = "Tipo: ${venta.tipo}"
        holder.tvFecha.text = "Fecha: ${venta.fechaVenta}"
    }

    override fun getItemCount(): Int = ventas.size

    fun updateData(newVentas: List<Venta>) {
        this.ventas = newVentas
        notifyDataSetChanged()
    }

    class VentasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAvatar: TextView   = view.findViewById(R.id.tvAvatar)
        val tvNombre: TextView   = view.findViewById(R.id.tvNombre)
        val tvCodigo: TextView   = view.findViewById(R.id.tvCodigo)
        val tvPrecio: TextView   = view.findViewById(R.id.tvPrecio)
        val tvCantidad: TextView = view.findViewById(R.id.tvCantidad)
        val tvTipo: TextView     = view.findViewById(R.id.tvTipo)
        val tvFecha: TextView    = view.findViewById(R.id.tvFecha)
    }
}