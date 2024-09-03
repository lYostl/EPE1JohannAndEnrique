package com.example.eva1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var usernameInput : EditText
    lateinit var passwordInput : EditText
    lateinit var loginBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginBtn = findViewById(R.id.Login_btn)

        loginBtn.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa tanto el nombre de usuario como la contraseña", Toast.LENGTH_LONG).show()
            } else {
                Log.i("Prueba", "Usuario:  $username y Clave es $password")
                Toast.makeText(this, "Hola, $username", Toast.LENGTH_LONG).show()

                // Crear un Intent para iniciar la WelcomeActivity
                val intent = Intent(this, WelcomeActivity::class.java)

                // Pasar el nombre de usuario a la WelcomeActivity
                intent.putExtra("USERNAME", username)

                // Iniciar la nueva actividad
                startActivity(intent)
            }
        }
    }
}
