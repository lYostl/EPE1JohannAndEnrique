package com.example.eva1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        val irFormularioBtn = findViewById<Button>(R.id.btn_ir_formulario)
        val registroBtn = findViewById<Button>(R.id.btn_registro)

        irFormularioBtn.setOnClickListener {

            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }

        registroBtn.setOnClickListener {

            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}
