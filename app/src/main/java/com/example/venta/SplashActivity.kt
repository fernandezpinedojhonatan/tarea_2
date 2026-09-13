package com.example.venta

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val ivLogo = findViewById<ImageView>(R.id.ivLogo)
        val tvTitulo = findViewById<TextView>(R.id.tvTitulo)

        // 🎬 Animación: fade in (aparecer suavemente)
        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        fadeIn.duration = 1500

        // 🎬 Animación: zoom (crecer)
        val zoom = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        zoom.duration = 2000

        ivLogo.startAnimation(fadeIn)
        tvTitulo.startAnimation(fadeIn)

        // ⏱️ Esperar 5 segundos y pasar a MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 5000) // 5000 ms = 5 segundos
    }
}