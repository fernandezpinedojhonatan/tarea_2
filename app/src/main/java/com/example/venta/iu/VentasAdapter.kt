package com.example.venta.iu

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.venta.R
import com.example.venta.model.Venta
import com.google.android.material.card.MaterialCardView

class VentasAdapter(private var ventas: List<Venta>) :
    RecyclerView.Adapter<VentasAdapter.VentasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VentasViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_venta, parent, false)
        return VentasViewHolder(view)
    }

    override fun onBindViewHolder(holder: VentasViewHolder, position: Int) {
        val venta = ventas[position]
        val contexto = holder.itemView.context

        holder.tvAvatar.text = venta.nombre.firstOrNull()?.uppercase() ?: "V"
        holder.tvNombre.text = venta.nombre
        holder.tvCodigo.text = "Código: ${venta.codigo}"
        holder.tvPrecio.text = "Precio: S/ ${venta.precio}"
        holder.tvCantidad.text = "Cantidad: ${venta.cantidad}"
        holder.tvTipo.text = "Tipo: ${venta.tipo}"
        holder.tvFecha.text = "Fecha: ${venta.fechaVenta}"

        // 🎨 BORDE Y TEXTO DEL TIPO SEGÚN EL COMPROBANTE
        when (venta.tipo.lowercase()) {
            "boleta" -> {
                holder.cardVenta.strokeColor = ContextCompat.getColor(contexto, R.color.rojo_boleta)
                holder.tvTipo.setTextColor(ContextCompat.getColor(contexto, R.color.rojo_boleta))
                holder.tvTipo.setTypeface(null, Typeface.BOLD)
            }
            "factura" -> {
                holder.cardVenta.strokeColor = ContextCompat.getColor(contexto, R.color.verde_factura)
                holder.tvTipo.setTextColor(ContextCompat.getColor(contexto, R.color.verde_factura))
                holder.tvTipo.setTypeface(null, Typeface.BOLD)
            }
        }
    }

    override fun getItemCount(): Int = ventas.size

    fun updateData(newVentas: List<Venta>) {
        this.ventas = newVentas
        notifyDataSetChanged()
    }

    class VentasViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardVenta: MaterialCardView = view.findViewById(R.id.cardVenta)
        val tvAvatar: TextView   = view.findViewById(R.id.tvAvatar)
        val tvNombre: TextView   = view.findViewById(R.id.tvNombre)
        val tvCodigo: TextView   = view.findViewById(R.id.tvCodigo)
        val tvPrecio: TextView   = view.findViewById(R.id.tvPrecio)
        val tvCantidad: TextView = view.findViewById(R.id.tvCantidad)
        val tvTipo: TextView     = view.findViewById(R.id.tvTipo)
        val tvFecha: TextView    = view.findViewById(R.id.tvFecha)
    }
}