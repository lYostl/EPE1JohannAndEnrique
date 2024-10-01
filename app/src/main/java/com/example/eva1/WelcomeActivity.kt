package com.example.eva1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    lateinit var welcomeMessageTextView: TextView
    lateinit var enviarDatosBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)



        welcomeMessageTextView = findViewById(R.id.welcome_message)
        enviarDatosBtn = findViewById(R.id.btn_enviar_datos)



        val username = intent.getStringExtra("USERNAME")
        val apellido = intent.getStringExtra("APELLIDO")
        val comuna = intent.getStringExtra("COMUNA")
        val observacion = intent.getStringExtra("OBSERVACION")

        // Mostrar un mensaje de bienvenida personalizado
        welcomeMessageTextView.text = "Bienvenido, $username!"

        val nombreEditText = findViewById<EditText>(R.id.et_nombre)
        val apellidoEditText = findViewById<EditText>(R.id.et_apellido)
        val comunaEditText = findViewById<EditText>(R.id.et_comuna)
        val observacionEditText = findViewById<EditText>(R.id.et_observacion)

        enviarDatosBtn.setOnClickListener {
            val recipient = "angeloc935115@gmail.com"
            val subject = "Datos del Usuario"
            val message = """
                Nombre: ${nombreEditText.text.toString().trim()}
                Apellido: ${apellidoEditText.text.toString().trim()}
                Comuna: ${comunaEditText.text.toString().trim()}
                Observación: ${observacionEditText.text.toString().trim()}
            """.trimIndent()

            val mIntent = Intent(Intent.ACTION_SEND).apply {
                data = Uri.parse("mailto:")
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, message)
            }

            try {
                startActivity(Intent.createChooser(mIntent, "Send Email"))
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
