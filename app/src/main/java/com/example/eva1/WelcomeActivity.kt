package com.example.eva1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Obtener la referencia del TextView desde el layout
        val welcomeMessageTextView: TextView = findViewById(R.id.welcome_message)

        // Obtener el nombre de usuario desde el Intent
        val username = intent.getStringExtra("USERNAME")

        // Mostrar un mensaje de bienvenida personalizado
        welcomeMessageTextView.text = "Bienvenido, $username!"

        // Funcionalidad del botón "Volver"
        val backButton: Button = findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            finish()  // Finaliza la actividad actual y regresa a la anterior
        }
    }
}
