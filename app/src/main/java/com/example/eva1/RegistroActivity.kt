package com.example.eva1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper
    private lateinit var etUsuario: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnEliminar: Button
    private lateinit var btnEditar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registros)


        dbHelper = DBHelper(this)


        etUsuario = findViewById(R.id.et_usuario)
        etContrasena = findViewById(R.id.et_contrasena)
        btnGuardar = findViewById(R.id.btn_guardar)
        btnEliminar = findViewById(R.id.btn_eliminar)
        btnEditar = findViewById(R.id.btn_editar)

        btnGuardar.setOnClickListener {
            val nombre = etUsuario.text.toString()
            val contrasena = etContrasena.text.toString()

            if (nombre.isNotEmpty() && contrasena.isNotEmpty()) {
                val result = dbHelper.addUser(nombre, contrasena)
                if (result == -1L) {
                    Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Usuario añadido correctamente", Toast.LENGTH_LONG).show()
                    etUsuario.text.clear()
                    etContrasena.text.clear()
                }
            } else {
                Toast.makeText(this, "Por favor, ingresa los datos completos", Toast.LENGTH_LONG).show()
            }
        }



        btnEditar.setOnClickListener {
            val nombre = etUsuario.text.toString()
            val nuevaContrasena = etContrasena.text.toString()

            if (nombre.isNotEmpty() && nuevaContrasena.isNotEmpty()) {
                val result = dbHelper.updateUser(nombre, nuevaContrasena)
                if (result > 0) {
                    Toast.makeText(this, "Usuario editado correctamente", Toast.LENGTH_LONG).show()
                    etUsuario.text.clear()
                    etContrasena.text.clear()
                } else {
                    Toast.makeText(this, "Error al editar el usuario", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Por favor, ingresa los datos completos", Toast.LENGTH_LONG).show()
            }
        }


        btnEliminar.setOnClickListener {
            val nombre = etUsuario.text.toString()

            if (nombre.isNotEmpty()) {
                val result = dbHelper.deleteUser(nombre)
                if (result > 0) {
                    Toast.makeText(this, "Usuario eliminado correctamente", Toast.LENGTH_LONG).show()
                    etUsuario.text.clear()
                    etContrasena.text.clear()
                } else {
                    Toast.makeText(this, "No se puede eliminar el usuario 'admin'", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Por favor, ingresa el nombre del usuario a eliminar", Toast.LENGTH_LONG).show()
            }
        }
    }
}

